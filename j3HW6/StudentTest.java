/**
 JUnit test for check class Student

 * @author Valeriy Kondaurov
 * @version dated MAR 22, 2018
 * @link https://github.com/ValeriKondaurov/Java3
 */

import org.junit.*;

public class StudentTest {
    final String DRIVER_NAME = "org.sqlite.JDBC";
    final String SQLITE_DB = "jdbc:sqlite:table.db";
    final String NAME_TABLE = "itemlist";

    @Test (timeout = 10000)
    public void add () {
        new Student(new String[]{"-add", "Student15", "5"});
        new Student(new String[]{"-getscore", "Student15"});
    }

    @Test (timeout = 10000)
    public void set () {
        new Student(new String[]{"-setscore", "Student15", "0"});
        new Student(new String[]{"-getscore", "Student15"});
    }

    @Test (timeout = 10000)
    public void del () {
        new Student(new String[]{"-delete", "Student15"});
    }

}
