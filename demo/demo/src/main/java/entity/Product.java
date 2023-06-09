package entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {
	private String id;
    private String name;
    private String img;
    private String category;
    private boolean sale;
    private int price;
    

    public Product() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }
    
    public String getCategory() {
        return category;
    }
    
    public String getImg() {
        return img;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setImg(String img) {
        this.img = img;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    public boolean getSale() {
        return sale;
    }
    
    public void setSale(boolean sale) {
        this.sale = sale;
    }
}
