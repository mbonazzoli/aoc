package com.bonazzoli.aoc.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpResourceConfiguration {

    @Value("${resource.day.one.file}")
    private String dayOneFile;

    public String getDayOneFile() {
        return dayOneFile;
    }
}
