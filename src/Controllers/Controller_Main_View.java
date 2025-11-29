package Controllers;

import Controllers.ComponentControllers.*;
import DataStructures.*;
import Models.Product;
import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.*;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
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
    private Button whatsappButton;

    @FXML
    private FlowPane productsFlowPane;
    
    @FXML
    private StackPane accountPopup;
    
    @FXML
    private Button changePasswordButton;
    
    @FXML
    private Button logoutButton;
    
    private String currentUserEmail;
    private final List_Double_User userList = Data_Manager.getManager().getList_user();
    private final Stack_Of_Product productStack = Data_Manager.getManager().getStack_Product();
    
    @FXML
    private StackPane cartPanel;
    
    @FXML
    private StackPane favoritesPanel;
    
    @FXML
    private VBox cartItemsContainer;
    
    @FXML
    private VBox favoritesItemsContainer;
    
    @FXML
    private Label cartTotalLabel;
    
    @FXML
    private Button payAllButton;
    @FXML
    private HBox headerBox;
    @FXML
    private ScrollPane scrollPane_P;
    
    @FXML
    private StackPane changePasswordOverlay;
    
    @FXML
    private StackPane historyPanel;
    
    @FXML
    private StackPane productDetailPanel;
    
    @FXML
    private StackPane contentContainer;
    
    @FXML
    private StackPane productDetailContainer;
    
    @FXML
    private StackPane whatsappOverlay;
    
    private boolean isAdmin = false;

    public Label getEmailLabel() {
        return emailLabel;
    }

    public void setUserType(boolean isAdmin) {
        this.isAdmin = isAdmin;
        if (!isAdmin && historyButton != null) {
            historyButton.setVisible(false);
            historyButton.setManaged(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userList.load_data_from_file_users();
        productStack.loadAllFromFiles();

        loadProducts();

        setupButtonEvents();
        
        setupAccountPopup();
        
        setupPanels();
                
        Platform.runLater(() -> {            
            Platform.runLater(() -> {
                setupResponsiveFlowPane();
            });
            
            if (emailLabel != null && emailLabel.getScene() != null) {
                Stage stage = (Stage) emailLabel.getScene().getWindow();
                if (stage != null) {
                    stage.setOnCloseRequest(e -> {                        
                        e.consume();
                                                
                        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                        confirmAlert.setTitle("Cerrar Sesión");
                        confirmAlert.setHeaderText("¿Desea cerrar sesión?");
                        confirmAlert.setContentText("Se cerrará la sesión y será redirigido a la pantalla de inicio de sesión.");
                        
                        confirmAlert.showAndWait().ifPresent(response -> {
                            if (response == ButtonType.OK) {                        
                                redirectToLogin(stage);
                            }                            
                        });
                    });
                }
            }
        });
    }
    
    private void setupPanels() {
        if (cartPanel != null) {
            cartPanel.setOnMouseClicked(e -> {
                if (e.getTarget() == cartPanel) {
                    cartPanel.setVisible(false);
                }
            });
        }
        
        if (favoritesPanel != null) {
            favoritesPanel.setOnMouseClicked(e -> {
                if (e.getTarget() == favoritesPanel) {
                    favoritesPanel.setVisible(false);
                }
            });
        }
        
        if (payAllButton != null) {
            payAllButton.setOnAction(e -> handlePayAll());
        }
    }
    
    private void setupAccountPopup() {
        if (accountPopup != null) {
            accountPopup.setOnMouseClicked((MouseEvent event) -> {
                Object target = event.getTarget();

                boolean isClickOnButton = false;

                if (target == changePasswordButton || target == logoutButton) {
                    isClickOnButton = true;
                } else if (target instanceof javafx.scene.Node) {
                    Node node = (javafx.scene.Node) target;
                    Node parent = node.getParent();
                    while (parent != null && parent != accountPopup) {
                        if (parent == changePasswordButton || parent == logoutButton) {
                            isClickOnButton = true;
                            break;
                        }
                        parent = parent.getParent();
                    }
                }

                if (!isClickOnButton) {
                    accountPopup.setVisible(false);
                    event.consume();
                }
            });

            if (changePasswordButton != null) {
                changePasswordButton.setOnMouseClicked((MouseEvent event) -> {
                    event.consume();
                });
            }

            if (logoutButton != null) {
                logoutButton.setOnMouseClicked((MouseEvent event) -> {
                    event.consume();
                });
            }
        }
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
            System.out.println("Error al redirigir al login: " + e.getMessage());
        }
    }

    private void setupResponsiveFlowPane() {
        if (productsFlowPane != null && productsFlowPane.getScene() != null) {                                    
            
            if (scrollPane_P != null) {                
                productsFlowPane.prefWrapLengthProperty().bind(
                    Bindings.createDoubleBinding(() -> {
                        double availableWidth = scrollPane_P.getViewportBounds().getWidth();
                        return Math.max(200, availableWidth - 20);
                    }, scrollPane_P.viewportBoundsProperty())
                );
            } else {                
                productsFlowPane.prefWrapLengthProperty().bind(
                    productsFlowPane.getScene().widthProperty().subtract(80)
                );
            }
                        
            productsFlowPane.setPrefWidth(javafx.scene.layout.Region.USE_COMPUTED_SIZE);
        }
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
                productCard.setUserData(controller);
                
                Platform.runLater(() -> {
                    controller.setProduct(product);
                    if (currentUserEmail != null) {
                        controller.setUserEmail(currentUserEmail);
                        controller.setOnAddToCartCallback(() -> {
                            loadCartItems();
                        });                        
                        controller.setOnProductDetailCallback(() -> {
                            showProductDetail(product);
                        });
                    }
                });

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
        
        if (whatsappButton != null) {
            whatsappButton.setOnAction(e -> handleWhatsAppClick());
        }
        
        if (changePasswordButton != null) {
            changePasswordButton.setOnAction(e -> handleChangePassword());
        }
        
        if (logoutButton != null) {
            logoutButton.setOnAction(e -> handleLogout());
        }
    }

    private void handleAccountClick() {
        if (accountPopup != null) {
            accountPopup.setVisible(!accountPopup.isVisible());
        }
    }
    
    private void handleChangePassword() {
        if (currentUserEmail == null || currentUserEmail.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Advertencia", "No se pudo identificar el usuario actual.");
            return;
        }
                
        if (productDetailPanel != null && productDetailPanel.isVisible()) {
            hideProductDetailDialog();
        }
                
        if (accountPopup != null) {
            accountPopup.setVisible(false);
        }
                
        showChangePasswordDialog();
    }
    
    private void showChangePasswordDialog() {
        if (changePasswordOverlay == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudo inicializar el componente de cambio de contraseña.");
            return;
        }
        
        try {            
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/Views/Components/Change_Password_Component.fxml")
            );
            AnchorPane changePasswordPane = loader.load();
            
            Controller_Change_Password_Component controller = loader.getController();
            controller.setUserEmail(currentUserEmail);
            controller.setOnSuccessCallback(() -> {
                showAlert(Alert.AlertType.INFORMATION, "Éxito", "Contraseña cambiada exitosamente.");
                hideChangePasswordDialog();
            });
            controller.setOnCancelCallback(() -> {
                hideChangePasswordDialog();
            });
                        
            changePasswordOverlay.setAlignment(javafx.geometry.Pos.CENTER);
            changePasswordOverlay.setOnMouseClicked(e -> {
                if (e.getTarget() == changePasswordOverlay) {
                    hideChangePasswordDialog();
                }
            });
            
            changePasswordOverlay.getChildren().clear();
            changePasswordOverlay.getChildren().add(changePasswordPane);
            changePasswordOverlay.setVisible(true);
            
        } catch (IOException e) {
            System.out.println("Error al cargar el componente de cambio de contraseña: " + e.getMessage());
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudo cargar el formulario de cambio de contraseña.");
        }
    }
    
    private void hideChangePasswordDialog() {
        if (changePasswordOverlay != null) {
            changePasswordOverlay.setVisible(false);
            changePasswordOverlay.getChildren().clear();
        }
    }
    
    private void handleLogout() {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Cerrar Sesión");
        confirmAlert.setHeaderText("¿Está seguro que desea cerrar sesión?");
        confirmAlert.setContentText("Será redirigido a la pantalla de inicio de sesión.");
        
        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                accountPopup.setVisible(false);
                Stage currentStage = (Stage) accountButton.getScene().getWindow();
                redirectToLogin(currentStage);
            }
        });
    }
    
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public void setCurrentUserEmail(String email) {
        this.currentUserEmail = email;        
        updateProductComponentsEmail();
    }
    
    private void updateProductComponentsEmail() {
        if (productsFlowPane != null && currentUserEmail != null) {
            for (Node node : productsFlowPane.getChildren()) {
                if (node.getUserData() instanceof Controller_Product_Component) {
                    Controller_Product_Component controller = (Controller_Product_Component) node.getUserData();
                    controller.setUserEmail(currentUserEmail);
                    controller.setOnAddToCartCallback(() -> {
                        loadCartItems();
                    });
                }
            }
        }
    }

    private void handleHistoryClick() {
        if (!isAdmin) {
            showAlert(Alert.AlertType.WARNING, "Advertencia", "Solo los administradores pueden ver el historial de compras.");
            return;
        }
        
        if (historyPanel != null) {            
            if (productDetailPanel != null && productDetailPanel.isVisible()) {
                hideProductDetailDialog();
            }
            
            historyPanel.setVisible(!historyPanel.isVisible());
            if (historyPanel.isVisible()) {
                showHistoryDialog();
            }
        }
    }
    
    private void showHistoryDialog() {
        if (historyPanel == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudo inicializar el panel de historial.");
            return;
        }
        
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/Views/Components/History_Component.fxml")
            );
            AnchorPane historyPane = loader.load();
            
            Controller_History_Component controller = loader.getController();
            controller.setOnExitCallback(() -> {
                hideHistoryDialog();
            });
                        
            historyPanel.setAlignment(Pos.CENTER);
            historyPanel.setOnMouseClicked(e -> {
                if (e.getTarget() == historyPanel) {
                    hideHistoryDialog();
                }
            });
            
            historyPanel.getChildren().clear();
            historyPanel.getChildren().add(historyPane);
            historyPanel.setVisible(true);
            
        } catch (IOException e) {
            System.out.println("Error al cargar el componente de historial: " + e.getMessage());
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudo cargar el panel de historial.");
        }
    }
    
    private void hideHistoryDialog() {
        if (historyPanel != null) {
            historyPanel.setVisible(false);
            historyPanel.getChildren().clear();
        }
    }
    
    private void handleWhatsAppClick() {
        showWhatsAppDialog();
    }
    
    private void showWhatsAppDialog() {
        if (whatsappOverlay == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudo inicializar el panel de WhatsApp.");
            return;
        }
        
        try {            
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/Views/Components/WhatsApp_Component.fxml")
            );
            AnchorPane whatsappPane = loader.load();
            
            Controller_WhatsApp_Component controller = loader.getController();
            controller.setOnCloseCallback(() -> {
                hideWhatsAppDialog();
            });
            
            whatsappOverlay.getChildren().clear();
            whatsappOverlay.getChildren().add(whatsappPane);
            whatsappOverlay.setVisible(true);
            whatsappOverlay.setManaged(true);
                        
            if (cartPanel != null && cartPanel.isVisible()) {
                cartPanel.setVisible(false);
            }
            if (favoritesPanel != null && favoritesPanel.isVisible()) {
                favoritesPanel.setVisible(false);
            }
            if (historyPanel != null && historyPanel.isVisible()) {
                hideHistoryDialog();
            }
            if (changePasswordOverlay != null && changePasswordOverlay.isVisible()) {
                hideChangePasswordDialog();
            }
                        
            whatsappOverlay.setOnMouseClicked(e -> {
                if (e.getTarget() == whatsappOverlay) {
                    hideWhatsAppDialog();
                }
            });
            
        } catch (IOException e) {
            System.out.println("Error al cargar el componente de WhatsApp: " + e.getMessage());
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudo cargar el panel de WhatsApp.");
        }
    }
    
    private void hideWhatsAppDialog() {
        if (whatsappOverlay != null) {
            whatsappOverlay.setVisible(false);
            whatsappOverlay.setManaged(false);
            whatsappOverlay.getChildren().clear();
        }
    }

    private void handleCartClick() {
        if (cartPanel != null) {
            boolean wasVisible = cartPanel.isVisible();
                        
            if (favoritesPanel != null && favoritesPanel.isVisible()) {
                favoritesPanel.setVisible(false);
            }
                        
            if (productDetailPanel != null && productDetailPanel.isVisible()) {
                hideProductDetailDialog();
            }
                        
            cartPanel.setVisible(!wasVisible);
            if (cartPanel.isVisible()) {
                loadCartItems();
            }
        }
    }
    
    private void handleFavoriteClick() {
        if (favoritesPanel != null) {
            boolean wasVisible = favoritesPanel.isVisible();
                        
            if (cartPanel != null && cartPanel.isVisible()) {
                cartPanel.setVisible(false);
            }
                        
            if (productDetailPanel != null && productDetailPanel.isVisible()) {
                hideProductDetailDialog();
            }
                        
            favoritesPanel.setVisible(!wasVisible);
            if (favoritesPanel.isVisible()) {
                loadFavoriteItems();
            }
        }
    }
    
    private void loadCartItems() {
        if (cartItemsContainer == null || currentUserEmail == null) {
            return;
        }
        
        cartItemsContainer.getChildren().clear();
        
        Stack<Product> cartProducts = productStack.getCartProductsByUser(currentUserEmail);
        
        if (cartProducts.isEmpty()) {
            Label emptyLabel = new Label("El carrito está vacío");
            emptyLabel.setStyle("-fx-text-fill: #666666; -fx-font-size: 16px;");
            cartItemsContainer.getChildren().add(emptyLabel);
        } else {
            float total = 0;
            for (Product product : cartProducts) {
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("/Views/Components/Cart_Item_Component.fxml")
                    );
                    AnchorPane cartItem = loader.load();
                    
                    Controller_Cart_Item_Component controller = loader.getController();
                    controller.setProduct(product, currentUserEmail);
                    controller.setOnRemoveCallback(() -> {
                        loadCartItems();
                    });
                    
                    cartItemsContainer.getChildren().add(cartItem);
                    total += product.getPrice();
                } catch (IOException e) {
                    System.out.println("Error al cargar el componente de item del carrito: " + e.getMessage());
                }
            }
            
            if (cartTotalLabel != null) {
                cartTotalLabel.setText("COP $ " + String.format("%,.0f", total));
            }
        }
    }
    
    private void loadFavoriteItems() {
        if (favoritesItemsContainer == null || currentUserEmail == null) {
            return;
        }
        
        favoritesItemsContainer.getChildren().clear();
        
        Stack<Product> favoriteProducts = productStack.getFavoriteProductsByUser(currentUserEmail);
        
        if (favoriteProducts.isEmpty()) {
            Label emptyLabel = new Label("No hay productos en favoritos");
            emptyLabel.setStyle("-fx-text-fill: #666666; -fx-font-size: 16px;");
            favoritesItemsContainer.getChildren().add(emptyLabel);
        } else {
            for (Product product : favoriteProducts) {
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("/Views/Components/Favorite_Item_Component.fxml")
                    );
                    AnchorPane favoriteItem = loader.load();
                    
                    Controller_Favorite_Item_Component controller = loader.getController();
                    controller.setProduct(product, currentUserEmail);
                    controller.setOnRemoveCallback(() -> {
                        loadFavoriteItems();
                    });
                    
                    favoritesItemsContainer.getChildren().add(favoriteItem);
                } catch (IOException e) {
                    System.out.println("Error al cargar el componente de item de favoritos: " + e.getMessage());
                }
            }
        }
    }
    
    private void handlePayAll() {
        if (currentUserEmail == null) {
            showAlert(Alert.AlertType.WARNING, "Advertencia", "No se pudo identificar el usuario.");
            return;
        }
        
        Stack<Product> cartProducts = productStack.getCartProductsByUser(currentUserEmail);
        
        if (cartProducts.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Información", "El carrito está vacío.");
            return;
        }
        
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmar Compra");
        confirmAlert.setHeaderText("¿Desea proceder con el pago?");
        
        float total = 0;
        for (Product product : cartProducts) {
            total += product.getPrice();
        }
        confirmAlert.setContentText("Total a pagar: COP $ " + String.format("%,.0f", total));
        
        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {                
                for (Product product : cartProducts) {
                    productStack.addToHistory(product, currentUserEmail);
                    productStack.removeFromCart(product.getIdProduct(), currentUserEmail);
                }
                
                showAlert(Alert.AlertType.INFORMATION, "Éxito", "Compra realizada exitosamente.");
                loadCartItems();
                cartPanel.setVisible(false);
            }
        });
    }
    
    private void showProductDetail(Product product) {
        if (productDetailContainer == null || scrollPane_P == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudo inicializar el panel de detalle de producto.");
            return;
        }
                
        if (cartPanel != null && cartPanel.isVisible()) {
            cartPanel.setVisible(false);
        }
        if (favoritesPanel != null && favoritesPanel.isVisible()) {
            favoritesPanel.setVisible(false);
        }
        
        try {            
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/Views/Components/Product_Detail_Component.fxml")
            );
            AnchorPane productDetailPane = loader.load();
            
            Controller_Product_Detail_Component controller = loader.getController();
            controller.setProduct(product);
            controller.setUserEmail(currentUserEmail);
            
            controller.setOnBackCallback(() -> {
                hideProductDetailDialog();
            });
            controller.setOnAddToCartCallback(() -> {
                loadCartItems();
            });
                        
            scrollPane_P.setVisible(false);
            scrollPane_P.setManaged(false);
            
            productDetailContainer.getChildren().clear();
            productDetailContainer.getChildren().add(productDetailPane);
            productDetailContainer.setVisible(true);
            productDetailContainer.setManaged(true);
            
        } catch (IOException e) {
            System.out.println("Error al cargar el componente de detalle de producto: " + e.getMessage());
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudo cargar el panel de detalle de producto.");
        }
    }
    
    private void hideProductDetailDialog() {
        if (productDetailContainer != null && scrollPane_P != null) {            
            productDetailContainer.setVisible(false);
            productDetailContainer.setManaged(false);
            productDetailContainer.getChildren().clear();
            
            scrollPane_P.setVisible(true);
            scrollPane_P.setManaged(true);
        }
    }

}
