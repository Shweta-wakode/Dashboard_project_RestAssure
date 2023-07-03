import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import java.time.LocalDateTime;
import org.testng.Assert;
import io.restassured.path.json.JsonPath;
public class Rest_Put_Reference {

	public static void main(String[] args) {
//		Declare BaseURL
		RestAssured.baseURI = "https://reqres.in/";
		
//		Declare RequestBody 
		String reqBody = "{\r\n"+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}" ;
//		Declare The Expected Result
		JsonPath jspReq = new JsonPath(reqBody);
		String req_name = jspReq.getString("name");
		String req_job  = jspReq.getString("job");
		LocalDateTime currentdate =LocalDateTime.now();
		String expecteddate = currentdate.toString().substring(0,11);
		
//		Declare Response Body Fetching Response BOdy
		String resBody = given().header("content-type","application/json").body(reqBody).when().put("api/users/2").then().extract().response().asString();
		System.out.println(resBody);
		
//		Create An object of JsonPath to Parse the ResponseBody
		JsonPath jspRes = new JsonPath(resBody);
		String res_name = jspRes.getString("name");
		String res_job = jspRes.getString("job");
		String res_updatedAt = jspRes.getString("updatedAt");
		res_updatedAt =res_updatedAt.substring(0,11);
		
//		Validate Response Body Parameter
		Assert.assertEquals(res_name, req_name);
		Assert.assertEquals(res_job, req_job);
		Assert.assertEquals(res_updatedAt, expecteddate);

		

	}

}
