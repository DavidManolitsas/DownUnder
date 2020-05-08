package com.cc.downunder.bigQuery;

import com.google.cloud.bigquery.*;

public class Query {
    public static void main(String[] args) throws InterruptedException {
        queryAverages("VIC", "01");
    }

    public static void queryTable(String query, String destinationDataset, String destinationTable) throws InterruptedException {
        BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
        QueryJobConfiguration queryConfig =
                QueryJobConfiguration.newBuilder(query)
                        // Save the results of the query to a permanent table.
                        .setDestinationTable(TableId.of(destinationDataset, destinationTable))
                        .build();

// Print the results.
        for (FieldValueList row : bigquery.query(queryConfig).iterateAll()) {
            for (FieldValue val : row) {
                System.out.printf("%s,", val.toString());
            }
            System.out.printf("\n");
        }
    }

    public static void queryAverages(String state, String mm) throws InterruptedException {
        String query = "SELECT AVG(CAST(stateCell.value AS NUMERIC)) as average_num_visitors_" + state + " "
                + "FROM australia.visitors, "
                + "UNNEST(state.column) AS stateCol, UNNEST(stateCol.cell) AS stateCell "
                + "WHERE stateCol.name = '" + state + "' "
                //TODO: can do date range later:
                // + " AND rowkey LIKE '201%' "
                + "AND rowkey LIKE '%" + mm + "'";

        queryTable(query, "australia", "destinationTable");
    }

}
