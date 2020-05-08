package com.cc.downunder.bigTable;

/**
 * @Author: Jessica Cui
 * @Project: Cloud Computing
 * @Date: 2020/05/02
 */

import com.google.api.gax.rpc.NotFoundException;
import com.google.api.gax.rpc.ServerStream;
import com.google.cloud.bigtable.admin.v2.BigtableTableAdminClient;
import com.google.cloud.bigtable.admin.v2.BigtableTableAdminSettings;
import com.google.cloud.bigtable.admin.v2.models.CreateTableRequest;
import com.google.cloud.bigtable.data.v2.BigtableDataClient;
import com.google.cloud.bigtable.data.v2.BigtableDataSettings;
import com.google.cloud.bigtable.data.v2.models.Query;
import com.google.cloud.bigtable.data.v2.models.Row;
import com.google.cloud.bigtable.data.v2.models.RowCell;
import com.google.cloud.bigtable.data.v2.models.RowMutation;

import java.io.IOException;
import java.util.Map;

// https://github.com/GoogleCloudPlatform/java-docs-samples/blob/master/bigtable/src/main/java/com/m/examples/bigtable/HelloWorld.java
public class BigTable {
    private final String tableId;
    private final BigtableDataClient dataClient;
    private final BigtableTableAdminClient adminClient;

    private String columnFamily;
    private String columnQualifier;
    private Map<String, Double> map;

    public BigTable(String projectId, String instanceId, String tableId, String columnFamily, String columnQualifier, Map<String, Double> map) throws IOException {
        this.tableId = tableId;
        this.map = map;
        this.columnFamily = columnFamily;
        this.columnQualifier = columnQualifier;


        // [START bigtable_hw_connect_veneer]
        // Creates the settings to configure a bigtable data client.
        BigtableDataSettings settings =
                BigtableDataSettings.newBuilder().setProjectId(projectId).setInstanceId(instanceId).build();

        // Creates a bigtable data client.
        dataClient = BigtableDataClient.create(settings);

        // Creates the settings to configure a bigtable table admin client.
        BigtableTableAdminSettings adminSettings =
                BigtableTableAdminSettings.newBuilder()
                        .setProjectId(projectId)
                        .setInstanceId(instanceId)
                        .build();

        // Creates a bigtable table admin client.
        adminClient = BigtableTableAdminClient.create(adminSettings);
        // [END bigtable_hw_connect_veneer]
    }


    public void run() throws Exception {
        createTable();
        writeToTable();
//////        readSingleRow();
        readTable();
//        deleteTable();
        dataClient.close();
        adminClient.close();
    }

    /**
     * Demonstrates how to create a table.
     */
    public void createTable() {
        // [START bigtable_hw_create_table_veneer]
        // Checks if table exists, creates table if does not exist.
        if (!adminClient.exists(tableId)) {
            System.out.println("Creating table: " + tableId);
            CreateTableRequest createTableRequest =
                    CreateTableRequest.of(tableId).addFamily(columnFamily);
            adminClient.createTable(createTableRequest);
            System.out.printf("Table %s created successfully%n", tableId);
        }
        // [END bigtable_hw_create_table_veneer]
    }

    /**
     * Demonstrates how to write some rows to a table.
     */
    public void writeToTable() {
        // [START bigtable_hw_write_rows_veneer]
        try {
            for (Map.Entry<String, Double> entry : map.entrySet()) {
                String key = entry.getKey();
                Double value = entry.getValue();
                RowMutation rowMutation =
                        RowMutation.create(tableId, key)
                                .setCell(columnFamily, columnQualifier, Double.toString(value));
                dataClient.mutateRow(rowMutation);
            }
        } catch (NotFoundException e) {
            System.err.println("Failed to write to non-existent table: " + e.getMessage());
        }
        // [END bigtable_hw_write_rows_veneer]
    }

    /**
     * Demonstrates how to read a single row from a table.
     */
//    public void readSingleRow() {
//        // [START bigtable_hw_get_by_key_veneer]
//        try {
//            System.out.println("\nReading a single row by row key");
//            Row row = dataClient.readRow(tableId, ROW_KEY_PREFIX + 0);
//            System.out.println("Row: " + row.getKey().toStringUtf8());
//            for (RowCell cell : row.getCells()) {
//                System.out.printf(
//                        "Family: %s    Qualifier: %s    Value: %s%n",
//                        cell.getFamily(), cell.getQualifier().toStringUtf8(), cell.getValue().toStringUtf8());
//            }
//        } catch (NotFoundException e) {
//            System.err.println("Failed to read from a non-existent table: " + e.getMessage());
//        }
//        // [END bigtable_hw_get_by_key_veneer]
//    }

    /**
     * Demonstrates how to read an entire table.
     */
    public void readTable() {
        // [START bigtable_hw_scan_all_veneer]
        try {
            System.out.println("\nReading the entire table");
            Query query = Query.create(tableId);
            ServerStream<Row> rowStream = dataClient.readRows(query);
            for (Row r : rowStream) {
                System.out.println("Row Key: " + r.getKey().toStringUtf8());
                for (RowCell cell : r.getCells()) {
                    System.out.printf(
                            "Family: %s    Qualifier: %s    Value: %s%n",
                            cell.getFamily(), cell.getQualifier().toStringUtf8(), cell.getValue().toStringUtf8());
                }
            }
        } catch (NotFoundException e) {
            System.err.println("Failed to read a non-existent table: " + e.getMessage());
        }
        // [END bigtable_hw_scan_all_veneer]
    }

    /**
     * Demonstrates how to delete a table.
     */
    public void deleteTable() {
        // [START bigtable_hw_delete_table_veneer]
        System.out.println("\nDeleting table: " + tableId);
        try {
            adminClient.deleteTable(tableId);
            System.out.printf("Table %s deleted successfully%n", tableId);
        } catch (NotFoundException e) {
            System.err.println("Failed to delete a non-existent table: " + e.getMessage());
        }
        // [END bigtable_hw_delete_table_veneer]
    }
}
