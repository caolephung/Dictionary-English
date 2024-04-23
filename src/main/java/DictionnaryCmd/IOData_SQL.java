package DictionnaryCmd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOData_SQL {
    private String URL;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Mysql123.";

    public IOData_SQL() {

    }

    public IOData_SQL(String URL) {
        this.URL = URL;
    }

    public List<Word> getAllWords() {
        List<Word> words = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "SELECT DISTINCT word, definition FROM words order by word";

            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String word_target = resultSet.getString("word");
                String word_explain = resultSet.getString("definition");
                Word word = new Word(word_target, word_explain);
                words.add(word);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return words;
    }

    public void addWord(String word, String definition) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "INSERT INTO words (word, definition) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, word);
                preparedStatement.setString(2, definition);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Thành công");
                } else {
                    System.out.println("Không có gì thay đổi");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateWord(String word, String newDefinition) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "UPDATE words SET definition = ? WHERE word = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, word);
                preparedStatement.setString(2, newDefinition);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Thành công");
                } else {
                    System.out.println("");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeWord(String word) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "DELETE FROM words WHERE word = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, word);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Thành công");
                } else {
                    System.out.println("Not Found!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchWord(String word) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM words WHERE word = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, word);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String foundWord = resultSet.getString("word");
                        String definition = resultSet.getString("definition");
                        System.out.println("Từ: " + foundWord);
                        System.out.println("Nghĩa: " + definition);
                    } else {
                        System.out.println("Không tìm thấy từ '" + word + "' trong từ điển.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filePath = "data/V_E.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("<html>");
                String word = parts[0];
                String definition = "<html>" + parts[1];
                IOData_SQL IO = new IOData_SQL("jdbc:mysql://localhost:3306/dictionaryvnese");
                // Thêm từ vào cơ sở dữ liệu
                IO.addWord(word, definition);
            }
            System.out.println("Thêm từ thành công.");
        } catch (IOException e) {
            e.printStackTrace();
        }
     }

//    public static void main(String[] args) {
//        IOData_SQL dataSql = new IOData_SQL();
//        dataSql.searchWord("a");
//    }
}
