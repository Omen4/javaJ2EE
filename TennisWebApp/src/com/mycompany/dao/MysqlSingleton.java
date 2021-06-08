package com.mycompany.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlSingleton {
	
 	private static String url = "jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";
    private static String username = "root";
    private static String password = "NJ.#F8ixKbHz";
    private static Connection connexion = null;
    
    public static Connection getInstance() throws SQLException {
    	
    	if(connexion==null) {
    		try {
    			Class.forName("com.mysql.cj.jdbc.Driver");
    	    } 
    		catch (ClassNotFoundException e) {
    	    }
    		connexion = DriverManager.getConnection(url, username, password);
        	
        }
        return connexion; 
    }
    
    public static void deconnection() throws SQLException {
    	if(connexion != null){
    		connexion.close();
	    	connexion = null;	
    	}
    	
    }
}
