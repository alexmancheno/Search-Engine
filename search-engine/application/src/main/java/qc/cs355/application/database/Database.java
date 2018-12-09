package qc.cs355.application.database;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import qc.cs355.application.database.ScriptRunner;
import qc.cs355.application.webcrawler.URLAndKeywords;

public class Database {
    static String host      = "jdbc:mysql://localhost:3306/phatsearch";
    static String user      = "root";
    static String pass      = "SomeRootPass098";
    
    static{
        try{
            //Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn  = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", user, pass);
            System.out.println("CONNECTED TO MYSQL");
            PreparedStatement checkIfSchemaExist = conn.prepareStatement("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'phatsearch'");
            ResultSet rs = checkIfSchemaExist.executeQuery();
            if(!rs.first()){   
                System.out.println("Migrating to local database");
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
        try {
            Connection conn = null;
            // The newInstance() call is a work around for some
            // broken Java implementations
            
            conn = DriverManager.getConnection(host, user, pass);
            System.out.println(conn.getMetaData());
            System.out.println("Created instance of connection!!!");
            conn.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (Exception ex) {
            // handle any errors
            System.out.println("Exception: " + ex.getMessage());
        }
    }

    public static void insertScrapeResults(URLAndKeywords page) {
        //TODO
        //out parameters returned by procedure.
        //SQLException: Column 'idWebPage' cannot be null
        Connection conn = null;
        CallableStatement insertingPage = null;
        CallableStatement insertingWord = null;
        CallableStatement insertingFrequency = null;
        try{
            try {
                conn = DriverManager.getConnection(host, user, pass);
                insertingPage      = conn.prepareCall("{CALL insertURLAndReturnID(? , ?)}");
                insertingWord      = conn.prepareCall("{CALL insertWordAndReturnID(? , ?)}");
                insertingFrequency = conn.prepareCall("{CALL insertFrequency(? , ?, ?)}");

                // Adding to WebPages table
                insertingPage.setString(1, page.url);
                insertingPage.registerOutParameter(2, Types.INTEGER);
                try{
                    insertingPage.execute();
                }catch(SQLException ex){
                    System.out.println("VendorError: "  + ex.getErrorCode());
                }
                //System.out.println("Inserted Page");
                int pageID = insertingPage.getInt(2);
                //System.out.println(pageID);
                for (Map.Entry<String, Integer> word : page.keywords.entrySet()) {
                    // Adding to the Words table
                    if( word.getKey().length() == 0){continue;}
                    int wordId = isWordInDatabase(word.getKey());
                    if(wordId != -1 ){continue;}
                    insertingWord.setString(1, word.getKey());
                    //System.out.println("THIS IS THE WORD: "+ word.getKey().length());
                    insertingWord.registerOutParameter(2, Types.INTEGER);
                    //System.out.println("INSERTING WORD----:"+ word.getKey());
                    try{
                        insertingWord.execute();
                     }catch(SQLException ex){
                        System.out.println("SQLException: " + ex.getMessage());
                        System.out.println("SQLState: "     + ex.getSQLState());
                        System.out.println("VendorError: "  + ex.getErrorCode());
                        //  if(ex.getErrorCode() == 1048){
                        //      insertingWord.clearParameters();
                        //      continue;
                        //  }
                     }        
                    wordId = insertingWord.getInt(2);
                    //System.out.println("INSERTING WORD FREQ---pageID:"+pageID+", wordId:"+wordId+", freq:"+ word.getValue());

                    // Add to Frequencies table
                    insertingFrequency.setInt(1, pageID);
                    insertingFrequency.setInt(2, wordId);
                    insertingFrequency.setInt(3, word.getValue());
                    try{
                        insertingFrequency.execute();
                    } catch (SQLException ex) {
                        System.out.println("SQLException: " + ex.getMessage());
                        System.out.println("SQLState: " + ex.getSQLState());
                        System.out.println("VendorError: " + ex.getErrorCode());
                    }

                    // clear parameters for next iteration
                    insertingWord.clearParameters();
                    insertingFrequency.clearParameters();
                } 
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            } catch (Exception ex) {
                System.out.println("Error in inserting scrape results: " + ex.getMessage());
            }finally{
                    insertingPage.close();
                    insertingWord.close();
                    insertingFrequency.close();
                    conn.close();      
            }
        }catch(Exception ex){
            System.out.println("Error closed" + ex.getMessage());
        }
    }


    public static boolean isWebPageInDatabase(String url) {
        boolean isInDatabase = false;
        try {
            Connection conn = null;
            conn = DriverManager.getConnection(host, user, pass);
            PreparedStatement stmnt = conn.prepareStatement("SELECT * FROM WebPages WHERE webPageLink = ?");
            stmnt.setString(1, url);
            ResultSet result = stmnt.executeQuery();
            if (result.next()) {
                isInDatabase = true;
            }
            result.close();
            stmnt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (Exception ex) {
            System.out.println("Error in is webpage in database " + ex.getMessage());
        }
        return isInDatabase;
    }

    private static int isWordInDatabase(String word) {
        int result = -1;
        try{
            Connection conn = null;
            PreparedStatement stmnt = null;
            ResultSet res = null;
            try { 
                conn = DriverManager.getConnection(host, user, pass);
                stmnt = conn.prepareStatement("SELECT idWord FROM Words WHERE word = ?");
                stmnt.setString(1, word);
                res = stmnt.executeQuery();
                if (res.next()) {
                    result = res.getInt(1);
                }
                res.close();
                stmnt.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            } catch (Exception ex) {
                System.out.println("Error in is webpage in database " + ex.getMessage());
            }finally{
                res.close();
                stmnt.close();
                conn.close();
            }
        }catch(Exception ex){
            System.out.println("Error closed" + ex.getMessage());
        }
        return result;
    }


    public static List<String> phatSearch(String search){
        List<String> result = new ArrayList<String>();
        try{
            Connection conn = null;
            conn = DriverManager.getConnection(host, user, pass);
            //Split by spaces
            String[] splitQuery = search.split("\\s+");
            //Build base query
            StringBuilder query = new StringBuilder("SELECT webPageLink from WebPages AS WP INNER JOIN Frequencies AS F"
                                                    + "ON  WP.idWebPage = F.idWebPage INNER JOIN (SELECT * FROM Words WHERE");

            int sizeOfSearch = splitQuery.length;
            
            //For every search, add a questionmark, and later replace it with the search
            for(int i = 0; i < sizeOfSearch; ++i ){
                query.append("word = ?");
            }       
            query.append(") AS W ON W.idWord = F.idWord ORDER BY F.frequency DESC;");
            PreparedStatement stmnt = conn.prepareStatement(query.toString()); 
            for(int i = 0; i < sizeOfSearch; ++i ){
                stmnt.setString(i, splitQuery[i]);
            }   
            ResultSet rs = stmnt.executeQuery();
            while(rs.next()){
                result.add(rs.getString(1));
            }
            rs.close();
            stmnt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (Exception ex) {
            System.out.println("Error in search: " + ex.getMessage());
        }
        return result;
    }
}