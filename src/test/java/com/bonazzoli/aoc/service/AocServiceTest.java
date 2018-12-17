package com.bonazzoli.aoc.service;

import com.bonazzoli.aoc.configuration.HttpResourceConfiguration;
import com.bonazzoli.aoc.dto.Box;
import com.bonazzoli.aoc.exception.CustomException;
import com.bonazzoli.aoc.util.ResourceReader;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AocServiceTest {

    @Mock
    private HttpResourceConfiguration httpResourceConfiguration;

    @Mock
    private ResourceReader resourceReader;

    @Mock
    private BoxService boxService;

    @InjectMocks
    private AocService aocService;

    private List<String> resourceList;
    private String dayOneFile;
    private List<Box> boxList;

    public AocServiceTest() throws IOException {
    }

    @Before
    public void init() throws IOException, CustomException {
        boxList = ResourceReader.readValueFromJson("boxes.json", new TypeReference<List<Box>>() {
        });
        resourceList = new ArrayList<String>(){
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
        dayOneFile = "testFile.txt";
    }

    @Test
    public void testGetAnswerToDayOneProblemOne() throws IOException {
        //arrange
        Integer expected = 45;
        // mocks
        when(httpResourceConfiguration.getDayOneFile()).thenReturn(dayOneFile);
        when(resourceReader.getTextListFromFile(anyString())).thenReturn(resourceList);
        //act
        Integer actual = aocService.getAnswerToDayOneProblemOne();
        //assert
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testGetAnswerToDayTwoProblemOne() throws Exception{

    }
}
