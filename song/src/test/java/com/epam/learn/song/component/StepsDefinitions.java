package com.epam.learn.song.component;

import com.epam.learn.song.domain.Song;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StepsDefinitions {

    @Autowired
    SongServiceClient songServiceClient;

    private ResponseEntity<Song> getResponse;

    private ResponseEntity<Map<String, Integer>> postResponse;

    @When("song's data saved")
    public void song_s_data_saved(Song song) {
        postResponse = songServiceClient.createSong(song);
    }

    @Then("POST response code is {int}")
    public void post_response_code_is(Integer code) {
        int codeValue = postResponse.getStatusCode().value();
        assertEquals(code, codeValue);
    }

    @When("GET request sent songs\\/{int}")
    public void get_request_sent_songs(Integer id) {
        getResponse = songServiceClient.getSong(id);
    }

    @Then("GET response code is {int}")
    public void get_response_code_is(Integer code) {
        int codeValue = getResponse.getStatusCode().value();
        assertEquals(code, codeValue);
    }

    @And("song's data returned with resourceId {}")
    public void song_s_data_returned_with_resourceId(Integer resourceId) {
        Song body = getResponse.getBody();
        assertNotNull(body);
        assertEquals(resourceId, body.getResourceId());
    }

}
