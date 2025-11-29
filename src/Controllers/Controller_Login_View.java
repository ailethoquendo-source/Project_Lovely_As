package Controllers;

import DataStructures.Data_Manager;
import DataStructures.List_Double_User;
import Models.Nodes.Node_User;
import java.io.IOException;
import java.net.*;
import java.util.ResourceBundle;
import java.util.logging.*;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
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

    private final List_Double_User userList = Data_Manager.getManager().getList_user();

    private final Image gitNormal = new Image(getClass().getResourceAsStream("/Images/git1.png"));
    private final Image gitHover = new Image(getClass().getResourceAsStream("/Images/git2.png"));
    private final Image whatsNormal = new Image(getClass().getResourceAsStream("/Images/whats1.png"));
    private final Image whatsHover = new Image(getClass().getResourceAsStream("/Images/whats2.png"));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userList.load_data_from_file_users();

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

    public void alert(Alert.AlertType alertType, String tit, String mj) {
        Alert a = new Alert(alertType);
        a.setTitle(tit);
        a.setContentText(mj);
        a.showAndWait();
    }

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
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            return;
        }

        if (validateUser(username, password)) {
            try {
                event.consume();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Main_View.fxml"));
                Parent root = loader.load();

                Controller_Main_View controller = loader.getController();
                
                Node_User foundUser = userList.search_email(username);
                boolean isAdmin = foundUser != null && foundUser.getUser() instanceof Models.Admin;

                Scene scene = new Scene(root);
                Stage newStage = new Stage();
                                
                Stage oldStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                double oldWidth = oldStage.getWidth();
                double oldHeight = oldStage.getHeight();
                double oldX = oldStage.getX();
                double oldY = oldStage.getY();
                boolean isMaximized = oldStage.isMaximized();
                                
                newStage.setScene(scene);
                if (isMaximized) {
                    newStage.setMaximized(true);
                } else {
                    newStage.setWidth(oldWidth);
                    newStage.setHeight(oldHeight);
                    newStage.setX(oldX);
                    newStage.setY(oldY);
                }
                
                newStage.setOnShowing(e -> {
                    controller.getEmailLabel().setText(username);
                    controller.setUserType(isAdmin);
                });
                
                oldStage.close();
                
                newStage.show();
                                
            } catch (IOException e) {
                Logger.getLogger(Controller_Login_View.class.getName()).log(Level.SEVERE, null, e);
            }
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
            
            Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            double currentWidth = currentStage.getWidth();
            double currentHeight = currentStage.getHeight();
            double currentX = currentStage.getX();
            double currentY = currentStage.getY();
            boolean isMaximized = currentStage.isMaximized();

            Parent root = FXMLLoader.load(getClass().getResource("/Views/Signing_View.fxml"));
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
                        
            if (isMaximized) {
                currentStage.setMaximized(true);
            } else {
                currentStage.setWidth(currentWidth);
                currentStage.setHeight(currentHeight);
                currentStage.setX(currentX);
                currentStage.setY(currentY);
            }
            
            currentStage.show();
        } catch (IOException e) {
            Logger.getLogger(Controller_Login_View.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public boolean validateUser(String email, String password) {

        Node_User foundUser = userList.search_email(email);

        if (foundUser != null) {

            if (foundUser.getUser().getPassword().equals(password)) {
                return true;
            } else {
                alert(Alert.AlertType.INFORMATION, "Aviso", "Contraseña incorrecta.");
                return false;
            }
        }
        alert(Alert.AlertType.CONFIRMATION, "Aviso", "Datos incorrectos.");
        return false;
    }
}
