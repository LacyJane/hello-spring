package org.launchcode.models;

import java.util.HashMap;

/**
 * Created by stephen on 3/6/17.
 */
public class Language {
    public String name;
    public String value;
    public String greeting;

    public Language(String name, String value, String greeting) {
        this.name = name;
        this.value = value;
        this.greeting = greeting;
    }

    public static HashMap<String, Language> getLanguages() {
        HashMap<String, Language> langs = new HashMap<>();
        langs.put("EN", new Language("English", "EN", "Hello"));
        langs.put("FR", new Language("French", "FR", "Bonjour"));
        langs.put("SP", new Language("Spanish", "SP", "Hola"));
        langs.put("GM", new Language("German", "GM", "Hallo"));
        langs.put("IT", new Language("Italian", "IT", "Ciao"));
        return langs;
    }

    public static String greetingForLang(String value){
        Language lang = getLanguages().get(value);
        if(lang == null) {
            return "Hello";
        }
        return lang.greeting;
    }
}
