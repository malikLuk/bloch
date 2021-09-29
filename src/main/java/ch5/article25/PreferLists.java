/**
 *
 *                                              Предпочесть списки массивам.
 * Массивы отличаются от средств обобщенного программирования в двух важных аспектах. Во-первых, массивы ковариантны.
 * Это значит, что если, например, Sub является подтипом Super, то есть Sub extends Super, то тогда тип массива Sub[]
 * является подтипом Super[]. Дженерики же, напротив, инвариантны - то есть для любых типов Type1 и Type2, типы
 * List<Type1> и List<Type2> никак не соотносятся между собой. Для массива это является недостатком. См main(). Видно,
 * что мы не можем вставить String в контейнер Long, но, работая с массивом, мы увидим ошибку только при запуске.
 * Второе важное отличие массивов от дженериков является то, что массивы материальны. Это значит, что массивы знают и
 * выполняют свои типы элементов при выполнении. Дженерики, напротив, реализуются стиранием. Это значит, что они
 * выполняют свои ограничения типов только на этапе компиляции и затем выбрасывают(или стирают) информацию о типах
 * элементов при выполнении. Именно стирание позволяет дженерикам взаимодействовать с кодом на Java версии ниже 1.5.
 * Массивы и дженерики не могут применяться одновременно. Например, нельзя создавать массив обобщенных типов и типов с
 * параметрами. Ни одно из следующих выражений не является разрешенным:
 * newList<E>[]; newList<String>[]; new E[];
 * Все эти выражения приведут к ошибкам создания обобщенных массивов на этапе компиляции. Создавать дженерик-массивы
 * нельзя потому что это небезопасно. Подробнее в main(). Чтобы избежать описанной свистопляски, нужно использовать
 * списки, а не массивы.
 *
 * */

package ch5.article25;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreferLists {

    public static void main(String[] args) {
        // Ошибка вылетит только при запуске
        Object[] objectArray = new Long[1];
        objectArray[0] = "Some message!";

        // Компилятор сругается сразу - еще до запуска
//        List<Object> ol = new ArrayList<Long>();
//        ol.add("Some message");

        // Представим, что эта строка разрешена
//        List<String>[] stringList = new List<String>[1];
        // Здесь создаем и инициализируем список Integer'ов, содержащий единственный элемент
//        List<Integer> intList = Arrays.asList(42);
        // Сохраняем массив List<String> в массив объектов
//        Object[] objects = stringList;
        // Сохраняем список интов в первый элемент массива объектов
//        objects[0] = intList;
        // Все вышеописанные действия не приводят к ошибкам компиляции, засчет ковариантности массивов (тут все
        // наследуется от Object) и если бы не первая строка, которая запрещена - мы бы скомпилировали некорректный код
//        String s = stringList[0].get(0);

    }

}
