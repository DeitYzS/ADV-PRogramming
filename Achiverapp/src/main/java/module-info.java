module com.example.compressfile {
    requires javafx.controls;
    requires javafx.fxml;


    opens archiver to javafx.fxml;
    exports archiver;
}