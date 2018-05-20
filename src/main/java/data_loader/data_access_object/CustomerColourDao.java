package data_loader.data_access_object;

import data_loader.SqlConnection;
import data_models.CustomerColour;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class CustomerColourDao {
    private static Connection con = SqlConnection.getConnection();
    private static PreparedStatement preparedStmt;

    public static List<CustomerColour> getAllCustomerColoursFromDb(){
        List<CustomerColour> customerColourList = new ArrayList<>();
        try {
            preparedStmt = con.prepareStatement("SELECT * FROM OPTKOS.CUSTOMERCOLOUR");
            ResultSet rs = preparedStmt.executeQuery();

            while(rs.next()){
                CustomerColour customerColour = new CustomerColour(
                        rs.getString("CUSTOMERCOLURID"),
                        rs.getInt("CONTENTWHITE"),
                        rs.getString("CUSTOMERID"),
                        rs.getInt("EXPOSURETIME"),
                        rs.getString("NATURAL"),
                        rs.getString("OXIDATION"),
                        rs.getString("RESULT")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerColourList;
    }

    public static List<CustomerColour> getCustomerColourByCustomerId(String customerId){
        List<CustomerColour> customerColourList = new ArrayList<>();
        if(customerColourList == null ){
            customerColourList = getAllCustomerColoursFromDb();
        }
        List<CustomerColour> tmpList = null;
        for (CustomerColour cuco : customerColourList)
        {
            if(cuco.getCustomerId() == customerId){
                tmpList.add(cuco);
            }
        }
        return tmpList;
    }

    public static void createCustomerColour(CustomerColour customerColour){
        try {
            preparedStmt= con.prepareStatement("INSERT INTO OPTKOS.CUSTOMERCOLOUR (NATURALCOLOUR, OXIDATION," +
                    " CONTENTWHITE, EXPOSURETIME, RESULT, CUSTOMERCOLOURID, CUSTOMERID) VALUES(?,?,?,?,?,?,?)");
            preparedStmt.setString(1, customerColour.getNatural());
            preparedStmt.setString(2,customerColour.getOxidation());
            preparedStmt.setInt(3, customerColour.getContentWhite());
            preparedStmt.setInt(4, customerColour.getExposureTime());
            preparedStmt.setString(5, customerColour.getResult());
            preparedStmt.setString(6, customerColour.getCustomerColourId().toString());
            preparedStmt.setString(7, customerColour.getCustomerId().toString());
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCustomerColourByCustomerId(String customerId){
        List<CustomerColour> customerColourList = new ArrayList<>();
        try {
            preparedStmt = con.prepareStatement("DELETE FROM OPTKOS.CUSTOMERCOLOUR WHERE CUSTOMERID=?");
            preparedStmt.setString(1, customerId.toString());
            preparedStmt.executeUpdate();

            for (int i = 0; i< customerColourList.size(); i++){
                if(customerColourList.get(i).getCustomerId() == customerId) {
                    customerColourList.remove(i);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeCustomerColourByCustomerId(String customerColour, String naturalColour,
                                                        String oxidation, int contenWhite, int exposureTime,
                                                        String result, String customerId){
        try {
            preparedStmt = con.prepareStatement("UPDATE OPTKOS.CUSTOMERCOLOUR SET CUSTOMERCOLOUR = ?," +
                    " NATURALCOLOUR = ?, OXIDATION = ?, CONTENTWHITE = ?, EXPOSURETIME = ?," +
                    " Result = ? WHERE CUSTOMERID=?");
            preparedStmt.setString(1, customerColour);
            preparedStmt.setString(2, naturalColour);
            preparedStmt.setString(3, oxidation);
            preparedStmt.setInt(4, contenWhite);
            preparedStmt.setInt(5, exposureTime);
            preparedStmt.setString(6, result);
            preparedStmt.setString(7, customerId.toString());
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
