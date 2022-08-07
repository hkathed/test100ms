package model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.security.Timestamp;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SessionModel {
    /**
     * The unique name of testcase.
     */
    @CsvBindByName(column = "testCaseName", required = true)
    private String testCaseName;
    /**
     * The type of operation on streaming video.
     */
    @CsvBindByName(column = "id")
    private String id;


    /**
     * The room id of the room which we need to start streaming/recording
     */
    @CsvBindByName(column = "room_id")
    private String roomId;

    /**
     * Flag to enable browser recording
     */
    @CsvBindByName(column = "active")
    private boolean active;

    /**
     * Timestamp before(inclusive) which session is created.
     */
    @CsvBindByName(column = "before")
    private Timestamp before;

    /**
     * Timestamp after(inclusive) which session is created.
     */
    @CsvBindByName(column = "after")
    private Timestamp after;

    /**
     * Number of sessions .
     */
    @CsvBindByName(column = "limit")
    private int limit;

    /**
     * D of the session after which to start the response.
     */
    @CsvBindByName(column = "start")
    private String start ;

}
