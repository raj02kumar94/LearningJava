import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ApiTesting {

    /*
    given(): Prepares the request by setting headers, parameters, body, and other configurations.
    when(): Specifies the HTTP method (GET, POST, PUT, DELETE) and the endpoint to call.
    then(): Asserts and validates the response (status code, body content, headers, etc.).
    */



    public static void main(String[] args) {

        RestAssured.baseURI = "https://automationexercise.com/";


        /**
         * Sends a GET request to the "/api/productsList" endpoint and validates the response.
         *
         * - given(): Sets up the request with "Content-Type" header as "application/json".
         * - when(): Executes a GET request to the "/api/productsList" endpoint.
         * - then(): Validates the response by:
         *   - Logging all request and response details.
         *   - Asserting that the HTTP status code is 200 (OK).
         *   - Asserting that the "products" field in the response body is not null.
         */


        given().contentType("application/json")
                .when().get("/api/productsList")
                .then().log().all().statusCode(200).body("products", notNullValue());


        given().header("Content-Type","application/json")
                .when().post("/api/productsList")
                .then().log().all().statusCode(200);


        given().header("Content-Type","application/json")
                .when().get("/api/brandsList")
                .then().log().all().statusCode(200).body("brands", notNullValue());

        given().contentType("application/json")
                .when().put("/api/brandsList")
                .then().log().all().statusCode(200);

        RestAssured.baseURI = "https://automationexercise.com/api/searchProduct";

        given().contentType("application/x-www-form-urlencoded; charset=utf-8").formParam("search_product","tshirt")
                .when().post().
                then().log().all();


    }


}
