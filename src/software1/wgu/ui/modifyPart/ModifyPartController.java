/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software1.wgu.ui.modifyPart;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

/**
 * FXML Controller class
 *
 * @author khue
 */
public class ModifyPartController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private RadioButton inHouseRadio;
    @FXML
    private RadioButton outsourceRadio;
    @FXML
    private Text companyOrMachineText;
    @FXML
    private TextField autogenField;
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
int index=0;

 Part tempPart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       inventory = Inventory.getInventoryInstance();
       
       initData();
        System.out.println(inventory);
       
    }    

    
    public void initData(){
     
       
        
              
     tempPart = inventory.returnPartModifyID();
     
 
           
            
        autogenField.setText("AUTOGEN "+tempPart.getPartID()+"");
        autogenField.setEditable(false);
       nameField.setText(tempPart.getName());
       invField.setText(tempPart.getInStock());
       priceCostField.setText(tempPart.getPrice());
       maxField.setText(tempPart.getMax());
       minField.setText(tempPart.getMin());
       
       
       if(tempPart instanceof Inhouse){
           companyOrMachineText.setText("Machine ID");
           inHouseRadio.setSelected(true);
           
        
    }else{
           companyOrMachineText.setText("Company Name");
           outsourceRadio.setSelected(true);
       }
       companyOrMachineField.setText(tempPart.returnCompanyNameOrString());
 
         
            
     
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

    @FXML
    private void savePart(ActionEvent event) {
        
       
       

       
                 boolean flag2 = nameField.getText().isEmpty()||priceCostField.getText().isEmpty()||priceCostField.getText().isEmpty()||invField.getText().isEmpty()||
                maxField.getText().isEmpty()||minField.getText().isEmpty();
       
       
       
       if(flag2){
            Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Please Enter in all fields");
        alert.showAndWait();
       }else if( Integer.parseInt(invField.getText())<Integer.parseInt(minField.getText())||Integer.parseInt(invField.getText()) >Integer.parseInt(maxField.getText())){
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
       int partID = tempPart.getPartID();
       
       tempPart.setInStock(inv);
       tempPart.setName(name);
       tempPart.setMax(max);
       tempPart.setMin(min);
       tempPart.setPrice(price);
       int indexOFPart = inventory.returnIndex(tempPart);
      Part newPart;
      
      if(inHouseRadio.isSelected()){
          newPart = new Inhouse(name, price, inv, min, max, companyOrMachineField.getText(),partID);
      }else{
          newPart = new Outsourced(name, price, inv, min, max,companyOrMachineField.getText(),partID);
      }
       
       inventory.updatePart(indexOFPart , newPart);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Saved");
        alert.showAndWait();
        
        
       }
        
    }

    @FXML
    private void cancelPart(ActionEvent event) {
         Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
        
    }
    
}
