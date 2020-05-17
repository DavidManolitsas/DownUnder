package com.cc.downunder.model.vision;

import com.google.cloud.vision.v1.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DetectLandmark {

    //TODO: change to your own
    String projectID = "s3763636-myapi";
    String bucketName = "s3763636-myapi.appspot.com";


    public void identifyLandmark(MultipartFile file) {
        String filePath = saveFile(file);

        UploadToBucket bucket = new UploadToBucket();
        bucket.uploadObject(projectID, bucketName, file.getOriginalFilename(), filePath);

        String gcsPath = "gs://" + bucketName + "/" + file.getOriginalFilename();
        analyseImage(gcsPath, System.out);

    }


    public void analyseImage(String gcsPath, PrintStream out) {
        List<AnnotateImageRequest> requests = new ArrayList<>();

        ImageSource imgSource = ImageSource.newBuilder().setGcsImageUri(gcsPath).build();
        Image img = Image.newBuilder().setSource(imgSource).build();
        Feature feat = Feature.newBuilder().setType(Feature.Type.LANDMARK_DETECTION).build();
        AnnotateImageRequest request =
                AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
        requests.add(request);

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    out.printf("Error: %s\n", res.getError().getMessage());
                    return;
                }

                // For full list of available annotations, see http://g.co/cloud/vision/docs
                for (EntityAnnotation annotation : res.getLandmarkAnnotationsList()) {
                    LocationInfo info = annotation.getLocationsList().listIterator().next();
                    out.printf("Landmark: %s\n %s\n", annotation.getDescription(), info.getLatLng());
                }
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
    private void analyseImage() {

    }
    private String saveFile(MultipartFile file) {
        //TODO: change to your own path
        String baseDir = "/Users/Jess/Documents/Master of IT/Workspace/DownUnder/src/main/resources/static/uploads/";
        String finalPath = baseDir + file.getOriginalFilename();
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(finalPath);
            Files.write(path, bytes);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return finalPath;
    }
}
