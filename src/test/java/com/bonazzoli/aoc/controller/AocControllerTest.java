package com.bonazzoli.aoc.controller;

import com.bonazzoli.aoc.service.AocService;
import org.apache.catalina.filters.CorsFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AocControllerTest {
    @Autowired
    WebApplicationContext context;

    private static final String HELLO_WORLD_URI = "/aoc/day0/helloWorld";
    private static final String DAY_ONE_PROBLEM_ONE_URI = "/aoc/day1/problem1";

    private MockMvc mockMvc;
    private MvcResult mvcResult;

    @MockBean
    private AocService aocService;


    @Before
    public void init(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context).build();
    }

    @Test
    public void testGetHelloWorldMessage() throws Exception{
        //act
        mvcResult = mockMvc.perform(get(HELLO_WORLD_URI).header("user", "tester"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testGetAnswerToDayOneProblemOne() throws Exception {
        //arrange
        // mocks
        given(aocService.getAnswerToDayOneProblemOne()).willReturn(428);
        //act
        mvcResult = mockMvc.perform(get(DAY_ONE_PROBLEM_ONE_URI).header("user", "tester"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
