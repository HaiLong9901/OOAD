module com.ooad.ooad {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.ooad.ooad to javafx.fxml;
    exports com.ooad.ooad;
}