module com.williamoverflow.cmpt354yelpgui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires java.sql;


    opens com.williamoverflow.cmpt354yelpgui to javafx.fxml;
    exports com.williamoverflow.cmpt354yelpgui;

    exports com.williamoverflow.cmpt354yelpgui.entities;
    exports com.williamoverflow.cmpt354yelpgui.functions;

}