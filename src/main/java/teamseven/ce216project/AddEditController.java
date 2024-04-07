package teamseven.ce216project;

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

    private void handleAuthorAdd(){

    }
    private void handleAuthorDelete(){

    }
    private void handleTagAdd(){

    }
    private void handleTagDelete(){

    }
    private void handleTranslatorAdd(){

    }
    private void handleTranslatorDelete(){

    }

    private void handleLoadCoverPath(){

    }
    private void handleDeleteCoverPath(){

    }
}
