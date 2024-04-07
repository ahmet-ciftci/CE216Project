package teamseven.ce216project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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

        MainController controller = loader.getController();
        Library library = new Library();

        library.search(null);
        controller.initialize(library);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
