package com.cc.downunder.model.gcp.translate;


/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-05-17
 *
 * This class is for testing translation
 */
public class TranslateMain {

    public static void main(String[] args) {
        Translate translate = new Translate();

        Language language = Language.JAPANESE;
        String text = " Hello, how is your day?";

        try {
            String TranslatedText = translate.translateText(language.getCode(), text);
            System.out.println(TranslatedText);
        } catch (Exception e) {
            System.err.println("TRANSLATION ERROR");
            e.printStackTrace();
        }
    }

}
