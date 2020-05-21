package com.cc.downunder.model.gcp.bigQuery;

import com.cc.downunder.model.gcp.GoogleCloudAccount;
import com.google.cloud.bigquery.*;

import java.math.RoundingMode;

public class BigQuery {
    com.google.cloud.bigquery.BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();

    public String queryTable(String query, String destinationDataset, String destinationTable) throws InterruptedException {
        QueryJobConfiguration queryConfig =
                QueryJobConfiguration.newBuilder(query)
                        // Save the results of the query to a permanent table.
                        .setDestinationTable(TableId.of(destinationDataset, destinationTable))
                        .build();

        // TODO refactor the loop
        for (FieldValueList row : bigquery.query(queryConfig).iterateAll()) {
            for (FieldValue val : row) {
                return val.getNumericValue().setScale(0, RoundingMode.UP).toString();
            }
        }

        return " ";
    }

    public void deleteTable(String tableName) {
        String datasetName = "australia";
        TableId tableId = TableId.of(GoogleCloudAccount.PROJECT_ID, datasetName, tableName);
        boolean deleted = bigquery.delete(tableId);
        if (deleted) {
            // the table was deleted
            System.out.println(tableName + " was deleted");
        } else {
            // the table was not found
            System.out.println("no, " + tableName + " was not deleted");
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

        String tableName = "averageMontlyTable";
        String result = queryTable(query, "australia", tableName);
        deleteTable(tableName);
        return result;
    }

    public String queryYearTotal(String state, String yyyy) throws InterruptedException {
        String query = "SELECT  CAST(stateCell.value AS NUMERIC) total_num_visitors_" + state + " "
                + "FROM australia.visitors, "
                + "UNNEST(state.column) AS stateCol, UNNEST(stateCol.cell) AS stateCell "
                + "WHERE stateCol.name = '" + state + "' "
                + "AND rowkey LIKE '" + yyyy + "%'";

        String tableName = "totalYearTable";
        String result = queryTable(query, "australia", tableName);
        deleteTable(tableName);
        return result;
    }

}
