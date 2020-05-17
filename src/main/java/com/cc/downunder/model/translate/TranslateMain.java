package com.cc.downunder.model.translate;


/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-05-17
 * <p>
 * This class is for testing differn
 */
public class TranslateMain {

    public static void main(String[] args) {
        Translate translate = new Translate();

        String text = " Hello";
        String targetLanguage = "de";

        try {
            translate.translateText(targetLanguage, text);
        } catch (Exception e) {
            System.err.println("TRANSLATION ERROR");
            e.printStackTrace();
        }
    }

}
