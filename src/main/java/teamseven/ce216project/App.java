package teamseven.ce216project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {throw new RuntimeException(e);}

        MainController mainController = loader.getController();
        ArrayList<String> bismarckTags = new ArrayList<>();
        ArrayList<String> hoodTags = new ArrayList<>();
        bismarckTags.add("Otto");
        bismarckTags.add("Prinz Eugen");
        bismarckTags.add("HoodDestroyer");
        hoodTags.add("Sunk");
        hoodTags.add("15 inch");
        hoodTags.add("Prince of Wales");

        Library library = new Library();
        library.addBook("efes","babuuuş","55459512","serhat","23.521.620","3","2532","yok","","turkish","10",null,null,null);
        library.addBook("bismarck","Battleship","1820-1894","prussia","1941","1","9000","yok","","turkish","10",null,null,bismarckTags);
        library.addBook("Hood","Battleship","1900-1941","britain","1900","1","19000","yok","","turkish","10",null,null,hoodTags);
        library.addBook("Bismarck","Battleship","1820-1894","prussia","1941","1","9000","yok","","turkish","10",null,null,bismarckTags);

        library.addBook("title","asd","1239512","ahmet","23.521.620","3","2532","yok","","turkish","10",null,null,null);
        mainController.initialize(library);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
