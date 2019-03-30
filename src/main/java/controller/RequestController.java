package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import model.request.Request;
import service.ResourceLoader;
import service.StageManager;

import java.util.Collection;

public class RequestController extends Controller {

    @FXML
    private JFXButton homeBtn;

    private Collection<Request> requests;
    private Collection<Request> pendingRequests;


    @FXML
    // switches window to home screen
    public void showHome() throws Exception {
        Stage stage = (Stage) homeBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(ResourceLoader.home);
        StageManager.changeExistingWindow(stage, root, "Home (Path Finder)");
    }

    // submits request to database
    // "confirm" button
    void makeRequest(Request type) {

    }

    // removes object from database
    void fufillRequest(String requestID, String byWho) {

    }

    // getter for pendingRequests
    public Collection<Request> getPendingRequests () {
        return pendingRequests  ;
    }

}
