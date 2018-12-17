package com.bonazzoli.aoc.service;

import com.bonazzoli.aoc.dto.Box;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoxService {

    public List<Box> createListOfBoxes(List<String> idList){
        List<Box> boxList = new ArrayList<>(idList.size());

        idList.forEach(id -> boxList.add(createBox(id)));

        return boxList;
    }

    public Box parseBox(Box box){
        Map<Character, Integer> idMap = new HashMap<>();
        String id = box.getId();

        for(char  c: id.toCharArray()){
            if(idMap.containsKey(c)){
               Integer num = idMap.get(c);
               idMap.put(c, ++num);
            }else{
                idMap.put(c, 1);
            }
        }

        box.setHasTwoChars(idMap.values().contains(2));
        box.setHasThreeChars(idMap.values().contains(3));

        return box;
    }

    public Box createBox(String boxId){
        Box box = new Box(boxId);
        return parseBox(box);
    }

}
