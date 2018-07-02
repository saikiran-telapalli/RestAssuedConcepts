package resources;

public class Payload {

	public static String getPostBody() {

		String body = "{"+
				"\"location\": {"+
				"\"lat\": -33.8669710,"+
				"\"lng\": 151.1958750"+
				" },"+
				"\"accuracy\": 50,"+
				"\"name\": \"Google Shoes!\","+
				" \"phone_number\": \"(02) 9374 4000\","+
				"\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
				"\"types\": [\"shoe_store\"],"+
				"\"website\": \"http://www.google.com.au/\","+
				"\"language\": \"en-AU\""+
				"}";

		return body;
	}

	public static String getJiraSessionBody() {
		String body = "{"
				+ " \"username\": "
				+ "\"saikiran.telapalli\", "
				+ "\"password\": "
				+ "\"Welcome123$\" "
				+ "}";
		return body;
	}

	public static String getJiraCreateBody() {
		String body = "{" + 

				"      \"fields\": {" + 
				"        \"project\": {" + 
				"          \"key\": \"MOOLYA\"" + 
				"        }," + 
				"        \"summary\": \"Creating issue using Resyassured\"," + 
				"        \"description\": \"Description issue using Resyassured\"," + 
				"        \"issuetype\": {" + 
				"          \"name\": \"Bug\" " + 
				"        },";
		
		return body;
	}
}
