module finleycrowther.au.physicssim {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;


    opens finleycrowther.au.physicssim to javafx.fxml;
    exports finleycrowther.au.physicssim;
    exports finleycrowther.au.physicssim.Controllers;
    opens finleycrowther.au.physicssim.Controllers to javafx.fxml;
}