package com.cc.downunder.model.gcp.translate;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-05-17
 */
public enum Language {
    ENGLISH("English", "en"),
    CHINESE_TRAD("Chinese (Traditional)", "zh-TW"),
    CHINESE_SIMP("Chinese (Simplified)", "zh-CN"),
    FRENCH("French", "fr"),
    GERMAN("German", "de"),
    GREEK("Greek", "el"),
    JAPANESE("Japanese", "ja"),
    KOREAN("Korean", "ko"),
    RUSSIAN("Russian", "ru"),
    SPANISH("Spanish", "es"),
    SWEDISH("Swedish", "sv"),
    VIETNAMESE("Vietnamese", "vi");



    private final String name;
    private final String code;

    Language(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
