package model.request;

import model.Node;

import java.util.Objects;

public class SanitationRequest extends Request {

    private String urgency;
    private String materialState;

    public SanitationRequest(int id, String notes, Node location, boolean completed, String urg, String ms) {
        super(id, notes, location, completed);
        this.urgency = urg;
        this.materialState = ms;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public String getMaterialState() {
        return materialState;
    }

    public void setMaterialState(String materialState) {
        this.materialState = materialState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SanitationRequest that = (SanitationRequest) o;
        return Objects.equals(urgency, that.urgency) &&
                Objects.equals(materialState, that.materialState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), urgency, materialState);
    }

    @Override
    public String toString() {
        return "SanitationRequest{" +
                "urgency='" + urgency + '\'' +
                ", materialState='" + materialState + '\'' +
                '}';
    }

    @Override
    public void makeRequest() {
        // Todo
    }

    @Override
    public void fillRequest() {
        // todo
    }
}
