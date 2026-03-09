import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Practical Load Testing Test Cases
 *
 * This class contains practical load testing scenarios for API endpoints.
 * It demonstrates how to use the LoadTestingUtil to test API performance.
 */
public class LoadTestPractical {

    @BeforeClass
    public static void setup() {
        // Replace with your actual API base URL
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    /**
     * Test Case 1: Basic Sequential Load Test
     * GIVEN: A public API endpoint (JSONPlaceholder)
     * WHEN: 50 sequential GET requests are made to "/posts"
     * THEN: All requests complete and metrics are collected
     */
    @Test
    public void testBasicSequentialLoad() {
        LoadTestingUtil.LoadTestConfig config = new LoadTestingUtil.LoadTestConfig(
                1,              // 1 thread (sequential)
                50,             // 50 requests per thread
                "/posts",       // endpoint
                200,            // expected status code
                1000            // max response time in ms
        );

        LoadTestingUtil.LoadTestResult result = LoadTestingUtil.executeSequentialLoadTest(config);
        System.out.println(result);

        // Validate results
        LoadTestingUtil.validateResults(result, 1000, 95);
    }

    /**
     * Test Case 2: Concurrent Load Test - 10 Users
     * GIVEN: An API endpoint
     * WHEN: 10 concurrent threads make 20 requests each (200 total requests)
     * THEN: Throughput and response metrics are measured
     */
    @Test
    public void testConcurrentLoad10Users() throws InterruptedException {
        LoadTestingUtil.LoadTestConfig config = new LoadTestingUtil.LoadTestConfig(
                10,             // 10 concurrent threads
                20,             // 20 requests per thread
                "/posts",       // endpoint
                200,            // expected status code
                2000            // max response time in ms
        );

        LoadTestingUtil.LoadTestResult result = LoadTestingUtil.executeConcurrentLoadTest(config);
        System.out.println(result);

        // Validate results
        LoadTestingUtil.validateResults(result, 2000, 90);
    }

    /**
     * Test Case 3: Heavy Load Test - 50 Users
     * GIVEN: An API endpoint
     * WHEN: 50 concurrent threads make 10 requests each (500 total requests)
     * THEN: System performance under heavy load is measured
     */
    @Test
    public void testHeavyLoad50Users() throws InterruptedException {
        LoadTestingUtil.LoadTestConfig config = new LoadTestingUtil.LoadTestConfig(
                50,             // 50 concurrent threads
                10,             // 10 requests per thread
                "/posts/1",     // endpoint (single resource for faster response)
                200,            // expected status code
                3000            // max response time in ms
        );

        LoadTestingUtil.LoadTestResult result = LoadTestingUtil.executeConcurrentLoadTest(config);
        System.out.println(result);

        // Validate results with relaxed criteria for heavy load
        LoadTestingUtil.validateResults(result, 3000, 85);
    }

    /**
     * Test Case 4: Endurance Test - Long Running
     * GIVEN: An API endpoint
     * WHEN: Continuous requests are made for extended period (testing endurance)
     * THEN: System stability under sustained load is assessed
     */
    @Test
    public void testEnduranceLoad() throws InterruptedException {
        LoadTestingUtil.LoadTestConfig config = new LoadTestingUtil.LoadTestConfig(
                5,              // 5 concurrent threads
                100,            // 100 requests per thread (500 total)
                "/posts",       // endpoint
                200,            // expected status code
                2000            // max response time in ms
        );

        LoadTestingUtil.LoadTestResult result = LoadTestingUtil.executeConcurrentLoadTest(config);
        System.out.println(result);

        LoadTestingUtil.validateResults(result, 2000, 90);
    }

    /**
     * Test Case 5: POST Request Load Test
     * GIVEN: An API endpoint that accepts POST requests
     * WHEN: Multiple concurrent POST requests are made with payload data
     * THEN: API performance for write operations is measured
     */
    @Test
    public void testPOSTRequestLoadTest() throws InterruptedException {
        System.out.println("\n========== POST REQUEST LOAD TEST ==========");

        int numThreads = 10;
        int requestsPerThread = 20;
        long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[numThreads];
        int[] successCount = {0};
        int[] failureCount = {0};

        for (int t = 0; t < numThreads; t++) {
            threads[t] = new Thread(() -> {
                for (int i = 0; i < requestsPerThread; i++) {
                    try {
                        RestAssured.given()
                                .contentType("application/json")
                                .body("{\"title\": \"Test Post\", \"body\": \"Load Testing\", \"userId\": 1}")
                                .when()
                                .post("/posts")
                                .then()
                                .statusCode(201); // Created status

                        synchronized (successCount) {
                            successCount[0]++;
                        }
                    } catch (Exception e) {
                        synchronized (failureCount) {
                            failureCount[0]++;
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
        long totalTime = endTime - startTime;
        int totalRequests = numThreads * requestsPerThread;

        System.out.println("Total Requests: " + totalRequests);
        System.out.println("Success: " + successCount[0]);
        System.out.println("Failed: " + failureCount[0]);
        System.out.println("Total Time: " + totalTime + " ms");
        System.out.println("Throughput: " + (totalRequests / (totalTime / 1000.0)) + " requests/sec");
        System.out.println("==========================================\n");
    }

    /**
     * Test Case 6: Stress Test with Increasing Load
     * GIVEN: An API endpoint
     * WHEN: Load is gradually increased to find breaking point
     * THEN: System behavior under stress is observed
     */
    @Test
    public void testStressTestIncreasingLoad() throws InterruptedException {
        System.out.println("\n========== STRESS TEST - INCREASING LOAD ==========");

        int[] loadLevels = {5, 10, 20, 30, 40};

        for (int numThreads : loadLevels) {
            System.out.println("\n--- Testing with " + numThreads + " concurrent users ---");

            LoadTestingUtil.LoadTestConfig config = new LoadTestingUtil.LoadTestConfig(
                    numThreads,     // Increasing thread count
                    10,             // 10 requests per thread
                    "/posts/1",     // endpoint
                    200,            // expected status code
                    3000            // max response time
            );

            LoadTestingUtil.LoadTestResult result = LoadTestingUtil.executeConcurrentLoadTest(config);
            System.out.println("Throughput: " + String.format("%.2f", result.throughputPerSec) + " req/sec");
            System.out.println("Avg Response Time: " + String.format("%.2f", result.avgResponseTimeMs) + " ms");
            System.out.println("Success Rate: " + String.format("%.2f", result.successRate) + "%");
        }

        System.out.println("\n===================================================\n");
    }

    /**
     * Test Case 7: Soak Test - Long Duration with Moderate Load
     * GIVEN: An API endpoint
     * WHEN: Moderate load is applied continuously for extended period
     * THEN: Memory leaks and resource exhaustion can be detected
     */
    @Test
    public void testSoakTest() throws InterruptedException {
        System.out.println("\n========== SOAK TEST (Extended Duration) ==========");

        LoadTestingUtil.LoadTestConfig config = new LoadTestingUtil.LoadTestConfig(
                3,              // 3 concurrent threads (moderate load)
                100,            // 100 requests per thread
                "/posts",       // endpoint
                200,            // expected status code
                2000            // max response time
        );

        LoadTestingUtil.LoadTestResult result = LoadTestingUtil.executeConcurrentLoadTest(config);
        System.out.println(result);

        System.out.println("===================================================\n");
    }
}

