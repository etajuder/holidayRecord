/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package holidayrecord;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author andeladeveloper
 */
public class LeaveFormController implements Initializable {

    @FXML
    private TextField fullName;

    @FXML
    private TextField empId;

    @FXML
    private DatePicker goingDate;

    @FXML
    private DatePicker comingDate;

    @FXML
    private TextArea reason;
    
    @FXML
    private Label errorCode;
    
    private final String USER_AGENT = "Mozilla/5.0";

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button formSubmit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String errorStyle = fullName.getStyle().concat("-fx-border-color: tomato;");
        String successStyle = fullName.getStyle().concat("-fx-border-color: green");

        fullName.setOnKeyReleased(((event) -> {
            if (fullName.getText().length() >= 6) {
                fullName.setStyle(successStyle);
            } else {
                fullName.setStyle(errorStyle);
            }
        }));

        empId.setOnKeyReleased(((event) -> {
            if (empId.getText().length() >= 6) {
                empId.setStyle(successStyle);
            } else {
                empId.setStyle(errorStyle);
            }
        }));
        
        reason.setOnKeyReleased(((event) -> {
            if (reason.getText().length() >= 20) {
                reason.setStyle(successStyle);
            } else {
                reason.setStyle(errorStyle);
            }
        }));
    }

    @FXML
    private void extButtonAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void submitButtonAction(ActionEvent event) throws Exception {
        sendLeave();
    }

    private void showSuccess() throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) formSubmit.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("FormSuccess.fxml"));
        Scene scene = new Scene(root);
        stage.setY(90);
        stage.setScene(scene);
    }
    
    @FXML
    private void goHome(MouseEvent event) throws Exception{
      Stage stage;
        Parent root;

        stage = (Stage) formSubmit.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        stage.setY(90);
        stage.setScene(scene);
    }
    private void sendLeave() throws Exception {
      errorCode.setStyle("");
      String urlLink = "https://labify.herokuapp.com/api/leave";
        URL url = new URL(urlLink);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        String urlParameters = String.format("fullName=%s&empId=%s&dateGoing=%s&dateComing=%s&reason=%s&status=%s",
                fullName.getText(),
                empId.getText(),
                goingDate.getEditor().getText(),
                comingDate.getEditor().getText(),
                reason.getText(),
                "Pending"
                );

        connection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = connection.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);
        
        switch (responseCode) {
            case 201:
                System.out.println("Leave submitted successfully");
                showSuccess();
                break;
            case 400:
                errorCode.setText("Error occurred");
                break;
            default:
                errorCode.setText("Error occurred please try again");
                break;
        }
    }
    
}
