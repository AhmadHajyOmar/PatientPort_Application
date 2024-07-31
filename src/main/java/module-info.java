module com.example.praxissoftware {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.praxissoftware to javafx.fxml;
    exports com.example.praxissoftware;
}