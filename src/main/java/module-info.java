module com.ooad.ooad {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.ooad.ooad.controller to javafx.fxml;
    opens com.ooad.ooad.entity to javafx.base;
    exports com.ooad.ooad;
}