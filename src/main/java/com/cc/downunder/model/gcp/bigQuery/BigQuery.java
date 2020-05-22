package com.cc.downunder.model.gcp.bigQuery;

import com.cc.downunder.model.gcp.GoogleCloudAccount;
import com.google.cloud.bigquery.*;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class BigQuery {
    com.google.cloud.bigquery.BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
    private List<String> resultsList = new ArrayList<>();

//    public String queryTable(String query, String destinationDataset, String destinationTable) throws InterruptedException {
//        QueryJobConfiguration queryConfig =
//                QueryJobConfiguration.newBuilder(query)
//                        // Save the results of the query to a permanent table.
//                        .setDestinationTable(TableId.of(destinationDataset, destinationTable))
//                        .build();
//
//        // TODO refactor the loop
//        for (FieldValueList row : bigquery.query(queryConfig).iterateAll()) {
//            for (FieldValue val : row) {
//                resultsList.add(val.toString())
//            }
//        }
//
//        return " ";
//    }

    public void query(String query, String destinationDataset, String destinationTable) throws InterruptedException {
        QueryJobConfiguration queryConfig =
                QueryJobConfiguration.newBuilder(query)
                        // Save the results of the query to a permanent table.
                        .setDestinationTable(TableId.of(destinationDataset, destinationTable))
                        .build();


        for (FieldValueList row : bigquery.query(queryConfig).iterateAll()) {
            for (FieldValue val : row) {
                resultsList.add(val.getNumericValue().setScale(0, RoundingMode.UP).toString());
            }
        }

//        return " ";
    }

    public String getAverageResult() {
//        getNumericValue().setScale(0, RoundingMode.UP).toString();
        return resultsList.get(0);
    }

    public void deleteTable(String tableName) {
        String datasetName = "australia";
        TableId tableId = TableId.of(GoogleCloudAccount.PROJECT_ID, datasetName, tableName);
        boolean deleted = bigquery.delete(tableId);
        if (deleted) {
            // used for testing if the table was deleted
//            System.out.println(tableName + " was deleted");
        } else {
            // used for testing if the table was not found
//            System.out.println("no, " + tableName + " was not deleted");
        }
    }

    /**
     * Query average number of visitors from 2000 up to 2020 for a specified month
     * @param state eg "VIC", "NSW"
     * @param mm    eg. "01", "09"
     * @throws InterruptedException
     */
    public String queryAverages(String state, String mm) throws InterruptedException {
        String query = "SELECT AVG(CAST(stateCell.value AS NUMERIC)) as average_num_visitors_" + state + " "
                + "FROM australia.visitors, "
                + "UNNEST(state.column) AS stateCol, UNNEST(stateCol.cell) AS stateCell "
                + "WHERE stateCol.name = '" + state + "' "
                + "AND rowkey LIKE '%" + mm + "'  AND rowkey LIKE '20%'";


        String tableName = "averageMonthlyTable";
//        String result = queryTable(query, "australia", tableName);
        query(query, "australia", tableName);
        deleteTable(tableName);
//        getAverageResult();
        return getAverageResult();
    }

    public List<String> queryYearTotal(String state, String yyyy) throws InterruptedException {
        String query = "SELECT  CAST(stateCell.value AS NUMERIC) total_num_visitors_" + state + " "
                + "FROM australia.visitors, "
                + "UNNEST(state.column) AS stateCol, UNNEST(stateCol.cell) AS stateCell "
                + "WHERE stateCol.name = '" + state + "' "
                + "AND rowkey LIKE '" + yyyy + "%' "
                + "ORDER BY rowkey asc";

        String tableName = "totalYearTable";
        resultsList.add(yyyy);
        query(query, "australia", tableName);
        deleteTable(tableName);
        return resultsList;
    }



}
