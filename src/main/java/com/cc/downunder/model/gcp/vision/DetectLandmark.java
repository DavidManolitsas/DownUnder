package com.cc.downunder.model.gcp.vision;

import com.cc.downunder.model.LanguageFilter;
import com.cc.downunder.model.gcp.GoogleCloudAccount;
import com.cc.downunder.model.gcp.vision.storage.UploadToBucket;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.ImageSource;
import com.google.cloud.vision.v1.LocationInfo;
import com.google.type.LatLng;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class DetectLandmark {

    private StringBuilder sb = new StringBuilder();
    private String landmarkName, landmarkLocation;

    public void identifyLandmark(MultipartFile file) {
        String filePath = saveFile(file);

        UploadToBucket bucket = new UploadToBucket();
        bucket.uploadObject(GoogleCloudAccount.PROJECT_ID, GoogleCloudAccount.VISION_BUCKET_NAME,
                            file.getOriginalFilename(), filePath);

        String gcsPath = "gs://" + GoogleCloudAccount.VISION_BUCKET_NAME + "/" + file.getOriginalFilename();
        analyseImage(gcsPath);

    }

    public void analyseImage(String gcsPath) {
        List<AnnotateImageRequest> requests = new ArrayList<>();
        sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

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
                    System.out.printf("Error: %s\n", res.getError().getMessage());
                    return;
                }

                // For full list of available annotations, see http://g.co/cloud/vision/docs
                for (EntityAnnotation annotation : res.getLandmarkAnnotationsList()) {
                    LocationInfo info = annotation.getLocationsList().listIterator().next();
                     setImageLocationDesc(annotation.getDescription(), info.getLatLng());

                }
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public void setImageLocationDesc(String landmarkLocation, LatLng latLng) {
//         sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        sb.append(formatter.format("Landmark: %s\n %s\n", landmarkLocation, latLng));
    }

    public String getImageLocationDesc() {
        LanguageFilter filter = LanguageFilter.getInstance();
        return sb.toString();
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
