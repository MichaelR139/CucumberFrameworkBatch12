package APIreview;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import javax.xml.bind.SchemaOutputResolver;

import static io.restassured.RestAssured.*;

public class APIExamples {
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String Token= "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NTYxNjgxNTIsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY1NjIxMTM1MiwidXNlcklkIjoiMzg5OCJ9.-juDa9uqNVJ77NJ5b3lsrDFbgAzYnB1sDZF8xLmfCzk";

    @Test
    public void createAnEmployee(){
        RequestSpecification request = given().header("Content-Type","application/json").
                header("Authorization",Token).body("{\n" +
                        "  \"emp_firstname\": \"Michael\",\n" +
                        "  \"emp_lastname\": \"Rad\",\n" +
                        "  \"emp_middle_name\": \"Boris\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2000-06-12\",\n" +
                        "  \"emp_status\": \"QA\",\n" +
                        "  \"emp_job_title\": \"Tester\"\n" +
                        "}");

        Response response = request.when().post("/createEmployee.php");
        response.prettyPrint();

        JsonElement json_element=new JsonParser().parse(response.asString());

    }
}
