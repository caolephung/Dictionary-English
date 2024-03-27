package com.example.learningenglishapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SearchControll {
    private static String DATA_FILE_PATH = "data/E_V.txt";
    private static final String SPLITTING_CHARACTERS = "<html>";
    private Map<String, Word> data = new HashMap<>();


    @FXML
    private AnchorPane Screen;

    @FXML
    private TextField Searching;

    @FXML
    private ImageView iconSearch;

    @FXML
    private ImageView iconPencil;

    @FXML
    private VBox VBoxMain;

    @FXML
    private Pane AllButton;

    @FXML
    private Button addWord;

    @FXML
    private Button search;

    @FXML
    private SplitMenuButton Games;

    @FXML
    private Button Game1;

    @FXML
    private Button Game2;

    @FXML
    private Button ListenAndRead;

    @FXML
    private HBox WordAndDefine;

    @FXML
    private ListView<String> AllWord;

    @FXML
    private WebView Define;

    @FXML
    private void initialize() {
        try {
            WordAndDefine();
            readData();
            loadWordList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void search(ActionEvent event) throws IOException {

    }

    @FXML
    public void WordAndDefine () {
        SearchControll context = this; // Lưu context hiện tại để sử dụng trong lambda expression
        this.AllWord.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        Word selectedWord = data.get(newValue);
                        String definition = selectedWord.getDef();
                        context.Define.getEngine().loadContent(definition, "text/html");
                    }
                }
        );
    }

    public void loadWordList() {
        this.AllWord.getItems().addAll(data.keySet());
    }

    public void readData() throws IOException {
        FileReader fis = new FileReader(DATA_FILE_PATH);
        BufferedReader br = new BufferedReader(fis);
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(SPLITTING_CHARACTERS);
            String word = parts[0];
            String definition = SPLITTING_CHARACTERS + parts[1];
            Word wordObj = new Word(word, definition);
            data.put(word, wordObj);
        }
        fis.close();
        br.close();
    }

    @FXML
    private void listenandreadButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListenAndRead_view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @FXML
//    public void wordSelected() {
//        String selectedWord = AllWord.getSelectionModel().getSelectedItem();
//        if (selectedWord != null) {
//            Word word = data.get(selectedWord);
//            if (word != null) {
//                WebView engine = Define.getEngine();
//                ((WebEngine) engine).loadContent(word.getDef(), "text/html");
//            }
//        }
//    }
}

class Word {
    private String word;
    private String def;

    public Word(String word, String def) {
        this.word = word;
        this.def = def;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }
}
