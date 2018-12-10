/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software1.wgu.Parts;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author khue
 */

    
    public abstract class Part {
        
     StringProperty inStock;
        StringProperty    max;
  StringProperty      min;
      StringProperty name;
   IntegerProperty partID;
    StringProperty price;
    static int autoPartID=1;
            
    //partID
    public void setPartID(Integer partID) {
        this.partID.set(partID);
    }
    
    public Integer getPartID() {
        return this.partID.get();
    }
    
    public IntegerProperty partIDProperty() {
        return partID;
    }
    public static void updatePartID(){
       
        
    }
    
    //name
    public void setName(String name) {
        this.name.set(name);
    }
    
    public String getName() {
        return this.name.get();
    }
    
    public StringProperty nameProperty() {
        return name;
    }
    
    //price
    public void setPrice(String price) {
        this.price.set(price);
    }
    
    public String getPrice() {
        return this.price.get();
    }
    
    public StringProperty priceProperty() {
        return price;
    }
    
    //inStock
    public void setInStock(String inStock) {
        this.inStock.set(inStock);
    }
    
    public String getInStock() {
        return this.inStock.get();
    }
    
    public StringProperty inStockProperty() {
        return inStock;
    }
    
    //min
    public void setMin(String min) {
        this.min.set(min);
    }
    
    public String getMin() {
        return this.min.get();
    }
    
    public StringProperty minProperty() {
        return min;
    }
    
    //max
    public void setMax(String max) {
        this.max.set(max);
    }
    
    public String getMax() {
        return this.max.get();
    }
    
    public StringProperty maxProperty() {
        return max;
    }


    public String getPartName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 public abstract String returnCompanyNameOrString();
        
    
    
}
    

