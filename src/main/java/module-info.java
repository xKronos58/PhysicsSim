module finleycrowther.au.physicssim {
    requires java.desktop;
    requires javafx.media;
    requires one.jpro.platform.mdfx;
    requires javafx.fxml;
    requires javafx.web;


    opens finleycrowther.au.physicssim to javafx.fxml;
    exports finleycrowther.au.physicssim;
    exports finleycrowther.au.physicssim.Controllers;
    opens finleycrowther.au.physicssim.Controllers to javafx.fxml;
}