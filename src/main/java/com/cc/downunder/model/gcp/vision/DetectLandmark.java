package com.cc.downunder.model.gcp.vision;

import com.cc.downunder.model.LanguageFilter;
import com.cc.downunder.model.gcp.GoogleCloudAccount;
import com.cc.downunder.model.gcp.vision.storage.Bucket;
import com.google.cloud.vision.v1.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Jessica Cui
 * @date: 2020/05/23
 * @project: Down under
 * IEEE reference: [14]
 */

public class DetectLandmark {

    private StringBuilder landmarkLocation;
    private final double NORTH_LATITUDE = -10.170768;
    private final double EAST_LONGITUDE = 154.127632;
    private final double SOUTH_LATITUDE = -44.105765;
    private final double WEST_LONGITUDE = 112.665803;


    public DetectLandmark() {
        this.landmarkLocation = new StringBuilder();
    }

    public void identifyLandmark(MultipartFile file) {
        String filePath = saveFile(file);

        Bucket bucket = new Bucket();
        bucket.uploadObject(GoogleCloudAccount.PROJECT_ID, GoogleCloudAccount.VISION_BUCKET_NAME,
                            file.getOriginalFilename(), filePath);

        String gcsPath = "gs://" + GoogleCloudAccount.VISION_BUCKET_NAME + "/" + file.getOriginalFilename();
        analyseImage(gcsPath);
        deleteFile(filePath);
    }

    public void deleteFile(String filePath) {
        try {
            Files.deleteIfExists(Paths.get(filePath));
        }  catch(NoSuchFileException e)
        {
            System.out.println("No such file/directory exists");
        }
        catch(DirectoryNotEmptyException e)
        {
            System.out.println("Directory is not empty.");
        }
        catch(IOException e)
        {
            System.out.println("Invalid permissions.");
        }

        System.out.println("Deletion successful.");
    }

    public void analyseImage(String gcsPath) {
        List<AnnotateImageRequest> requests = new ArrayList<>();
        // empty string builder
        landmarkLocation.setLength(0);

        ImageSource imgSource = ImageSource.newBuilder().setGcsImageUri(gcsPath).build();
        Image img = Image.newBuilder().setSource(imgSource).build();
        Feature feat = Feature.newBuilder().setType(Feature.Type.LANDMARK_DETECTION).build();
        AnnotateImageRequest request =
                AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
        requests.add(request);

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            String landmark = "";
            String location = "";
            double latitude = 0;
            double longitude = 0;

            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    System.out.printf("Error: %s\n", res.getError().getMessage());
                    return;
                }

                int count = 0;

                // For full list of available annotations, see http://g.co/cloud/vision/docs
                for (EntityAnnotation annotation : res.getLandmarkAnnotationsList()) {
                    LocationInfo info = annotation.getLocationsList().listIterator().next();
                    String description = annotation.getDescription();

                    latitude = info.getLatLng().getLatitude();
                    longitude = info.getLatLng().getLongitude();

                    if (count == 0) {
                        landmark = description;
                    } else if (count == 1) {
                        if (!landmark.equalsIgnoreCase(description)) {
                            location = description;
                        }
                    }
                    count++;
                }
            }

            setImageLocationDescription(landmark, location, latitude, longitude);

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }


    public void setImageLocationDescription(String landmark, String location, double latitude, double longitude) {
        if (!isAustralia(latitude, longitude)) {
            landmarkLocation.append("The picture you uploaded is not a location in Australia");
        } else if (location.trim().isEmpty()) {
            landmarkLocation.append("The picture you uploaded is a picture of ").append(landmark);
        } else {
            landmarkLocation.append("The picture you uploaded is a picture of ").append(landmark).append(" located in ")
                            .append(location);
        }

    }

    public String getImageLocationDescription() {
        LanguageFilter filter = LanguageFilter.getInstance();
        if (landmarkLocation.length() != 0) {
            return filter.translateText(landmarkLocation.toString());
        }
        return "";
    }

    private String saveFile(MultipartFile file) {
        String finalPath = GoogleCloudAccount.UPLOAD_PATH + file.getOriginalFilename();
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(finalPath);
            Files.write(path, bytes);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return finalPath;
    }

    private boolean isAustralia(double latitude, double longitude) {
        // north and south boundary
        if (latitude > NORTH_LATITUDE || latitude < SOUTH_LATITUDE) {
            return false;
        }
        // east and west boundary
        else return !(longitude > EAST_LONGITUDE) && !(longitude < WEST_LONGITUDE);
    }
}
