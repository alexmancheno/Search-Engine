package qc.cs355.application.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import qc.cs355.application.database.ScriptRunner;


import qc.cs355.application.webcrawler.URLAndKeywords;

public class Database {
    static String host      = "jdbc:mysql://localhost:3306/phatsearch";
    static String user      = "root";
    static String pass      = "SomeRootPass098";
    
    static{
        try{
            Connection conn  = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", user, pass);
            System.out.println("CONNECTED TO MYSQL");
            PreparedStatement checkIfSchemaExist = conn.prepareStatement("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'phatsearch'");
            ResultSet rs = checkIfSchemaExist.executeQuery();
            if(!rs.first()){   
                ScriptRunner runner = new ScriptRunner(conn, false, false);
                runner.runScript(new BufferedReader(new FileReader("migration_phatsearch.sql")));
            }
            checkIfSchemaExist.close();
            rs.close();
            conn.close();
        }catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: "     + ex.getSQLState());
            System.out.println("VendorError: "  + ex.getErrorCode());
        }catch(Exception ex){
            System.out.println("Error in migrating Database: " + ex.getMessage());
        }


    }


    public static void test() {
        Connection conn = null;
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations
            // Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(host, user, pass);
            System.out.println(conn.getMetaData());
            System.out.println("Created instance of connection!!!");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (Exception ex) {
            // handle any errors
            System.out.println("Exception: " + ex.getMessage());
        }
    }

    private static void openConnection() {

    }

    public static List<String> phatSearch(String query) {
        List<String> results = new ArrayList<String>();
        try {
            Connection conn = DriverManager.getConnection(host, user, pass);
            // String sqlQuery = String.format();
            // PreparedStatement statement = conn.prepareStatement();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (Exception ex) {
            System.out.println("Error in inserting scrape results: " + ex.getMessage());
        }
        return results;
    }

    public static void insertScrapeResults(URLAndKeywords page) {
        try {
            Connection conn = null;
            conn = DriverManager.getConnection(host, user, pass);
            CallableStatement insertingPage = conn.prepareCall("{CALL insertURLAndReturnID(? , ?)}");
            CallableStatement insertingWord = conn.prepareCall("{CALL insertWordAndReturnID(? , ?)}");
            CallableStatement insertingFrequency = conn.prepareCall("{CALL insertFrequencyAndReturnID(? , ?, ?)}");
            // Adding to WebPages table
            insertingPage.setString(1, page.url);
            insertingPage.registerOutParameter(2, Types.INTEGER);
            insertingPage.execute();
            int pageID = insertingPage.getInt(2);
            for (Map.Entry<String, Integer> word : page.keywords.entrySet()) {
                // Adding to the Words table
                insertingWord.setString(1, word.getKey());
                insertingWord.registerOutParameter(2, Types.INTEGER);
                insertingWord.execute();
                int wordId = insertingWord.getInt(2);

                // Add to Frequencies table
                insertingFrequency.setInt(1, pageID);
                insertingFrequency.setInt(2, wordId);
                insertingFrequency.setInt(3, word.getValue());
                insertingFrequency.execute();

                // clear parameters for next interation
                insertingWord.clearParameters();
                insertingFrequency.clearParameters();
            }
            insertingPage.clearParameters();
            insertingPage.close();
            insertingWord.close();
            insertingFrequency.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (Exception ex) {
            System.out.println("Error in inserting scrape results: " + ex.getMessage());
        }
    }

    private static void dropWordsOnPages(URLAndKeywords page) {
    }

    public static boolean isWebPageInDatabase(String url) {
        boolean isInDatabase = false;
        try {
            Connection conn = null;
            conn = DriverManager.getConnection(host, user, pass);
            PreparedStatement stmnt = conn.prepareStatement(
                    "SELECT WebSELECT WebPages.idWebPage INTO id FROM WebPages WHERE WebPages.webPageLink = ?");
            stmnt.setString(1, url);
            ResultSet result = stmnt.executeQuery();
            if (result.next()) {
                isInDatabase = true;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (Exception ex) {
            System.out.println("Error in inserting scrape results: " + ex.getMessage());
        }
        return isInDatabase;
    }

    // public static List<String> search(String search){
    //     String[] splitQuery = search.split("\\s+");
    //     //int size = splitQuery.length;
    //     StringBuilder query = new StringBuilder("SELECT WebPages.webPageLink FROM WebPages INNER JOIN");

    //     for(String s : splitQuery ){

    //     }

    // }

}