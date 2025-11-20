package Models;

public class Product {

    private int idProduct;
    private String name;
    private String brand;
    private String[] sizes;
    private float price;
    private String description;

    public Product() {
    }

    public Product(int idProduct, String name, String brand, String[] sizes, float price, String description) {
        this.idProduct = idProduct;
        this.name = name;
        this.brand = brand;
        this.sizes = sizes;
        this.price = price;
        this.description = description;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String[] getSizes() {
        return sizes;
    }

    public void setSizes(String[] sizes) {
        this.sizes = sizes;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }        
}