package com.bonazzoli.aoc.service;


import com.bonazzoli.aoc.configuration.HttpResourceConfiguration;
import com.bonazzoli.aoc.dto.Box;
import com.bonazzoli.aoc.exception.AutowiredDependencyNotFoundException;
import com.bonazzoli.aoc.util.ResourceReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AocService {

    private ResourceReader resourceReader;
    private HttpResourceConfiguration httpResourceConfiguration;
    private BoxService boxService;

    @Autowired
    public AocService(ResourceReader resourceReader, HttpResourceConfiguration httpResourceConfiguration, BoxService boxService) throws AutowiredDependencyNotFoundException {
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
        if(!ObjectUtils.isEmpty(boxService)){
            this.boxService = boxService;
        }else{
            throw new AutowiredDependencyNotFoundException("BoxService cannot be null or empty");
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

    public Integer getAnswerToDayOneProblemTwo() throws IOException {
        List<String> resourceList = resourceReader.getTextListFromFile(httpResourceConfiguration.getDayOneFile());

        List<Integer> sumsList = new ArrayList<>();

        Integer sum = 0;
        boolean match = false;
        Integer frequencyMatch = null;
        int i = 0;
        while(!match){
            Integer num = Integer.valueOf(resourceList.get(i));
            sum += num;

            if(sumsList.contains(sum)){
                frequencyMatch = sum;
                match = true;
            }else{
                sumsList.add(sum);
                i++;
                if(i >= resourceList.size()){
                    i = 0;
                }

            }
        }

        return frequencyMatch;
    }

    /**
     * Day 2: https://adventofcode.com/2018/day/2
     * @return checkSum of box similarities
     * @throws IOException
     */
    public Integer getAnswerToDayTwoProblemOne() throws IOException {
        List<String> resourceList = resourceReader.getTextListFromFile(httpResourceConfiguration.getDayTwoFile());
        List<Box> boxList = boxService.createListOfBoxes(resourceList);
        int num2 = 0;
        int num3 = 0;

        for(Box box: boxList){
            if(box.getHasThreeChars()){
                num3++;
            }
            if(box.getHasTwoChars()){
                num2++;
            }
        }

        return num2*num3;
    }
}
