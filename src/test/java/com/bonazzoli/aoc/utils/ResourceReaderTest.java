package com.bonazzoli.aoc.utils;

import com.bonazzoli.aoc.exception.CustomException;
import com.bonazzoli.aoc.util.ResourceReader;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ResourceReaderTest {

    private ResourceReader resourceReader;

    private List<String> expectedTextList;

    @Before
    public void init(){
        resourceReader = new ResourceReader();
        expectedTextList = new ArrayList<String>(){
            {
                add("0");
                add("+1");
                add("+2");
                add("+3");
                add("+4");
                add("+5");
                add("+6");
                add("+7");
                add("+8");
                add("+9");
            }
        };
    }

    @Test
    public void readLinesFromTextFile() throws IOException, CustomException {
        //arrange
        String fileName = "testFile.txt";
        //act
        List<String> actual = resourceReader.getTextListFromFile(fileName);
        //assert
        assertThat(actual, equalTo(expectedTextList));
    }
}
