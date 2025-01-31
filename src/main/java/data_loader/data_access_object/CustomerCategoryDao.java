package data_loader.data_access_object;

import data_loader.SqlConnection;
import data_models.CustomerCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerCategoryDao {

    private static final Connection con = SqlConnection.getConnection();
    private static PreparedStatement preparedStmt;

    private CustomerCategoryDao() {
    }

    public static List<CustomerCategory> getAllCustomerCategoriesFromDb() {
        List<CustomerCategory> customerCategoryList = new ArrayList<>();
        try {
            preparedStmt= con.prepareStatement("SELECT * FROM OPTKOS.CUSTOMERCATEGORY");
            try (ResultSet rs = preparedStmt.executeQuery()) {

                while (rs.next()) {
                    CustomerCategory customerCategory = buildCustomercategory(rs);
                    customerCategoryList.add(customerCategory);
                }
            }
            preparedStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerCategoryList;
    }

    public static CustomerCategory getCustomerCategoryByIdFromDb(String uuid){
        CustomerCategory customerCategory = null;
        try {
            preparedStmt = con.prepareStatement("SELECT * FROM OPTKOS.CUSTOMERCATEGORY " +
                    "WHERE CUSTOMERCATEGORYID=?");
            preparedStmt.setString(1, uuid);
            try (ResultSet rs = preparedStmt.executeQuery()) {
                if (rs.next()) {
                    customerCategory = buildCustomercategory(rs);
                }
            }
            preparedStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerCategory;
    }

    public static boolean createCustomerCategory(CustomerCategory customerCategory){
        try {
            preparedStmt = con.prepareStatement("INSERT INTO OPTKOS.CUSTOMERCATEGORY(CUSTOMERCATEGORYID, NAME," +
                    "DESCRIPTION, DURATIONPERCENT,DURATIONFLAT) VALUES (?,?,?,?,?)");

            preparedStmt.setString(1,customerCategory.getCustomerCategoryId());
            preparedStmt.setString(2,customerCategory.getName());
            preparedStmt.setString(3,customerCategory.getDescription());
            preparedStmt.setDouble(4,customerCategory.getTimeFactor());
            preparedStmt.setInt(5, customerCategory.getTimeBonus());

            preparedStmt.execute();
            preparedStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public static boolean updateCustomerCategory(CustomerCategory customerCategory){
        try{
            preparedStmt = con.prepareStatement("UPDATE OPTKOS.CUSTOMERCATEGORY SET NAME=?," +
                    "DURATIONFLAT=?, DURATIONPERCENT=?, DESCRIPTION=? WHERE CUSTOMERCATEGORYID=?");
            preparedStmt.setString(1, customerCategory.getName());
            preparedStmt.setInt(2, customerCategory.getTimeBonus());
            preparedStmt.setDouble(3, customerCategory.getTimeFactor());
            preparedStmt.setString(4, customerCategory.getDescription());
            preparedStmt.setString(5, customerCategory.getCustomerCategoryId());

            preparedStmt.executeUpdate();
            preparedStmt.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    public static boolean deleteCustomerCategory(CustomerCategory customerCategory){
        try {
            preparedStmt = con.prepareStatement("DELETE FROM OPTKOS.CUSTOMERCATEGORY WHERE CUSTOMERCATEGORYID=?");
            preparedStmt.setString(1, customerCategory.getCustomerCategoryId());

            preparedStmt.executeUpdate();
            preparedStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static CustomerCategory buildCustomercategory(ResultSet rs){
        CustomerCategory cc = null;
        try {
            cc = new CustomerCategory(
                    rs.getString("CUSTOMERCATEGORYID"),
                    rs.getString("NAME"),
                    rs.getString("DESCRIPTION"),
                    rs.getInt("DURATIONFLAT"),
                    rs.getDouble("DURATIONPERCENT"));
        } catch (SQLException e) {
            System.err.println("Error while building CustomerCategory");
            e.printStackTrace();
        }
        return cc;
    }
}
