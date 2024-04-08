module teamseven.ce216project {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens teamseven.ce216project to javafx.fxml,com.google.gson;
    exports teamseven.ce216project;
}