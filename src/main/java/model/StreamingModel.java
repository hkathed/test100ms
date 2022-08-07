package model;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class StreamingModel {

    /**
     * The unique name of testcase.
     */
    @CsvBindByName(column = "testCaseName", required = true)
    private String testCaseName;
    /**
     * The type of operation on streaming video.
     */
    @CsvBindByName(column = "operation")
    private String operation;

    /**
     * The room id of the room which we need to start streaming/recording
     */
    @CsvBindByName(column = "room_id")
    private String roomId;

    /**
     * Single click meeting url which we need to start streaming/recording
     */
    @CsvBindByName(column = "meeting_url")
    private String meetingUrl;

    /**
     * List of RTMP output urls to which the meeting will be streamed.
     * Required when RTMP streaming needs to be started.
     * Supports upto 3 rtmp:// / rtmps:// urls
     */
    @CsvBindAndSplitByName(column = "rtmp_urls", elementType = String.class, splitOn = ",")
    private List<String> rtmpUrls;

    /**
     * Flag to enable browser recording
     */
    @CsvBindByName(column = "record")
    private boolean record;

    /**
     *  Video resolution for streaming/recording
     */
    @CsvBindByName(column = "resolution")
    private Object resolution;

}
