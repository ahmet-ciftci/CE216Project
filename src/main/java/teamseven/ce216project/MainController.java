package teamseven.ce216project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

public class MainController {
    private Library library;

    @FXML
    private Button exportButton;

    public void initialize(Library library) {
        this.library = library;
    }

    public void exportJsonPath() {
        Stage stage = (Stage) exportButton.getScene().getWindow();
        FileChooser file = new FileChooser();
        file.setInitialFileName("library.json");
        FileChooser.ExtensionFilter fileExtensions = new FileChooser.ExtensionFilter("File","*.json");
        file.getExtensionFilters().add(fileExtensions);
        file.setTitle("Choose Export Location");
        File f = file.showSaveDialog(stage);
        library.exportJson(f.getPath());
    }
}
