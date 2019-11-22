package com.example.sheanwoey_yifan.Model;

import java.util.Arrays;

public class PokeList {

    public String name;
    public String icon;
    public String[] type;

    public PokeList(String name, String icon, String[] type) {
        this.name = name;
        this.icon = icon;
        this.type = type;
    }

    public PokeList() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PokeList{" +
                "name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", type=" + Arrays.toString(type) +
                '}';
    }
}
