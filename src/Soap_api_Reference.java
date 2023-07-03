import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.path.xml.*;
import io.restassured.RestAssured;
public class Soap_api_Reference {

	public static void main(String[] args) {
		//Declare the base url
		RestAssured.baseURI="https://www.dataaccess.com";
		//Declare Requesrt Body
		String RequestBody="<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap12:Envelope xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\r\n"
				+ "  <soap12:Body>\r\n"
				+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <ubiNum>500</ubiNum>\r\n"
				+ "    </NumberToWords>\r\n"
				+ "  </soap12:Body>\r\n"
				+ "</soap12:Envelope>\r\n"
				+ "";
		//Extract responsebody
		String resBody = given()
				.header("Content-Type","text/xml; charset=utf-8")
				.body(RequestBody)
				.when().post("webservicesserver/NumberConversion.wso").then().extract().response().asString();
		System.out.println(resBody);
		//Parse the ResponseBody
		XmlPath XmlResponse = new XmlPath(resBody);
		String ResponseParameter = XmlResponse.getString("NumberToWordsResult");
		System.out.println(ResponseParameter);
		//Validate the ResponseBody
		Assert.assertEquals(ResponseParameter,"five hundred ");
  
	}
}
