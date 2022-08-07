import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Session;


public class SessionAPITests {
    @Test
    void testHappyCase() {
        Response response = new Session().sessionApiCall("happyPath");
        System.out.println("response " + response.getBody().prettyPrint());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test
    void testLimitZeroCase() {
        Response response = new Session().sessionApiCall("limitZero");
        System.out.println("response " + response.getBody().prettyPrint());
        Assert.assertTrue(response.getBody().prettyPrint().contains("limit can only be a number between 1-20"));
        Assert.assertEquals(response.getStatusCode(), 400);
    }
    @Test
    void testNoParamWithLimitOneCase() {
        Response response = new Session().sessionApiCall("noParamWithLimitOne");
        System.out.println("response " + response.getBody().prettyPrint());
        Assert.assertTrue(response.getBody().prettyPrint().contains("\"limit\": 1"));
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test
    void testIncorrectRoomIdCase() {
        Response response = new Session().sessionApiCall("incorrectRoomId");
        System.out.println("response " + response.getBody().prettyPrint());
        Assert.assertTrue(response.getBody().prettyPrint().contains("\"limit\": 1"));
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test
    void testIncorrectRoomIdWithActiveFalseCase() {
        Response response = new Session().sessionApiCall("incorrectRoomIdWithActiveFalse");
        System.out.println("response " + response.getBody().prettyPrint());
        Assert.assertTrue(response.getBody().prettyPrint().contains("\"limit\": 1"));
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test
    void testCorrectRoomIdWithActiveFalseCase() {
        Response response = new Session().sessionApiCall("correctRoomIdWithActiveFalse");
        System.out.println("response " + response.getBody().prettyPrint());
        Assert.assertTrue(response.getBody().prettyPrint().contains("\"limit\": 1"));
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test
    void testCorrectRoomIdWithActiveTrueCase() {
        Response response = new Session().sessionApiCall("correctRoomIdWithActiveTrue");
        System.out.println("response " + response.getBody().prettyPrint());
        Assert.assertTrue(response.getBody().prettyPrint().contains("\"limit\": 1"));
        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
