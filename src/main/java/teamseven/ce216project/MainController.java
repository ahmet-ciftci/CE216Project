package teamseven.ce216project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;


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


}
