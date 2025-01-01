/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expense_incone_tracker_with_database;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author ADITYA
 */
public class TransactionDAO {
    // Method to retrieve all transactions from the database
    public static List<Transaction> getAllTransaction(){
        
        // Create a list to store Transaction objects
        List<Transaction> transactions = new ArrayList<>();
        
        Connection connection = DatabaseConnection.getConnection();
        
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = connection.prepareStatement("SELECT * FROM `transaction_table`");
            rs = ps.executeQuery();
            
            // Iterate through the result set obtained from the SQL query
            while (rs.next()) {  
                // Extract transaction details from the result set
                int id = rs.getInt("id");
                String type = rs.getString("transaction_type");
                String description = rs.getString("description");
                double amount = rs.getDouble("amount");
                
                // Create a Transaction object with the retrieved details
                Transaction transaction = new Transaction(id, type, description, amount);
                // Add the Transaction object to the list
                transactions.add(transaction);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TransactionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Return the list of transactions
        return transactions;
    }

}
