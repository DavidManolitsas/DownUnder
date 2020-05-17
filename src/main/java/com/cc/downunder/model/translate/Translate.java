package com.cc.downunder.model.translate;

import com.google.cloud.translate.v3.*;

import java.io.IOException;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-05-17
 */
public class Translate {
    private final String projectID = "s3763636-myapi";

    public Translate() {

    }

    public String translateText(String targetLanguage, String text) throws IOException {
        try (TranslationServiceClient client = TranslationServiceClient.create()) {
            // Supported Locations: `global`, [glossary location], or [model location]
            // Glossaries must be hosted in `us-central1`
            // Custom Models must use the same location as your model. (us-central1)
            LocationName parent = LocationName.of(projectID, "global");

            // Supported Mime Types: https://cloud.google.com/translate/docs/supported-formats
            TranslateTextRequest request =
                    TranslateTextRequest.newBuilder()
                                        .setParent(parent.toString())
                                        .setMimeType("text/plain")
                                        .setTargetLanguageCode(targetLanguage)
                                        .addContents(text)
                                        .build();

            TranslateTextResponse response = client.translateText(request);

            // Display the translation for each input text provided
            for (Translation translation : response.getTranslationsList()) {
                return translation.getTranslatedText();
            }
        }

        return "";
    }
}
