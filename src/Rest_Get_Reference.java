import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
public class Rest_Get_Reference{
	public static void main(String[]args) {
		RestAssured.baseURI="http://regres.in/";
		
		//Declare Response Body
		String responseBody=given().header("Content-Type","application/json").
				body("requestBody").when().get("api/users?page=2").
				then().extract().response().asString();
		System.out.print(responseBody);
		
}
}