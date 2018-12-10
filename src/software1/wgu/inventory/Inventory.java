/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software1.wgu.inventory;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableListValue;
import software1.wgu.Parts.Inhouse;
import software1.wgu.Parts.Outsourced;
import software1.wgu.Parts.Part;
import software1.wgu.Product.Product;

/**
 *
 * @author khue
 */
public class Inventory {
  static ArrayList<Part> partList;
  static Inventory inventory;
  static ArrayList<Product> productList;  
   
  
   int partModifyID=0;
   int productModifyID = 0;
   
     
   
   public static Inventory getInventoryInstance(){
      
     if(inventory==null){
         inventory= new Inventory();
     }
     if(partList==null){
         partList = new ArrayList<>();
         productList = new ArrayList<>();
     }
     return inventory;
}
   
   public Part returnPartModifyID(){
       
       for(Part part: partList){
           if(part.getPartID()==partModifyID){

               return part;
           }
       }
       
       
       return null;
   }
   public void addPartModify(int ID){
       partModifyID=ID;
   }
   public Product returnProductModifyID(){
    
       for(Product product: productList){
           if(product.getProductID()== productModifyID){
             
               return product;
           }
       }
       return null;
   }
   public void addProductModify(int ID){
       this.productModifyID=ID;
   }
public  void addPart(Part part){
    partList.add(part);
    
}

    public  ArrayList<Part> getPartList() {
        return partList;
    }
    

    public static Inventory getInventory() {
        return inventory;
    }
public ArrayList<Part> getPart(){
    return partList;
}
public void updatePart(int index,Part part){
    partList.set(index,part);
   
}

public Part returnPart(int index){
    if(partList.get(index)==null){
        return null;
    }else
        return partList.get(index);
}

public void addProduct(Product product){
    productList.add(product);
}

public ArrayList<Product> getProductList(){
    return productList;
    
}
public void updateProduct(int index,Product product){
    productList.set(index, product);
}


public Product returnProduct(int index){
    if(productList.get(index)==null){
        return null;
    }else
        return productList.get(index);
}

public void removePart(Part part){
    if(part!=null){
    partList.remove(part);
    }
}
public void removeProduct(Product product){
    if(product!=null){
        productList.remove(product);
    }
}

public int returnIndex(Part part){
  return  partList.indexOf(part);
    
}



}
