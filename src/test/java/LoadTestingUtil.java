import io.restassured.RestAssured;

/**
 * Simple Load Testing Utility Class
 *
 * This utility provides methods for basic load testing including:
 * - Sequential request testing
 * - Concurrent load simulation
 * - Performance metrics collection
 * - Response time tracking
 */
public class LoadTestingUtil {

    /**
     * Configuration class for load test parameters
     */
    public static class LoadTestConfig {
        public int numThreads;
        public int requestsPerThread;
        public String endpoint;
        public int expectedStatusCode;
        public long maxResponseTimeMs;

        public LoadTestConfig(int numThreads, int requestsPerThread, String endpoint,
                             int expectedStatusCode, long maxResponseTimeMs) {
            this.numThreads = numThreads;
            this.requestsPerThread = requestsPerThread;
            this.endpoint = endpoint;
            this.expectedStatusCode = expectedStatusCode;
            this.maxResponseTimeMs = maxResponseTimeMs;
        }
    }

    /**
     * Result class to store test metrics
     * GIVEN: A load test has been executed
     * WHEN: Results are collected
     * THEN: Metrics include success count, failure count, and response times
     */
    public static class LoadTestResult {
        public int totalRequests;
        public int successCount;
        public int failureCount;
        public long totalTimeMs;
        public double avgResponseTimeMs;
        public long minResponseTimeMs;
        public long maxResponseTimeMs;
        public double throughputPerSec;
        public double successRate;

        @Override
        public String toString() {
            return String.format(
                    "\n========== LOAD TEST RESULTS ==========\n" +
                    "Total Requests: %d\n" +
                    "Success: %d\n" +
                    "Failed: %d\n" +
                    "Success Rate: %.2f%%\n" +
                    "Total Time: %d ms\n" +
                    "Average Response Time: %.2f ms\n" +
                    "Min Response Time: %d ms\n" +
                    "Max Response Time: %d ms\n" +
                    "Throughput: %.2f requests/sec\n" +
                    "=========================================\n",
                    totalRequests, successCount, failureCount, successRate,
                    totalTimeMs, avgResponseTimeMs, minResponseTimeMs,
                    maxResponseTimeMs, throughputPerSec
            );
        }
    }

    /**
     * Execute sequential load test
     * GIVEN: A configured load test
     * WHEN: Sequential requests are made to an endpoint
     * THEN: Metrics are collected and returned
     *
     * @param config Load test configuration
     * @return LoadTestResult with performance metrics
     */
    public static LoadTestResult executeSequentialLoadTest(LoadTestConfig config) {
        LoadTestResult result = new LoadTestResult();
        long[] responseTimes = new long[config.requestsPerThread];

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < config.requestsPerThread; i++) {
            long reqStart = System.currentTimeMillis();
            try {
                RestAssured.given()
                        .when()
                        .get(config.endpoint)
                        .then()
                        .statusCode(config.expectedStatusCode);

                responseTimes[i] = System.currentTimeMillis() - reqStart;
                result.successCount++;
            } catch (Exception e) {
                responseTimes[i] = -1;
                result.failureCount++;
            }
        }

        long endTime = System.currentTimeMillis();
        populateResults(result, responseTimes, endTime - startTime, config.requestsPerThread);

        return result;
    }

    /**
     * Execute concurrent load test with multiple threads
     * GIVEN: A configured load test with multiple threads
     * WHEN: Concurrent requests are made from multiple threads
     * THEN: Total throughput and response metrics are calculated
     *
     * @param config Load test configuration
     * @return LoadTestResult with performance metrics
     */
    public static LoadTestResult executeConcurrentLoadTest(LoadTestConfig config) throws InterruptedException {
        LoadTestResult result = new LoadTestResult();
        int totalRequests = config.numThreads * config.requestsPerThread;
        long[] allResponseTimes = new long[totalRequests];
        int[] counters = {0, 0, 0}; // successCount, failureCount, index

        Thread[] threads = new Thread[config.numThreads];
        long startTime = System.currentTimeMillis();

        for (int t = 0; t < config.numThreads; t++) {
            final int threadNum = t;
            threads[t] = new Thread(() -> {
                for (int i = 0; i < config.requestsPerThread; i++) {
                    long reqStart = System.currentTimeMillis();
                    try {
                        RestAssured.given()
                                .when()
                                .get(config.endpoint)
                                .then()
                                .statusCode(config.expectedStatusCode);

                        long responseTime = System.currentTimeMillis() - reqStart;
                        synchronized (counters) {
                            allResponseTimes[counters[2]] = responseTime;
                            counters[2]++;
                            counters[0]++;
                        }
                    } catch (Exception e) {
                        synchronized (counters) {
                            allResponseTimes[counters[2]] = -1;
                            counters[2]++;
                            counters[1]++;
                        }
                    }
                }
            });
            threads[t].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long endTime = System.currentTimeMillis();
        result.successCount = counters[0];
        result.failureCount = counters[1];

        populateResults(result, allResponseTimes, endTime - startTime, totalRequests);

        return result;
    }

    /**
     * Helper method to populate result metrics
     *
     * @param result The result object to populate
     * @param responseTimes Array of response times
     * @param totalTimeMs Total test duration
     * @param totalRequests Total number of requests made
     */
    private static void populateResults(LoadTestResult result, long[] responseTimes,
                                       long totalTimeMs, int totalRequests) {
        result.totalRequests = totalRequests;
        result.totalTimeMs = totalTimeMs;

        long sum = 0;
        long validCount = 0;
        result.minResponseTimeMs = Long.MAX_VALUE;
        result.maxResponseTimeMs = 0;

        for (long time : responseTimes) {
            if (time >= 0) {
                sum += time;
                validCount++;
                result.minResponseTimeMs = Math.min(result.minResponseTimeMs, time);
                result.maxResponseTimeMs = Math.max(result.maxResponseTimeMs, time);
            }
        }

        result.avgResponseTimeMs = validCount > 0 ? (double) sum / validCount : 0;
        result.throughputPerSec = totalTimeMs > 0 ? (totalRequests * 1000.0) / totalTimeMs : 0;
        result.successRate = (result.successCount * 100.0) / totalRequests;
    }

    /**
     * Validate if results meet performance criteria
     * GIVEN: Load test results
     * WHEN: Results are validated against criteria
     * THEN: Returns true if all criteria are met
     *
     * @param result The result to validate
     * @param maxAvgResponseTimeMs Maximum acceptable average response time
     * @param minSuccessRate Minimum acceptable success rate (0-100)
     * @return true if results meet criteria, false otherwise
     */
    public static boolean validateResults(LoadTestResult result,
                                         long maxAvgResponseTimeMs,
                                         double minSuccessRate) {
        boolean avgResponseTimeOk = result.avgResponseTimeMs <= maxAvgResponseTimeMs;
        boolean successRateOk = result.successRate >= minSuccessRate;

        System.out.println("\nValidation Results:");
        System.out.println("Average Response Time " + (avgResponseTimeOk ? "✓ PASS" : "✗ FAIL") +
                          " (Actual: " + result.avgResponseTimeMs + "ms, Max: " + maxAvgResponseTimeMs + "ms)");
        System.out.println("Success Rate " + (successRateOk ? "✓ PASS" : "✗ FAIL") +
                          " (Actual: " + result.successRate + "%, Min: " + minSuccessRate + "%)");

        return avgResponseTimeOk && successRateOk;
    }
}

