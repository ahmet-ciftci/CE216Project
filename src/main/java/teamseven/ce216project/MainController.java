package teamseven.ce216project;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Optional;


public class MainController{
    private Library library;
    private ArrayList<String> previouslySelected;
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
    private Button tagsButton;
    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, String> titleCol;
    @FXML
    private TableColumn<Book, String> authorsCol;
    @FXML
    private TableColumn<Book, String> dateCol;
    @FXML
    private TableColumn<Book, String> tagsCol;
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
    private TableColumn<Book, String> translatorsCol;

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
    @FXML
    private Pane imagePane;

    public void initialize(Library library) {
        this.library = library;
        library.importJson(library.getJsonPath());
        for (Book book : library.getBooks()) {library.addTag(book);}
        refreshTableView();
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorsCol.setCellValueFactory(cellData -> new SimpleStringProperty(arrayListToString(cellData.getValue().getAuthors())));
        tagsCol.setCellValueFactory(cellData -> new SimpleStringProperty(arrayListToString(cellData.getValue().getTags())));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        subtitleCol.setCellValueFactory(new PropertyValueFactory<>("subtitle"));
        ISBNCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        editionCol.setCellValueFactory(new PropertyValueFactory<>("edition"));
        pagesCol.setCellValueFactory(new PropertyValueFactory<>("numberOfPages"));
        coverCol.setCellValueFactory(new PropertyValueFactory<>("coverType"));
        languageCol.setCellValueFactory(new PropertyValueFactory<>("language"));
        translatorsCol.setCellValueFactory(cellData -> new SimpleStringProperty(arrayListToString(cellData.getValue().getTranslators())));
        coverImage.fitHeightProperty().bind(imagePane.heightProperty());
        coverImage.fitWidthProperty().bind(imagePane.widthProperty());

        disableDeleteButton();
        disableEditButton();
    }

    private void disableDeleteButton() {
        deleteButton.setDisable(ifAnyRowSelected());
    }
    private void disableEditButton() {
        editButton.setDisable(ifAnyRowSelected());
    }

    public boolean ifAnyRowSelected() {
        if(bookTable.getSelectionModel().getSelectedIndex() < 0) {
            return true;
        }
        return false;
    }

    public void handleAddButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add-edit.fxml"));
            DialogPane bookDialogPane = fxmlLoader.load();
            AddEditController controller = fxmlLoader.getController();
            controller.initialize();

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

        }
        refreshTableView();

    }

    public void handleEditButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add-edit.fxml"));
            DialogPane bookDialogPane = fxmlLoader.load();
            AddEditController controller = fxmlLoader.getController();
            controller.initialize();

            int index = bookTable.getSelectionModel().getSelectedIndex();
            if (index == -1){return;}
            controller.setBookToEdit(books.get(index));

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Edit a book");
            dialog.setDialogPane(bookDialogPane);
            controller.initialize();

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
                Book oldBook = bookTable.getSelectionModel().getSelectedItem();

                books.set(index, book);
                if (library.getSameBookIndex(oldBook) != -1){
                    library.getBooks().set(library.getSameBookIndex(oldBook), book);
                }
                if(library.getSameFoundBookIndex(oldBook) != -1){
                    library.getFoundBooks().set(library.getSameFoundBookIndex(oldBook), book);
                }
                library.deleteTag(oldBook);
                library.addTag(book);
                refreshTableView();
            }

        } catch (IOException e) {
            System.err.println(e);
        }
    }
    public void handleTagsButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tag-selector.fxml"));
            DialogPane tagsDialogPane = fxmlLoader.load();
            TagSelectorController controller = fxmlLoader.getController();
            controller.initialize(library.getUniqueTags(), previouslySelected);

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Choose tags");
            dialog.setDialogPane(tagsDialogPane);

            Optional<ButtonType> buttonType = dialog.showAndWait();
            if(buttonType.get() == ButtonType.APPLY){
                if (controller.getSelectedTags().isEmpty()) {
                    for (Book book : books){
                        book.setFound(false);
                    }
                    library.search("");
                    refreshTableView();
                } else {
                    searchTags(controller.getSelectedTags());
                }
                previouslySelected = controller.getSelectedTags();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        refreshTableView();

    }

    public void handleDeleteButton() {
        int index = bookTable.getSelectionModel().getSelectedIndex();
        if (index == -1){return;}
        Book bookToDelete = bookTable.getSelectionModel().getSelectedItem();
        books.remove(index);
        library.deleteBook(bookToDelete);
        library.getFoundBooks().remove(bookToDelete);
        refreshTableView();
    }

    public void refreshTableView(){
        books = FXCollections.observableArrayList(library.getFoundBooks());
        bookTable.setItems(books);
        disableEditButton();
        disableDeleteButton();
    }

    public void exportJsonPath() {
        Stage stage = (Stage) exportButton.getScene().getWindow();
        FileChooser file = new FileChooser();
        file.setInitialFileName("library.json");
        FileChooser.ExtensionFilter fileExtensions = new FileChooser.ExtensionFilter("JSON File","*.json");
        file.getExtensionFilters().add(fileExtensions);
        file.setTitle("Choose Export Location");
        try {
            File f = file.showSaveDialog(stage);
            library.exportJson(f.getPath());
        } catch (NullPointerException e) {return;}
    }

    public void importJsonPath() {
        Stage stage = (Stage) importButton.getScene().getWindow();
        FileChooser file = new FileChooser();
        file.setInitialFileName("library.json");
        FileChooser.ExtensionFilter fileExtensions = new FileChooser.ExtensionFilter("JSON File","*.json");
        file.getExtensionFilters().add(fileExtensions);
        file.setTitle("Choose Import Location");
        try {
            File f = file.showOpenDialog(stage);
            library.importJson(f.getPath());
            library.search(null);
            refreshTableView();
        } catch (NullPointerException e) {return;}
    }

    public void showDetailedView() {
        int index = bookTable.getSelectionModel().getSelectedIndex();
        if (index == -1){
            titleLabel.setText("Title: ");
            authorsLabel.setText("Author(s): ");
            dateLabel.setText("Date: ");
            tagsLabel.setText("Tag(s): ");
            ratingLabel.setText("Rating: ");
            subtitleLabel.setText("Subtitle: ");
            isbnLabel.setText("ISBN: ");
            publisherLabel.setText("Publisher: ");
            editionLabel.setText("Edition: ");
            numberOfPagesLabel.setText("Number of Pages: ");
            coverLabel.setText("Cover: ");
            languageLabel.setText("Language: ");
            translatorsLabel.setText("Translator(s): ");
            coverImage.setImage(null);
            return;
        }
        if (library.getFoundBooks().get(index).getTitle() == null) {
            titleLabel.setText("Title: ");
        } else {
            titleLabel.setText("Title: " + library.getFoundBooks().get(index).getTitle());
        }
        if(library.getFoundBooks().get(index).getAuthors() == null) {
            authorsLabel.setText("Author(s): ");
        } else {
            authorsLabel.setText("Author(s): " + arrayListToString(library.getFoundBooks().get(index).getAuthors()));
        }
        if (library.getFoundBooks().get(index).getDate() == null) {
            dateLabel.setText("Date: ");
        } else {
            dateLabel.setText("Date: " + library.getFoundBooks().get(index).getDate());
        }
        if (library.getFoundBooks().get(index).getTags() == null) {
            tagsLabel.setText("Tag(s): ");
        } else {
            tagsLabel.setText("Tag(s): " + arrayListToString(library.getFoundBooks().get(index).getTags()));
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
        if (library.getFoundBooks().get(index).getCoverType() == null) {
            coverLabel.setText("Cover: ");
        } else {
            coverLabel.setText("Cover: " + library.getFoundBooks().get(index).getCoverType());
        }
        if (library.getFoundBooks().get(index).getLanguage() == null) {
            languageLabel.setText("Language: ");
        } else {
            languageLabel.setText("Language: " + library.getFoundBooks().get(index).getLanguage());
        }
        if (library.getFoundBooks().get(index).getTranslators() == null) {
            translatorsLabel.setText("Translator(s): ");
        } else {
            translatorsLabel.setText("Translator(s): " + arrayListToString(library.getFoundBooks().get(index).getTranslators()));
        }
        if(library.getFoundBooks().get(index).getCover() == null) {
            try {
                coverImage.setImage(new Image(getClass().getResource("default.png").openStream()));
            } catch (IOException e) {
                System.out.println();
            }
        } else {
            try {
                File file = new File(library.getFoundBooks().get(index).getCover());
                Image image;
                if (file.exists()) {
                    image = new Image(file.toURI().toString());
                } else {
                    image = new Image(getClass().getResource("default.png").openStream());
                }
                coverImage.setImage(image);
            } catch (IOException e) {
                System.out.println();
            }
        }
        disableDeleteButton();
        disableEditButton();
    }


    public void saveChanges() {
        library.updateJson();
    }


    public void searchList(ActionEvent event) {
        previouslySelected = null;
        for (Book book : books){
            book.setFound(false);
        }
        String txt = searchBar.getText();
        library.search(txt);
        refreshTableView();

    }

    public void searchTags(ArrayList<String> selectedTags) {
        library.setFoundBooks(new ArrayList<>(library.getBooks()));
        if(selectedTags != null) {
            if (library.getFoundBooks() != null) library.getFoundBooks().clear();
            for (Book book : library.getBooks()) {
                book.setFound(false);
            }
            for (Book book : library.getBooks()) {
                library.filterByTags(selectedTags, book);
            }
        }
        refreshTableView();
    }

    private String arrayListToString(ArrayList<String> arr) {
        String s = "";
        if(arr == null || arr.isEmpty()) return "";
        for (String item : arr) {
            s = s.concat(item + ", ");
        }
        s = s.substring(0, s.length() - 2);
        return s;
    }


    @FXML
    public void handleHelp() {
        try {
            // Get the file as a stream
            InputStream is = getClass().getResourceAsStream("Help.pdf");

            File tempFile = File.createTempFile("Help", ".pdf"); // Temporary file and copy the resource to it
            tempFile.deleteOnExit();
            try (OutputStream os = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) != -1) {
                    os.write(buffer, 0, length);
                }
            }
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(tempFile);
            }
        } catch (IOException ex) {

        }
    }
}
