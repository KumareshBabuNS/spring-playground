package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.http.HttpMethod;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
    locations = {
        "classpath:omdb.properties"
    },
    properties = {
        "api.key=fakeKey"
    }
)
public class MoviesTest {

    @Autowired
    private JsonService jsonService;

    @Autowired
    private OMDBService omdbService;

    @Test
    public void testMoviesService() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        MockRestServiceServer mockRestServiceServer = MockRestServiceServer.createServer(omdbService.getRestTemplate());

        mockRestServiceServer
                .expect(requestTo("http://www.omdbapi.com/?s=harry&apikey=fakeKey"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonService.getJSON("/fullharry.json"), MediaType.APPLICATION_JSON));

        assertThat(mapper.writeValueAsString(omdbService.getMovies("harry")), equalTo(jsonService.getJSON("/harry.json")));
        mockRestServiceServer.verify();

    }

}
