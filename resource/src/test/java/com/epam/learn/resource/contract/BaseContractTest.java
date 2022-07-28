package com.epam.learn.resource.contract;

import com.epam.learn.resource.controller.ResourceController;
import com.epam.learn.resource.service.ResourceService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@AutoConfigureMessageVerifier
public class BaseContractTest {

    @Autowired
    private ResourceController resourceController;

    @MockBean
    ResourceService resourceService;

    @BeforeEach
    public void setup() {
        predefineGetBehavior();
        StandaloneMockMvcBuilder standaloneMockMvcBuilder
                = MockMvcBuilders.standaloneSetup(resourceController);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
    }

    private void predefineGetBehavior(){
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("test".getBytes());
        when(resourceService.download(1)).thenReturn(byteArrayInputStream);
    }

}
