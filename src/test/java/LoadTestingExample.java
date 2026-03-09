import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Load Testing Example using RestAssured
 *
 * This class demonstrates basic load testing by making multiple concurrent requests
 * to an API endpoint and measuring response times and success rates.
 *
 * GIVEN: An API endpoint is available
 * WHEN: Multiple concurrent requests are made to the endpoint
 * THEN: The system should handle the load and return valid responses
 */
public class LoadTestingExample {

    private static final String BASE_URL = "https://api.example.com"; // Replace with actual URL
    private static final int NUM_THREADS = 10; // Number of concurrent requests
    private static final int REQUESTS_PER_THREAD = 100; // Requests per thread

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    /**
     * Test: Sequential Load Test
     * Makes multiple sequential requests to measure average response time
     *
     * GIVEN: An API endpoint for testing
     * WHEN: 100 sequential GET requests are made to "/users" endpoint
     * THEN: All requests should complete with status code 200
     */
    @Test
    public void testSequentialLoadTest() {
        long startTime = System.currentTimeMillis();
        int successCount = 0;
        int failureCount = 0;

        for (int i = 0; i < REQUESTS_PER_THREAD; i++) {
            try {
                Response response = RestAssured
                        .given()
                        .when()
                        .get("/users")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
                successCount++;
                System.out.println("Request " + (i + 1) + " - Status: " + response.getStatusCode());
            } catch (Exception e) {
                failureCount++;
                System.out.println("Request " + (i + 1) + " - Failed: " + e.getMessage());
            }
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        double avgResponseTime = (double) totalTime / REQUESTS_PER_THREAD;

        System.out.println("\n=== Sequential Load Test Results ===");
        System.out.println("Total Requests: " + REQUESTS_PER_THREAD);
        System.out.println("Success: " + successCount);
        System.out.println("Failed: " + failureCount);
        System.out.println("Total Time: " + totalTime + " ms");
        System.out.println("Average Response Time: " + avgResponseTime + " ms");
    }

    /**
     * Test: Concurrent Load Test using Threads
     * Makes concurrent requests to simulate multiple users
     *
     * GIVEN: An API endpoint for load testing
     * WHEN: Multiple threads make concurrent requests to the endpoint
     * THEN: All threads should complete and system should handle concurrent load
     */
    @Test
    public void testConcurrentLoadTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        long startTime = System.currentTimeMillis();

        for (int t = 0; t < NUM_THREADS; t++) {
            final int threadNumber = t;
            threads[t] = new Thread(() -> {
                int localSuccess = 0;
                int localFailure = 0;

                for (int i = 0; i < REQUESTS_PER_THREAD; i++) {
                    try {
                        Response response = RestAssured
                                .given()
                                .when()
                                .get("/users")
                                .then()
                                .statusCode(200)
                                .extract()
                                .response();
                        localSuccess++;
                    } catch (Exception e) {
                        localFailure++;
                    }
                }

                System.out.println("Thread-" + threadNumber + ": Success=" + localSuccess + ", Failed=" + localFailure);
            });
            threads[t].start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            thread.join();
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        int totalRequests = NUM_THREADS * REQUESTS_PER_THREAD;

        System.out.println("\n=== Concurrent Load Test Results ===");
        System.out.println("Total Threads: " + NUM_THREADS);
        System.out.println("Total Requests: " + totalRequests);
        System.out.println("Total Time: " + totalTime + " ms");
        System.out.println("Throughput: " + (totalRequests / (totalTime / 1000.0)) + " requests/second");
    }

    /**
     * Test: Load Test with Response Time Assertion
     * Verifies that response times are within acceptable limits
     *
     * GIVEN: An API endpoint with expected response time SLA of 500ms
     * WHEN: Requests are made to the endpoint
     * THEN: Average response time should be less than 500ms
     */
    @Test
    public void testResponseTimeAssertion() {
        long[] responseTimes = new long[REQUESTS_PER_THREAD];

        for (int i = 0; i < REQUESTS_PER_THREAD; i++) {
            long startTime = System.currentTimeMillis();
            try {
                RestAssured
                        .given()
                        .when()
                        .get("/users")
                        .then()
                        .statusCode(200);
                long endTime = System.currentTimeMillis();
                responseTimes[i] = endTime - startTime;
            } catch (Exception e) {
                responseTimes[i] = -1; // Mark as failed
            }
        }

        // Calculate statistics
        long totalTime = 0;
        int successCount = 0;
        long maxTime = 0;
        long minTime = Long.MAX_VALUE;

        for (long time : responseTimes) {
            if (time >= 0) {
                totalTime += time;
                successCount++;
                maxTime = Math.max(maxTime, time);
                minTime = Math.min(minTime, time);
            }
        }

        double avgTime = (double) totalTime / successCount;

        System.out.println("\n=== Response Time Analysis ===");
        System.out.println("Average Response Time: " + avgTime + " ms");
        System.out.println("Min Response Time: " + minTime + " ms");
        System.out.println("Max Response Time: " + maxTime + " ms");
        System.out.println("Success Rate: " + ((successCount * 100) / REQUESTS_PER_THREAD) + "%");

        // Assert average response time is within acceptable limits (500ms)
        assert avgTime < 500 : "Average response time exceeds 500ms threshold";
    }

    /**
     * Test: Spike Load Test
     * Simulates sudden increase in traffic
     *
     * GIVEN: An API endpoint
     * WHEN: Sudden spike from 5 to 50 concurrent requests occurs
     * THEN: System should handle the spike gracefully
     */
    @Test
    public void testSpikeLoadTest() throws InterruptedException {
        System.out.println("=== Spike Load Test ===");

        // Normal load
        System.out.println("Phase 1: Normal Load (5 concurrent requests)");
        simulateConcurrentRequests(5, 50);

        Thread.sleep(2000); // 2 second rest

        // Spike load
        System.out.println("Phase 2: Spike Load (50 concurrent requests)");
        simulateConcurrentRequests(50, 50);

        Thread.sleep(2000); // 2 second rest

        // Return to normal
        System.out.println("Phase 3: Return to Normal (5 concurrent requests)");
        simulateConcurrentRequests(5, 50);
    }

    /**
     * Helper method to simulate concurrent requests
     * @param numThreads Number of concurrent threads
     * @param requestsPerThread Requests per thread
     */
    private void simulateConcurrentRequests(int numThreads, int requestsPerThread) throws InterruptedException {
        Thread[] threads = new Thread[numThreads];
        long startTime = System.currentTimeMillis();

        for (int t = 0; t < numThreads; t++) {
            threads[t] = new Thread(() -> {
                for (int i = 0; i < requestsPerThread; i++) {
                    try {
                        RestAssured.given().when().get("/users").then().statusCode(200);
                    } catch (Exception e) {
                        // Ignore
                    }
                }
            });
            threads[t].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Completed in: " + (endTime - startTime) + " ms");
    }
}

