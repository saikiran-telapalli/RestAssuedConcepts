package restAPItest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import resources.ReusableMethods;

import static io.restassured.RestAssured.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TwitterAPI {

	//https://apps.twitter.com/app/new
	//https://developer.twitter.com/en/docs/tweets/timelines/api-reference/get-statuses-home_timeline.html
	//**********https://github.com/rest-assured/rest-assured/wiki/usage#logging********

	Properties prop;

	@BeforeTest
	public void getData() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream("/Users/saikiran/eclipse-workspace/DemoProject/src/resources/envronment.properties");
		prop.load(fis);

		//prop.getProperty("HOST"); //To get the value of the key from properties file
	}


	String consumerKey= "7nWFfTOK6kgcSEYN0xzUUfPAo";
	String consumerSecret = "PZA37TjoLOk8KGsCa0UE0kO8uo0pV8zCzZiKs0keb08YiY9cnA";
	String Token = "1013085890862833666-XoRxdAMD1AASkUFJxtiUDbL96e5P2o";
	String TokenSecret = "Y3YFjDXZRi7lYlBlK1Xq6nzl9Y20KQZ4qYx9mlZm60CAr";
	String id;

	//*********** If Oauth_1.0 authentication needed then we need to import following jars or else we get the following error **************//
	//java.lang.NoClassDefFoundError: com/github/scribejava/core/model/AbstractRequest

	//https://mvnrepository.com/artifact/com.github.scribejava/scribejava-apis/2.5.3
	//https://mvnrepository.com/artifact/com.github.scribejava/scribejava-core/2.5.3

	@Test(priority=2)
	public void getLatestTweets() {

		RestAssured.baseURI =prop.getProperty("TwitterHost");

		Response resp= given().auth().oauth(consumerKey, consumerSecret, Token, TokenSecret)
				.queryParam("count", "1")
				.when().get("/home_timeline.json").then().extract().response();

		//String text = ReusableMethods.rawToJson(resp).get("text").toString();
		System.out.println(ReusableMethods.rawToJson(resp).get("text").toString());
		//String ids = ReusableMethods.rawToJson(resp).get("id").toString();
		System.out.println(ReusableMethods.rawToJson(resp).get("id").toString());


	}

	@Test(priority=1)
	public void createTweet() {

		RestAssured.baseURI =prop.getProperty("TwitterHost");

		Response resp= given().auth().oauth(consumerKey, consumerSecret, Token, TokenSecret)
				.queryParam("status", "Creating first tweet using rest assured api testing!!@@2")
				.when().post("/update.json").then().extract().response();
		System.out.println("Tweet got created");
		id = ReusableMethods.rawToJson(resp).get("id").toString();
		System.out.println(id);


	}
	
	@Test(priority=3)
	public void deleteTwitterPost() {
		
		RestAssured.baseURI =prop.getProperty("TwitterHost");

		Response resp= given().auth().oauth(consumerKey, consumerSecret, Token, TokenSecret)
				.queryParam("status", "Creating first tweet using rest assured api testing!!@@@@")
				.when().post("/destroy/"+id+".json").then().extract().response();
		System.out.println("Tweet got deleted");
		//String text = ReusableMethods.rawToJson(resp).get("text").toString();
		System.out.println(ReusableMethods.rawToJson(resp).get("text").toString());
		//String id = ReusableMethods.rawToJson(resp).get("truncated").toString();
		System.out.println(ReusableMethods.rawToJson(resp).get("truncated").toString());
		
	}
}

