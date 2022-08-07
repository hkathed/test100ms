package store;

import java.io.InputStreamReader;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import model.SessionModel;
import model.StreamingModel;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.Reader;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Collections;
import java.util.HashMap;

public class DataSetStore {

    /**
     * readDataSetEntriesForCSV reads the data set entries from the given input file. Convert that csv data into required
     * class type passed in input.
     * @param filePath input file path from where we want to fetch use-case & there input details.
     * @param entryType Class type of which input should be converted.
     * @return List<T> List of the T
     * @param <T> class type based on the api type required.
     * @throws IOException
     */
    private <T> List<T> readDataSetEntriesForCSV(final String filePath, final Class<T> entryType) throws IOException {
        InputStream inputStream = new ClassPathResource("/" + filePath).getInputStream();
        Reader targetReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        System.out.println("Reading csv entries from filePath : " + filePath);
        try {
            return Optional.ofNullable(new CsvToBeanBuilder<T>(targetReader)
                            .withSkipLines(0)
                            .withType(entryType)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build())
                    .map(CsvToBean::parse).orElseGet(Collections::emptyList);
        } catch (final Throwable e) {
            System.out.println("Error while reading dataset: " + filePath+ " error" + e);
            throw e;
        }
    }

    /**
     * streamRequestInJson reads the value of use-case from testData.csv file and convert that bean into string for
     * StreamingModel object.
     * @param usecase input use-case name.
     * @return String the json string of Streaming input pojo
     * @throws IOException
     */
    public String streamRequestInJson(final String usecase) throws IOException {
        List<StreamingModel> streamingModelList = readDataSetEntriesForCSV("testData.csv", StreamingModel.class);
        StreamingModel streamModel =   streamingModelList.stream()
                .filter(streamingModel -> streamingModel.getTestCaseName().equals(usecase))
                .findAny()
                .orElse(null);

        String str =  "{\r\n" + "    \"operation\":"+ "\""+streamModel.getOperation()+ "\"" + ",\r\n"
                + "    \"roomId\":" + "\""+ streamModel.getRoomId() + "\""+ ",\r\n"
                + "    \"meetingUrl\": " + "\""+ streamModel.getMeetingUrl()+ "\"" + ",\r\n"
                + "    \"rtmpUrls\":"+ streamModel.getRtmpUrls() + ",\r\n"
                + "    \"record\":"+ streamModel.isRecord() +",\r\n"
                + "    \"resolution\":" + streamModel.getResolution() +"\r\n"
                + "}";
        System.out.println("the input request:  " + str + " for use-case: " + usecase);
        return  str;
    }

    /**
     * sessionRequestQueryParams reads the value of use-case from sessionTestData.csv file and convert that bean into
     * hashmap for queryParams input.
     * @param usecase input use-case name.
     * @return HashMap<String, Object> hashmap of query params
     * @throws IOException
     */
    public  HashMap<String, Object> sessionRequestQueryParams(final String usecase) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        List<SessionModel> sessionModelList = readDataSetEntriesForCSV("sessionTestData.csv", SessionModel.class);
        SessionModel sessionModel =   sessionModelList.stream()
                .filter(sessionmodel -> sessionmodel.getTestCaseName().equals(usecase))
                .findAny()
                .orElse(null);
        HashMap<String, Object> queryParams = objectMapper.convertValue(sessionModel, HashMap.class);
        queryParams.remove("testCaseName");
        queryParams.forEach((key, value) -> System.out.println("key "+ key + " value " + value));
        return queryParams;
    }

}
