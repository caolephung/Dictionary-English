package com.example.learningenglishapp;

public class Sound {
    public static void textToSpeech(String text) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice voice = voiceManager.getVoice("kevin16");

        if (voice != null) {
            voice.allocate();
            voice.speak(text);
        } else {
            System.err.println("Not Found!");
        }
    }
}