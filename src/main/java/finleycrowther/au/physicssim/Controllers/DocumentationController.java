package finleycrowther.au.physicssim.Controllers;

import finleycrowther.au.physicssim.PhysicsSim;
import javafx.scene.layout.AnchorPane;
import one.jpro.platform.mdfx.MarkdownView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class DocumentationController {
    public AnchorPane ContentRoot;

    public void initialize() {
//        String DefaultFile = Objects.requireNonNull(PhysicsSim.class.getResource("Documentation/README.md")).toExternalForm();
        // Placeholder absolute path until i can figure out why the above line doesn't work
        String DefaultFile = "/Users/finleycrowther/Desktop/Code/PhysicsSim/PhysicsSim/src/main/resources/finleycrowther/au/physicssim/Documentation/GravitationalField.md";

        try {
            String mdContent = new String(Files.readAllBytes(Paths.get(DefaultFile)));

            MarkdownView mdv = new MarkdownView(mdContent);
            ContentRoot.getChildren().add(mdv);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
