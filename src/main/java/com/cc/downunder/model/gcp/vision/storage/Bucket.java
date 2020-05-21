package com.cc.downunder.model.gcp.vision.storage;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.StorageOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 */
public class Bucket {

    public void uploadObject(
            String projectId, String bucketName, String objectName, String filePath) {
        try {
            com.google.cloud.storage.Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
            BlobId blobId = BlobId.of(bucketName, objectName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
            storage.create(blobInfo, Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        /**
         *  Print line used only for testing purposes to check if file was successfully uploaded
         */
//        System.out.println(
//                "File " + filePath + " uploaded to bucket " + bucketName + " as " + objectName);
    }

}
