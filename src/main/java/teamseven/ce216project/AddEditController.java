package teamseven.ce216project;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

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

    @FXML
    private ImageView imageView;

    private String coverPath;

    private Book bookToEdit;

    public void initialize() {
        try {

            numberOfPagesField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null && !newValue.matches("\\d*")) {
                    numberOfPagesField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });

            ISBNField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (ISBNField.getText() != null) {
                    String isbn = ISBNField.getText().replaceAll("-", "");
                    if (!isbn.matches("^$|\\d{1,13}")) {
                        ISBNField.setText(oldValue);
                    } else {
                        // Add hyphens at specific locations for ISBN-13
                        if (isbn.length() > 3) isbn = isbn.substring(0, 3) + "-" + isbn.substring(3);
                        if (isbn.length() > 5) isbn = isbn.substring(0, 5) + "-" + isbn.substring(5);
                        if (isbn.length() > 8) isbn = isbn.substring(0, 8) + "-" + isbn.substring(8);
                        if (isbn.length() > 15) isbn = isbn.substring(0, 15) + "-" + isbn.substring(15);
                        String finalIsbn = isbn;
                        Platform.runLater(() -> {
                            ISBNField.setText(finalIsbn);
                            ISBNField.positionCaret(finalIsbn.length());
                        });

                    }
                }
            });


            ratingField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (ratingField.getText() != null) {
                    String rating = ratingField.getText().replaceAll("\\.", "");
                    if (!rating.matches("^$|[0-4]|[0-4][1-9]|5")) {
                        ratingField.setText(oldValue);
                    } else {

                        if (rating.length() > 1) rating = rating.substring(0, 1) + "." + rating.substring(1);
                        String finalRating = rating;
                        Platform.runLater(() -> {
                            ratingField.setText(finalRating);
                            ratingField.positionCaret(finalRating.length());
                        });

                    }
                }
            });

        }catch (Exception e) {
            System.err.println();
        }

    }



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
    @FXML
    public void handleDragDropCoverImage(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        if(dragboard.hasFiles() || dragboard.hasImage()){
            String imagePath = dragboard.getFiles().getFirst().toURI().toString();
            if(imagePath.endsWith(".jpg") || imagePath.endsWith(".png")) {
                coverPath = imagePath;
                imageView.setImage(new Image(coverPath));
            }

        }
    }

    public void handleDragOverCoverImage(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        if(dragboard.hasFiles() || dragboard.hasImage()){
            event.acceptTransferModes(TransferMode.COPY);
        }

        event.consume();
    }

    public String getTitleField() {
        String text = titleField.getText();
        if(text != null) {
            if (text.isBlank()) return null;
        }
        return text;
    }

    public String getDateField() {
        String text = dateField.getText();
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
            else if( !text.matches("\\d{3}-\\d-\\d{2}-\\d{6}-\\d")){
                return null;
            }
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
        dateField.setText(bookToEdit.getDate());
        ratingField.setText(bookToEdit.getRating());
        if(bookToEdit.getAuthors() != null){
            authorList.setItems(FXCollections.observableList(bookToEdit.getAuthors()));
        }
        coverPath = bookToEdit.getCoverPath();
        if(coverPath != null) {
            imageView.setImage(new Image(coverPath));
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
}
