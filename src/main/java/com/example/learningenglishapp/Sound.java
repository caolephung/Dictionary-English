package com.example.learningenglishapp;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.voicerss.tts.*;
import com.voicerss.tts.AudioFormat;


import javax.sound.sampled.*;
import java.io.*;

public class Sound {
    public static void Speech(String text) {
        // Khởi tạo trình quản lý giọng nói
        VoiceManager voiceManager = VoiceManager.getInstance();

        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

        // Chọn một giọng nói từ danh sách các giọng nói đã cài đặt
        Voice voice = voiceManager.getVoice("kevin16");

        if (voice == null) {
            System.out.println("Không thể tìm thấy giọng nói!");
            return;
        }

        voice.allocate();

        voice.speak(text);

        voice.deallocate();
    }

    public static void SpeechEnglish(String text) throws Exception {
        String apiKey = "d3e63e8353374e97879f809e9fe49d42";
        VoiceProvider tts = new VoiceProvider(apiKey);
        VoiceParameters params = new VoiceParameters(text, Languages.English_UnitedStates);


        params.setCodec(AudioCodec.WAV);
        params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_mono);
        params.setBase64(false);
        params.setSSML(false);
        params.setRate(0);

        byte[] voice = tts.speech(params);

        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new ByteArrayInputStream(voice))) {

            javax.sound.sampled.AudioFormat format = audioInputStream.getFormat();

            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);

            line.open(format);
            line.start();

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = audioInputStream.read(buffer)) != -1) {
                line.write(buffer, 0, bytesRead);
            }

            line.drain();
            line.stop();
            line.close();
        }
    }

    public static void SpeechVietNamese(String text) throws Exception {
        String apiKey = "f17e778c46e44f57be4e990d07c273d9";
        VoiceProvider tts = new VoiceProvider(apiKey);
        VoiceParameters params = new VoiceParameters(text, Languages.Vietnamese);
        params.setCodec(AudioCodec.WAV);
        params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_mono);
        params.setBase64(false);
        params.setSSML(false);
        params.setRate(0);

        byte[] voice = tts.speech(params);

        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new ByteArrayInputStream(voice))) {

            javax.sound.sampled.AudioFormat format = audioInputStream.getFormat();


            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);


            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);


            line.open(format);
            line.start();


            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = audioInputStream.read(buffer)) != -1) {
                line.write(buffer, 0, bytesRead);
            }

            line.drain();
            line.stop();
            line.close();
        }
    }
}