package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
 public class HardCodeExamples {


        String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
        String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NTU0MTE2NzIsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY1NTQ1NDg3MiwidXNlcklkIjoiMzg5OCJ9.ATA_9bATMAEqjqGBw4bj6GNF-zatP4mMjCz3w59d3mE";
        static String employee_id;

@Test
public void acreateEmployee(){
        RequestSpecification request = given().header("Content-Type","application/json").
        header("Authorization",token).body("{\n" +
        "  \"emp_firstname\": \"eddie\",\n" +
        "  \"emp_lastname\": \"patsatsia\",\n" +
        "  \"emp_middle_name\": \"MS\",\n" +
        "  \"emp_gender\": \"F\",\n" +
        "  \"emp_birthday\": \"2000-06-11\",\n" +
        "  \"emp_status\": \"Probation\",\n" +
        "  \"emp_job_title\": \"QA\"\n" +
        "}");

        Response response = request.when().post("/createEmployee.php");
        //response.prettyPrint();
        response.then().assertThat().statusCode(201);
        //Hamcrest matchers
        response.then().assertThat().body("Message",equalTo("Employee Created"));
        response.then().assertThat().body("Employee.emp_firstname",equalTo("eddie"));

        //using jsonPath(), to specify the key in the body so that it returns the value against it
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);

        }

@Test
public void bgetCreatedEmployee(){
        RequestSpecification preparedRequest = given().header("Content-Type","application/json").
                header("Authorization",token).queryParam("employee_id",employee_id);

        Response response = preparedRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);

        String tempId = response.jsonPath().getString("employee.employee_id");
        Assert.assertEquals(tempId, employee_id);


        }
@Test
public void cUpdateEmployee(){
        //update the existing employee
        RequestSpecification preparedRequest = given().header("Authorization", token).
        header("Content-Type", "application/json").body("{\n" +
        "  \"employee_id\": \""+ employee_id +"\",\n" +
        "  \"emp_firstname\": \"Ecat\",\n" +
        "  \"emp_lastname\": \"nico\",\n" +
        "  \"emp_middle_name\": \"MSA\",\n" +
        "  \"emp_gender\": \"M\",\n" +
        "  \"emp_birthday\": \"2002-02-19\",\n" +
        "  \"emp_status\": \"Employee\",\n" +
        "  \"emp_job_title\": \"Consultant\"\n" +
        "}");

        Response response = preparedRequest.when().put("/updateEmployee.php");
        response.prettyPrint();

        //verification and validation
        response.then().assertThat().body("Message", equalTo("Employee record Updated"));
        response.then().assertThat().statusCode(200);
        }



@Test
public void dGetUpdatedEmployee(){
        RequestSpecification request = given().header("Content-Type","application/json").
        header("Authorization",token).queryParam("employee_id", employee_id);

        Response response = request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();

        }

        @Test
        public void eGetAllEmployees(){
                RequestSpecification request = given().header("Authorization",token)
                        .header("Content-Type","application/json");

                Response response = request.when().get("/getAllEmployees.php");

                //it returns string of response
                String allEmployees = response.prettyPrint();

                //jsonPath() vs jsonPath
                //jsonPath is a class that contains method for converting the values into json object
                //jsonPath() is a method belongs to jsonPath class

                //creating object of jsonPath class
                JsonPath js = new JsonPath(allEmployees);

                //retrieving the total number of employees
                int count = js.getInt("Employees.size()");
                System.out.println(count);

                //to print only employee id of all the employees
                for (int i=0; i<count; i++){
                        String empID =  js.getString("Employees["+ i + "].employee_id");
                }


        }


}



