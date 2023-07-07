module com.ooad.ooad {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.ooad.ooad.controller to javafx.fxml;
    exports com.ooad.ooad;
}