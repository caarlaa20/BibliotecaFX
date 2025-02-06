module org.example.blibliotecafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.blibliotecafx to javafx.fxml;
    exports org.example.blibliotecafx;
}