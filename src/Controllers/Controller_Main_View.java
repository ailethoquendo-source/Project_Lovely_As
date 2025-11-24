package Controllers;

import Controllers.ComponentControllers.Controller_Product_Component;
import Models.Product;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Controller_Main_View implements Initializable {

    @FXML
    private Label emailLabel;

    @FXML
    private Button accountButton;

    @FXML
    private Button historyButton;

    @FXML
    private Button favoriteButton;

    @FXML
    private Button cartButton;

    @FXML
    private FlowPane productsFlowPane;

    public Label getEmailLabel() {
        return emailLabel;
    }

    public void setUserType(boolean isAdmin) {
        if (!isAdmin && historyButton != null) {
            historyButton.setVisible(false);
            historyButton.setManaged(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setupResponsiveFlowPane();

        loadProducts();

        setupButtonEvents();
                
        Platform.runLater(() -> {
            if (emailLabel != null && emailLabel.getScene() != null) {
                Stage stage = (Stage) emailLabel.getScene().getWindow();
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
            
            // Eliminar el handler de cierre para permitir cerrar la aplicación desde login
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
            System.out.println("Error al redirigir al login: " + e.getMessage());
        }
    }

    private void setupResponsiveFlowPane() {
        productsFlowPane.prefWrapLengthProperty().bind(
                productsFlowPane.widthProperty().subtract(40)
        );
    }

    private void loadProducts() {
        List<Product> products = getProducts();

        for (Product product : products) {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/Views/Components/Product_Component.fxml")
                );
                AnchorPane productCard = loader.load();

                Controller_Product_Component controller = loader.getController();
                Platform.runLater(() -> controller.setProduct(product));

                productCard.setPrefWidth(150);
                productCard.setPrefHeight(180);

                productsFlowPane.getChildren().add(productCard);

            } catch (IOException e) {
                System.out.println("Error al cargar el componente de producto: " + e.getMessage());
            }
        }
    }

    private List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        for (int i = 1; i <= 15; i++) {
            String fileName = String.format("%04d", i) + " - información.txt";
            Product product = loadProductFromFile(i, "src/Files/InfoProducts/" + fileName);
            if (product != null) {
                products.add(product);
            }
        }

        return products;
    }

    private Product loadProductFromFile(int idProduct, String filePath) {
        String basePath = System.getProperty("user.dir");
        Path fullPath = Paths.get(basePath, filePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(fullPath.toFile()))) {
            String name = "";
            String brand = "Lovely A's";
            String[] sizes = {};
            float price = 0;
            String description = "";

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("Nombre:")) {
                    name = line.substring(7).trim();
                } else if (line.toLowerCase().startsWith("marca")) {
                    String[] parts = line.split(":", 2);
                    if (parts.length > 1) {
                        brand = parts[1].trim();
                    }
                } else if (line.toLowerCase().startsWith("precio") || line.toLowerCase().startsWith("precio:")) {
                    String[] parts = line.split(":", 2);
                    if (parts.length > 1) {
                        String priceStr = parts[1].trim();
                        priceStr = priceStr.replace("$", "").replace(" ", "");
                        if (priceStr.contains(".") && priceStr.lastIndexOf(".") == priceStr.length() - 4) {
                            priceStr = priceStr.replace(".", "");
                        } else if (priceStr.contains(",")) {
                            priceStr = priceStr.replace(",", "");
                        }
                        try {
                            price = Float.parseFloat(priceStr);
                        } catch (NumberFormatException e) {
                            System.out.println("Error al parsear precio para producto " + idProduct + ": " + priceStr);
                        }
                    }
                } else if (line.toLowerCase().startsWith("tallas")) {
                    String[] parts = line.split(":", 2);
                    if (parts.length > 1) {
                        String sizesStr = parts[1].trim();
                        String[] rawSizes = sizesStr.split("[-\\s]+");
                        List<String> sizeList = new ArrayList<>();
                        for (String size : rawSizes) {
                            String trimmed = size.trim();
                            if (!trimmed.isEmpty()) {
                                sizeList.add(trimmed);
                            }
                        }
                        sizes = sizeList.toArray(new String[0]);
                    }
                } else if (line.toLowerCase().startsWith("descripción") || line.toLowerCase().startsWith("descripcion")) {
                    String[] parts = line.split(":", 2);
                    if (parts.length > 1) {
                        description = parts[1].trim();
                    }
                }
            }

            if (!name.isEmpty()) {
                return new Product(idProduct, name, brand, sizes, price, description);
            }

        } catch (IOException e) {
            System.out.println("Error al leer archivo " + filePath + ": " + e.getMessage());
        }

        return null;
    }

    private void setupButtonEvents() {
        accountButton.setOnAction(e -> handleAccountClick());
        historyButton.setOnAction(e -> handleHistoryClick());
        favoriteButton.setOnAction(e -> handleFavoriteClick());
        cartButton.setOnAction(e -> handleCartClick());
    }

    private void handleAccountClick() {
        System.out.println("Cuenta clickeada");
    }

    private void handleHistoryClick() {
        System.out.println("Historial clickeado");
    }

    private void handleFavoriteClick() {
        System.out.println("Favoritos clickeado");
    }

    private void handleCartClick() {
        System.out.println("Carrito clickeado");
    }

}
