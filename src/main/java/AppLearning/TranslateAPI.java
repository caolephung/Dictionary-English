package AppLearning;



import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TranslateAPI {
    private static final String API = "https://api.mymemory.translated.net/get";

    public static String translateText(String text, String source, String target) {
        try {
            String encodedText = URLEncoder.encode(text, "UTF-8");

            String apiUrl = String.format("%s?q=%s&langpair=%s|%s", API, encodedText, source, target);
            URL url = new URL(apiUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connection.disconnect();

            // Trích xuất và trả về văn bản dịch từ kết quả JSON
            String translatedText = extractTranslatedText(response);
            return translatedText;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /** trích xuất văn bản dịch từ kết quả JSON nhận được từ API dịch. */
    private static String extractTranslatedText(StringBuilder response) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonResponse = (JSONObject) parser.parse(response.toString());
            if (jsonResponse.containsKey("responseData")) {
                JSONObject responseData = (JSONObject) jsonResponse.get("responseData");

                if (responseData.containsKey("translatedText")) {
                    String translatedText = (String) responseData.get("translatedText");
                    return translatedText;
                }
            }
            return "Not Found!";

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}

