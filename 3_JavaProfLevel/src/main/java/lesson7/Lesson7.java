package lesson7;

/*
    1. Создать класс, который может выполнять «тесты». В качестве тестов выступают классы с наборами методов
        с аннотациями @Test. Для этого у него должен быть статический метод start(), которому в качестве параметра
        передается или объект типа Class, или имя класса.
    2. Из «класса-теста» вначале должен быть запущен метод с аннотацией @BeforeSuite, если такой имеется.
    3. Далее запущены методы с аннотациями @Test,
    4. а по завершении всех тестов – метод с аннотацией @AfterSuite.
    5. К каждому тесту необходимо добавить приоритеты (int числа от 1 до 10), в соответствии с которыми будет
        выбираться порядок их выполнения. Если приоритет одинаковый, то порядок не имеет значения.
    6. Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в не более чем в одном экземпляре,
        иначе необходимо бросить RuntimeException при запуске «тестирования».
 */

import lesson7.annotations.AfterSuite;
import lesson7.annotations.BeforeSuite;
import lesson7.annotations.Test;
import lesson7.tests.ClassWithRightStructureTest;
import lesson7.tests.ClassWithWrongStructureTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Lesson7
{
    public static void main(String[] args)
    {
        processTests(ClassWithRightStructureTest.class);
        processTests(ClassWithWrongStructureTest.class);
    }

    private static void processTests(Class testClass)
    {
        System.out.println("Test the class " + testClass.getName());
        Method[] methods = testClass.getMethods();

        Method beforeMethod = null;
        Method afterMethod = null;
        List<Method> testMethods = new ArrayList<>();

        for (Method method : methods)
        {
            if (method.getAnnotation(BeforeSuite.class) != null)
            {
                if (beforeMethod != null)
                {
                    throw new RuntimeException("Only one BeforeSuite method is possible!");
                }

                beforeMethod = method;
                continue;
            }

            if (method.getAnnotation(AfterSuite.class) != null)
            {
                if (afterMethod != null)
                {
                    throw new RuntimeException("Only one AfterSuite method is possible!");
                }

                afterMethod = method;
                continue;
            }

            if (method.getAnnotation(Test.class) != null)
            {
                testMethods.add(method);
            }
        }

        Collections.sort(testMethods, (val1, val2) -> {
            Test testAnnotation = val1.getAnnotation(Test.class);
            int prio1 = testAnnotation == null ? 0 : testAnnotation.priority();

            testAnnotation = val2.getAnnotation(Test.class);
            int prio2 = testAnnotation == null ? 0 : testAnnotation.priority();

            return prio1 - prio2;
        });

        try
        {
            Object classInstance = testClass.newInstance();

            beforeMethod.invoke(classInstance);

            for (Method method : testMethods)
            {
                method.invoke(classInstance);
            }

            afterMethod.invoke(classInstance);
        }
        catch (IllegalAccessException | InvocationTargetException | InstantiationException e)
        {
            e.printStackTrace();
        }
    }
}
