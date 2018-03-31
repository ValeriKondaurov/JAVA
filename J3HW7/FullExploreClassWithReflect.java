/*
*1. Create a research method. The name of the class is supplied
* to the input, and the method displays to the console all the information
* about the class that can be obtained using Reflection.
*
2. *** Создать класс, который может выполнять «тесты»,
в качестве тестов выступают классы с наборами методов с аннотациями @Test.
Для этого у него должен быть статический метод start(),
которому в качестве параметра передается или объект типа Class,
или имя класса. Из «класса-теста» вначале должен быть запущен метод
с аннотацией @BeforeSuite, если такой имеется, далее запущены методы
с аннотациями @Test, а по завершению всех тестов – метод с аннотацией @AfterSuite.
К каждому тесту необходимо также добавить приоритеты (int числа от 1 до 10),
в соответствии с которыми будет выбираться порядок их выполнения,
если приоритет одинаковый, то порядок не имеет значения. Методы с аннотациями
@BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре,
иначе необходимо бросить RuntimeException при запуске «тестирования».
 */

import com.sun.org.apache.xpath.internal.SourceTree;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class FullExploreClassWithReflect {

    public static void main(String[] args) {
        explore ("ru.AnimalExplore.Cat");
    }

    static void explore(String nameclass) {
        try {
            Class refclass = Class.forName(nameclass);
            System.out.println("-----<Information about Class>-----");
            System.out.println("Full name: " + refclass.getName());
            System.out.println("Short name: " + refclass.getSimpleName());
            if (!(refclass.getPackage() == null))
                System.out.println("Include package - " + refclass.getPackage());
            System.out.println("Her Super class - " + refclass.getSuperclass().getSimpleName());
            System.out.println("Modifiers class is " + modifier(refclass.getModifiers(),false));

            // get all fields of class
            System.out.println("-----<Fields>-----");
            for (Field field : refclass.getDeclaredFields())
                System.out.println(modifier(field.getModifiers(), true)   + " | "
                                + field.getName()+ ":" + field.getType().getSimpleName());

            // get public constructors
            System.out.println("--------<Constructors>-------");
            for (Constructor constructor : refclass.getDeclaredConstructors()) {
                System.out.print(modifier(constructor.getModifiers(), true) + " | " + constructor.getName() + "(");
                for (Class c:constructor.getParameterTypes())
                    System.out.print(" " + c.getSimpleName() + ",");
                System.out.println(")");
            }

            // get public methods
            for (Method method : refclass.getMethods()) {
                System.out.println(method);

            }
            System.out.println("---------ALL methods");
            for (Method method : refclass.getDeclaredMethods())
                System.out.println(method);

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {


        }
    }

    private static String modifier(int m, boolean type) {
        switch (m) {
            case Modifier.PUBLIC:
                return (type ? "+" : "(public)");
            case Modifier.PRIVATE:
                return (type ? "-" : "(private)");
            case Modifier.PROTECTED:
                return (type ? "#" : "(protected)");
            case Modifier.FINAL:
                return "(final)";
            case Modifier.ABSTRACT:
                return "(abstract)";
            case Modifier.STATIC:
                return "(static)";
            default:
                return "(" + Modifier.toString(m) + ")";

        }
    }
}


