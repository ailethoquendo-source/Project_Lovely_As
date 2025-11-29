package Controllers.ComponentControllers;

import DataStructures.Data_Manager;
import DataStructures.Stack_Of_Product;
import Models.Product;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Controller_Product_Detail_Component implements Initializable {

    @FXML
    private AnchorPane productDetailContainer;
    
    @FXML
    private Button backButton;
    
    @FXML
    private VBox thumbnailsContainer;
    
    @FXML
    private Label productTitleLabel;
    
    @FXML
    private Label priceLabel;
    
    @FXML
    private ImageView mainImageView;
    
    @FXML
    private TextArea descriptionTextArea;
    
    @FXML
    private ComboBox<String> sizeComboBox;
    
    @FXML
    private Button addToCartButton;
    
    private Product product;
    private String userEmail;
    private List<Image> productImages;
    private ImageView selectedThumbnail;
    
    private Runnable onBackCallback;
    private Runnable onAddToCartCallback;
    
    private final Stack_Of_Product productStack = Data_Manager.getManager().getStack_Product();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupEventHandlers();
    }
    
    private void setupEventHandlers() {
        backButton.setOnAction(e -> handleBackClick());
        addToCartButton.setOnAction(e -> handleAddToCart());
    }
    
    public void setProduct(Product product) {
        this.product = product;
        if (product != null) {
            updateProductDisplay();
        }
    }
    
    public void setUserEmail(String email) {
        this.userEmail = email;
    }
    
    public void setOnBackCallback(Runnable callback) {
        this.onBackCallback = callback;
    }
    
    public void setOnAddToCartCallback(Runnable callback) {
        this.onAddToCartCallback = callback;
    }
    
    private void updateProductDisplay() {
        if (product == null) {
            return;
        }
                
        String brand = (product.getBrand() != null && !product.getBrand().isEmpty()) 
            ? product.getBrand() 
            : "Lovely A's";
        productTitleLabel.setText(product.getName() + " - " + brand);
                
        if (product.getPrice() > 0) {
            priceLabel.setText(String.format("COP $ %,.0f", product.getPrice()));
        } else {
            priceLabel.setText("COP $ N/A");
        }
                
        if (product.getDescription() != null && !product.getDescription().isEmpty()) {
            descriptionTextArea.setText(product.getDescription());
        } else {
            descriptionTextArea.setText("No hay descripción disponible.");
        }
                
        if (product.getSizes() != null && product.getSizes().length > 0) {
            sizeComboBox.getItems().clear();
            sizeComboBox.getItems().addAll(product.getSizes());
            sizeComboBox.setValue(null);
        }
                
        loadProductImages();
    }
    
    private void loadProductImages() {
        productImages = new ArrayList<>();
        thumbnailsContainer.getChildren().clear();
        
        if (product == null) {
            return;
        }
        
        String extension = (product.getIdProduct() >= 6 && product.getIdProduct() <= 10) ? ".jpg" : ".png";
        int imageNumber = 1;
                
        while (true) {
            try {
                String imagePath = "/Images/ProductsImages/" + 
                    String.format("%04d", product.getIdProduct()) + 
                    "- " + product.getName() + " - " + imageNumber + extension;
                
                Image image = new Image(getClass().getResourceAsStream(imagePath));
                productImages.add(image);
                                
                ImageView thumbnail = new ImageView(image);
                thumbnail.setFitHeight(80);
                thumbnail.setFitWidth(80);
                thumbnail.setPreserveRatio(true);
                thumbnail.getStyleClass().add("thumbnail-image");
                thumbnail.setPickOnBounds(true);
                                
                thumbnail.setOnMouseEntered(e -> {
                    thumbnail.setStyle("-fx-opacity: 0.8; -fx-cursor: hand;");
                });
                thumbnail.setOnMouseExited(e -> {
                    if (thumbnail != selectedThumbnail) {
                        thumbnail.setStyle("-fx-opacity: 1.0; -fx-cursor: hand;");
                    }
                });
                                
                final Image currentImage = image;
                final ImageView currentThumbnail = thumbnail;
                thumbnail.setOnMouseClicked(e -> {
                    updateMainImage(currentImage);
                    selectThumbnail(currentThumbnail);
                });
                
                thumbnailsContainer.getChildren().add(thumbnail);
                
                imageNumber++;
            } catch (Exception e) {                
                break;
            }
        }
                
        if (!productImages.isEmpty()) {
            updateMainImage(productImages.get(0));
            if (!thumbnailsContainer.getChildren().isEmpty()) {
                selectThumbnail((ImageView) thumbnailsContainer.getChildren().get(0));
            }
        }
    }
    
    private void updateMainImage(Image image) {
        if (mainImageView != null && image != null) {
            mainImageView.setImage(image);
        }
    }
    
    private void selectThumbnail(ImageView thumbnail) {        
        if (selectedThumbnail != null) {
            selectedThumbnail.setStyle("-fx-opacity: 1.0; -fx-border-width: 0;");
        }
                
        selectedThumbnail = thumbnail;
        thumbnail.setStyle("-fx-opacity: 1.0; -fx-border-color: #E75480; -fx-border-width: 3; -fx-border-radius: 5;");
    }
    
    private void handleBackClick() {
        if (onBackCallback != null) {
            onBackCallback.run();
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
}

