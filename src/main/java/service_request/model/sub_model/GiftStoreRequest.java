package service_request.model.sub_model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import employee.model.Employee;
import employee.model.JobType;
import map.Node;
import database.DatabaseService;
import service_request.model.Request;

import java.util.Objects;

import static employee.model.JobType.*;

public class GiftStoreRequest extends Request {

    public enum GiftType {BALLOONS, TEDDY_BEAR, GIFT_BASKET}
    GiftType gType ;
    String patientName ;


    public GiftStoreRequest(int id, String notes, Node location, boolean completed, GiftType gType, String patientName) {
        super(id, notes, location, completed);
        this.gType = gType ;
        this.patientName = patientName ;
    }

    public GiftType getgType() {
        return gType;
    }

    public void setgType(GiftType gType) {
        this.gType = gType;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GiftStoreRequest)) return false;
        if (!super.equals(o)) return false;
        GiftStoreRequest that = (GiftStoreRequest) o;
        return getgType() == that.getgType() &&
                Objects.equals(getPatientName(), that.getPatientName());
    }



    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getgType(), getPatientName());
    }


    @Override
    public String toString() {
        return "GiftStoreRequest{" +
                "gType=" + gType +
                ", patientName='" + patientName + '\'' +
                '}';
    }

    @Override
    public void makeRequest() {
        DatabaseService.getDatabaseService().insertGiftStoreRequest(this);
    }

    @Override
    public void fillRequest() {
        this.setCompleted(true);
        DatabaseService.getDatabaseService().updateGiftStoreRequest(this);
    }

    static DatabaseService myDBS = DatabaseService.getDatabaseService();

    @Override
    public ObservableList<Employee> returnCorrectEmployee () {
        ObservableList<Employee> rightEmployee = FXCollections.observableArrayList();
        ObservableList<Employee> allEmployee = FXCollections.observableArrayList();
        allEmployee.addAll( myDBS.getAllEmployees()) ;

        for (int i = 0; i < allEmployee.size(); i++) {
            if (allEmployee.get(i).getJob() == GIFT_SERVICES || allEmployee.get(i).getJob() == ADMINISTRATOR) {
                rightEmployee.add(allEmployee.get(i)) ;
            }
        }
        return rightEmployee ;
    }

    @Override
    public ObservableList<Request> showProperRequest() {
        ObservableList<Request> x = FXCollections.observableArrayList();
        x.addAll(myDBS.getAllCompleteGiftStoreRequests()) ;
        x.addAll(myDBS.getAllIncompleteGiftStoreRequests()) ;
        return x ;
    }

    @Override
    public void updateEmployee (Request selectedTask, Employee selectedEmp) {
        myDBS.updateGiftStoreRequest((GiftStoreRequest)selectedTask) ;
    }

    @Override
    public boolean fulfillableByType(JobType jobType) {
        if (jobType == GIFT_SERVICES || jobType == ADMINISTRATOR) return true;
        return false;
    }


    @Override
    public String toDisplayString() {
        if (this.getAssignedTo() == 0) this.setAssignedTo(-1);
        return String.format("Gift Store Request %d, Description: %s, Type: %s, Assigned To: %s, Fulfilled: %s, Patient Name: %s",
                this.getId(), this.getNotes(), this.getgType().name(), this.getAssignedTo() == -1 ? "None" : "" + this.getAssignedTo(), this.isCompleted() ? "Yes" : "No", this.getPatientName());
    }

    @Override
    public boolean isOfType(String typeString) {
        return typeString.equals("Gift Store");
    }
}
