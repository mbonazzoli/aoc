package com.bonazzoli.aoc.controller;

import com.bonazzoli.aoc.exception.AutowiredDependencyNotFoundException;
import com.bonazzoli.aoc.service.AocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller()
@RequestMapping("/aoc")
public class AocController {

    private AocService aocService;

    @Autowired
    public AocController(AocService aocService) throws AutowiredDependencyNotFoundException {
        if(!ObjectUtils.isEmpty(aocService)){
            this.aocService = aocService;
        }else{
            throw new AutowiredDependencyNotFoundException("aocService cannot be null or empty");
        }
    }

    @GetMapping("/day0/helloWorld")
    public String getHelloWorld(@RequestHeader String user){
        return aocService.getHelloWorldMessage();
    }

    @GetMapping("/day1/problem1")
    public ResponseEntity<Integer> getAnswerToDayOneProblemOne(@RequestHeader String user) throws IOException {
        return ResponseEntity.status((HttpStatus.OK)).body(aocService.getAnswerToDayOneProblemOne());
    }

}
