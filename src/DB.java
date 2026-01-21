import java.sql.*;


public class DB {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432?JAVA";
    private static final String USER = "postgres";
    private static final String PASS = "010203";

    public static Connection getConnection() throws SQLException{
    return DriverManager.getConnection(DB_URL, USER, PASS);}
}