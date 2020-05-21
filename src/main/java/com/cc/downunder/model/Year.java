package com.cc.downunder.model;

import java.util.ArrayList;
import java.util.List;

public class Year {
    private List<String> travelYearList;

    public Year() {
        generateYearList();
    }
    public void generateYearList() {
        int year = 1991;
        travelYearList = new ArrayList<>();
        do {
            travelYearList.add(Integer.toString(year++));
        } while (year < 2021);
    }

    public List<String> getYearList() {
        return travelYearList;
    }
}
