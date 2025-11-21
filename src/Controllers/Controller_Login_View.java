package Controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class Controller_Login_View implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private ImageView githubIcon;
    @FXML
    private ImageView whatsappIcon;
    @FXML
    private ImageView settingsIcon;
    @FXML
    private ImageView unboxingImage;
    @FXML
    private HBox mainContainer;
    @FXML
    private VBox leftSection;
    @FXML
    private VBox rightSection;
    @FXML
    private ImageView logoImage;
    @FXML
    private Label userIcon;
    @FXML
    private Label passwordIcon;

    private final Image gitNormal = new Image(getClass().getResourceAsStream("/Images/git1.png"));
    private final Image gitHover = new Image(getClass().getResourceAsStream("/Images/git2.png"));
    private final Image whatsNormal = new Image(getClass().getResourceAsStream("/Images/whats1.png"));
    private final Image whatsHover = new Image(getClass().getResourceAsStream("/Images/whats2.png"));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (unboxingImage != null) {
            unboxingImage.imageProperty().addListener((obs, oldImage, newImage) -> {
                if (newImage != null) {
                    applyUnboxingClip();
                }
            });

            if (unboxingImage.getImage() != null) {
                applyUnboxingClip();
            }
        }
    }

    private void applyUnboxingClip() {
        double width = 520.0;
        double height = 600.0;
        double radius = 30.0;

        Rectangle clip = new Rectangle(0, 0, width, height);

        clip.setArcWidth(radius * 2);
        clip.setArcHeight(radius * 2);

        Image img = unboxingImage.getImage();
        if (img != null) {
            double imageWidth = img.getWidth();
            double imageHeight = img.getHeight();

            double viewportY = imageHeight * 0.15;
            double viewportHeight = Math.min(height, imageHeight * 0.85); // Mostrar el 85% restante

            unboxingImage.setViewport(new javafx.geometry.Rectangle2D(0, viewportY, imageWidth, viewportHeight));
        }

        unboxingImage.setClip(clip);
    }

    @FXML
    private void onGithubClick(MouseEvent event) {
        try {
            java.awt.Desktop.getDesktop().browse(new URI("https://github.com/ailethoquendo-source/Project_Lovely_As.git"));
        } catch (IOException | URISyntaxException e) {
            Logger.getLogger(Controller_Login_View.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void onGithubEnter(MouseEvent event) {
        githubIcon.setImage(gitHover);
    }

    @FXML
    private void onGithubExit(MouseEvent event) {
        githubIcon.setImage(gitNormal);
    }

    @FXML
    private void onWhatsAppClick(MouseEvent event) {

    }

    @FXML
    private void onWhatsAppEnter(MouseEvent event) {
        whatsappIcon.setImage(whatsHover);
    }

    @FXML
    private void onWhatsAppExit(MouseEvent event) {
        whatsappIcon.setImage(whatsNormal);
    }

    @FXML
    private void onLoginClick(ActionEvent event) {

    }

}
