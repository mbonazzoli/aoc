package com.bonazzoli.aoc.service;


import com.bonazzoli.aoc.configuration.HttpResourceConfiguration;
import com.bonazzoli.aoc.exception.AutowiredDependencyNotFoundException;
import com.bonazzoli.aoc.util.ResourceReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.List;

@Service
public class AocService {

    private ResourceReader resourceReader;
    private HttpResourceConfiguration httpResourceConfiguration;

    @Autowired
    public AocService(ResourceReader resourceReader, HttpResourceConfiguration httpResourceConfiguration) throws AutowiredDependencyNotFoundException {
        if(!ObjectUtils.isEmpty(resourceReader)){
            this.resourceReader = resourceReader;
        }else{
            throw new AutowiredDependencyNotFoundException("Resource reader cannot be null or empty");
        }
        if(!ObjectUtils.isEmpty(httpResourceConfiguration)){
            this.httpResourceConfiguration = httpResourceConfiguration;
        }else{
            throw new AutowiredDependencyNotFoundException("HttpResourceConfiguration cannot be null or empty");
        }
    }

    public String getHelloWorldMessage(){
        return "Hello World!";
    }

    public Integer getAnswerToDayOneProblemOne() throws IOException {
        List<String> resourceList = resourceReader.getTextListFromFile(httpResourceConfiguration.getDayOneFile());

        Integer sum = 0;

        for(String text: resourceList){
            Integer num = Integer.valueOf(text);
            sum += num;
        }
        return sum;
    }

}
