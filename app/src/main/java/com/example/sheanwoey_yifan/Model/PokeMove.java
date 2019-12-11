package com.example.sheanwoey_yifan.Model;

import java.io.Serializable;

public class PokeMove implements Serializable {
    private String moveName;
    private String pp;
    private String accuracy;
    private String damageClass;
    private String element;
    private String power;
    private String lvl;

    public PokeMove() {
    }

    public PokeMove(String moveName, String pp, String accuracy, String damageClass, String element, String power, String lvl) {
        this.moveName = moveName;
        this.pp = pp;
        this.accuracy = accuracy;
        this.damageClass = damageClass;
        this.element = element;
        this.power = power;
        this.lvl = lvl;
    }

    public String getMoveName() {
        return moveName;
    }

    public void setMoveName(String moveName) {
        this.moveName = moveName;
    }

    public String getPp() {
        return pp;
    }

    public void setPp(String pp) {
        this.pp = pp;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getDamageClass() {
        return damageClass;
    }

    public void setDamageClass(String damageClass) {
        this.damageClass = damageClass;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getLvl() {
        return lvl;
    }

    public void setLvl(String lvl) {
        this.lvl = lvl;
    }

    @Override
    public String toString() {
        return "PokeMove{" +
                "moveName='" + moveName + '\'' +
                ", pp='" + pp + '\'' +
                ", accuracy='" + accuracy + '\'' +
                ", damageClass='" + damageClass + '\'' +
                ", element='" + element + '\'' +
                ", power='" + power + '\'' +
                ", lvl=" + lvl +
                '}';
    }
}
