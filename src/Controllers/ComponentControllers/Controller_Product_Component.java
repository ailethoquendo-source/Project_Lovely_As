package Controllers.ComponentControllers;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        addToCartButton.setOnAction(e -> handleAddToCart());
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
        if (product != null && sizeComboBox.getValue() != null) {    
            System.out.println("Agregando al carrito: " + product.getName() + 
                             " - Talla: " + sizeComboBox.getValue());            
        } else if (sizeComboBox.getValue() == null) {
            System.out.println("Por favor selecciona una talla");
        }
    }
    
    public Product getProduct() {
        return product;
    }
    
}
