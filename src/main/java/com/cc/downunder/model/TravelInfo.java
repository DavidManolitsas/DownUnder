package com.cc.downunder.model;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-05-09
 */
public class TravelInfo {

    private String stateName;
    private Month travelMonth;

    public TravelInfo(String stateName) {
        this.stateName = stateName;
    }


    public String showTravelInfo() {
        if (travelMonth != null) {
            return "Ahh great choice! " + travelMonth.getName() + " is a perfect time to visit " + stateName;
        }
        return "";
    }

    public Month getTravelMonth() {
        return travelMonth;
    }

    public void setTravelMonth(Month travelMonth) {
        this.travelMonth = travelMonth;
    }
}
