package com.cc.downunder.model;

import com.cc.downunder.model.bigQuery.Query;
/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-05-09
 */
public class TravelInfo {

    private String stateCapitalCity;
    private String stateName;
    private Month travelMonth;

    public TravelInfo(String stateCapitalCity) {
        this.stateCapitalCity = stateCapitalCity;
    }

    public TravelInfo(String stateCapitalCity, String stateName) {
        this.stateCapitalCity = stateCapitalCity;
        this.stateName = stateName;
    }

    /**
     * TODO handle exception
     *
     * @return
     * @throws InterruptedException
     */
    public String showTravelInfo() throws InterruptedException {
        if (travelMonth != null) {
            String result = "Ahh great choice! " + travelMonth.getName() + " is a perfect time to visit " + stateCapitalCity + getStateMonthVisitorAverage();
            return result;
        }
        return "";
    }

    public String getStateMonthVisitorAverage() throws InterruptedException {
        Query query = new Query();
//        String num = "07";
//        if (travelMonth.getName().equalsIgnoreCase("February")) {
//            num = "02";
//        }
//            int num1 = 01;
        return ".\n If you visit during " + travelMonth.getName() + ", you just might be exploring alongside " + query.queryAverages(stateName, travelMonth.getMonthNum()) + " other international visitors!";
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
