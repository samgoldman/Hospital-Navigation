package service_request.model.sub_model;

import database.DatabaseService;
import employee.model.Employee;
import employee.model.JobType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import map.Node;
import service_request.model.Request;

public class HelpRequest extends Request {
    static DatabaseService myDBS = DatabaseService.getDatabaseService();

    public HelpRequest(int id, String notes, Node location, boolean completed) {
        super(id, notes, location, completed);
    }

    @Override
    public void makeRequest() {
        DatabaseService.getDatabaseService().insertHelpRequest(this);
    }

    @Override
    public void fillRequest() {
        this.setCompleted(true);
        DatabaseService.getDatabaseService().updateHelpRequest((HelpRequest)this);
    }

    @Override
    public ObservableList<Request> showProperRequest() {
        return (ObservableList) myDBS.getAllHelpRequests();
    }

    @Override
    public ObservableList<Employee> returnCorrectEmployee() {
        ObservableList<Employee> allEmployee = FXCollections.observableArrayList();
        allEmployee.addAll(myDBS.getAllEmployees()) ;

        return allEmployee ;
    }

    @Override
    public void updateEmployee(Request selectedTask, Employee selectedEmp) {
        myDBS.updateHelpRequest((HelpRequest) selectedTask) ;
    }

    @Override
    public boolean fulfillableByType(JobType jobType) {
        return true;
    }

    @Override
    public String toDisplayString() {
        if (this.getAssignedTo() == 0) this.setAssignedTo(-1);
        return String.format("Help Request %d, Description: %s, Assigned To: %s, Fulfilled: %s",
                this.getId(), this.getNotes(), this.getAssignedTo() == -1 ? "None" : "" + this.getAssignedTo(), this.isCompleted() ? "Yes" : "No");
    }

    @Override
    public boolean isOfType(String typeString) {
        return typeString.equals("Help");
    }
}
