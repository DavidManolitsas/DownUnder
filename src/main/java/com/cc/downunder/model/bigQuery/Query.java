package com.cc.downunder.model.bigQuery;

import com.google.cloud.bigquery.*;

import java.math.MathContext;
import java.math.RoundingMode;

public class Query {
    BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
    int count = 0;

    public String queryTable(String query, String destinationDataset, String destinationTable) throws InterruptedException {
//        BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
        QueryJobConfiguration queryConfig =
                QueryJobConfiguration.newBuilder(query)
                        // Save the results of the query to a permanent table.
                        .setDestinationTable(TableId.of(destinationDataset, destinationTable))
                        .build();

// Print the results.
//
        MathContext mc = new MathContext(0);
        for (FieldValueList row : bigquery.query(queryConfig).iterateAll()) {
            for (FieldValue val : row) {
                return val.getNumericValue().setScale(0, RoundingMode.UP).toString();
//               String string = results.toString();
//                System.out.printf("%s,", val.toString());
            }
//            System.out.printf("\n");
        }

        return " ";
    }

    public void deleteTable(String tableName) {
        String projectID = "s3763636-myapi";
        String datasetName = "australia";
        TableId tableId = TableId.of(projectID, datasetName, tableName);
        boolean deleted = bigquery.delete(tableId);
        if (deleted) {
            // the table was deleted
        } else {
            // the table was not found
        }
    }

    /**
     * @param state eg "VIC", "NSW"
     * @param mm    eg. "01", "09"
     * @throws InterruptedException
     */
    public String queryAverages(String state, String mm) throws InterruptedException {
        String query = "SELECT AVG(CAST(stateCell.value AS NUMERIC)) as average_num_visitors_" + state + " "
                + "FROM australia.visitors, "
                + "UNNEST(state.column) AS stateCol, UNNEST(stateCol.cell) AS stateCell "
                + "WHERE stateCol.name = '" + state + "' "
                //TODO: can do date range later:
                // + " AND rowkey LIKE '201%' "
                + "AND rowkey LIKE '%" + mm + "'";

        String tableName = "table" + count++;
        String result = queryTable(query, "australia", tableName);
        deleteTable(tableName);
        return result;
    }

}
