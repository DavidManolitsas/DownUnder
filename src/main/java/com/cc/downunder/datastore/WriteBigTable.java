package com.cc.downunder.datastore;

public class WriteBigTable {
    private static final String COLUMN_FAMILY_NAME = "cf1";

    //    public static void main(String[] args) {
//        writeSimple("s3763636-myapi","cc2020", "my-table");
//    }
    public static final String COLUMN_FAMILY = "age";
    public static final String COLUMN_QUALIFIER = "greeting";
    public static final String ROW_KEY_PREFIX = "rowKey";


    public static void main(String[] args) throws Exception {

        String projectId = "s3763636-myapi";
        String instanceId = "cc2020";

        HelloWorld helloWorld = new HelloWorld(projectId, instanceId, "my-table");
        helloWorld.run();
    }


}
