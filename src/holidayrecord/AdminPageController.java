/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package holidayrecord;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author andeladeveloper
 */
public class AdminPageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox vHolder;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        vHolder.setSpacing(50);
        
        try {
            getAllData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void logoutAction(MouseEvent event) throws Exception {
              Stage stage;
        Parent root;

        stage = (Stage) vHolder.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        stage.setY(90);
        stage.setScene(scene);
    }

    private void getAllData() throws Exception {
        String sURL = "https://labify.herokuapp.com/api/leave"; //just a string

        // Connect to the URL using java's native library
        URL url = new URL(sURL);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
       
            // Convert to a JSON object to print data
    JsonParser jp = new JsonParser(); //from gson
    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
    JsonArray rootobj = root.getAsJsonArray(); //May be an array, may be an object. 
    rootobj.forEach((obj) -> {
     JsonObject leave = obj.getAsJsonObject();
        System.out.println(leave.get("id"));
         LeaveItem item = new LeaveItem();
         item.setfullName(leave.get("fullName").getAsString());
         item.setEmpId(leave.get("empId").getAsString());
         item.setDateGoing(leave.get("dateGoing").getAsString());
         item.setDateComing(leave.get("dateComing").getAsString());
         item.setLeaveReason(leave.get("reason").getAsString());
            vHolder.getChildren().add(item);
    });
    }
}
