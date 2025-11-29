package Controllers.ComponentControllers;

import DataStructures.Data_Manager;
import DataStructures.Stack_Of_Product;
import Models.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;

public class Controller_Favorite_Item_Component implements Initializable {

    @FXML
    private ImageView productImageView;
    
    @FXML
    private Label brandLabel;
    
    @FXML
    private Label sizeLabel;
    
    @FXML
    private Label priceLabel;
    
    @FXML
    private Button moveToCartButton;
    
    @FXML
    private Button removeButton;
    
    private Product product;
    private String userEmail;
    private Runnable onRemoveCallback;
    
    private final Stack_Of_Product productStack = Data_Manager.getManager().getStack_Product();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        moveToCartButton.setOnAction(e -> handleMoveToCart());
        removeButton.setOnAction(e -> handleRemove());
    }
    
    public void setProduct(Product product, String userEmail) {
        this.product = product;
        this.userEmail = userEmail;
        if (product != null) {
            updateProductDisplay();
        }
    }
    
    public void setOnRemoveCallback(Runnable callback) {
        this.onRemoveCallback = callback;
    }
    
    private void updateProductDisplay() {
        if (product != null) {

            if (product.getBrand() != null && !product.getBrand().isEmpty()) {
                brandLabel.setText("MARCA: " + product.getBrand());
            } else {
                brandLabel.setText("MARCA: Lovely A's");
            }
                        
            if (product.getPrice() > 0) {
                priceLabel.setText("PRECIO: COP $ " + String.format("%,.0f", product.getPrice()));
            } else {
                priceLabel.setText("PRECIO: N/A");
            }
                        
            if (product.getSelectedSize() != null && !product.getSelectedSize().isEmpty()) {
                sizeLabel.setText("TALLA: " + product.getSelectedSize());
            } else {
                sizeLabel.setText("TALLA: -");
            }
                        
            try {
                String extension = (product.getIdProduct() >= 6 && product.getIdProduct() <= 10) ? ".jpg" : ".png";
                String imagePath = "/Images/ProductsImages/" + String.format("%04d", product.getIdProduct()) + "- " + product.getName() + " - 1" + extension;
                Image image = new Image(getClass().getResourceAsStream(imagePath));
                productImageView.setImage(image);
            } catch (Exception e) {
                System.out.println("No se pudo cargar la imagen del producto: " + e.getMessage());
            }
        }
    }
    
    private void handleMoveToCart() {
        if (product != null && userEmail != null) {
            boolean moved = productStack.moveFromFavoritesToCart(product.getIdProduct(), userEmail);
            if (moved) {
                showAlert(Alert.AlertType.INFORMATION, "Éxito", "Producto movido al carrito.");
                if (onRemoveCallback != null) {
                    onRemoveCallback.run();
                }
            } else {
                showAlert(Alert.AlertType.WARNING, "Advertencia", "No se pudo mover el producto al carrito.");
            }
        }
    }
    
    private void handleRemove() {
        if (product != null && userEmail != null) {
            boolean removed = productStack.removeFromFavorites(product.getIdProduct(), userEmail);
            if (removed) {
                showAlert(Alert.AlertType.INFORMATION, "Éxito", "Producto eliminado de favoritos.");
                if (onRemoveCallback != null) {
                    onRemoveCallback.run();
                }
            } else {
                showAlert(Alert.AlertType.WARNING, "Advertencia", "No se pudo eliminar el producto.");
            }
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

