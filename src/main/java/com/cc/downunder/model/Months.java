package com.cc.downunder.model;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-05-09
 */
public enum Months {
    JAN("January"),
    FEB("February"),
    MAR("March"),
    APR("April"),
    MAY("May"),
    JUN("June"),
    JUL("July"),
    AUG("August"),
    SEP("September"),
    OCT("October"),
    NOV("November"),
    DEC("December");

    private final String name;

    Months(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
