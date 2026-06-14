package db;

import java.sql.Connection;

public class TestConncetion {

    public static void main(String[] args) {
        try {
            Connection con = DBConnection.getConnection();
            if (con != null) {
                System.out.println("✅ Connection Successful!");
                con.close();
            }
        } catch (Exception e) {
            System.out.println("❌ Connection Failed!");
            System.out.println("Reason: " + e.getMessage());
        }
    }
}
