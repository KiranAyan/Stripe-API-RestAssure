package stepDefStripCustomer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class GetStripCustomer {
	
	private Response response;
	private String secretKey;
	private String endPoint;
	private String customerId;

    @Given("I have set base URI")
    public void setBaseURI() {
        // In a real framework, you would get this from object.properties
    	 RestAssured.baseURI = LoadVariables.prop.getProperty("baseURI");
    	 String jenkinsKey = System.getenv("secretkey");
    	    if (jenkinsKey == null) {
    	        jenkinsKey = System.getProperty("secretkey");
    	    }

    	    if (jenkinsKey != null && !jenkinsKey.isEmpty()) {
    	        secretKey = jenkinsKey.trim();
    	        System.out.println("Using Secret Key from Jenkins/System environment.");
    	    } else {
    	        secretKey = LoadVariables.prop.getProperty("secretkey");
    	        System.out.println(secretKey);
    	        System.out.println("Using Secret Key from local property file.");
    	    }
         endPoint = LoadVariables.prop.getProperty("endpoint");
    }

    @When("I send a GET request to retrieve the customer")
    public void hitGet( ) {
        response = given()
                        .auth().basic(secretKey, "")
                   .when()
                        .get(endPoint);
    }

    @Then("the response status code should be 200")
    public void verify_status_code() {
        response.then().statusCode(200);

    }

    @Then("the response body should contain the id {string}")	
    public void verify_customer_id(String expectedID) {
    	System.out.println(expectedID);
    	System.out.println(response);
    	customerId = LoadVariables.prop.getProperty(expectedID);
        
        // 2. Add a check to ensure the key actually existed in your file
        if(customerId == null) {
            throw new RuntimeException("The key '" + expectedID + "' was not found in object.properties!");
        }
        response.then().body("id", equalTo(customerId));
        response.prettyPrint(); // Optional: print to console
    }
    
    @Then("the response body should contain id {string}")	
    public void verify_customer_idFearure(String expectedID) {
    	System.out.println(expectedID);
    	System.out.println("CI/CD also ran scuccessfully");
        // 2. Add a check to ensure the key actually existed in your file
        if(expectedID == null) {
            throw new RuntimeException("The key '" + expectedID + "' was not found in object.properties!");
        }
        response.then().body("id", equalTo(expectedID));
        response.prettyPrint(); // Optional: print to console
    }
}