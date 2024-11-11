package finleycrowther.au.physicssim.Controllers;

import finleycrowther.au.physicssim.PhysicsSim;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.File;
import java.util.Objects;

/**
 * MainController class is the controller for the main page of the application.
 * It is responsible for setting up the landing page video, the navigation bar, and the on hover and on click effects.
 * @see javafx.scene.media.MediaView
 * @see javafx.scene.media.MediaPlayer
 * @see javafx.scene.media.Media
 * @see javafx.scene.text.Text
 * @see javafx.scene.layout.AnchorPane
 * @see javafx.scene.layout.VBox
 * @see javafx.fxml.FXMLLoader
 * @see javafx.scene.Parent
 * @see finleycrowther.au.physicssim.PhysicsSim
 * */
public class MainController {
    public MediaView landingVideoContainer;
    public AnchorPane root;
    public VBox nav_list;
    public AnchorPane nav_back;
    public Text new_simulation;
    public Text load_simulation;
    public Text documentation;
    public Text statistics;
    public Text settings;

    /**
     * Initializes the main page of the application by setting up the landing page video,
     * the navigation bar, and the on hover and on click effects.
     * */
    public void initialize() {

        // Load the landing page video
        String landingPageVideo = Objects.requireNonNull(PhysicsSim.class.getResource("videos/LandingVideo.mp4")).getPath();

        Media media = new Media(new File(landingPageVideo).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setOnError(() -> System.err.println("Error occurred: " + mediaPlayer.getError().getMessage()));

        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        // Add MediaView to the AnchorPane (if not already done in FXML)
        landingVideoContainer.setMediaPlayer(mediaPlayer);

//        DoubleProperty mvw = landingVideoContainer.fitWidthProperty();
//        DoubleProperty mvh = landingVideoContainer.fitHeightProperty();
//        mvw.bind(Bindings.selectDouble(landingVideoContainer.sceneProperty(), "width"));
//        mvh.bind(Bindings.selectDouble(landingVideoContainer.sceneProperty(), "height"));

        //TODO - Fix video resizing to fully cover screen.

        // Disable preserve ratio to allow stretching if necessary
        landingVideoContainer.setPreserveRatio(false);

        DoubleProperty mvw = landingVideoContainer.fitWidthProperty();
        DoubleProperty mvh = landingVideoContainer.fitHeightProperty();

        // Define aspect ratio (e.g., 16:9)
        double aspectRatio = 16.0 / 9.0;

        // Set up clipping to crop excess video
        Rectangle clip = new Rectangle();
        landingVideoContainer.setClip(clip);

        // Bind width and height to keep the aspect ratio consistent with the window size
        landingVideoContainer.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene == null) return;
            // Bind when the scene is attached to the window
            newScene.widthProperty().addListener((obsWidth, oldWidth, newWidth) -> {
                adjustMediaSize(newWidth.doubleValue(), newScene.getHeight(), aspectRatio, mvw, mvh, clip);
            });
            newScene.heightProperty().addListener((obsHeight, oldHeight, newHeight) -> {
                adjustMediaSize(newScene.getWidth(), newHeight.doubleValue(), aspectRatio, mvw, mvh, clip);
            });
        });


        // Set on hover affect.
        onHover(new_simulation, load_simulation, documentation, statistics, settings);

        // Set Page Navigation
        setOnClick(new clickItem(new_simulation, "new_simulation_main.fxml"),
                new clickItem(load_simulation, "load_simulation_main.fxml"),
                new clickItem(documentation, "documentation.fxml"),
                new clickItem(statistics, "statistics.fxml"),
                new clickItem(settings, "settings_Root.fxml"));
    }

    /**
     * Resizes the landing page media view to fit a 16:9 aspect ratio to ensure that it does not look weird
     * @see MediaView
     * @param newWidth The new width of the window
     * @param newHeight The new height of the window
     * @param aspectRatio The aspect ratio of the media view
     * @param mvw The width property of the media view
     * @param mvh The height property of the media view
     * @param clip The clip to apply to the media view
     *
     * */
    private void adjustMediaSize(double newWidth, double newHeight, double aspectRatio, DoubleProperty mvw, DoubleProperty mvh, Rectangle clip) {
        double windowAspectRatio = newWidth / newHeight;

        if (windowAspectRatio > aspectRatio) {
            // Window is wider than 16:9, so adjust height and clip the excess width
            landingVideoContainer.setFitHeight(newHeight);
            landingVideoContainer.setFitWidth(newHeight * aspectRatio);  // Scale width to maintain aspect ratio

            // Clip excess width (center the video horizontally)
            clip.setWidth(newWidth);  // Clip width to the window width
            clip.setHeight(newHeight);  // Clip height to the window height
            clip.setX((landingVideoContainer.getFitWidth() - newWidth) / 2);  // Offset to center
            clip.setY(0);
        } else {
            // Window is taller, adjust width and clip the excess height
            landingVideoContainer.setFitWidth(newWidth);
            landingVideoContainer.setFitHeight(newWidth / aspectRatio);  // Scale height to maintain aspect ratio

            // Clip excess height (center the video vertically)
            clip.setWidth(newWidth);  // Clip width to the window width
            clip.setHeight(newHeight);  // Clip height to the window height
            clip.setX(0);
            clip.setY((landingVideoContainer.getFitHeight() - newHeight) / 2);  // Offset to center
        }
    }

    /**
     * Sets the text button on hover effect
     * @param text list of all text elements for the on hover effect to be applied to. */
    private void onHover(Text... text) {
        for(Text t : text) t.setOnMouseEntered(_ -> t.setStyle("-fx-fill: #c2c2c2"));
        for(Text t : text) t.setOnMouseExited(_ -> t.setStyle("-fx-fill: #ffffff"));
    }

    /**
     * Sets the on click event for the text buttons to navigate to the respective pages
     * @param items list of all text elements and their respective page names */
    private void setOnClick(clickItem... items) {
        for(clickItem item : items) {
            item.button.setOnMouseClicked(_ -> {
                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(PhysicsSim.class.getResource("fxml/" + item.PageName)));
                try {
                    Parent root = loader.load();
                    PhysicsSim.mainScene.setRoot(root);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * Helper class to store the text button and the page name it navigates to
     * @param button The text button to be clicked
     * @param PageName The name of the page to navigate to */
    record clickItem(Text button, String PageName){}
}
