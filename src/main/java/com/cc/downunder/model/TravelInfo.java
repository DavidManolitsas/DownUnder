package com.cc.downunder.model;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-05-09
 */
public class TravelInfo {

    private String stateCapitalCity;
    private Month travelMonth;

    public TravelInfo(String stateName) {
        this.stateCapitalCity = stateName;
    }


    public String showTravelInfo() {
        if (travelMonth != null) {
            return "Ahh great choice! " + travelMonth.getName() + " is a perfect time to visit " + stateCapitalCity;
        }
        return "";
    }

    public Month getTravelMonth() {
        return travelMonth;
    }

    public void setTravelMonth(Month travelMonth) {
        this.travelMonth = travelMonth;
    }

    public String getStateCapitalCity() {
        return stateCapitalCity;
    }

    public void setStateCapitalCity(String stateCapitalCity) {
        this.stateCapitalCity = stateCapitalCity;
    }
}
