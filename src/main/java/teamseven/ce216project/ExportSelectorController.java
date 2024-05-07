package teamseven.ce216project;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExportSelectorController {
    @FXML
    private ListView<Book> bookList;
    private final Map<Book, BooleanProperty> itemSelectedMap = new HashMap<>();

    public void initialize(ArrayList<Book> books) {
        bookList.setCellFactory(CheckBoxListCell.forListView(item -> {
            BooleanProperty selected = itemSelectedMap.get(item);
            if (selected == null) {
                selected = new SimpleBooleanProperty(false);
                itemSelectedMap.put(item, selected);
            }
            return selected;
        }));

        if(books != null){
            bookList.setItems(FXCollections.observableList(books));
        }
    }

    public ArrayList<Book> getSelectedBooks() {
        try {
            ObservableList<Book> selectedBooks = bookList.getItems().filtered(item -> {
                BooleanProperty selected = itemSelectedMap.get(item);
                return selected != null && selected.get();
            });
            return new ArrayList<>(selectedBooks);
        }catch (Exception e){
            System.err.println("No item is selected");
        }
        return null;
    }
}
