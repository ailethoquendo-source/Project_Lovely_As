package Controllers;

import DataStructures.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.*;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller_Signing_View implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Button confirmButton;
    @FXML
    private ImageView settingsIcon;
    @FXML
    private StackPane settingsPopup;
    @FXML
    private Button loginOptionButton;
    @FXML
    private Button signingUpOptionButton;
    @FXML
    private Label errorLabel;
    @FXML
    private HBox mainContainer;
    @FXML
    private VBox leftSection;
    @FXML
    private StackPane signingImageContainer;
    @FXML
    private ImageView signingImage;
    @FXML
    private VBox rightSection;
    @FXML
    private StackPane logoContainer;
    @FXML
    private ImageView logoImage;
    @FXML
    private Label userIcon1;
    @FXML
    private TextField useridentField;
    @FXML
    private Label userIcon;
    @FXML
    private Label passwordIcon;
    @FXML
    private Label confirmPasswordIcon;
    
    private int logoClickCount = 0;
    private Timer clickTimer;
    private static final long CLICK_TIMEOUT = 2000;
    private boolean isAdmin = false;

    private final List_Double_User userList = Data_Manager.getManager().getList_user();
    @FXML
    private Label userIcon11;
    @FXML
    private TextField useremailField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            userList.load_data_from_file_users();
        } catch (Exception e) {
            Logger.getLogger(Controller_Signing_View.class.getName()).log(Level.WARNING, "No se pudieron cargar usuarios existentes", e);
        }

        if (logoImage != null) {
            logoImage.setOnMouseClicked((MouseEvent event) -> {
                onLogoClick(event);
            });
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

        if (useridentField != null) {
            useridentField.setOnKeyTyped(e -> hideError());
        }
        if (usernameField != null) {
            usernameField.setOnKeyTyped(e -> hideError());
        }
        if (passwordField != null) {
            passwordField.setOnKeyTyped(e -> hideError());
        }
        if (confirmPasswordField != null) {
            confirmPasswordField.setOnKeyTyped(e -> hideError());
        }
                
        Platform.runLater(() -> {
            if (confirmButton != null && confirmButton.getScene() != null) {
                Stage stage = (Stage) confirmButton.getScene().getWindow();
                if (stage != null) {
                    stage.setOnCloseRequest(e -> {
                        e.consume();
                        redirectToLogin(stage);
                    });
                }
            }
        });
    }
    
    private void redirectToLogin(Stage currentStage) {
        try {            
            double currentWidth = currentStage.getWidth();
            double currentHeight = currentStage.getHeight();
            double currentX = currentStage.getX();
            double currentY = currentStage.getY();
            boolean isMaximized = currentStage.isMaximized();
                        
            currentStage.setOnCloseRequest(null);
            
            Parent root = FXMLLoader.load(getClass().getResource("/Views/Login_View.fxml"));
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
            Logger.getLogger(Controller_Signing_View.class.getName()).log(Level.SEVERE, "Error al redirigir al login", e);
        }
    }
    
    @FXML
    private void onConfirmRegistration(ActionEvent event) {
        hideError();

        if (useridentField.getText().isEmpty() || useremailField.getText().isEmpty() || passwordField.getText().isEmpty() || confirmPasswordField.getText().isEmpty()) {
            showError("Por favor, complete todos los campos.");
            return;
        }

        if (!isValidIdentification(useridentField.getText())) {
            showError("La identificación debe ser numérica y tener al menos 7 dígitos.");
            return;
        }

        if (!isValidUsername(useremailField.getText())) {
            showError("El email debe ser válido.");
            return;
        }

        if (passwordField.getText().length() < 6) {
            showError("La contraseña debe tener al menos 6 caracteres.");
            return;
        }

        if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            showError("Las contraseñas no coinciden. Por favor, verifique.");
            return;
        }

        String roll = isAdmin ? "ADMIN" : "CLIENT";

        try {                       

            userList.addUser(usernameField, useridentField, useremailField, passwordField, roll);
           
            userList.save_data_file_users();

            showSuccess("Registro exitoso como " + roll + ". Redirigiendo al login...");
            clearFields();
            logoClickCount = 0;
            isAdmin = false;
                        
            Stage currentStage = (Stage) confirmButton.getScene().getWindow();
            double currentWidth = currentStage.getWidth();
            double currentHeight = currentStage.getHeight();
            double currentX = currentStage.getX();
            double currentY = currentStage.getY();
            boolean isMaximized = currentStage.isMaximized();
                        
            Timer redirectTimer = new Timer();
            redirectTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("/Views/Login_View.fxml"));
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
                            Logger.getLogger(Controller_Signing_View.class.getName()).log(Level.SEVERE, "Error al redirigir al login", e);
                        }
                    });
                }
            }, 2000);
            
        } catch (NumberFormatException e) {
            showError("La identificación debe ser un número válido.");
        } catch (Exception e) {
            Logger.getLogger(Controller_Signing_View.class.getName()).log(Level.SEVERE, "Error al guardar el usuario", e);
            showError("Error al guardar el registro. Por favor, intente nuevamente.");
        }
    }

    @FXML
    private void onLogoClick(MouseEvent event) {
        logoClickCount++;

        if (clickTimer != null) {
            clickTimer.cancel();
        }

        if (logoClickCount >= 3) {
            isAdmin = true;
            showSuccess("Modo Administrador activado. El próximo registro será como ADMIN.");
            logoClickCount = 0;
        } else {
            clickTimer = new Timer();
            clickTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    logoClickCount = 0;
                    isAdmin = false;
                }
            }, CLICK_TIMEOUT);
        }

        event.consume();
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
            
            Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            double currentWidth = currentStage.getWidth();
            double currentHeight = currentStage.getHeight();
            double currentX = currentStage.getX();
            double currentY = currentStage.getY();
            boolean isMaximized = currentStage.isMaximized();

            Parent root = FXMLLoader.load(getClass().getResource("/Views/Login_View.fxml"));
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
            Logger.getLogger(Controller_Signing_View.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void onSigningUpOptionClick(ActionEvent event) {
        try {
            event.consume();
            if (settingsPopup != null) {
                settingsPopup.setVisible(false);
            }
        } catch (Exception e) {
            Logger.getLogger(Controller_Signing_View.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private boolean isValidIdentification(String identification) {
        return identification.matches("^[0-9]{7,}$");
    }

    private boolean isValidUsername(String username) {
        return username.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }  

    private void showError(String message) {
        if (errorLabel != null) {
            errorLabel.setText(message);
            errorLabel.setVisible(true);
            errorLabel.setStyle("-fx-text-fill: #FF0000;");
        }
    }

    private void showSuccess(String message) {
        if (errorLabel != null) {
            errorLabel.setText(message);
            errorLabel.setVisible(true);
            errorLabel.setStyle("-fx-text-fill: #00AA00;");
        }
    }

    private void hideError() {
        if (errorLabel != null) {
            errorLabel.setVisible(false);
            errorLabel.setText("");
        }
    }

    private void clearFields() {
        if (useridentField != null) {
            useridentField.clear();
        }
        if (usernameField != null) {
            usernameField.clear();
        }
        if (passwordField != null) {
            passwordField.clear();
        }
        if (confirmPasswordField != null) {
            confirmPasswordField.clear();
        }
    }
}
