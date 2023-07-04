package com.conexionbd;
public class App 
{
    public static void main( String[] args )
    {
        DbConnection connection = new DbConnection();

        connection.showData();
        System.out.println("\n");
        // connection.insertData(3, "Juan", "Perez");
        // connection.insertData(4, "Maria", "Lopez");
        // connection.insertData(5, "Pedro", "Garcia");
        // System.out.println("\n");
        // connection.showData();
    }
}
