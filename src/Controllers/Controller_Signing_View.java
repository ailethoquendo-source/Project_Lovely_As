package Controllers;

import DataStructures.Data_Manager;
import DataStructures.List_Double_User;
import Models.Admin;
import Models.Client;
import Models.Nodes.Node_User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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

    private static final String USERS_FILE_PATH = "src/Files/Users";
    private static final String DELIMITER = ":";
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
    
    // Contador de clics en el logo para determinar Admin/Client
    private int logoClickCount = 0;
    private Timer clickTimer;
    private static final long CLICK_TIMEOUT = 2000; // 2 segundos para resetear el contador
    private boolean isAdmin = false;
        
    private Data_Manager dataManager;
    private List_Double_User userList;
        
    private String getUsersFilePath() {        
        String currentDir = System.getProperty("user.dir");
        return currentDir + File.separator + USERS_FILE_PATH;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        dataManager = Data_Manager.getManager();
        userList = dataManager.getList_user();
                
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
    }

    @FXML
    private void onConfirmRegistration(ActionEvent event) {        
        hideError();
        
        String identification = useridentField.getText().trim();
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
                        
        if (identification.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showError("Por favor, complete todos los campos.");
            return;
        }
                        
        if (!isValidIdentification(identification)) {
            showError("La identificación debe ser numérica y tener al menos 7 dígitos.");
            return;
        }
                
        if (!isValidUsername(username)) {
            showError("El email debe ser válido.");
            return;
        }
        
        if (password.length() < 6) {
            showError("La contraseña debe tener al menos 6 caracteres.");
            return;
        }
                        
        if (!password.equals(confirmPassword)) {
            showError("Las contraseñas no coinciden. Por favor, verifique.");
            return;
        }
               
        String roll = isAdmin ? "ADMIN" : "CLIENT";
               
        String name = username.split("@")[0];
                
        try {
            int identificationInt = Integer.parseInt(identification);
                       
            Node_User existingUser = userList.search_email(username);
            if (existingUser != null) {
                showError("Este email ya está registrado. Por favor, use otro email.");
                return;
            }
                        
            Node_User newUser = null;
            if (roll.equals("ADMIN")) {
                Admin admin = new Admin(identificationInt, name, username, password);
                newUser = new Node_User(admin);
            } else {
                Client client = new Client(identificationInt, name, username, password);
                newUser = new Node_User(client);
            }
                        
            if (userList.getHead() == null) {
                userList.setHead(newUser);
            } else {
                newUser.setNext(userList.getHead());
                userList.getHead().setFormer(newUser);
                userList.setHead(newUser);
            }
                        
            userList.save_data_file_users();
            
            showSuccess("Registro exitoso como " + roll + ". Puede iniciar sesión ahora.");
            clearFields();            
            logoClickCount = 0;
            isAdmin = false;
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
            
            Parent root = FXMLLoader.load(getClass().getResource("/Views/Login_View.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
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
    
    public static boolean validateUser(String username, String password) {
        
        Data_Manager manager = Data_Manager.getManager();
        List_Double_User userList = manager.getList_user();
            
        try {
            userList.load_data_from_file_users();
        } catch (Exception e) {
            Logger.getLogger(Controller_Signing_View.class.getName()).log(Level.WARNING, "No se pudieron cargar usuarios", e);
        }
                
        Node_User foundUser = userList.search_email(username);
        if (foundUser != null) {
            
            if (foundUser.getUser().getPassword().equals(password)) {
                return true;
            }
        }
                
        try {
            int identification = Integer.parseInt(username);
            Node_User current = userList.getHead();
            while (current != null) {
                if (current.getUser().getIdentification() == identification 
                    && current.getUser().getPassword().equals(password)) {
                    return true;
                }
                current = current.getNext();
            }
        } catch (NumberFormatException e) {
            
        }
        
        return false;
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