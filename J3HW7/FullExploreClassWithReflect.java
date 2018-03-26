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

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class FullExploreClassWithReflect {

    public static void main(String[] args) {
        explore ("Cat");
    }

    static void explore(String nameclass) {
        try {
            Class refclass = Class.forName(nameclass);
            System.out.println("Full name: " + refclass.getName());
            System.out.println("Short name: " + refclass.getSimpleName());
            int modifiers = refclass.getModifiers();
            System.out.println("Modifiers = " + modifiers);

            // get modifiers of class
            if (Modifier.isPublic(modifiers))
                System.out.println(refclass.getSimpleName() + " - public");
            if (Modifier.isAbstract(modifiers))
                System.out.println(refclass.getSimpleName() + " - abstract");
            if (Modifier.isFinal(modifiers))
                System.out.println(refclass.getSimpleName() + " - final");

            // get public fields of class
            for (Field field : refclass.getDeclaredFields())
                System.out.println(
                        "Type name: " + field.getType().getName() + " " + field.getName());

            // get public constructors
            System.out.println("--------public constructors");
            for (Constructor constructor : refclass.getConstructors())
                System.out.println(constructor);

            // get public methods
            for (Method method : refclass.getMethods())
                System.out.println(method);
            System.out.println("---------ALLL methods");
            for (Method method : refclass.getDeclaredMethods())
                System.out.println(method);

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {


        }
    }
}
