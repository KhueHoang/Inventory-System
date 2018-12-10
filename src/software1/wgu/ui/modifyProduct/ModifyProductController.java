/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software1.wgu.ui.modifyProduct;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import software1.wgu.Parts.Part;
import software1.wgu.Product.Product;
import software1.wgu.inventory.Inventory;

/**
 * FXML Controller class
 *
 * @author khue
 */
public class ModifyProductController implements Initializable {
 Inventory inventory;
    
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField productIDField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField invField;
    @FXML
    private TextField priceCostField;
    @FXML
    private TextField maxField;
    @FXML
    private TextField minField;
    @FXML
    private TableView<Part> allPartTable;
    @FXML
    private TableColumn<Part, Integer> partIDCol;
    @FXML
    private TableColumn<Part, String> partNameCol;
    @FXML
    private TableColumn<Part,Integer> partInvCol;
    @FXML
    private TableColumn<Part, Double> partPriceUnit;
    @FXML
    private TableView<Part> allProductPartsTable;
    @FXML
    private TableColumn<Part, Integer> partIDProduct;
    @FXML
    private TableColumn<Part, String> partNameProduct;
    @FXML
    private TableColumn<Part, Integer> invPartProduct;
    @FXML
    private TableColumn<Part, Double> pricePartProduct;
    @FXML
    private Button addPartButton;
    @FXML
    private TextField searchAllPart;
    ObservableList<Part> list = FXCollections.observableArrayList();
    ObservableList<Part> listProductParts = FXCollections.observableArrayList();
    Part part;
   int index=0;
    Product tempProduct;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        productIDField.setEditable(false);
        
        inventory = Inventory.getInventoryInstance();
        index =   inventory.getProductList().size()-1;
        initData();
        initPartCol();
        loadPartData();
        initProductPartsCol();
        loadProductParts(); 
        
       
        
    }    
    public void initData(){
        tempProduct = inventory.returnProductModifyID();
        
        
        
        productIDField.setText("AUTOGEN"+tempProduct.getProductID()+"");
        nameField.setText(tempProduct.getName());
        invField.setText(tempProduct.getInStock()+"");
        priceCostField.setText(tempProduct.getPrice()+"");
        maxField.setText(tempProduct.getMax()+"");
        minField.setText(tempProduct.getMin()+"");
        
       
    }
public void loadProductParts(){
    listProductParts.addAll(tempProduct.getProductParts());
    allProductPartsTable.getItems().setAll(listProductParts);
    
}
   

    @FXML
    private void saveProductBut(ActionEvent event) {
        
        
boolean flag = nameField.getText().isEmpty()|| invField.getText().isEmpty()||priceCostField.getText().isEmpty()||maxField.getText().isEmpty()||minField.getText().isEmpty();
        
         if(flag){
            Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Please Enter in all fields");
        alert.showAndWait();
       }else if(checkProductPrice()){
           Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Product price cannot be less then part price");
        alert.showAndWait();
        listProductParts.removeAll();
       }
         
         else{
             String nameTemp = nameField.getText();
             int inventoryTemp = Integer.parseInt(invField.getText());
             int max = Integer.parseInt(maxField.getText());
             int min = Integer.parseInt(minField.getText());
            double priceCost = Double.parseDouble(priceCostField.getText());
             
             tempProduct.setName(nameTemp);
             tempProduct.setInStock(inventoryTemp);
             tempProduct.setMax(max);
             tempProduct.setMin(min);
             tempProduct.setPrice(priceCost);
             
             
            
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             
        alert.setHeaderText(null);
        alert.setContentText("Saved");
        alert.showAndWait();
         
          if(part!=null){
              tempProduct.addParts(part);
              listProductParts.removeAll(part);
          }
        
         }
         
      
    }

    @FXML
    private void cancelBut(ActionEvent event) {
           Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void deletePart(ActionEvent event) {
        
              Part part1 = allProductPartsTable.getSelectionModel().getSelectedItem();
       
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            
            alert.setTitle("Product Delete");
            alert.setHeaderText("Confirm?");
            alert.setContentText("Are you sure you want to delete "  + "?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
               listProductParts.remove(part1);
          allProductPartsTable.getItems().setAll(listProductParts);
                
                System.out.println("Part " + " was removed.");
            } else {
                System.out.println("Part " + " was not removed.");
            }
        
        
        
    }

    @FXML
    private void searchAllPartsBut(ActionEvent event) {
             String partName = searchAllPart.getText();
        
        for(Part part: inventory.getPartList()){
            if(part.getName().equals(partName)){
                list.clear();
                list.add(part);
                allPartTable.getItems().setAll(part);
                
              
            }
            
        }
    }

    @FXML
    private void addPartBut(ActionEvent event) {
                   part = allPartTable.getSelectionModel().getSelectedItem();
        if(part!= null){ 
            if(!listProductParts.contains(part)){
        listProductParts.add(part);
        allProductPartsTable.getItems().setAll(listProductParts);
       
          list.clear();
        loadPartData();
            }
        }else
        System.out.println("Part is null or already on list");
       
        
    }
    
    
    public  void initPartCol(){
        
     
         partIDCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
       partPriceUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
        
    }
      public  void loadPartData(){
      list.addAll(inventory.getPart());
        allPartTable.getItems().setAll(list);
        
       
    
                }
      
       public void initProductPartsCol(){
         partIDProduct.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameProduct.setCellValueFactory(new PropertyValueFactory<>("name"));
        invPartProduct.setCellValueFactory(new PropertyValueFactory<>("inStock"));
       pricePartProduct.setCellValueFactory(new PropertyValueFactory<>("price"));
     }

    @FXML
    private void generateData(MouseEvent event) {
        
        if(searchAllPart.getText().isEmpty()){
            list.clear();
            loadPartData();
        }
        
    }

    @FXML
    private void generateDataTable(MouseEvent event) {
    }
    
    private boolean checkProductPrice(){
        
       Double priceCheck= Double.parseDouble(priceCostField.getText());
        
     boolean productPriceCheck = false;
       for(Part part: listProductParts){
           if(priceCheck < Double.parseDouble(part.getPrice())){
               productPriceCheck =true;
           }
       }
       
     
       return productPriceCheck;
    }
    
    
}
