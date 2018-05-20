package data_loader.data_access_object;

import data_loader.SqlConnection;
import data_models.AppointmentType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AppointmentTypeDao {

    private static final Connection con = SqlConnection.getConnection();
    private static PreparedStatement preparedStmt;

    private AppointmentTypeDao() {}

    public static List<AppointmentType> getAllAppointmentTypesFromDb(){
        List<AppointmentType> appointmentTypeList = new ArrayList<>();
        try {
            preparedStmt = con.prepareStatement("SELECT * FROM OPTKOS.APOINTMENTTYPE");
            try (ResultSet rs = preparedStmt.executeQuery()) {

                while (rs.next()) {
                    AppointmentType appointmentType = new AppointmentType(
                            rs.getString("APPOINTMENTTYPEID"),
                            rs.getString("NAME"),
                            rs.getString("DESCRIPTION"));
                    appointmentTypeList.add(appointmentType);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentTypeList;
    }

    public static AppointmentType getAppointmentTypeById(String appTId){
        List<AppointmentType> appointmentTypeList = new ArrayList<>();
        AppointmentType appT = null;
        for (AppointmentType appointmentTypes : appointmentTypeList) {
            if (Objects.equals(appointmentTypes.getAppointmentTypeId(), appTId)) {
                appT = appointmentTypes;
                break;
            }
        }

        if( appT == null){
            appT = getAppointmentTypeByIdFromDb(appTId);
            if(appT !=null)
                appointmentTypeList.add(appT);
        }
        return appT;
    }


    public static AppointmentType getAppointmentTypeByIdFromDb(String appTId){
        AppointmentType appointmentType = null;
        try {
            preparedStmt = con.prepareStatement("SELECT * FROM OPTKOS.APOINTMENTTYPE at WHERE at.APPOINTMENTTYPEID=\" + appTId + \";");
            try (ResultSet rs = preparedStmt.executeQuery()) {

                appointmentType = new AppointmentType(
                        rs.getString("APPOINTMENTTYPEID"),
                        rs.getString("NAME"),
                        rs.getString("DESCRIPTION"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointmentType;
    }

}
