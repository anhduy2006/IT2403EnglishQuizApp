module com.anhduy.quizapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.anhduy.quizapp to javafx.fxml;
    exports com.anhduy.quizapp;
}
