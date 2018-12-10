/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software1.wgu.Product;

import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import software1.wgu.Parts.Part;

/**
 *
 * @author khue
 */
public class Product {
 private ArrayList<Part> partList = new ArrayList<>();
  private IntegerProperty productID;
 private    StringProperty productName;
private     DoubleProperty productPrice;
private   IntegerProperty productInStock;
 private    IntegerProperty productMin;
private     IntegerProperty productMax;
  private static   Integer autoProductID=1;

    public Product( String name, double price, int inStock, int min, int max) {
        this.productID = new SimpleIntegerProperty(autoProductID);
        this.productName = new SimpleStringProperty(name);
        this.productPrice = new SimpleDoubleProperty(price);
        this.productInStock = new SimpleIntegerProperty(inStock);
        this.productMin = new SimpleIntegerProperty(min);
        this.productMax = new SimpleIntegerProperty(max);
        autoProductID++;
    }



    //productID
    public void setProductID(int productID) {
        this.productID.set(productID);
    }
   

    public int getProductID() {
        return this.productID.get();
    }

    public IntegerProperty productIDProperty() {
        return productID;
    }
    
    //productName
     public void setName(String name) {
        this.productName.set(name);
    }

    public String getName() {
        return this.productName.get();
    }

    public StringProperty productNameProperty() {
        return productName;
    }
    
    //productPrice
    public void setPrice(double price) {
        this.productPrice.set(price);
    }

    public double getPrice() {
        return this.productPrice.get();
    }

    public DoubleProperty productPriceProperty() {
        return productPrice;
    }

    //productInStock
    public void setInStock(int inStock) {
        this.productInStock.set(inStock);
    }

    public int getInStock() {
        return this.productInStock.get();
    }

    public IntegerProperty productInStockProperty() {
        return productInStock;
    }
    
    //productMin
    public void setMin(int min) {
        this.productMin.set(min);
    }
    
    public int getMin() {
        return this.productMin.get();
    }

    public IntegerProperty productMinProperty() {
        return productMin;
    }
    
    //productMax
     public void setMax(int max) {
        this.productMax.set(max);
    }

    public int getMax() {
        return this.productMax.get();
    }
    
    public IntegerProperty productMaxProperty() {
        return productMax;
    }
    
    //associatedParts
    public void setProductParts(Part part) {
      partList.add(part);
    }
    
    public ArrayList<Part> getProductParts() {
        return partList;
    }
    public void addParts(Part part){
        partList.add(part);
    }
    

}