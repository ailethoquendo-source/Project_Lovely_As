package Controllers.ComponentControllers;

import DataStructures.Data_Manager;
import DataStructures.Stack_Of_Product;
import Models.Product;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class Controller_History_Component implements Initializable {

    @FXML
    private TableView<Product> historyTable;
    
    @FXML
    private TableColumn<Product, String> idColumn;
    
    @FXML
    private TableColumn<Product, String> brandColumn;
    
    @FXML
    private TableColumn<Product, String> nameColumn;
    
    @FXML
    private TableColumn<Product, String> priceColumn;
    
    @FXML
    private TableColumn<Product, String> dateColumn;
    
    @FXML
    private TableColumn<Product, String> emailColumn;
    
    @FXML
    private Button exitButton;
    
    @FXML
    private AnchorPane historyContainer;
    
    private Runnable onExitCallback;
    
    private final Stack_Of_Product productStack = Data_Manager.getManager().getStack_Product();
    private final ObservableList<Product> historyData = FXCollections.observableArrayList();
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTableColumns();
        loadHistoryData();
        exitButton.setOnAction(e -> handleExit());
    }
    
    public void setOnExitCallback(Runnable callback) {
        this.onExitCallback = callback;
    }
    
    private void setupTableColumns() {        
        idColumn.setCellValueFactory(cellData -> {
            Product product = cellData.getValue();
            return new SimpleStringProperty(
                String.format("%04d", product.getIdProduct())
            );
        });
        
        brandColumn.setCellValueFactory(cellData -> {
            Product product = cellData.getValue();
            String brand = product.getBrand();
            return new SimpleStringProperty(
                brand != null && !brand.isEmpty() ? brand : "Lovely A's"
            );
        });
        
        nameColumn.setCellValueFactory(cellData -> {
            Product product = cellData.getValue();
            return new SimpleStringProperty(
                product.getName() != null ? product.getName() : ""
            );
        });
        
        priceColumn.setCellValueFactory(cellData -> {
            Product product = cellData.getValue();
            return new SimpleStringProperty(
                String.format("%.0f", product.getPrice())
            );
        });
        
        dateColumn.setCellValueFactory(cellData -> {
            Product product = cellData.getValue();
            if (product.getDate_purchase() != null) {
                return new SimpleStringProperty(
                    product.getDate_purchase().format(dateFormatter)
                );
            }
            return new SimpleStringProperty("N/A");
        });
        
        emailColumn.setCellValueFactory(cellData -> {
            Product product = cellData.getValue();
            String email = product.getEmail_customer();
            return new SimpleStringProperty(
                email != null && !email.isEmpty() ? email : "N/A"
            );
        });
    }
    
    private void loadHistoryData() {
        historyData.clear();
        
        Stack<Product> allHistoryProducts = productStack.getHistoryProducts();
        
        for (Product product : allHistoryProducts) {
            historyData.add(product);
        }
        
        historyTable.setItems(historyData);
    }
    
    private void handleExit() {
        if (onExitCallback != null) {
            onExitCallback.run();
        }
    }
}

