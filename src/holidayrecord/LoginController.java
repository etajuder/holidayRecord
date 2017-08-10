/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package holidayrecord;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author andeladeveloper
 */
public class LoginController implements Initializable {

    @FXML
    private Label breakLink;

    @FXML
    private TextField username;

    @FXML
    private TextField password;
    
    @FXML
    private Label errorCode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleButttonExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleBreakLinkMouseOver(MouseEvent event) {
        breakLink.setStyle(breakLink.getStyle() + " -fx-text-fill: #4aaee7;");
    }
    private final String USER_AGENT = "Mozilla/5.0";

    @FXML
    private void handleBreakLinkAction(MouseEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) breakLink.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("LeaveForm.fxml"));
        Scene scene = new Scene(root);
        stage.setY(10);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleLoginAction(ActionEvent event) {
        errorCode.setText("");
        String userName = username.getText();
        String passWord = password.getText();
        try {
            login(userName, passWord);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void login(String username, String password) throws Exception {
        String urlLink = "https://labify.herokuapp.com/api/admin/login";
        URL url = new URL(urlLink);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        String urlParameters = String.format("username=%s&password=%s", username, password);

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
            case 200:
                showAdminPage();
                break;
            case 404:
                errorCode.setText("Invalid credentials supplied");
                break;
            default:
                errorCode.setText("Error occurred please try again");
                break;
        }
    }
    
    private void showAdminPage() throws IOException {
            Stage stage;
        Parent root;
        stage = (Stage) breakLink.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AdminPage.fxml"));
        Scene scene = new Scene(root);
        stage.setY(20);
        stage.setScene(scene);
        stage.show();
    }

}
