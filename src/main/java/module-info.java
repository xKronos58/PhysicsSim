module finleycrowther.au.physicssim {
    requires java.desktop;
    requires javafx.media;
    requires one.jpro.platform.mdfx;
    requires javafx.fxml;
    requires javafx.web;


    exports finleycrowther.au.physicssim;
    opens finleycrowther.au.physicssim to javafx.fxml;
    exports finleycrowther.au.physicssim.Controllers;
    opens finleycrowther.au.physicssim.Controllers to javafx.fxml;
    exports finleycrowther.au.physicssim.FluidSimulation;
    opens finleycrowther.au.physicssim.FluidSimulation to javafx.fxml;
}