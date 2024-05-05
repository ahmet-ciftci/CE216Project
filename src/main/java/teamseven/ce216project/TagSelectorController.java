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

public class TagSelectorController {
    @FXML
    private ListView<String> tagList;
    private final Map<String, BooleanProperty> itemSelectedMap = new HashMap<>();

    public void initialize(ArrayList<String> uniqueTags, ArrayList<String> previouslySelected) {
        tagList.setCellFactory(CheckBoxListCell.forListView(item -> {
            BooleanProperty selected = itemSelectedMap.get(item);
            if (selected == null) {
                if (previouslySelected == null) {selected = new SimpleBooleanProperty(false);}
                else {selected = new SimpleBooleanProperty(previouslySelected.contains(item));}
                itemSelectedMap.put(item, selected);
            }
            return selected;
        }));

        if(uniqueTags != null){
            tagList.setItems(FXCollections.observableList(uniqueTags));
        }
    }

    public ArrayList<String> getSelectedTags() {
        try {
            ObservableList<String> selectedTags = tagList.getItems().filtered(item -> {
                BooleanProperty selected = itemSelectedMap.get(item);
                return selected != null && selected.get();
            });
            return new ArrayList<>(selectedTags);
        }catch (Exception e){
            System.err.println("No item is selected");
        }
        return null;
    }


}
