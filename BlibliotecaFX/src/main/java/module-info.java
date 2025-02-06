module org.example.blibliotecafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    opens org.example.blibliotecafx to javafx.fxml;
    exports org.example.blibliotecafx;
    exports org.example.blibliotecafx.Entities;
    opens org.example.blibliotecafx.Entities to javafx.fxml;
}