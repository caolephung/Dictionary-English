package com.example.learningenglishapp;

import DictionnaryCmd.IOData_SQL;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class ShowAllWordsControll {
    private static final String SPLITTING_CHARACTERS = "<html>";

    private Map<String, Word> data = new HashMap<>();

    @FXML
    private ImageView Flag_source;

    @FXML
    private ImageView Flag_target;

    @FXML
    private TextField Searching;

    @FXML
    private Button swap;

    @FXML
    private Label source;

    @FXML
    private Label target;

    @FXML
    private VBox VBoxMain;

    @FXML
    private Button addWord;

    @FXML
    private Button search;

    @FXML
    private HBox WordAndDefine;

    @FXML
    private ListView<String> AllWord;

    @FXML
    private WebView Define;

    private IOData_SQL dataSql = new IOData_SQL("jdbc:mysql://localhost:3306/dictionaryenglish");

    private void setURL(String source) {
        if (source.equals("English")) {
            dataSql = new IOData_SQL("jdbc:mysql://localhost:3306/dictionaryenglish");
        } else if (source.equals("VietNamese")) {
            dataSql = new IOData_SQL("jdbc:mysql://localhost:3306/dictionaryvnese");
        }
    }

    @FXML
    private void initialize() {
        try {
            WordAndDefine();
            readData();
            loadWordList();
            // Thiết lập sự kiện lắng nghe những thay đổi của Searching để liên tục cập nhật
            setUpSearchListener();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void readData() throws IOException {
        data.clear();
        AllWord.getItems().clear();
        try (Connection connection = DriverManager.getConnection(dataSql.URL, "root", "Mysql123.")) {
            // Tạo một câu lệnh SQL
            String query = "SELECT * FROM words";

            // Tạo một đối tượng Statement để thực thi truy vấn
            Statement statement = connection.createStatement();

            // Thực thi truy vấn và nhận kết quả
            ResultSet resultSet = statement.executeQuery(query);

            // Lặp qua kết quả và in ra các dòng dữ liệu
            while (resultSet.next()) {
                String word = resultSet.getString("word");
                String definition = resultSet.getString("definition");
                Word wordObj = new Word(word, definition);
                data.put(word, wordObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    private void swapFlag() {
        Image tmp = Flag_source.getImage();
        Flag_source.setImage(Flag_target.getImage());
        Flag_target.setImage(tmp);
    }

    @FXML
    private void swapButtonAction() throws IOException {
        if (source.getText().equals("English")) {
            source.setText("VietNamese");
            target.setText("English");
            swapFlag();
            setURL("VietNamese");
        } else if (source.getText().equals("VietNamese")) {
            source.setText("English");
            target.setText("VietNamese");
            swapFlag();
            setURL("English");
        }
        initialize();
    }

    @FXML
    private void AddWordButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddWord_view.fxml"));
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
    private void DeleteWordButtonAction(ActionEvent event) {
        String selectedWord = AllWord.getSelectionModel().getSelectedItem();

        if (selectedWord != null) {
            // Xóa từ khỏi dữ liệu
            data.remove(selectedWord);
            dataSql.removeWord(selectedWord);

            // Xóa từ khỏi ListView
            AllWord.getItems().remove(selectedWord);

            // Cập nhật WebView nếu từ bị xóa là từ đang được hiển thị
            String currentSelectedWord = Define.getEngine().executeScript("document.body.innerText").toString();
            if (currentSelectedWord.equalsIgnoreCase(selectedWord)) {
                Define.getEngine().loadContent(""); // Xóa nội dung của WebView
            }
        }
    }

    @FXML
    private void UpdateWordButtonAction(ActionEvent event) {
        try {
            // Tạo một FXMLLoader mới để tải layout của view mới
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Update_view.fxml"));
            Parent root = loader.load();

            // Truy cập controller của view mới
            UpdateControll updateControll = loader.getController();

            // Lấy từ được chọn từ ListView
            String selectedWord = AllWord.getSelectionModel().getSelectedItem();
            Word selectedWordObj = data.get(selectedWord);
            String definition = selectedWordObj.getDef();

            // Gọi phương thức setWord trên controller của view mới và truyền từ được chọn
            updateControll.setWord(selectedWord, definition);

            updateControll.getSource(source.getText());
            // Tạo một Scene mới từ Parent
            Scene scene = new Scene(root);

            // Lấy Stage hiện tại từ sự kiện
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Đặt Scene cho Stage
            stage.setScene(scene);

            // Hiển thị Stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void voiceSourceButton(ActionEvent actionEvent) throws Exception {
        String sourceLanguage = source.getText();
        if(sourceLanguage.equals("English")) {
            Sound.SpeechEnglish(AllWord.getSelectionModel().getSelectedItem());
        } else if(sourceLanguage.equals("Vietnamese")) {
            Sound.SpeechVietNamese(AllWord.getSelectionModel().getSelectedItem());
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
