module com.alana.bibliotecafxml {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.alana.bibliotecafxml to javafx.fxml;
    exports com.alana.bibliotecafxml;
    exports com.alana.bibliotecafxml.view;
    opens com.alana.bibliotecafxml.view to javafx.fxml;
}