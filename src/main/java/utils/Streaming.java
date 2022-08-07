package utils;


import common.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import store.DataSetStore;


public class Streaming {
    public Response streamApiCall(String usecase)  {
        try {
            return RestAssured.given()
                    .baseUri(Constants.HUNDREDS_MS_URL)
                    .header("Authorization","Bearer "+ new ManagementToken().generateManagementToken())
                    .contentType(ContentType.JSON)
                    .body(new DataSetStore().streamRequestInJson(usecase))
                    .post();
        }
        catch (Exception ex) {
            System.out.println("Request failed due to  " + ex.getMessage());
        }
        return null;
    }

}
