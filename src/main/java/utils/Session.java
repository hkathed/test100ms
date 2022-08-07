package utils;

import common.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import store.DataSetStore;

public class Session {
    public Response sessionApiCall(String usecase)  {
        try {
            return RestAssured.given()
                    .header("Authorization","Bearer "+ new ManagementToken().generateManagementToken())
                    .queryParams(new DataSetStore().sessionRequestQueryParams(usecase))
                    .when()
                    .get(Constants.HUNDREDS_MS_SESSION_URL);
        }
        catch (Exception ex) {
            System.out.println("Request failed due to " + ex.getMessage());
        }
        return null;
    }
}
