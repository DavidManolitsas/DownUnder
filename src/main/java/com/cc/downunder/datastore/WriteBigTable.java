package com.cc.downunder.datastore;

public class WriteBigTable {
    private static final String COLUMN_FAMILY_NAME = "cf1";

    //    public static void main(String[] args) {
//        writeSimple("s3763636-myapi","cc2020", "my-table");
//    }

    private String columnFamily;
    private String columnQualifier;
    private String rowKey;

    public static void main(String[] args) throws Exception {
        String projectId = "s3763636-myapi";
        String instanceId = "cc2020";
        String tableName = "new-table";

//        HelloWorld helloWorld = new HelloWorld(projectId, instanceId, tableName);
//        helloWorld.run();
    }

    public WriteBigTable(String columnFamily, String rowKey, String columnQualifier) {
        this.columnFamily = columnFamily;
        this.rowKey = rowKey;
        this.columnQualifier = columnQualifier;
    }

    public String getColumnFamily() {
        return columnFamily;
    }

    public String getColumnQualifier() {
        return columnQualifier;
    }

    public String getRowKey() {
        return rowKey;
    }
}
