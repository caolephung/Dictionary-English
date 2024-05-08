# Application to support learning English using Java

## Author
* Group:
    - [**Cao Lê Phụng**](https://github.com/caolephung) - 22022176
    - [**Vũ Hải Triều**](https://github.com/Trieu1802) - 22022111
    - [**Lương Quốc Hùng**](https://github.com/luonghung123) - 22022136

## Description
The application is designed to support learning English. The application is written in Java and uses the JavaFX library. The application is based on the MVC model. The application has two types of dictionaries: English-Vietnamese and Vietnamese-English. The application use text files and SQL DB to store data.
1. The application is designed to support learning English.
2. The application is written in Java and uses the JavaFX library, JBDC.
3. The application is based on the MVC model.
4. The application has two types of dictionaries: English-Vietnamese and Vietnamese-English.
5. The application use E_V.txt, V_E.txt files and Database to store data.

## UML

## Installation
1. Clone the project from the repository.
2. Open the project in the IDE.
3. Import the voice RSS jar to the module of the project (.mvn/wrapper/voicerss_tts.jar).
4. Find the class name LearningEnglishApp.java in package Screen (src/main/java/com/example/learningenglishapp/LearningEnglishApp.java) to run the application.
5. Run the project.
6. If you want to change the data, you can change the E_V.txt and V_E.txt files.

## Usage
1. Select mode: At the main screen interface, select the function you want to use by clicking the buttons.
2. Translate: 
   - Click the Translate button.
   - Enter the word or text you want to translate into the TextArea on the left.
   - Click "Dịch" button.
   - The Translation will be shown in the right textArea.
   - Click the sound button in order to read the whole sentence in the text area.
   - Click the swap button to switch between the interface translating from E to V or V to E.
   - Click the back button to return the main screen of the dictionary.
3. Show All Words in the Dictionary and search:
   - Click the Show All Words button.
   - All words in the dictionary will display on the left, choose the word to show its definition, click the sound button in order to pronounce the word.
   - If you want to find a word, click on the search bar and enter the word you want to find.
   - Click the swap button to switch between the interface displaying English and Vietnamese words.
   - Click the back button to return the main screen of the dictionary.
4. Add word:
   - Click the Add Words button.
   - Choose the type of word you want to add (English or VietNamese) by clicking the swap button.
   - Enter the word and definition that you want to add and click Add Words to add word.
   - Click the back button to return the main screen of the dictionary.
   
In show all words, if you see a word that you think is incorrect, you can delete or update it.

7. Delete word:
   - On the show all words interface, select the word to delete.
   - Click the delete button.
8. Update word:
   - On the show all word interface, select the word whose definition needs to be changed.
   - Click the Update button.
   - Words and old definitions will be displayed, enter new definitions.
   - Click the confirm button.
   - Click the back button to return the show all words screen.
9. Games: Multiple Choice and Worddrop
   - Click the Update button in the main screen.
* Multiple Choice:
  - Click to Start button to Start the Game with 15 question for each turn.
  - Choose the answer for each question.
  - If you can't answer that question, click next to skip and move on to a new question.
* Worddrop: 
  - Click to Start button to Start the Game with 15 question for each turn.
  - A meaningful word will be displayed, but the letter positions has been disturbed.
  - Your task is to find that meaningful word and enter it in the answer box.

## Demo
![Demo](

## Future improvements
1. Add more dictionaries.
2. Add more complex games.
3. Optimize the word lookup algorithm.
4. Improve the user interface.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## Project status
The project is completed.

## Notes
The application is written for educational purposes.