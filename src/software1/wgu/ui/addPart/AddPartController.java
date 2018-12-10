/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software1.wgu.ui.addPart;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import software1.wgu.Parts.Inhouse;
import software1.wgu.Parts.Outsourced;
import software1.wgu.Parts.Part;
import software1.wgu.inventory.Inventory;
import software1.wgu.ui.main.MainController;

/**
 * FXML Controller class
 *
 * @author khue
 */
public class AddPartController implements Initializable {
    
    @FXML
    private RadioButton inHouseRadio;
    @FXML
    private RadioButton outsourceRadio;
    @FXML
    private Text companyOrMachineText;
   
    @FXML
    private TextField nameField;
    @FXML
    private TextField invField;
    @FXML
    private TextField priceCostField;
    @FXML
    private TextField maxField;
    @FXML
    private TextField companyOrMachineField;
    @FXML
    private TextField minField;
    @FXML
    private Button saveButton;
    Inventory inventory;
   static int partIDCouanter =1;
   
 
   
   
    @FXML
    private TextField autogenField;

    @FXML
    private AnchorPane rootPane;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        autogenField.setEditable(false);
        autogenField.setText("AUTOGEN "+partIDCouanter+"");
       inventory = Inventory.getInventoryInstance();
        System.out.println(inventory);
     companyOrMachineText.setText("Company Name");
     outsourceRadio.setSelected(true);
       
    }    

    public void updatePartID(){
         partIDCouanter++;
        autogenField.setText(partIDCouanter+"");
       
    }
    
   public void initData(Inventory inventory){
       
    
      
  
      
       
   }
    @FXML
    private void savePart(ActionEvent event) {
     
        boolean flag2 = nameField.getText().isEmpty()||priceCostField.getText().isEmpty()||priceCostField.getText().isEmpty()||invField.getText().isEmpty()||
                maxField.getText().isEmpty()||minField.getText().isEmpty();
       

    
   
       
       boolean ishouse = inHouseRadio.isSelected();
       
       
       
       if(flag2){
            Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Please Enter in all fields");
        alert.showAndWait();
       }else if( Integer.parseInt(invField.getText())<Integer.parseInt(minField.getText())||Integer.parseInt(invField.getText()) >Integer.parseInt(maxField.getText()) ){
         Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Inventory cannot be lower or higher then min/max");
        alert.showAndWait();
    }else{
                    String name = nameField.getText();
       String price = priceCostField.getText();
       String inv = invField.getText();
       String max =  maxField.getText();
       String min = minField.getText();
     
       int invInt = Integer.parseInt(inv);
       int maxInt = Integer.parseInt(max);
       int minInt = Integer.parseInt(min);
       
    String machineIDOrCompany = companyOrMachineField.getText();  
           
                   
                 if(ishouse){
               inventory.addPart(new Inhouse(name,price,inv,min,max,machineIDOrCompany));
               
    }else{
               inventory.addPart(new Outsourced(name, price, inv, min, max, machineIDOrCompany));
        
    }     
       
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Saved");
        alert.showAndWait();
        
        clearMessage();
           updatePartID();
     
       }  
     
       }
    void clearMessage(){
         nameField.setText("");
    
     invField.setText("");
    
     priceCostField.setText("");
    
     maxField.setText("");
    
     companyOrMachineField.setText("");
   
  minField.setText("");
    }

    @FXML
    private void cancelPart(ActionEvent event) {
          Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void inHouseAction(ActionEvent event) {
        
               if(outsourceRadio.isSelected()){
          outsourceRadio.setSelected(false);
          companyOrMachineText.setText("Machine ID");
        }
        
    }

    @FXML
    private void outSourceAction(ActionEvent event) {
    
                  if(inHouseRadio.isSelected()){
            inHouseRadio.setSelected(false);
            companyOrMachineText.setText("Company Name");
        }        
        
      
} 
       
    }
    

    
   

  
    

