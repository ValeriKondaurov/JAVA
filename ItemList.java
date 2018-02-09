/**
 * 1. Work with DateBase (SQLite)
 * 2. CRUD app
 *
 * @author Valeriy Kondaurov
 * @version dated Feb 09, 2018
 * @link https://github.com/ValeriKondaurov/Java3
 */
import java.util.*;
import java.sql.*;

class ItemList  {

    final String DRIVER_NAME = "org.sqlite.JDBC";
    final String SQLITE_DB = "jdbc:sqlite:table.db";
    final String NAME_TABLE = "itemlist";
    final String SQL_CREATE_TABLE =
        "DROP TABLE IF EXISTS " + NAME_TABLE + ";" +
        "CREATE TABLE " + NAME_TABLE +
        "(id  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
        " title TEXT NOT NULL," +
        " cost  INTEGER NOT NULL);" ;
    final String SQL_SELECT = "SELECT * FROM " + NAME_TABLE + ";";
    final String DB_CREATED = "Darabase created.";
    final String RECORD_ADDED = "Record added.";
    final String RECORD_DELETED = "Record deleted.";
    final String RECORD_UPDATED = "Record updated.";
    final String UNKNOWN_COMMAND = "Unknown command, use [-create,-init,-print,-getprice, -setprice, -delete] only.";
    final String ID_COL = "id";
    final String TITLE_COL = "title";
    final String COST_COL = "cost";

    Connection connect;
    Statement stmt;
    PreparedStatement ps;
    ResultSet rs;
    String sql;

    public static void main(String[] args) {
        new ItemList(args);
    }

    ItemList(String[] args) {
        if (args.length == 0) {
            Scanner sc = new Scanner(System.in);
            System.out.print("> ");
            String line = sc.nextLine();
            args = line.split(" ");
        }

        switch (args[0]) {
            case "-create":
                openDBFile(SQLITE_DB).
                    createTable(SQL_CREATE_TABLE);
                System.out.println(DB_CREATED);
                break;
            case "-init":
                openDBFile(SQLITE_DB)
                    .init();
                System.out.println(RECORD_ADDED);
                break;
            case "-print":
                openDBFile(SQLITE_DB).
                print();
                break;
            case "-getprice":
                openDBFile(SQLITE_DB)
                        .getprice(args[1]);
                break;
            case "-setprice":
                openDBFile(SQLITE_DB)
                    .update(args[1], args[2]);
                System.out.println(RECORD_UPDATED);
                break;
            case "-list":
                openDBFile(SQLITE_DB)
                        .list(args[1], args[2]);
                break;
            case "-delete":
                openDBFile(SQLITE_DB)
                    .delete(args[1]);
                System.out.println(RECORD_DELETED);
                break;
            default:
                System.out.println(UNKNOWN_COMMAND);
        }
    }

    private ItemList openDBFile(String dbName) { // open/create database
        try {
            Class.forName(DRIVER_NAME);
            connect = DriverManager.getConnection(dbName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    private void createTable(String sqlCreateTable) { // create table
        try {
            stmt = connect.createStatement();
            stmt.executeUpdate(sqlCreateTable);
        } catch (Exception e) { 
            e.printStackTrace();
        }
    }

    private void init() { // init record
        try {
            stmt = connect.createStatement();
            stmt.executeUpdate("DELETE FROM " + NAME_TABLE);
            ps = connect.prepareStatement("INSERT INTO " + NAME_TABLE +
                    "(" + ID_COL +","+ TITLE_COL + "," + COST_COL + ")" +
                    "VALUES (?, ?, ?);");
            for (int i=1; i<=10; i++) {
                ps.setInt(1,i);
                ps.setInt(3,i*10);
                ps.setString(2,"item"+i);
                ps.addBatch();
            }
            ps.executeBatch();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getprice(String item) { // getprice record
        try {
            stmt = connect.createStatement();
            rs = stmt.executeQuery("SELECT * FROM " + NAME_TABLE +
                                        " WHERE " + TITLE_COL + " = '" + item  + "';");
            if  (rs.next()) {
                System.out.println("TITLE\tCOST");
                do {
                    System.out.println(
                            rs.getString(ID_COL) + ".\t" +
                                    rs.getString(TITLE_COL) + "  -\t" +
                                    rs.getString(COST_COL));
                } while (rs.next());
            } else System.out.println("Not found");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void update(String item, String cost) { // update cost by title
        try {
            stmt = connect.createStatement();
            stmt.executeUpdate("UPDATE " + NAME_TABLE +
                " set cost='" + cost +
                "' where title='" + item+ "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void delete(String item) { // delete record by title
        try {
            stmt = connect.createStatement();
            stmt.executeUpdate("DELETE from " + NAME_TABLE +
                " where title='" + item + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void list (String coststart, String costend) { // print table
        try {
            System.out.println("ID\tTITLE\tCOST");
            stmt = connect.createStatement();
            rs = stmt.executeQuery("SELECT * FROM " + NAME_TABLE +
                    " WHERE cost >= " + coststart +" AND cost <= " + costend +" ;");
            while (rs.next())
                System.out.println(
                        rs.getString(ID_COL) + ".\t" +
                                rs.getString(TITLE_COL) + "  -\t" +
                                rs.getString(COST_COL));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void print() { // print table
        try {
            System.out.println("ID\tTITLE\tCOST");
            stmt = connect.createStatement();
            rs = stmt.executeQuery(SQL_SELECT);
            while (rs.next())
                System.out.println(
                    rs.getString(ID_COL) + ".\t" +
                    rs.getString(TITLE_COL) + "  -\t" +
                    rs.getString(COST_COL));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
