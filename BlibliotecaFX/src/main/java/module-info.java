module org.example.blibliotecafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires org.kordamp.bootstrapfx.core;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    opens org.example.blibliotecafx to javafx.fxml;
    exports org.example.blibliotecafx;
    exports org.example.blibliotecafx.Entities;
    opens org.example.blibliotecafx.Entities to javafx.fxml, org.hibernate.orm.core;
    exports org.example.blibliotecafx.Service;
    exports org.example.blibliotecafx.Gestiones;

    opens org.example.blibliotecafx.Gestiones to javafx.fxml;
    opens org.example.blibliotecafx.Service to javafx.fxml;
    exports org.example.blibliotecafx.App;
    opens org.example.blibliotecafx.App to javafx.fxml;
}