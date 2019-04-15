package home;

import application_state.ApplicationState;
import application_state.Observer;
import com.google.common.eventbus.EventBus;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import application_state.Event;
import database.CSVService;
import service.ResourceLoader;
import service.StageManager;

import java.io.IOException;

public class AdminServiceController {

    private Event event = ApplicationState.getApplicationState().getFeb().getEvent();

    @FXML
    private JFXButton fulfillRequestBtn, editEmployeeBtn, mapEditorController, exportCSVBtn, showHomeBtn, newNode_btn;

    @FXML
    private JFXToggleNode aStarToggle;

    @FXML
    private JFXToggleNode depthFirstToggle;

    @FXML
    private JFXToggleNode breadthFirstToggle;

    @FXML
    private ToggleGroup algorithm;

    @FXML
    private void showFulfillRequest() throws Exception {
        Stage stage = (Stage) fulfillRequestBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(ResourceLoader.fulfillrequest);
        StageManager.changeExistingWindow(stage, root, "Fulfill Request");
    }

    @FXML
    void showSearchResults(ActionEvent e) {
        event = ApplicationState.getApplicationState().getFeb().getEvent();
        event.setEventName("closeDrawer");
        ApplicationState.getApplicationState().getFeb().updateEvent(event);
    }

    @FXML
    private void showEditEmployees() throws Exception {
        Stage stage = (Stage) editEmployeeBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(ResourceLoader.employeeEdit);
        StageManager.changeExistingWindow(stage, root, "Edit Employees");
    }

    @FXML
    private void exportCSV() throws IOException {
        CSVService.exportEdges();
        CSVService.exportNodes();
        CSVService.exportEmployees();
        CSVService.exportReservableSpaces();
    }

    public void astarSwitch(ActionEvent actionEvent) {
        algorithm.selectToggle(aStarToggle);
        event = ApplicationState.getApplicationState().getFeb().getEvent();
        event.setEventName("methodSwitch");
        event.setSearchMethod("astar");
        ApplicationState.getApplicationState().getFeb().updateEvent(event);
    }

    public void depthSwitch(ActionEvent actionEvent) {
        algorithm.selectToggle(depthFirstToggle);
        event = ApplicationState.getApplicationState().getFeb().getEvent();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!! function called !!!!!!!!!!!!!!!!!!");
        event.setEventName("methodSwitch");
        event.setSearchMethod("depth");
        ApplicationState.getApplicationState().getFeb().updateEvent(event);    }

    public void breadthSwitch(ActionEvent actionEvent) {
        algorithm.selectToggle(breadthFirstToggle);
        event = ApplicationState.getApplicationState().getFeb().getEvent();
        event.setEventName("methodSwitch");
        event.setSearchMethod("breadth");
        ApplicationState.getApplicationState().getFeb().updateEvent(event);
    }

    @FXML
    void showNewNode(ActionEvent e) throws  Exception{
        Parent root = FXMLLoader.load(ResourceLoader.createNode);
        Stage stage = (Stage) newNode_btn.getScene().getWindow();
        StageManager.changeExistingWindow(stage, root, "Add Node");
    }

    @FXML
    void initialize() {    // Added so that search method toggle is already selected
        if (event.getSearchMethod().equals("astar")) {
            algorithm.selectToggle(aStarToggle);
        }
        else if (event.getSearchMethod().equals("breadth")) {
            algorithm.selectToggle(breadthFirstToggle);
        }
        else {
            algorithm.selectToggle(depthFirstToggle);
        }
    }
}
