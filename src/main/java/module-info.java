module com.williamoverflow.cmpt354yelpgui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens com.williamoverflow.cmpt354yelpgui to javafx.fxml;
    exports com.williamoverflow.cmpt354yelpgui;
}