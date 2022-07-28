package com.epam.learn.processor.e2e;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@SpringBootTest
@CucumberOptions(features = "src/test/resources/e2e",
        plugin = {"pretty", "html:build/test-results/test/cucumber.html"})
@CucumberContextConfiguration
public class E2EUploadTest {
}
