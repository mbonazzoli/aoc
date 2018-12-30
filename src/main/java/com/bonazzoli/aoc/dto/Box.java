package com.bonazzoli.aoc.dto;

import java.util.Objects;


public class Box {

    private String id;
    private Boolean hasTwoChars;
    private Boolean hasThreeChars;
    private String match;

    public Box(){}

    public Box(String id) {
        this.id = id;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getHasTwoChars() {
        return hasTwoChars;
    }

    public void setHasTwoChars(Boolean hasTwoChars) {
        this.hasTwoChars = hasTwoChars;
    }

    public Boolean getHasThreeChars() {
        return hasThreeChars;
    }

    public void setHasThreeChars(Boolean hasThreeChars) {
        this.hasThreeChars = hasThreeChars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return Objects.equals(id, box.id) &&
                Objects.equals(hasTwoChars, box.hasTwoChars) &&
                Objects.equals(hasThreeChars, box.hasThreeChars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hasTwoChars, hasThreeChars);
    }

    @Override
    public String toString() {
        return "Box{" +
                "id='" + id + '\'' +
                ", hasTwoChars=" + hasTwoChars +
                ", hasThreeChars=" + hasThreeChars +
                '}';
    }
}
