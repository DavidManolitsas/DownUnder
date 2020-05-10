package com.cc.downunder.model;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-05-09
 */
public enum Month {
    JAN("January", "01"),
    FEB("February", "02"),
    MAR("March", "03"),
    APR("April", "04"),
    MAY("May", "05"),
    JUN("June", "06"),
    JUL("July", "07"),
    AUG("August", "08"),
    SEP("September", "09"),
    OCT("October", "10"),
    NOV("November", "11"),
    DEC("December", "12");

    private final String name;
    private final String monthNum;

    Month(String name, String monthNum) {
        this.name = name;
        this.monthNum = monthNum;
    }

    public String getName() {
        return name;
    }

    public String getMonthNum() {
        return monthNum;
    }
}
