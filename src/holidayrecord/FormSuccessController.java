/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package holidayrecord;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author andeladeveloper
 */
public class FormSuccessController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button exitButton;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void exitButtonAction(ActionEvent event) throws IOException {
      Stage stage;
      Parent root;
      stage = (Stage) exitButton.getScene().getWindow();
      root = FXMLLoader.load(getClass().getResource("Login.fxml"));
      Scene scene = new Scene(root);
      stage.setScene(scene);
    }
    
}
