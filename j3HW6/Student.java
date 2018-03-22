/**
 * 1. Work with DateBase (SQLite)
 * 2. CRUD app
 *
 * @author Valeriy Kondaurov
 * @version dated Feb 09, 2018
 * @link https://github.com/ValeriKondaurov/Java3
 */
import com.sun.deploy.util.ArrayUtil;

import java.util.*;
import java.sql.*;

class Student  {

    final String DRIVER_NAME = "org.sqlite.JDBC";
    final String SQLITE_DB = "jdbc:sqlite:table.db";
    final String NAME_TABLE = "itemlist";
    final String SQL_CREATE_TABLE =
        "DROP TABLE IF EXISTS " + NAME_TABLE + ";" +
        "CREATE TABLE " + NAME_TABLE +
        "(id  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
        " student TEXT NOT NULL," +
        " score  INTEGER NOT NULL);" ;
    final String SQL_SELECT = "SELECT * FROM " + NAME_TABLE + ";";
    final String DB_CREATED = "Darabase created.";
    final String RECORD_ADDED = "Record added.";
    final String RECORD_DELETED = "Record deleted.";
    final String RECORD_UPDATED = "Record updated.";
    final String UNKNOWN_COMMAND = "Unknown command, use [-create,-init,-print,-getscore, " +
                                                    "-setscore,-listscore, -delete -exit] only.";
    final String ID_COL = "id";
    final String STUDENT_COL = "student";
    final String SCORE_COL = "score";

    Connection connect;
    Statement stmt;
    PreparedStatement ps;
    ResultSet rs;
    String sql;

    public static void main(String[] args) {
        new Student(args);
    }

    Student(String[] args) {
        List <String> arg = new ArrayList<>(Arrays.asList(args));
        boolean exit = false;
  //      do {
            if (arg.size() == 0) {
                System.out.println("Work with DB.\n" +
                        "use command [-create,-init,-print,-getscore, -setscore, -list, -delete -exit]");
                Scanner sc = new Scanner(System.in);
                System.out.print("> ");
                args = sc.nextLine().split(" ");
                arg.clear();
                Collections.addAll(arg, args);
            }
            switch (arg.get(0)) {
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
                    case "-add":
                        openDBFile(SQLITE_DB)
                                .add(arg.get(1), arg.get(2));
                        System.out.println(RECORD_ADDED);
                        break;
                    case "-print":
                        openDBFile(SQLITE_DB).
                                print();
                        break;
                    case "-getscore":
                        openDBFile(SQLITE_DB)
                                .getprice(args[1]);
                        break;
                    case "-setscore":
                        openDBFile(SQLITE_DB)
                                .update(arg.get(1), arg.get(2));
                        System.out.println(RECORD_UPDATED);
                        break;
                    case "-list":
                        openDBFile(SQLITE_DB)
                                .list(arg.get(1), arg.get(2));
                        break;
                    case "-delete":
                        openDBFile(SQLITE_DB)
                                .delete(arg.get(1));
                        System.out.println(RECORD_DELETED);
                        break;
                    case "-exit":
                        exit = true;
                         break;
                    default:
                        System.out.println(UNKNOWN_COMMAND);
                }
               arg.clear();
   //     } while (!(exit));
    }

    private Student openDBFile(String dbName) { // open/create database
        try {
            Class.forName(DRIVER_NAME);
            connect = DriverManager.getConnection(dbName);
            stmt = connect.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    private void createTable(String sqlCreateTable) { // create table
        try {
            stmt.executeUpdate(sqlCreateTable);
        } catch (Exception e) { 
            e.printStackTrace();
        }
    }

    private void init() { // init record
        try {
            Random r = new Random();
            stmt.executeUpdate("DELETE FROM " + NAME_TABLE);
            ps = connect.prepareStatement("INSERT INTO " + NAME_TABLE +
                    "(" + ID_COL +","+ STUDENT_COL + "," + SCORE_COL + ")" +
                    "VALUES (?, ?, ?);");
            for (int i=1; i<=10; i++) {
                ps.setInt(1,i);
                ps.setInt(3,rand(5));
                ps.setString(2,"Student"+i);
                ps.addBatch();
            }
            ps.executeBatch();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void add (String student, String score) { // add record
        try {
            stmt.executeUpdate("INSERT INTO " + NAME_TABLE +
                    "("+ STUDENT_COL + "," + SCORE_COL + ")" +
                    "VALUES ('" + student+ "','" + score + "');");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int rand(int max)
    {
        return (int) (Math.random() * ++max);
    }

    private void getprice(String student) { // getprice record
        try {
            rs = stmt.executeQuery("SELECT * FROM " + NAME_TABLE +
                                        " WHERE " + STUDENT_COL + " = '" + student  + "';");
            if  (rs.next()) {
                System.out.println("STUDENT\tSCORE");
                do {
                    System.out.println(
                            rs.getString(ID_COL) + ".\t" +
                                    rs.getString(STUDENT_COL) + "  -\t" +
                                    rs.getString(SCORE_COL));
                } while (rs.next());
            } else System.out.println("Not found");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void update(String student, String score) { // update cost by title
        try {
            stmt = connect.createStatement();
            stmt.executeUpdate("UPDATE " + NAME_TABLE +
                " set score='" + score +
                "' where student='" + student+ "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void delete(String student) { // delete record by title
        try {
            stmt = connect.createStatement();
            stmt.executeUpdate("DELETE from " + NAME_TABLE +
                " where student='" + student + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void list (String scorestart, String scoreend) { // print table
        try {
            System.out.println("ID\tSTUDENT\tSCORE");
            stmt = connect.createStatement();
            rs = stmt.executeQuery("SELECT * FROM " + NAME_TABLE +
                    " WHERE score >= " + scorestart +" AND score <= " + scoreend +" ;");
            while (rs.next())
                System.out.println(
                        rs.getString(ID_COL) + ".\t" +
                                rs.getString(STUDENT_COL) + "  -\t" +
                                rs.getString(SCORE_COL));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void print() { // print table
        try {
            System.out.println("ID\tSTUDENT\tSCORE");
            stmt = connect.createStatement();
            rs = stmt.executeQuery(SQL_SELECT);
            while (rs.next())
                System.out.println(
                    rs.getString(ID_COL) + ".\t" +
                    rs.getString(STUDENT_COL) + "  -\t" +
                    rs.getString(SCORE_COL));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
