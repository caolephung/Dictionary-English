package com.example.learningenglishapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ShowAllWordsControll {
    private static final String SPLITTING_CHARACTERS = "<html>";

    private Map<String, Word> data = new HashMap<>();

    @FXML
    private AnchorPane Screen;

    @FXML
    private TextField Searching;

    @FXML
    private Button swap;

    @FXML
    private Text source;

    @FXML
    private Text target;

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

    private void initialize(String path) {
        try {
            WordAndDefine();
            readData(path);
            loadWordList();
            // Thiết lập sự kiện lắng nghe những thay đổi của Searching để liên tục cập nhật
            setUpSearchListener();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        String initialPath = "data/E_V.txt";
        initialize(initialPath);
    }

    @FXML
    private void setUpSearchListener() {
        // Sử dụng sự kiện textProperty() của TextField để theo dõi sự thay đổi
        Searching.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                search(); // Gọi hàm search() mỗi khi nội dung của TextField thay đổi
            }
        });
    }
    @FXML
    private void search() {
        String keyword = Searching.getText().toLowerCase(); // Lấy từ nhập từ TextField và chuyển thành chữ thường

        // Xóa danh sách từ hiện tại
        AllWord.getItems().clear();

        // Tạo danh sách mới để chứa các từ phù hợp
        ObservableList<String> matchingWords = FXCollections.observableArrayList();

        // Duyệt qua tất cả các từ trong dữ liệu và thêm các từ phù hợp vào danh sách mới
        for (Map.Entry<String, Word> entry : data.entrySet()) {
            String word = entry.getKey().toLowerCase();
            if (word.startsWith(keyword)) {
                matchingWords.add(entry.getKey());
            }
        }

        // Cập nhật ListView với danh sách từ phù hợp
        AllWord.setItems(matchingWords);
    }

    @FXML
    public void WordAndDefine () {
        ShowAllWordsControll context = this;
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

    public void readData(String DATA_FILE_PATH) throws IOException {
        data.clear();
        AllWord.getItems().clear();
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
    private void BackButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Welcome_view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void swapButtonAction() throws IOException {
        if (source.getText().equals("EN")) {
            source.setText("VI");
            target.setText("EN");
            initialize("data/V_E.txt");
        } else if (source.getText().equals("VI")) {
            source.setText("EN");
            target.setText("VI");
            initialize("data/E_V.txt");
        }
    }
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
