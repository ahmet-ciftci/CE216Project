package teamseven.ce216project;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AddEditController {
    @FXML
    private TextField titleField;
    @FXML
    private DatePicker dateField;
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

    @FXML
    private ImageView imageView;

    private String coverPath;

    private Book bookToEdit;


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
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg");
        fc.getExtensionFilters().add(filter);
        fc.setTitle("Select an cover image");
        Stage stage = new Stage();
        File file = fc.showOpenDialog(stage);

        if(file != null) {
            String imagePath = file.toURI().toString();
            imageView.setImage(new Image(imagePath));
            coverPath = imagePath;
        }

    }
    @FXML
    private void handleDeleteCoverPath(ActionEvent event){
        coverPath = null;
        imageView.setImage(null);
    }

    public String getTitleField() {
        String text = titleField.getText();
        if(text != null) {
            if (text.isBlank()) return null;
        }
        return text;
    }

    public String getDateField() {
        if (dateField.getValue() == null) {return null;}
        LocalDate date = dateField.getValue();
        String text = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        if(text != null) {
            if (text.isBlank()) return null;
        }
        return text;
    }

    public String getRatingField() {
        String text = ratingField.getText();
        if(text != null) {
            if (text.isBlank()) return null;
        }
        return text;
    }

    public String getSubtitleField() {
        String text = subtitleField.getText();
        if(text != null) {
            if (text.isBlank()) return null;
        }
        return text;
    }

    public String getISBNField() {
        String text = ISBNField.getText();
        if(text != null) {
            if (text.isBlank()) return null;
        }
        return text;
    }

    public String getPublisherField() {
        String text = publisherField.getText();
        if(text != null) {
            if (text.isBlank()) return null;
        }
        return text;
    }

    public String getEditionField() {
        String text = editionField.getText();
        if(text != null) {
            if (text.isBlank()) return null;
        }
        return text;
    }

    public String getNumberOfPagesField() {
        String text = numberOfPagesField.getText();
        if(text != null) {
            if (text.isBlank()) return null;
        }
        return text;
    }

    public String getCoverField() {
        String text = coverField.getText();
        if(text != null) {
            if (text.isBlank()) return null;
        }
        return text;
    }

    public String getLanguageField() {
        String text = languageField.getText();
        if(text != null) {
            if (text.isBlank()) return null;
        }
        return text;
    }

    public ArrayList<String> getAuthorList() {
        ArrayList<String> list = new ArrayList<>(authorList.getItems());
        if(list.isEmpty()) return null;
        return list;
    }

    public ArrayList<String> getTagList() {
        ArrayList<String> list = new ArrayList<>(tagList.getItems());
        if(list.isEmpty()) return null;
        return list;
    }

    public ArrayList<String> getTranslatorList() {
        ArrayList<String> list = new ArrayList<>(translatorList.getItems());
        if(list.isEmpty()) return null;
        return list;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setBookToEdit(Book bookToEdit) {
        this.bookToEdit = bookToEdit;

        titleField.setText(bookToEdit.getTitle());
        if (bookToEdit.getDate() != null) {
            dateField.setValue(LocalDate.parse(bookToEdit.getDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        }
        ratingField.setText(bookToEdit.getRating());
        if(bookToEdit.getAuthors() != null){
            authorList.setItems(FXCollections.observableList(bookToEdit.getAuthors()));
        }
        coverPath = bookToEdit.getCoverPath();
        if(coverPath != null) {
            imageView.setImage(new Image(coverPath));
        }
        else{
            try {
                imageView.setImage(new Image(getClass().getResource("default.png").openStream()));
            } catch (IOException e) {
                System.out.println();
            }
        }
        if(bookToEdit.getTags() != null){
            tagList.setItems(FXCollections.observableList(bookToEdit.getTags()));
        }
        subtitleField.setText(bookToEdit.getSubtitle());
        ISBNField.setText(bookToEdit.getIsbn());
        publisherField.setText(bookToEdit.getPublisher());
        editionField.setText(bookToEdit.getEdition());
        numberOfPagesField.setText(bookToEdit.getNumberOfPages());
        coverField.setText(bookToEdit.getCover());
        languageField.setText(bookToEdit.getLanguage());
        if(bookToEdit.getTranslators() != null) {
            translatorList.setItems(FXCollections.observableList(bookToEdit.getTranslators()));
        }

    }

    public void initialize() {
        dateField.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {return dateFormatter.format(date);}
                else {return "";}
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {return LocalDate.parse(string, dateFormatter);}
                else {return null;}
            }
        });
    }
}
