/**
 * Java. Level 2. Lesson 7
 * Making SQLite db file with users
 * Note:
 * a) Download latest ver of sqlite-jdbc-(VER).jar from
 *    https://bitbucket.org/xerial/sqlite-jdbc/downloads
 * b) Put this jar into [JDK]\jre\lib\ext
 * c) See also http://www.tutorialspoint.com/sqlite/sqlite_java.htm
 *
 * @author Valeriy Kondaurov
 * @version dated Jan 19, 2017
 * @link https://github.com/ValeriKondaurov/Java2HW7
 */
import java.sql.*;
import java.util.Set;

class MakeDBFile implements IConstants {

    final String NAME_TABLE = "users";
    final String SQL_CREATE_TABLE =
        "DROP TABLE IF EXISTS " + NAME_TABLE + ";" +
        "CREATE TABLE " + NAME_TABLE +
        "(login  CHAR(6) PRIMARY KEY NOT NULL," +
        " passwd CHAR(6) NOT NULL);";
    final String SQL_INSERT_MIKE =
        "INSERT INTO " + NAME_TABLE +
        " (login, passwd) " +
        "VALUES ('mike', 'qwe');";
    final String SQL_INSERT_JONH =
        "INSERT INTO " + NAME_TABLE +
        " (login, passwd) " +
        "VALUES ('john', 'rty');";
    final String SQL_SELECT = "SELECT * FROM " + NAME_TABLE + ";";
    Connection connect;
    Statement stmt;
    String nametable;

    public static void main(String[] args) {
        new MakeDBFile();
    }

    MakeDBFile() {
        try {
            // loads a class, including running its static initializers
            Class.forName(DRIVER_NAME);
            // attempts to establish a connection to the given database URL
            connect = DriverManager.getConnection(SQLITE_DB);
            // сreates an object for sending SQL statements to the database
            stmt = connect.createStatement();

            // create table
            stmt.executeUpdate(SQL_CREATE_TABLE);

            // insert record(s)
            stmt.executeUpdate(SQL_INSERT_MIKE);
            stmt.executeUpdate(SQL_INSERT_JONH);

            // print records
            ResultSet rs = stmt.executeQuery(SQL_SELECT);
            System.out.println("LOGIN\tPASSWD");
            while (rs.next())
                System.out.println(
                    rs.getString("login") + "\t" +
                    rs.getString(PASSWD_COL));
            rs.close();
            stmt.close();
            connect.close();
        } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
        }
    }

    public void DBFileOpen(String dbname) {
        try {
            // loads a class, including running its static initializers
            Class.forName(DRIVER_NAME);
            connect = DriverManager.getConnection("jdbc:sqlite:"+dbname);
            stmt = connect.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void DBFileClose() {
        try {
            if (!stmt.isClosed()) stmt.close();
            if (!connect.isClosed()) connect.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void DBFileCreate(String name) {
        this.nametable = name;
        try {
            stmt.executeUpdate(
                    "DROP TABLE IF EXISTS " + nametable + ";" +
                            "CREATE TABLE " + nametable +
                            "(login  CHAR(6) PRIMARY KEY NOT NULL," +
                            " passwd CHAR(6) NOT NULL);");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void DBFileInsert(String login, String passwd) {
        try {
            stmt.executeUpdate(
                    "INSERT INTO " + nametable +
                            " (login, passwd) " +
                            "VALUES ('" + login + "', '" + passwd + "'");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void DBFileRead() throws SQLException {
        try {
            ResultSet rs  = stmt.executeQuery("SELECT * FROM users ");
            System.out.println("LOGIN\tPASSWD");
            while (rs.next())
                System.out.println(
                        rs.getString("login") + "\t" +
                                rs.getString(PASSWD_COL));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}