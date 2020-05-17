package com.cc.downunder.model;

import com.cc.downunder.model.translate.Language;
import com.cc.downunder.model.translate.Translate;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-05-17
 */
public class LanguageFilter {

    private Translate translator = new Translate();
    private Language language;

    private static LanguageFilter instance;

    private LanguageFilter() {
        this.language = Language.ENGLISH;
    }

    public static LanguageFilter getInstance() {
        if (instance == null) {
            instance = new LanguageFilter();
        }
        return instance;
    }

    public String getHeading() {
        try {
            String heading = "Welcome to the Land Down Under!";

            return translator.translateText(language.getCode(), heading);
        } catch (Exception e) {
            System.err.println("Translation error");
        }
        return "";
    }

    public String getWelcomeMessage() {
        try {
            String welcomeMessage =
                    "so you've decided to travel down under? Don't worry we've got you covered. Click on a " +
                            "State or Territory to find out everything you need to know about travelling to all the " +
                            "major cities in Australia.";

            return translator.translateText(language.getCode(), welcomeMessage);
        } catch (Exception e) {
            System.err.println("Translation error");
        }
        return "";
    }

    public String getPopulationWord() {
        try {
            return translator.translateText(language.getCode(), "Population:");
        } catch (Exception e) {
            System.err.println("Translation error");
        }
        return "";
    }

    public String getCapitalCityWord() {
        try {
            return translator.translateText(language.getCode(), "Capital City: ");
        } catch (Exception e) {
            System.err.println("Translation error");
        }
        return "";
    }

    public String translateText(String text) {
        try {
            return translator.translateText(language.getCode(), text);
        } catch (Exception e) {
            System.err.println("Translation error");
        }
        return "";
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
