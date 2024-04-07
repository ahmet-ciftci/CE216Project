package teamseven.ce216project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;


public class MainController{
    private Library library;
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

    public void initialize(Library library) {
        this.library = library;
        refreshTableView();
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorsCol.setCellValueFactory(new PropertyValueFactory<>("authors"));
        tagsCol.setCellValueFactory(new PropertyValueFactory<>("tags"));
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
            dialog.setDialogPane(bookDialogPane);
            dialog.setTitle("Add a book");


            Optional<ButtonType> buttonType = dialog.showAndWait();
            if(buttonType.get() == ButtonType.APPLY){
                library.addBook(controller.getTitleField(),controller.getSubtitleField(),controller.getISBNField(),controller.getPublisherField(),controller.getDateField(),controller.getEditionField(),controller.getNumberOfPagesField(),controller.getCoverField(), null,controller.getLanguageField(),controller.getRatingField(),null,null,null);
                // SELF-NOTE: DONT FORGET TO ADD PATH AND LIST
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        refreshTableView();

    }

    public void handleEditButton(ActionEvent event) {

    }

    public void handleDeleteButton(ActionEvent event) {



    }
    public void refreshTableView(){
        books = FXCollections.observableArrayList(library.getFoundBooks());
        bookTable.setItems(books);
    }

}
