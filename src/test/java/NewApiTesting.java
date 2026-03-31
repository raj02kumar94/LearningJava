import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class NewApiTesting  {


        public static void main(String[] args) {
            System.out.println("This is a new API testing class");


            RestAssured.baseURI ="https://dummy.restapiexample.com/";

            given().header("Content-Type","application/json")
                    .when().get("api/v1/employees")
                    .then().statusCode(200).log().all();
        }
}
