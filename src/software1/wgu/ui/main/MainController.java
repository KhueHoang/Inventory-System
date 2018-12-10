/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software1.wgu.ui.main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import software1.wgu.Parts.Inhouse;
import software1.wgu.Parts.Part;
import software1.wgu.Product.Product;
import software1.wgu.inventory.Inventory;
import software1.wgu.ui.addPart.AddPartController;

/**
 * FXML Controller class
 *
 * @author khue
 */
public class MainController implements Initializable {
   ObservableList<Part> list = FXCollections.observableArrayList();
  ObservableList<Product> listProduct = FXCollections.observableArrayList();
    @FXML
    private TableView<Part> partTableView;
    @FXML
    private TableColumn<Part, String> partIDCol;
    @FXML
    private TableColumn<Part, String> partNameCol;
    @FXML
    private TableColumn<Part, String> partInvCol;
    @FXML
    private TableColumn<Part, String> partPriceUnit;
    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TableColumn<Product, Integer> productIDCol;
    @FXML
    private TableColumn<Product, String> productNameCol;
    @FXML
    private TableColumn<Product, Integer> productInvCol;
    @FXML
    private TableColumn<Product, Double> productPriceCol;
   
    @FXML
    private Button deletePart;
    
    @FXML
    private Button deleteProduct;
    @FXML
    private Button searchPartButton;
    @FXML
    private Button searchProductButton;
    @FXML
    private TextField partSearchField;
    @FXML
    private TextField productSearchField;
   
Inventory inventory;
    @FXML
    private AnchorPane rootPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          inventory= Inventory.getInventoryInstance();
                
       
        initPartCol();
        loadPartData();
        
        initProductCol();
        loadPartData();
        
     
        
        
    }  
   
    
    
    
   public  void initPartCol(){
       
 
  
    
     
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
       partPriceUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
        
    }
   public void initProductCol(){
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productInvCol.setCellValueFactory(new PropertyValueFactory<>("productInStock"));
       productPriceCol.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
       
   }
   public  void loadPartData(){
      list.addAll(inventory.getPart());
        partTableView.getItems().setAll(list);
        
       
    
                }
   
   public void loadProductData(){
       listProduct.addAll(inventory.getProductList());
       productTableView.getItems().setAll(listProduct);
       
   }
   
   public void updatePartTable(){
       
       
   }
   
   
    void loadWindow(String loc,String title){
        
    try {
        Parent parent = FXMLLoader.load(getClass().getResource(loc));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle(title);
        stage.setScene(new Scene(parent));
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
    }   
    }

    @FXML
    private void addPartWindow(ActionEvent event) {
        
       
        loadWindow("/software1/wgu/ui/addPart/addPart.fxml", "Add Parts");
        
        
    }

    @FXML
    private void modifyPartWindow(ActionEvent event) {
        
         int partID =partTableView.getSelectionModel().getSelectedItem().getPartID();
        inventory.addPartModify(partID);
        
         loadWindow("/software1/wgu/ui/modifyPart/modifyPart.fxml", "Modify Parts");
        
    }

    @FXML
    private void addProductWindow(ActionEvent event) {
         loadWindow("/software1/wgu/ui/addProduct/addProduct.fxml", "Add Product");
        
    }

    @FXML
    private void modifyProductWindow(ActionEvent event) {
        int productID = productTableView.getSelectionModel().getSelectedItem().getProductID();
        inventory.addProductModify(productID);
        
        loadWindow("/software1/wgu/ui/modifyProduct/modifyProduct.fxml", "Modify Product");
    }

    @FXML
    private void generateDataTable(MouseEvent event) {
     
            if(partSearchField.getText().isEmpty()){
                list.clear();
                loadPartData();
                
            }
            if(productSearchField.getText().isEmpty()){
                listProduct.clear();
                loadProductData();
            }
      
  
        
    }
    public ArrayList<Part> addToArray(){
        return inventory.getPart();
    }

    @FXML
    private void searchParts(ActionEvent event) {
        String partName = partSearchField.getText();
        
        for(Part part: inventory.getPartList()){
            if(part.getName().equals(partName)){
                list.clear();
                list.add(part);
                partTableView.getItems().setAll(part);
                
              
            }
            
        }
        
    }

    @FXML
    private void deletePartBut(ActionEvent event) {
        Part  part = partTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(AlertType.CONFIRMATION);
            
            alert.setTitle("Product Delete");
            alert.setHeaderText("Confirm?");
            alert.setContentText("Are you sure you want to delete "  + "?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
               inventory.removePart(part);
                list.clear();
                loadPartData();
                System.out.println("was removed.");
            } else {
                System.out.println(" was not removed.");
            }
        
        
    }

    @FXML
    private void deleteProductBut(ActionEvent event) {
        
                Product  product = productTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(AlertType.CONFIRMATION);
            
            alert.setTitle("Product Delete");
            alert.setHeaderText("Confirm?");
            alert.setContentText("Are you sure you want to delete "  + "?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
               inventory.removeProduct(product);
                listProduct.clear();
                loadProductData();
                System.out.println("Product " + " was removed.");
            } else {
                System.out.println("Product " + " was not removed.");
            }
        
    }

    @FXML
    private void exit(ActionEvent event) {
         Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
        
    }

    @FXML
    private void searchProduct(ActionEvent event) {
        
                String productName = productSearchField.getText();
        
        for(Product product: inventory.getProductList()){
            if(product.getName().equals(productName)){
                listProduct.clear();
                listProduct.add(product);
                productTableView.getItems().setAll(product);
                
              
            }
            
        }
    }
    
    
    
    
}
