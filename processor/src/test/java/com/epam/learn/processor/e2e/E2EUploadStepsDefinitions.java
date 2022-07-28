package com.epam.learn.processor.e2e;

import com.epam.learn.processor.dto.SongDTO;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class E2EUploadStepsDefinitions {

    private final RestTemplate restTemplate = new RestTemplate();

    private Integer postResourceId;

    private final String RESOURCES_URL = "http://localhost:8091/resources";

    @When("upload file with name {string} to the resource service")
    public void upload_file_to_the_resource_service(String fileName) {
        HttpEntity<MultiValueMap<String, Object>> entity = getMultipartEntity(fileName);
        ResponseEntity<Map> response = restTemplate.postForEntity(RESOURCES_URL, entity, Map.class);

        Map<String, Integer> responseBody = response.getBody();
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(responseBody);

        Integer uploadedId = responseBody.get("id");
        postResourceId = uploadedId;
        assertNotNull(uploadedId);
    }

    @Then("wait for processor service to parse data")
    public void wait_for_processor_service_to_parse_data() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
    }

    @Then("check data is saved via GET call to the song service")
    public void check_data_is_saved_via_get_call_to_the_song_service() {
        String url = "http://localhost:8093/songs/" + postResourceId;
        ResponseEntity<SongDTO> response = restTemplate.exchange(url, HttpMethod.GET, null, SongDTO.class);
        int statusCodeValue = response.getStatusCodeValue();
        SongDTO actual = response.getBody();
        SongDTO expected = createExpectedDTO();

        assertEquals(200, statusCodeValue);
        assertNotNull(actual);
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getArtist(), actual.getArtist());
        assertEquals(expected.getAlbum(), actual.getAlbum());
        assertEquals(expected.getLength(), actual.getLength());
        assertEquals(expected.getYear(), actual.getYear());
    }

    private HttpEntity<MultiValueMap<String, Object>> getMultipartEntity(String fileName) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new ClassPathResource(fileName));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        return new HttpEntity<>(body, headers);
    }

    private SongDTO createExpectedDTO() {
        SongDTO songDTO = new SongDTO();
        songDTO.setName("Sixteen Saltines");
        songDTO.setArtist("Jack White");
        songDTO.setAlbum("Blunderbuss [SICP 3480]");
        songDTO.setLength(157L);
        songDTO.setYear(2012);
        return songDTO;
    }
}
