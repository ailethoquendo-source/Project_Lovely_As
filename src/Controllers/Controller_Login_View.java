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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.RotateTransition;
import javafx.util.Duration;

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
    private StackPane unboxingContainer;
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
    @FXML
    private StackPane settingsPopup;
    @FXML
    private Button loginOptionButton;
    @FXML
    private Button signingUpOptionButton;

    private final Image gitNormal = new Image(getClass().getResourceAsStream("/Images/git1.png"));
    private final Image gitHover = new Image(getClass().getResourceAsStream("/Images/git2.png"));
    private final Image whatsNormal = new Image(getClass().getResourceAsStream("/Images/whats1.png"));
    private final Image whatsHover = new Image(getClass().getResourceAsStream("/Images/whats2.png"));


    private void applyRoundedCorners() {
        double radius = 30.0;
        double width = unboxingImage.getFitWidth();
        double height = unboxingImage.getFitHeight();
                
        Rectangle clip = new Rectangle(width, height);
        clip.setArcWidth(radius * 2);
        clip.setArcHeight(radius * 2);
        
        Image img = unboxingImage.getImage();
        if (img != null && (img.getWidth() > width || img.getHeight() > height)) {
            double imgWidth = img.getWidth();
            double imgHeight = img.getHeight();
            double startX = Math.max(0, (imgWidth - width) / 2);
            double startY = Math.max(0, (imgHeight - height) / 2);
            unboxingImage.setViewport(new Rectangle2D(startX, startY, width, height));
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
        // Obtener los valores de los campos
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        // Validar campos vacíos
        if (username.isEmpty() || password.isEmpty()) {
            // Mostrar mensaje de error si hay campos vacíos
            // Por ahora, solo validamos
            return;
        }

        // Validar usuario con el archivo
        if (Controller_Signing_View.validateUser(username, password)) {
            // Usuario válido - aquí puedes navegar a la vista principal
            // Por ahora, solo validamos
        } else {
            // Usuario inválido - mostrar mensaje de error
            // Por ahora, solo validamos
        }
    }

    @FXML
    private void onSettingsClick(MouseEvent event) {
        if (settingsPopup != null) {
            settingsPopup.setVisible(!settingsPopup.isVisible());            
            event.consume();
        }
    }

    @FXML
    private void onSettingsEnter(MouseEvent event) {
        if (settingsIcon != null) {
            RotateTransition rotateTransition = new RotateTransition(Duration.millis(500), settingsIcon);
            rotateTransition.setFromAngle(0);
            rotateTransition.setToAngle(360);
            rotateTransition.setCycleCount(1);
            rotateTransition.setAutoReverse(false);
            rotateTransition.play();
        }
    }

    @FXML
    private void onSettingsExit(MouseEvent event) {
        if (settingsIcon != null) {
            RotateTransition rotateTransition = new RotateTransition(Duration.millis(500), settingsIcon);
            rotateTransition.setFromAngle(settingsIcon.getRotate());
            rotateTransition.setToAngle(0);
            rotateTransition.setCycleCount(1);
            rotateTransition.setAutoReverse(false);
            rotateTransition.play();
        }
    }

    @FXML
    private void onLoginOptionClick(ActionEvent event) {
        try {            
            event.consume();            
            if (settingsPopup != null) {
                settingsPopup.setVisible(false);
            }            
        } catch (Exception e) {
            Logger.getLogger(Controller_Login_View.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void onSigningUpOptionClick(ActionEvent event) {
        try {            
            event.consume();            
            if (settingsPopup != null) {
                settingsPopup.setVisible(false);
            }
                        
            Parent root = FXMLLoader.load(getClass().getResource("/Views/Signing_View.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(Controller_Login_View.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (unboxingImage != null && unboxingContainer != null) {
            applyRoundedCorners();
        }
                
        if (settingsPopup != null) {
            settingsPopup.setOnMouseClicked((MouseEvent event) -> {                
                Object target = event.getTarget();
                                
                boolean isClickOnButton = false;
                
                if (target == loginOptionButton || target == signingUpOptionButton) {
                    isClickOnButton = true;
                } else if (target instanceof javafx.scene.Node) {
                    Node node = (javafx.scene.Node) target;                    
                    Node parent = node.getParent();
                    while (parent != null && parent != settingsPopup) {
                        if (parent == loginOptionButton || parent == signingUpOptionButton) {
                            isClickOnButton = true;
                            break;
                        }
                        parent = parent.getParent();
                    }
                }
                                
                if (!isClickOnButton) {
                    settingsPopup.setVisible(false);
                    event.consume();
                }
            });
                        
            if (loginOptionButton != null) {
                loginOptionButton.setOnMouseClicked((MouseEvent event) -> {
                    event.consume();
                });
            }
            
            if (signingUpOptionButton != null) {
                signingUpOptionButton.setOnMouseClicked((MouseEvent event) -> {
                    event.consume();
                });
            }
        }
    }

}
