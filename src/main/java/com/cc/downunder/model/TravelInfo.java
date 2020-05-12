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
     *
     * @return
     * @throws InterruptedException
     */
    public String showTravelInfo() throws InterruptedException {
        try {
            if (travelMonth != null) {
                int month = Integer.parseInt(travelMonth.getMonthNum());
                if (month <= 2 || month == 12) {
                    //summer
                    return "Nothing better than an Australian Summer! " + travelMonth.getName() +
                            " is a perfect time to visit " + stateCapitalCity + getStateMonthVisitorAverage();
                } else if (month < 6) {
                    //autumn
                    return "You just can't beat the colours of Autumn! " + travelMonth.getName() +
                            " is a very popular time to visit " + stateCapitalCity + getStateMonthVisitorAverage();
                } else if (month < 9) {
                    //winter
                    return "Brrr! Winter is Coming. " + travelMonth.getName() +
                            " is a great time to explore the Australian Winter activities in " + stateCapitalCity +
                            getStateMonthVisitorAverage();
                } else if (month < 12) {
                    //spring
                    return "Spring. The season of rejuvenation. Some would argue there is not better time to visit " +
                            stateCapitalCity + getStateMonthVisitorAverage();
                }
            }
        } catch (Exception e) {
            return "Sorry we can't retrieve the travel info for " + stateCapitalCity + " at the moment";
        }
        return "";
    }

    public String getStateMonthVisitorAverage() throws InterruptedException {
        Query query = new Query();
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
