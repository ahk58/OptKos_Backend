package data_loader.data_access_object;

import data_loader.SqlConnection;
import data_models.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServiceDao {
    private static final Connection con = SqlConnection.getConnection();
    private static Statement stmt;
    private static PreparedStatement preparedStmt;
    private static List<Service> serviceList = new ArrayList<>();

    private ServiceDao() {
    }

    public static List<Service> getAllServicesFromDb() {
        try {
            stmt = con.createStatement();
            String query = "SELECT * FROM OPTKOS.SERVICE";
            try (ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    serviceList.add(new Service(rs.getString("SERVICEID"),
                            rs.getString("NAME"), rs.getString("DESCRIPTION"),
                            rs.getBigDecimal("PRICE"), rs.getTime("DURATIONPLANNED"),
                            rs.getTime("DURATIONAVERAGE"), rs.getString("ISDELETED")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serviceList;
    }

    // TODO: change db type of the durations, or change type in service class
    public static void createService(Service service) {
        try {
            preparedStmt = con.prepareStatement(
                    "INSERT INTO OPTKOS.SERVICE(SERVICEID, NAME, DESCRIPTION, PRICE," +
                            " DURTATIONPLANNED, DURATIONAVERAGE) VALUES(?,?,?,?,?,?)");
            preparedStmt.setString(1, service.getServiceId());
            preparedStmt.setString(2, service.getName());
            preparedStmt.setString(3, service.getDescription());
            preparedStmt.setBigDecimal(4, service.getPrice());
            preparedStmt.setTime(5, service.getDurationPlanned());
            preparedStmt.setTime(6, service.getDurationAverage());

            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Service getServiceById(String serviceId){
        if(serviceList == null){
            serviceList = getAllServicesFromDb();
        }

        Service tmp = null;
        for (Service p : serviceList) {
            if (Objects.equals(p.getServiceId(), serviceId)) {
                tmp = p;
            }
        }
        return tmp;
    }

    public static void deleteServiceByServiceId(String serviceId){

        try {
            preparedStmt = con.prepareStatement("DELETE FROM OPTKOS.SERVICE WHERE SERVICEID =?;");
            preparedStmt.setString(1, serviceId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
