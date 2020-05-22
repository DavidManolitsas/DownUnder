package com.cc.downunder.model;

import com.cc.downunder.model.gcp.bigQuery.BigQuery;

import java.util.List;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-05-09
 */
public class TravelInfo {

    private String stateCapitalCity;
    private String stateName;
    private Month travelMonth;
    private String travelYear;
    private String[] yearlyList = new String[13];



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
        LanguageFilter filter = LanguageFilter.getInstance();

        try {
            if (travelMonth != null) {
                int month = Integer.parseInt(travelMonth.getMonthNum());
                if (month <= 2 || month == 12) {
                    //summer
                    return filter.translateText("Nothing better than an Australian Summer! " + travelMonth.getName() +
                                                        " is a perfect time to visit " + stateCapitalCity +
                                                        getStateMonthVisitorAverage());
                } else if (month < 6) {
                    //autumn
                    return filter.translateText("You just can't beat the colours of Autumn! " + travelMonth.getName() +
                                                        " is a very popular time to visit " + stateCapitalCity +
                                                        getStateMonthVisitorAverage());
                } else if (month < 9) {
                    //winter
                    return filter.translateText("Brrr! Winter is Coming. " + travelMonth.getName() +
                            " is a great time to explore the Australian Winter activities in " + stateCapitalCity +
                                                        getStateMonthVisitorAverage());
                } else if (month < 12) {
                    //spring
                    return filter.translateText(
                            "Spring. The season of rejuvenation. Some would argue there is not better time to visit " +
                                    stateCapitalCity + getStateMonthVisitorAverage());
                }
            }
        } catch (Exception e) {
            return filter.translateText(
                    "Sorry we can't retrieve the travel info for " + stateCapitalCity + " at the moment");
        }
        return "";
    }

    public String getStateMonthVisitorAverage() throws InterruptedException {
        BigQuery query = new BigQuery();
        return ".\n If you visit during " + travelMonth.getName() + ", you just might be exploring alongside " + query.queryAverages(stateName, travelMonth.getMonthNum()) + " other international visitors!";
    }

    public void getStateYearlyVisitors() {
        BigQuery query = new BigQuery();
//        String[] yearArray = new String[12];
        System.out.println("hello#####################");
        try {
            List<String> results = query.queryYearTotal(stateName, travelYear);
            System.out.println("results list size: " + results.size());
            yearlyList = new String[results.size()];
            yearlyList = results.toArray(yearlyList);
            System.out.println("year array size: " + yearlyList.length);
           for (String i: yearlyList) {
               System.out.println(i);
           }
            this.yearlyList = yearlyList;
            System.out.println("i dunno why");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public Month getTravelMonth() {
        return travelMonth;
    }

    public void setTravelMonth(Month travelMonth) {
        this.travelMonth = travelMonth;
    }

    public void setTravelYear(String travelYear) {
        this.travelYear = travelYear;
        getStateYearlyVisitors();
    }

    public String getTravelYear() {
        return travelYear;
    }

    public String getStateCapitalCity() {
        return stateCapitalCity;
    }

    public void setStateCapitalCity(String stateCapitalCity) {
        this.stateCapitalCity = stateCapitalCity;
    }

    public String[] getYearlyList() {
        if (yearlyList[0] == null) {
            yearlyList[0] = "Year not selected yet";
        }
        return yearlyList;
    }

    public void setYearlyList(String[] yearlyList) {
        this.yearlyList = yearlyList;
    }
}
