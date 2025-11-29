package Controllers.ComponentControllers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Controller_WhatsApp_Component implements Initializable {
    
    @FXML
    private Button number1Button;
    
    @FXML
    private Button number2Button;
    
    @FXML
    private Button number3Button;
    
    @FXML
    private Button cancelButton;
    
    @FXML
    private ImageView whatsappIcon1;
    
    @FXML
    private ImageView whatsappIcon2;
    
    @FXML
    private ImageView whatsappIcon3;
    
    private Runnable onCloseCallback;
        
    private final String NUMBER_1 = "3137543381";
    private final String NUMBER_2 = "3005930262";
    private final String NUMBER_3 = "3136296899";
        
    private final Image whatsNormal = new Image(getClass().getResourceAsStream("/Images/whats1.png"));
    private final Image whatsHover = new Image(getClass().getResourceAsStream("/Images/whats2.png"));
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
    }
    
    public void setOnCloseCallback(Runnable callback) {
        this.onCloseCallback = callback;
    }
    
    @FXML
    private void handleNumber1Click() {
        openWhatsApp(NUMBER_1);
    }
    
    @FXML
    private void handleNumber2Click() {
        openWhatsApp(NUMBER_2);
    }
    
    @FXML
    private void handleNumber3Click() {
        openWhatsApp(NUMBER_3);
    }
    
    @FXML
    private void handleCancelClick() {
        if (onCloseCallback != null) {
            onCloseCallback.run();
        }
    }
    
    @FXML
    private void onNumber1Enter(MouseEvent event) {
        if (whatsappIcon1 != null) {
            whatsappIcon1.setImage(whatsHover);
        }
    }
    
    @FXML
    private void onNumber1Exit(MouseEvent event) {
        if (whatsappIcon1 != null) {
            whatsappIcon1.setImage(whatsNormal);
        }
    }
    
    @FXML
    private void onNumber2Enter(MouseEvent event) {
        if (whatsappIcon2 != null) {
            whatsappIcon2.setImage(whatsHover);
        }
    }
    
    @FXML
    private void onNumber2Exit(MouseEvent event) {
        if (whatsappIcon2 != null) {
            whatsappIcon2.setImage(whatsNormal);
        }
    }
    
    @FXML
    private void onNumber3Enter(MouseEvent event) {
        if (whatsappIcon3 != null) {
            whatsappIcon3.setImage(whatsHover);
        }
    }
    
    @FXML
    private void onNumber3Exit(MouseEvent event) {
        if (whatsappIcon3 != null) {
            whatsappIcon3.setImage(whatsNormal);
        }
    }
    
    private void openWhatsApp(String phoneNumber) {
        try {            
            String formattedNumber = phoneNumber.replaceAll("[^0-9+]", "");
                                    
            String whatsappUrl = "https://w.app/" + formattedNumber+"a";
                        
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(whatsappUrl));
            } else {                
                showAlert("Información", "Por favor, abre WhatsApp y contacta a: " + phoneNumber);
            }
                        
            if (onCloseCallback != null) {
                onCloseCallback.run();
            }
        } catch (IOException | URISyntaxException e) {
            showAlert("Error", "No se pudo abrir WhatsApp. Por favor, contacta manualmente a: " + phoneNumber);
            System.out.println("Error al abrir WhatsApp: " + e.getMessage());
        }
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

