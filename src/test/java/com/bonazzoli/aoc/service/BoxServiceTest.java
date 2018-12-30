package com.bonazzoli.aoc.service;

import com.bonazzoli.aoc.dto.Box;
import com.bonazzoli.aoc.exception.CustomException;
import com.bonazzoli.aoc.util.ResourceReader;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class BoxServiceTest {

    private BoxService boxService;

    private List<Box> expectedBoxList;
    private List<String> boxIds;

    @Before
    public void init() throws IOException, CustomException {
        boxService = new BoxService();
        expectedBoxList = ResourceReader.readValueFromJson("boxes.json", new TypeReference<List<Box>>() {
        });
        //create box id list from boxes
        boxIds = new ArrayList<>();
        expectedBoxList.forEach(box -> boxIds.add(box.getId()));
    }


    @Test
    public void testCreateListOfBoxes(){
        //arrange

        //act
        List<Box> actual = boxService.createListOfBoxes(boxIds);
        //assert
        assertThat(actual, equalTo(expectedBoxList));
    }

    @Test
    public void testParseBox(){
        //arrange
        Box box = new Box("aaabb");
        Box expectedBox = new Box("aaabb");
        expectedBox.setHasTwoChars(true);
        expectedBox.setHasThreeChars(true);

        //act
        Box actual = boxService.parseBox(box);

        //assert
        assertThat(actual, equalTo(expectedBox));

    }

    @Test
    public void testCountCharSimilarities_shouldReturnOne(){
        Box box = new Box("aaaaa");
        Box box2 = new Box("abaaa");

        //act
        String actual = boxService.getMatchFromSimilarities(box, box2).get();

        //assert
        assertThat(actual, equalTo("aaaa"));
    }

    @Test
    public void testCountCharSimilarities_DiffAtEnd_shouldReturnOne(){
        Box box = new Box("aaaaa");
        Box box2 = new Box("aaaab");

        //act
        String actual = boxService.getMatchFromSimilarities(box, box2).get();

        //assert
        assertThat(actual, equalTo("aaaa"));
    }

    @Test
    public void testCountCharSimilarities_shouldReturnTwo(){
        Box box = new Box("aaaaa");
        Box box2 = new Box("ababa");

        //act
        Optional<String> actual = boxService.getMatchFromSimilarities(box, box2);

        //assert
        assertThat(actual.isPresent(), is(false));
    }

}
