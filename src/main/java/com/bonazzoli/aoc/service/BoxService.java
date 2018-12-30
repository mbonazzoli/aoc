package com.bonazzoli.aoc.service;

import com.bonazzoli.aoc.dto.Box;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public Optional<String> getMatchFromSimilarities(Box b1, Box b2) {
        int diffs = 0;
        String s1 = b1.getId();
        String s2 = b2.getId();
        char[] one = s1.toCharArray();
        char[] two = s2.toCharArray();
        for(int i = 0;i <s1.length();i++){
            if(one[i] != two[i]){
                diffs++;
                b1.setMatch(s1.substring(0, i)+s1.substring(i+1));
            }
            if(diffs > 1 || s1.equals(s2)){
                return Optional.empty();
            }
        }
            return Optional.of(b1.getMatch());


    }
}
