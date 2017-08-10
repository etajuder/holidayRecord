/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package holidayrecord;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 *
 * @author andeladeveloper
 */
public class LeaveItem extends Pane {

    @FXML
    private Label fullName;

    @FXML
    private Label empId;

    @FXML
    private Label goingDate;

    @FXML
    private Label comingDate;

    @FXML
    private Button reason;
    
    private String leaveReason;

    public LeaveItem() {
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("LeaveItem.fxml"));
        fXMLLoader.setController(this);
        fXMLLoader.setRoot(this);
        
        try {
            fXMLLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @FXML
    private void getReasonAction(ActionEvent event) {
        Alert al = new Alert(Alert.AlertType.INFORMATION, this.leaveReason, ButtonType.OK);
        al.show();
    }
    
    public void setLeaveReason(String reason) {
        this.leaveReason = reason;
    }
    
    public String getReason() {
      return this.leaveReason;
    }
    public String getFullName() {
        return fullName.textProperty().get();
    }

    public void setfullName(String name) {
        fullName.textProperty().set(name);
    }

    public String getEmpId() {
        return empId.textProperty().get();
    }
    
    public void setEmpId(String id) {
      empId.textProperty().set(id);
    }
    
    public void setDateGoing(String date) {
      goingDate.textProperty().set(date);
    }
    
    public String getDateGoing() {
      return goingDate.textProperty().get();
    }
    
    public String getDateComing() {
      return comingDate.textProperty().get();
    }
    
    public void setDateComing(String date) {
      comingDate.textProperty().set(date);
    }
    
    
}
