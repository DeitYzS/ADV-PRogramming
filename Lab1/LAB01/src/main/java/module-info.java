module se2333.chapter1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens se2333.chapter1 to javafx.fxml;
    exports se2333.chapter1;
}