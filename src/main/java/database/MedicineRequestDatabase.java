package database;

import service_request.model.sub_model.MedicineRequest;

import java.util.List;

public class MedicineRequestDatabase {
    private final DatabaseService databaseService;

    public MedicineRequestDatabase(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    /**
     * @param req the service_request to insert into the database
     * @return true if the insert succeeds and false if otherwise
     */
    public boolean insertMedicineRequest(MedicineRequest req) {
        String insertQuery = ("INSERT INTO MEDICINEREQUEST(notes, locationNodeID, completed, medicineType, quantity, assignedEmployee) VALUES(?, ?, ?, ?, ?, ?)");
        return databaseService.executeInsert(insertQuery, req.getNotes(), req.getLocation().getNodeID(), req.isCompleted(), req.getMedicineType(), req.getQuantity(), ((req.getAssignedTo() != -1 && req.getAssignedTo() != 0) ? req.getAssignedTo() : null));
    }

    /**
     * @param id the id of the medicine service_request to get
     * @return the medicine service_request with the given ID
     */
    public MedicineRequest getMedicineRequest(int id) {
        String query = "SELECT * FROM MEDICINEREQUEST WHERE (serviceID = ?)";
        return (MedicineRequest) databaseService.executeGetById(query, MedicineRequest.class, id);
    }

    /**
     * @return all medicine service_request in the database
     */
    public List<MedicineRequest> getAllMedicineRequests() {
        String query = "Select * FROM MEDICINEREQUEST";
        return (List<MedicineRequest>) (List<?>) databaseService.executeGetMultiple(query, MedicineRequest.class, new Object[]{});
    }

    /**
     * @param req the given service_request to update
     * @return true if the update succeeded and false if otherwise.
     */
    public boolean updateMedicineRequest(MedicineRequest req) {
        String query = "UPDATE MEDICINEREQUEST SET notes=?, locationNodeID=?, completed=?, medicineType=?, quantity=?, assignedEmployee=? WHERE (serviceID = ?)";
        return databaseService.executeUpdate(query, req.getNotes(), req.getLocation().getNodeID(), req.isCompleted(), req.getMedicineType(), req.getQuantity(), ((req.getAssignedTo() != -1 && req.getAssignedTo() != 0) ? req.getAssignedTo() : null), req.getId());
    }

    /**
     * @param req the given service_request to delete
     * @return true if the delete succeeded and false if otherwise.
     */
    public boolean deleteMedicineRequest(MedicineRequest req) {
        String query = "DELETE FROM MEDICINEREQUEST WHERE (serviceID = ?)";
        return databaseService.executeUpdate(query, req.getId());
    }

    /**
     * @return a list of all medicine service_request that haven't been completed yet
     */
    public List<MedicineRequest> getAllIncompleteMedicineRequests() {
        String query = "Select * FROM MEDICINEREQUEST where (completed = ?)";
        return (List<MedicineRequest>) (List<?>) databaseService.executeGetMultiple(query, MedicineRequest.class, false);
    }

    /**
     * @return a list of all medicine service_request that haven't been completed yet
     */
    public List<MedicineRequest> getAllCompleteMedicineRequests() {
        String query = "Select * FROM MEDICINEREQUEST where (completed = ?)";
        return (List<MedicineRequest>) (List<?>) databaseService.executeGetMultiple(query, MedicineRequest.class, true);
    }
}