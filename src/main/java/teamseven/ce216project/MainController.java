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
    private Button saveChangesButton;
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
        library.importJson(library.getJsonPath());
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

    public void handleAddButton() {
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
                        controller.getRatingField() == null &&
                        controller.getAuthorList() == null &&
                        controller.getTagList() == null &&
                        controller.getTranslatorList() == null &&
                        controller.getCoverPath() == null){
                    return;
                }
                library.addBook(controller.getTitleField(),controller.getSubtitleField(),controller.getISBNField(),controller.getPublisherField(),controller.getDateField(),controller.getEditionField(),controller.getNumberOfPagesField(),controller.getCoverField(), controller.getCoverPath(),controller.getLanguageField(),controller.getRatingField(),controller.getAuthorList(),controller.getTranslatorList(),controller.getTagList());

            }
        } catch (IOException e) {
            System.err.println(e);
        }
        refreshTableView();

    }

    public void handleEditButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add-edit.fxml"));
            DialogPane bookDialogPane = fxmlLoader.load();
            AddEditController controller = fxmlLoader.getController();

            int index = bookTable.getSelectionModel().getSelectedIndex();
            controller.setBookToEdit(library.getFoundBooks().get(index));

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Edit a book");
            dialog.setDialogPane(bookDialogPane);

            Optional<ButtonType> buttonType = dialog.showAndWait();
            if(buttonType.get() == ButtonType.APPLY) {
                if (controller.getTitleField() == null &&
                        controller.getSubtitleField() == null &&
                        controller.getISBNField() == null &&
                        controller.getPublisherField() == null &&
                        controller.getDateField() == null &&
                        controller.getEditionField() == null &&
                        controller.getNumberOfPagesField() == null &&
                        controller.getCoverField() == null &&
                        controller.getLanguageField() == null &&
                        controller.getRatingField() == null &&
                        controller.getAuthorList() == null &&
                        controller.getTagList() == null &&
                        controller.getTranslatorList() == null &&
                        controller.getCoverPath() == null) {
                    return;
                }
                Book book = new Book(controller.getTitleField(),controller.getSubtitleField(),controller.getISBNField(),controller.getPublisherField(),controller.getDateField(),controller.getEditionField(),controller.getNumberOfPagesField(),controller.getCoverField(), controller.getCoverPath(),controller.getLanguageField(),controller.getRatingField(),controller.getAuthorList(),controller.getTranslatorList(),controller.getTagList());
                Book oldBook = library.getFoundBooks().get(index);
                library.getFoundBooks().set(index, book);
                if (library.getSameBookIndex(oldBook) != -1){
                    library.getBooks().set(library.getSameBookIndex(oldBook), book);

                }
                refreshTableView();
            }

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void handleDeleteButton() {
        int index = bookTable.getSelectionModel().getSelectedIndex();
        Book bookToDelete = bookTable.getSelectionModel().getSelectedItem();
        library.getFoundBooks().remove(index);
        library.deleteBook(bookToDelete);
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
        library.search(null);
        refreshTableView();
    }

    public void showDetailedView() {
        int index = bookTable.getSelectionModel().getSelectedIndex();
        if (index == -1){return;}
        if (library.getFoundBooks().get(index).getTitle() == null) {
            titleLabel.setText("Title: ");
        } else {
            titleLabel.setText("Title: " + library.getFoundBooks().get(index).getTitle());
        }
        if(library.getFoundBooks().get(index).getAuthors() == null) {
            authorsLabel.setText("Authors: ");
        } else {
            authorsLabel.setText("Authors: " + library.getFoundBooks().get(index).getAuthors());
        }
        if (library.getFoundBooks().get(index).getDate() == null) {
            dateLabel.setText("Date: ");
        } else {
            dateLabel.setText("Date: " + library.getFoundBooks().get(index).getDate());
        }
        if (library.getFoundBooks().get(index).getTags() == null) {
            tagsLabel.setText("Tags: ");
        } else {
            tagsLabel.setText("Tags: " + library.getFoundBooks().get(index).getTags());
        }
        if (library.getFoundBooks().get(index).getRating() == null) {
            ratingLabel.setText("Rating: ");
        } else {
            ratingLabel.setText("Rating: " + library.getFoundBooks().get(index).getRating());
        }
        if (library.getFoundBooks().get(index).getSubtitle() == null) {
            subtitleLabel.setText("Subtitle: ");
        } else {
            subtitleLabel.setText("Subtitle: " + library.getFoundBooks().get(index).getSubtitle());
        }
        if (library.getFoundBooks().get(index).getIsbn() == null) {
            isbnLabel.setText("ISBN: ");
        } else {
            isbnLabel.setText("ISBN: " + library.getFoundBooks().get(index).getIsbn());
        }
        if (library.getFoundBooks().get(index).getPublisher() == null) {
            publisherLabel.setText("Publisher: ");
        } else {
            publisherLabel.setText("Publisher: " + library.getFoundBooks().get(index).getPublisher());
        }
        if (library.getFoundBooks().get(index).getEdition() == null) {
            editionLabel.setText("Edition: ");
        } else {
            editionLabel.setText("Edition: " + library.getFoundBooks().get(index).getEdition());
        }
        if (library.getFoundBooks().get(index).getNumberOfPages() == null) {
            numberOfPagesLabel.setText("Number of Pages: ");
        } else {
            numberOfPagesLabel.setText("Number of Pages: " + library.getFoundBooks().get(index).getNumberOfPages());
        }
        if (library.getFoundBooks().get(index).getCover() == null) {
            coverLabel.setText("Cover: ");
        } else {
            coverLabel.setText("Cover: " + library.getFoundBooks().get(index).getCover());
        }
        if (library.getFoundBooks().get(index).getLanguage() == null) {
            languageLabel.setText("Language: ");
        } else {
            languageLabel.setText("Language: " + library.getFoundBooks().get(index).getLanguage());
        }
        if (library.getFoundBooks().get(index).getTranslators() == null) {
            translatorsLabel.setText("Translators: ");
        } else {
            translatorsLabel.setText("Translators: " + library.getFoundBooks().get(index).getTranslators());
        }
        if(library.getFoundBooks().get(index).getCoverPath() == null) {
            coverImage.setImage(null);
        } else {
            coverImage.setImage(new Image(library.getFoundBooks().get(index).getCoverPath()));
        }

    }


    public void saveChanges() {
        library.updateJson();
    }
}
