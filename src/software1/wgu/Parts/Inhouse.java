/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software1.wgu.Parts;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author khue
 */
public class Inhouse extends Part {
    
  private  SimpleStringProperty machineID;
  
   public Inhouse(String name, String price, String inStock, String min, String max, String machineID) {
        this.partID = new SimpleIntegerProperty(autoPartID);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(price);
        this.inStock = new SimpleStringProperty(inStock);
        this.min = new SimpleStringProperty(min);
        this.max = new SimpleStringProperty(max);
        this.machineID = new SimpleStringProperty(machineID);
        autoPartID++;
    }
   public Inhouse(String name, String price, String inStock, String min, String max, String machineID,int partID) {
        this.partID = new SimpleIntegerProperty(partID);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(price);
        this.inStock = new SimpleStringProperty(inStock);
        this.min = new SimpleStringProperty(min);
        this.max = new SimpleStringProperty(max);
        this.machineID = new SimpleStringProperty(machineID);
        
    }

   

  
    String getMachineID() {
           return machineID.get();
    }
     

    

    

    public void setMachineID(String machineID) {
        this.machineID.set(machineID);
    }

    String getCompanyName() {
       return null;
    }

    @Override
    public String returnCompanyNameOrString() {
        return getMachineID();
        
    }
    
    }
    
    
    
   


