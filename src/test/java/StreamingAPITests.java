
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.Streaming;
import org.testng.Assert;

public class StreamingAPITests {

    @Test
    void testHappyCase() {
        Response response = new Streaming().streamApiCall("happyFlow");
        System.out.println("response " + response.getBody().prettyPrint());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test
    void testStopOperation() {
        Response response = new Streaming().streamApiCall("stopOperation");
        System.out.println("response " + response.getBody().prettyPrint());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test
    void testCapsStopOperation() {
        Response response = new Streaming().streamApiCall("STOPOperation");
        System.out.println("response " + response.getBody().prettyPrint());
        Assert.assertTrue(response.getBody().prettyPrint().contains("expected values=[start,stop]"));
        Assert.assertEquals(response.getStatusCode(), 500);
    }
    @Test
    void testIncorrectRoomId() {
        Response response = new Streaming().streamApiCall("incorrectRoomId");
        System.out.println("response " + response.getBody().prettyPrint());
        Assert.assertTrue(response.getBody().prettyPrint().contains("Invalid id="));
        Assert.assertTrue(response.getBody().prettyPrint().contains("Code: 2"));
        Assert.assertEquals(response.getStatusCode(), 500);
    }
    @Test
    void testOperationInvalid() {
        Response response = new Streaming().streamApiCall("operationInvalid");
        System.out.println("response " + response.getBody().prettyPrint());
        Assert.assertTrue(response.getBody().prettyPrint().contains("Code: 8"));
        Assert.assertEquals(response.getStatusCode(), 500);
    }
    @Test
    void testRoomIdMissing() {
        Response response = new Streaming().streamApiCall("roomIdMissing");
        System.out.println("response " + response.getBody().prettyPrint());
        Assert.assertTrue(response.getBody().prettyPrint().contains("Code: 3"));
        Assert.assertEquals(response.getStatusCode(), 500);
    }
    @Test
    void testOperationMissing() {
        Response response = new Streaming().streamApiCall("operationMissing");
        System.out.println("response " + response.getBody().prettyPrint());
        Assert.assertTrue(response.getBody().prettyPrint().contains("expected values=[start,stop]"));
        Assert.assertTrue(response.getBody().prettyPrint().contains("Code: 8"));
        Assert.assertEquals(response.getStatusCode(), 500);
    }
    @Test
    void testResolutionNotSupported() {
        Response response =new Streaming().streamApiCall("resolutionNotSupported");
        System.out.println("response " + response.getBody().prettyPrint());
        Assert.assertTrue(response.getBody().prettyPrint().contains("Message: Resolution not supported"));
        Assert.assertTrue(response.getBody().prettyPrint().contains("Code: 11"));
        Assert.assertEquals(response.getStatusCode(), 500);
    }
    @Test
    void testMeetingUrlInvalid() {
        Response response =new Streaming().streamApiCall("meetingUrlInvalid");
        System.out.println("response " + response.getBody().prettyPrint());
        Assert.assertTrue(response.getBody().prettyPrint().contains("Invalid id="));
        Assert.assertTrue(response.getBody().prettyPrint().contains("Code: 7"));
        Assert.assertEquals(response.getStatusCode(), 500);
    }

}