package teamseven.ce216project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
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

    public void initialize(Library library) { this.library = library; }

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


    }

    public void handleEditButton(ActionEvent event) {

    }

    public void handleDeleteButton(ActionEvent event) {




    }

}
