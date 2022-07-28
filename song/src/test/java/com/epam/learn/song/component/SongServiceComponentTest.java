package com.epam.learn.song.component;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

@RunWith(Cucumber.class)
@SpringBootTest
@CucumberOptions(features = "src/test/resources/component",
        plugin = {"pretty", "html:build/test-results/test/cucumber.html"})
@CucumberContextConfiguration
@EnableFeignClients
public class SongServiceComponentTest {
}
