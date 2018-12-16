package com.bonazzoli.aoc.util;

import com.bonazzoli.aoc.exception.CustomException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;
import java.util.logging.Logger;

@Component
public class ResourceReader {
    private final Logger LOGGER = Logger.getLogger("DEBUG");


    private File getFileFromFileName(String fileName){
        ClassLoader classLoader = ResourceReader.class.getClassLoader();
        URL url = classLoader.getResource(fileName);
        return new File(url.getFile());
    }

    public List<String> getTextListFromFile(String fileName) throws IOException {
        List<String> textList = new ArrayList<>();

        File textFile = getFileFromFileName(fileName);
        FileReader fr = new FileReader(textFile);
        Scanner scanner = new Scanner(fr);

        while(scanner.hasNextLine()){
            textList.add(scanner.nextLine());
        }
        scanner.close();
        return textList;
    }

    public static <C> C readValueFromJson (String fileName, TypeReference<C> typeReference) throws IOException, CustomException {
        ClassLoader classLoader = ResourceReader.class.getClassLoader();
        URL url = classLoader.getResource(fileName);
        File file = new File(url.getFile());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        C retVal = (C) objectMapper.readValue(file, typeReference);

        return retVal;
    }


}
