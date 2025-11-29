package Controllers.ComponentControllers;

import DataStructures.*;
import Models.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;

public class Controller_Product_Component implements Initializable {

    @FXML
    private ImageView productImageView;
    
    @FXML
    private ComboBox<String> sizeComboBox;
    
    @FXML
    private Label priceLabel;
    
    @FXML
    private Button addToCartButton;
    
    private Product product;
    private String userEmail;
    private Runnable onAddToCartCallback;
    private Runnable onProductDetailCallback;
    
    private final Stack_Of_Product productStack = Data_Manager.getManager().getStack_Product();

    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        addToCartButton.setOnAction(e -> handleAddToCart());
                
        if (productImageView != null) {
            Tooltip tooltip = new Tooltip("Haz doble click\npara ver más\ninformación.");
            tooltip.setStyle(
                "-fx-background-color: #FFFACD; " +
                "-fx-background-radius: 10px; " +
                "-fx-border-color: #000000; " +
                "-fx-border-width: 3px; " +
                "-fx-border-radius: 10px; " +
                "-fx-text-fill: #000000; " +
                "-fx-font-size: 14px; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 10px 15px;"
            );
            Tooltip.install(productImageView, tooltip);
                    
            productImageView.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2) {                    
                    if (onProductDetailCallback != null) {
                        onProductDetailCallback.run();
                    }
                }
            });
        }
    }
    
    public void setUserEmail(String email) {
        this.userEmail = email;
    }
    
    public void setOnAddToCartCallback(Runnable callback) {
        this.onAddToCartCallback = callback;
    }
    
    public void setOnProductDetailCallback(Runnable callback) {
        this.onProductDetailCallback = callback;
    }
    
    public void setProduct(Product product) {
        this.product = product;
        if (product != null) {
            updateProductDisplay();
        }
    }
    
    private void updateProductDisplay() {        
        if (product.getPrice() > 0) {
            priceLabel.setText(String.format("COP $ %,.0f", product.getPrice()));
        }
                
        if (product.getSizes() != null && product.getSizes().length > 0) {
            sizeComboBox.getItems().clear();
            sizeComboBox.getItems().addAll(product.getSizes());            
            sizeComboBox.setValue(null);
        }
                
        try {            
            String extension = (product.getIdProduct() >= 6 && product.getIdProduct() <= 10) ? ".jpg" : ".png";
                        
            String imagePath = "/Images/ProductsImages/" + String.format("%04d", product.getIdProduct()) + "- " + product.getName() + " - 1" + extension;
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            productImageView.setImage(image);
        } catch (Exception e) {            
            System.out.println("No se pudo cargar la imagen: " + e.getMessage());
        }
    }
    
    private void handleAddToCart() {
        if (product == null) {
            showAlert(Alert.AlertType.WARNING, "Advertencia", "No hay producto seleccionado.");
            return;
        }
        
        if (sizeComboBox.getValue() == null) {
            showAlert(Alert.AlertType.WARNING, "Advertencia", "Por favor selecciona una talla.");
            return;
        }
        
        if (userEmail == null || userEmail.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Advertencia", "No se pudo identificar el usuario.");
            return;
        }
        
        String selectedSize = sizeComboBox.getValue();
        boolean added = productStack.addToCart(product, userEmail, selectedSize);
        if (added) {
            showAlert(Alert.AlertType.INFORMATION, "Éxito", "Producto agregado al carrito.");
            if (onAddToCartCallback != null) {
                onAddToCartCallback.run();
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Advertencia", "El producto ya está en el carrito.");
        }
    }
    
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public Product getProduct() {
        return product;
    }
    
}
