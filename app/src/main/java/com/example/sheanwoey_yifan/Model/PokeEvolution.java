package com.example.sheanwoey_yifan.Model;

import java.io.Serializable;

public class PokeEvolution implements Serializable {
    private int id;
    private String name;
    private String icon;

    public PokeEvolution(int id, String name, String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public PokeEvolution() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "PokeEvolution{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
