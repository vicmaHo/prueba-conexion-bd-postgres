package com.conexionbd;

import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnection {
    // connection  data
    private final String url = "jdbc:postgresql://localhost/demodb";
    private final String user = "postgres";
    private final String password = "admin";

    public void showData(){
            try {
                // Connection to the database
                Connection conn = DriverManager.getConnection(url, user, password);
                System.out.println("Connected to the PostgreSQL server successfully.");

                String script = "SELECT id, nombre, apellido FROM public.usuarios;";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(script);

                // to display the data in a table
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Id");
                model.addColumn("Nombre");
                model.addColumn("Apellido");
                
                // display the information of the result set
                while (rs.next()) {
                     System.out.println(rs.getInt("id") + "\t"
                    + rs.getString("nombre") + "\t"
                    + rs.getString("apellido"));
                    
                    // to add the data to the table
                    Object[] row = new Object[3];
                    row[0] = rs.getInt("id");
                    row[1] = rs.getString("nombre");
                    row[2] = rs.getString("apellido");
                    model.addRow(row); 
                }

                JTable table = new JTable(model);
                JScrollPane scrollPane = new JScrollPane(table);

                // display the table
                JOptionPane.showMessageDialog(null, scrollPane, "Table", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }

    public void insertData(int id, String nombre, String apellido){
        try {
            // Connection to the database
            Connection conn = DriverManager.getConnection(url, user, password);


            String script = "INSERT INTO public.usuarios(" + //
                    "id, nombre, apellido)" + //
                    "VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = conn.prepareStatement(script);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, apellido);

            preparedStatement.executeUpdate();
            System.out.println("Data inserted successfully!");

        } catch (SQLException e) {
           System.out.println(e.getMessage());
        }
    }

}
