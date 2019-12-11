package com.example.sheanwoey_yifan.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class PokeDetail implements Serializable {

    private int id;
    private String name;
    private String[] types;
    private String sprite;
    private String description;
    private ArrayList<PokeMove> pokeMoves;
    private ArrayList<PokeEvolution> pokeEvolutions;
    private int baseHp,baseDef,baseAtk,baseSpd,baseSpAtk,baseSpDef;

    public PokeDetail() {
    }

    public PokeDetail(int id, String name, String[] types, String sprite, String description, ArrayList<PokeMove> pokeMoves, ArrayList<PokeEvolution> pokeEvolutions, int baseHp, int baseDef, int baseAtk, int baseSpd, int baseSpAtk, int baseSpDef) {
        this.id = id;
        this.name = name;
        this.types = types;
        this.sprite = sprite;
        this.description = description;
        this.pokeMoves = pokeMoves;
        this.pokeEvolutions = pokeEvolutions;
        this.baseHp = baseHp;
        this.baseDef = baseDef;
        this.baseAtk = baseAtk;
        this.baseSpd = baseSpd;
        this.baseSpAtk = baseSpAtk;
        this.baseSpDef = baseSpDef;
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

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<PokeMove> getPokeMoves() {
        return pokeMoves;
    }

    public void setPokeMoves(ArrayList<PokeMove> pokeMoves) {
        this.pokeMoves = pokeMoves;
    }

    public ArrayList<PokeEvolution> getPokeEvolutions() {
        return pokeEvolutions;
    }

    public void setPokeEvolutions(ArrayList<PokeEvolution> pokeEvolutions) {
        this.pokeEvolutions = pokeEvolutions;
    }

    public int getBaseHp() {
        return baseHp;
    }

    public void setBaseHp(int baseHp) {
        this.baseHp = baseHp;
    }

    public int getBaseDef() {
        return baseDef;
    }

    public void setBaseDef(int baseDef) {
        this.baseDef = baseDef;
    }

    public int getBaseAtk() {
        return baseAtk;
    }

    public void setBaseAtk(int baseAtk) {
        this.baseAtk = baseAtk;
    }

    public int getBaseSpd() {
        return baseSpd;
    }

    public void setBaseSpd(int baseSpd) {
        this.baseSpd = baseSpd;
    }

    public int getBaseSpAtk() {
        return baseSpAtk;
    }

    public void setBaseSpAtk(int baseSpAtk) {
        this.baseSpAtk = baseSpAtk;
    }

    public int getBaseSpDef() {
        return baseSpDef;
    }

    public void setBaseSpDef(int baseSpDef) {
        this.baseSpDef = baseSpDef;
    }

    @Override
    public String toString() {
        return "PokeDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", types=" + Arrays.toString(types) +
                ", sprite='" + sprite + '\'' +
                ", description='" + description + '\'' +
                ", pokeMoves=" + pokeMoves +
                ", pokeEvolutions=" + pokeEvolutions +
                ", baseHp=" + baseHp +
                ", baseDef=" + baseDef +
                ", baseAtk=" + baseAtk +
                ", baseSpd=" + baseSpd +
                ", baseSpAtk=" + baseSpAtk +
                ", baseSpDef=" + baseSpDef +
                '}';
    }
}