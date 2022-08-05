package APISteps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.APIConstants;

import static io.restassured.RestAssured.given;

public class GenerateTokenSteps {
    /*
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    public static String token;

    @Given("a JWT is generated")
    public void a_jwt_is_generated() {
        RequestSpecification request = given().header("Content-Type","application/json").
                body("{\n" +
                        "  \"email\": \"batchtwelve89@test.com\",\n" +
                        "  \"password\": \"Test@12\"\n" +
                        "}");

        Response response = request.when().post("/generateToken.php");


        token="Bearer " + response.jsonPath().getString("token");
        System.out.println(token);


     */
    //String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    public static String token;
// after start using APIConstans
    @Given("a JWT is generated")
    public void a_jwt_is_generated() {
        RequestSpecification request = given().header(APIConstants.HEADER_CONTENT_TYPE, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                body("{\n" +
                        "  \"email\": \"batchtwelve@test.com\",\n" +
                        "  \"password\": \"Test@123\"\n" +
                        "}");

        Response response = request.when().post(APIConstants.GENERATE_TOKEN_URI);

        token = "Bearer " + response.jsonPath().getString("token");
        System.out.println(token);
    }

}
