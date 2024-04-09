package teamseven.ce216project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class MainController{
    private Library library;
    @FXML
    private Button exportButton;
    @FXML
    private Button importButton;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, String> titleCol;
    @FXML
    private TableColumn<Book, ArrayList<String>> authorsCol;
    @FXML
    private TableColumn<Book, String> dateCol;
    @FXML
    private TableColumn<Book, ArrayList<String>> tagsCol;
    @FXML
    private TableColumn<Book, String> ratingCol;
    @FXML
    private TableColumn<Book, String> subtitleCol;
    @FXML
    private TableColumn<Book, String> ISBNCol;
    @FXML
    private TableColumn<Book, String> publisherCol;
    @FXML
    private TableColumn<Book, String> editionCol;
    @FXML
    private TableColumn<Book, String>  pagesCol;
    @FXML
    private TableColumn<Book, String> coverCol;
    @FXML
    private TableColumn<Book, String> languageCol;
    @FXML
    private TableColumn<Book, ArrayList<String>> translatorsCol;

    @FXML
    private TextField searchBar;

    private ObservableList<Book> books;

    @FXML
    private Label titleLabel;
    @FXML
    private Label authorsLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label tagsLabel;
    @FXML
    private Label ratingLabel;
    @FXML
    private Label subtitleLabel;
    @FXML
    private Label isbnLabel;
    @FXML
    private Label publisherLabel;
    @FXML
    private Label editionLabel;
    @FXML
    private Label numberOfPagesLabel;
    @FXML
    private Label coverLabel;
    @FXML
    private Label languageLabel;
    @FXML
    private Label translatorsLabel;
    @FXML
    private ImageView coverImage;

    public void initialize(Library library) {
        this.library = library;
        refreshTableView();
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorsCol.setCellValueFactory(new PropertyValueFactory<>("authors"));
        tagsCol.setCellValueFactory(new PropertyValueFactory<>("tags"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        subtitleCol.setCellValueFactory(new PropertyValueFactory<>("subtitle"));
        ISBNCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        editionCol.setCellValueFactory(new PropertyValueFactory<>("edition"));
        pagesCol.setCellValueFactory(new PropertyValueFactory<>("numberOfPages"));
        coverCol.setCellValueFactory(new PropertyValueFactory<>("cover"));
        languageCol.setCellValueFactory(new PropertyValueFactory<>("language"));
        translatorsCol.setCellValueFactory(new PropertyValueFactory<>("translators"));


    }

    public void handleAddButton(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add-edit.fxml"));
            DialogPane bookDialogPane = fxmlLoader.load();
            AddEditController controller = fxmlLoader.getController();

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Add a book");
            dialog.setDialogPane(bookDialogPane);



            Optional<ButtonType> buttonType = dialog.showAndWait();
            if(buttonType.get() == ButtonType.APPLY){
                if(controller.getTitleField() == null &&
                        controller.getSubtitleField() == null &&
                        controller.getISBNField() == null &&
                        controller.getPublisherField() == null &&
                        controller.getDateField() == null &&
                        controller.getEditionField() == null &&
                        controller.getNumberOfPagesField() == null &&
                        controller.getCoverField() == null &&
                        controller.getLanguageField() == null &&
                        controller.getRatingField() == null){
                    return;
                }
                library.addBook(controller.getTitleField(),controller.getSubtitleField(),controller.getISBNField(),controller.getPublisherField(),controller.getDateField(),controller.getEditionField(),controller.getNumberOfPagesField(),controller.getCoverField(), null,controller.getLanguageField(),controller.getRatingField(),null,null,null);
                // SELF-NOTE: DON'T FORGET TO ADD PATH AND LIST
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        refreshTableView();

    }

    public void handleEditButton(ActionEvent event) {

    }

    public void handleDeleteButton(ActionEvent event) {
        int index = bookTable.getSelectionModel().getSelectedIndex();
        library.getFoundBooks().remove(index);
        refreshTableView();
    }

    public void refreshTableView(){
        books = FXCollections.observableArrayList(library.getFoundBooks());
        bookTable.setItems(books);
    }

    public void exportJsonPath() {
        Stage stage = (Stage) exportButton.getScene().getWindow();
        FileChooser file = new FileChooser();
        file.setInitialFileName("library.json");
        FileChooser.ExtensionFilter fileExtensions = new FileChooser.ExtensionFilter("JSON File","*.json");
        file.getExtensionFilters().add(fileExtensions);
        file.setTitle("Choose Export Location");
        File f = file.showSaveDialog(stage);
        library.exportJson(f.getPath());
    }

    public void importJsonPath() {
        Stage stage = (Stage) importButton.getScene().getWindow();
        FileChooser file = new FileChooser();
        file.setInitialFileName("library.json");
        FileChooser.ExtensionFilter fileExtensions = new FileChooser.ExtensionFilter("JSON File","*.json");
        file.getExtensionFilters().add(fileExtensions);
        file.setTitle("Choose Export Location");
        File f = file.showOpenDialog(stage);
        library.importJson(f.getPath());
    }

    public void showDetailedView() {
        int index = bookTable.getSelectionModel().getSelectedIndex();
        titleLabel.setText("Title: " + library.getFoundBooks().get(index).getTitle());
        authorsLabel.setText("Authors: " + library.getFoundBooks().get(index).getAuthors());
        dateLabel.setText("Date: " + library.getFoundBooks().get(index).getDate());
        tagsLabel.setText("Tags: " + library.getFoundBooks().get(index).getTags());
        ratingLabel.setText("Rating: " + library.getFoundBooks().get(index).getRating());
        subtitleLabel.setText("Subtitle: " + library.getFoundBooks().get(index).getSubtitle());
        isbnLabel.setText("ISBN: " + library.getFoundBooks().get(index).getIsbn());
        publisherLabel.setText("Publisher: " + library.getFoundBooks().get(index).getPublisher());
        editionLabel.setText("Edition: " + library.getFoundBooks().get(index).getEdition());
        numberOfPagesLabel.setText("Number of Pages: " + library.getFoundBooks().get(index).getNumberOfPages());
        coverLabel.setText("Cover: " + library.getFoundBooks().get(index).getCover());
        languageLabel.setText("Language: " + library.getFoundBooks().get(index).getLanguage());
        translatorsLabel.setText("Translators: " + library.getFoundBooks().get(index).getTranslators());
        coverImage.setImage(new Image(library.getFoundBooks().get(index).getCoverPath()));
    }


    private ArrayList<Book> searchList(String searchedWord){
        library.search(searchedWord);
        return library.getFoundBooks();
    }
}
