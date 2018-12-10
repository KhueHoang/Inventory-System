/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software1.wgu.Parts;

/**
 *
 * @author khue
 */

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Outsourced extends Part {

    private final StringProperty companyName;

    public Outsourced( String name, String price, String  inStock, String  min, String  max, String companyName) {
        this.partID = new SimpleIntegerProperty(autoPartID);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(price);
        this.inStock = new SimpleStringProperty(inStock);
        this.min = new SimpleStringProperty(min);
        this.max = new SimpleStringProperty(max);
        this.companyName = new SimpleStringProperty(companyName);
        autoPartID++;
        
    }
      public Outsourced( String name, String price, String  inStock, String  min, String  max, String companyName,int partID) {
        this.partID = new SimpleIntegerProperty(partID);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(price);
        this.inStock = new SimpleStringProperty(inStock);
        this.min = new SimpleStringProperty(min);
        this.max = new SimpleStringProperty(max);
        this.companyName = new SimpleStringProperty(companyName);
        
        
    }
    
    

    
    String getMachineID() {
        return companyName.get();
    }

  

  
    public String getCompanyName() {
        return this.companyName.get();
    }

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }

    @Override
    public String returnCompanyNameOrString() {
        return getCompanyName();
    }
    
   
}