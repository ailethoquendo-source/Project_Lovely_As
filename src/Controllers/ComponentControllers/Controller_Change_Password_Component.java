package Controllers.ComponentControllers;

import DataStructures.Data_Manager;
import DataStructures.List_Double_User;
import Models.Nodes.Node_User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class Controller_Change_Password_Component implements Initializable {

    @FXML
    private PasswordField currentPasswordField;
    
    @FXML
    private PasswordField newPasswordField;
    
    @FXML
    private PasswordField confirmPasswordField;
    
    @FXML
    private Label errorLabel;
    
    @FXML
    private Button changeButton;
    
    @FXML
    private Button cancelButton;
    
    @FXML
    private AnchorPane changePasswordContainer;
    
    private String userEmail;
    private Runnable onSuccessCallback;
    private Runnable onCancelCallback;
    
    private final List_Double_User userList = Data_Manager.getManager().getList_user();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        changeButton.setOnAction(e -> handleChangePassword());
        cancelButton.setOnAction(e -> handleCancel());
                
        currentPasswordField.setOnKeyTyped(e -> hideError());
        newPasswordField.setOnKeyTyped(e -> hideError());
        confirmPasswordField.setOnKeyTyped(e -> hideError());
    }
    
    public void setUserEmail(String email) {
        this.userEmail = email;
    }
    
    public void setOnSuccessCallback(Runnable callback) {
        this.onSuccessCallback = callback;
    }
    
    public void setOnCancelCallback(Runnable callback) {
        this.onCancelCallback = callback;
    }
    
    private void handleChangePassword() {
        hideError();
        
        if (userEmail == null || userEmail.isEmpty()) {
            showError("No se pudo identificar el usuario actual.");
            return;
        }
        
        Node_User userNode = userList.search_email(userEmail);
        if (userNode == null) {
            showError("Usuario no encontrado.");
            return;
        }
        
        String currentPassword = currentPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();
                
        if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            showError("Por favor, complete todos los campos.");
            return;
        }
                
        if (!userNode.getUser().getPassword().equals(currentPassword)) {
            showError("La contraseña actual es incorrecta.");
            return;
        }
                
        if (newPassword.length() < 6) {
            showError("La nueva contraseña debe tener al menos 6 caracteres.");
            return;
        }
                
        if (!newPassword.equals(confirmPassword)) {
            showError("Las contraseñas no coinciden.");
            return;
        }
                
        userNode.getUser().setPassword(newPassword);
        userList.save_data_file_users();
                
        clearFields();
                
        if (onSuccessCallback != null) {
            onSuccessCallback.run();
        }
    }
    
    private void handleCancel() {
        clearFields();
        hideError();
        if (onCancelCallback != null) {
            onCancelCallback.run();
        }
    }
    
    private void showError(String message) {
        if (errorLabel != null) {
            errorLabel.setText(message);
            errorLabel.setVisible(true);
        }
    }
    
    private void hideError() {
        if (errorLabel != null) {
            errorLabel.setVisible(false);
            errorLabel.setText("");
        }
    }
    
    private void clearFields() {
        if (currentPasswordField != null) {
            currentPasswordField.clear();
        }
        if (newPasswordField != null) {
            newPasswordField.clear();
        }
        if (confirmPasswordField != null) {
            confirmPasswordField.clear();
        }
    }
}

