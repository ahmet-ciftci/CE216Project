package teamseven.ce216project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AddEditController {
    @FXML
    private TextField titleField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField ratingField;
    @FXML
    private TextField subtitleField;
    @FXML
    private TextField ISBNField;
    @FXML
    private TextField publisherField;
    @FXML
    private TextField editionField;
    @FXML
    private TextField numberOfPagesField;
    @FXML
    private TextField coverField;
    @FXML
    private TextField languageField;

    @FXML
    private ListView<String> authorList;
    @FXML
    private ListView<String> tagList;
    @FXML
    private ListView<String> translatorList;

    @FXML
    private TextField authorField;
    @FXML
    private TextField tagField;
    @FXML
    private TextField translatorField;

    @FXML
    private Button authorAddButton;
    @FXML
    private Button authorDeleteButton;
    @FXML
    private Button tagAddButton;
    @FXML
    private Button tagDeleteButton;
    @FXML
    private Button translatorAddButton;
    @FXML
    private Button translatorDeleteButton;

    @FXML
    private Button loadImageButton;
    @FXML
    private Button deleteImageButton;

    private String coverPath;

    @FXML
    private void handleAuthorAdd(ActionEvent event){
        String text = authorField.getText();
        if(!text.isBlank()) {
            authorList.getItems().add(text);
        }
        authorField.clear();
    }
    @FXML
    private void handleAuthorDelete(ActionEvent event){
        try {
            int index = authorList.getSelectionModel().getSelectedIndex();
            authorList.getItems().remove(index);
        }catch (Exception e){
            System.err.println("No item is selected");
        }
    }
    @FXML
    private void handleTagAdd(ActionEvent event){
        String text = tagField.getText();
        if(!text.isBlank()) {
            tagList.getItems().add(text);
        }
        tagField.clear();
    }
    @FXML
    private void handleTagDelete(ActionEvent event){
        try {
            int index = tagList.getSelectionModel().getSelectedIndex();
            tagList.getItems().remove(index);
        }catch (Exception e){
            System.err.println("No item is selected");
        }
    }
    @FXML
    private void handleTranslatorAdd(ActionEvent event){
        String text = translatorField.getText();
        if(!text.isBlank()) {
            translatorList.getItems().add(text);
        }
        translatorField.clear();
    }
    @FXML
    private void handleTranslatorDelete(ActionEvent event){
        try {
            int index = translatorList.getSelectionModel().getSelectedIndex();
            translatorList.getItems().remove(index);
        }catch (Exception e){
            System.err.println("No item is selected");
        }
    }
    @FXML
    private void handleLoadCoverPath(ActionEvent event){

    }
    @FXML
    private void handleDeleteCoverPath(ActionEvent event){

    }

    public String getTitleField() {
        String text = titleField.getText();
        if (text.isBlank()) return null;
        return text;
    }

    public String getDateField() {
        String text = dateField.getText();
        if (text.isBlank()) return null;
        return text;
    }

    public String getRatingField() {
        String text = ratingField.getText();
        if (text.isBlank()) return null;
        return text;
    }

    public String getSubtitleField() {
        String text = subtitleField.getText();
        if (text.isBlank()) return null;
        return text;
    }

    public String getISBNField() {
        String text = ISBNField.getText();
        if (text.isBlank()) return null;
        return text;
    }

    public String getPublisherField() {
        String text = publisherField.getText();
        if (text.isBlank()) return null;
        return text;
    }

    public String getEditionField() {
        String text = editionField.getText();
        if (text.isBlank()) return null;
        return text;
    }

    public String getNumberOfPagesField() {
        String text = numberOfPagesField.getText();
        if (text.isBlank()) return null;
        return text;
    }

    public String getCoverField() {
        String text = coverField.getText();
        if (text.isBlank()) return null;
        return text;
    }

    public String getLanguageField() {
        String text = languageField.getText();
        if (text.isBlank()) return null;
        return text;
    }

    // Lists will be done here
}
