/**
 *
 *                                          Избегать ненужных объектов.
 * Вместо того, чтобы создавать новый функциональный эквивалент всякий раз, когда в нем возникает необходимость, часто
 * можно просто использовать уже существующий объект. Так изящнее и быстрее. Рассмотрим, как делать не надо:
 *   String s = new String("stringette");
 * При каждом проходе этот оператор создает новый экземпляр String, но ни одна из этих процедур создания объектов не
 * является необходимой. Аргумент коснтруктора String - "stringgette" - сам является экземпляром класса String и
 * функционально равнозначен всем объектам, создаваемым этим конструктором. Исправленная версия выглядит просто:
 *   String s = "stringgette";
 * В этом варианте используется единственный экземпляр String вместо того, чтобы при каждом проходе создавать новые.
 * Более того, дается гарантия того, что этот объект будет повторно использоваться любым другим кодом, выполняющимся на
 * той же JVM, где содержится эта строка-констранта (JLS 3.10.5)
 * JLS - это спецификация языка Java, Java Language Specification.
 * Создания дулирующих объектов можно избежать, если в неизменяемом классе вместо конструктора использовать статические
 * методы генерации. Например, в неизменяемом классе Boolean пределен статический метод генерации Boolean.valueOf выглядит
 * так
 *   public static Boolean valueOf(boolean b) {
 *       return (b ? TRUE : FALSE);
 *   }
 * где TRUE и FALSE уже инициализированные статические переменные класса Boolean
 *   public static final Boolean TRUE = new Boolean(true);
 * То есть от статического метода генерации не требуется всегда создавать новый объект, в то время как конструктор делает
 * это всегда.
 * Мы можем также повторно использовать не только неизменяемые объекты, но и изменяемые, которые больше не будут меняться.
 * Рассмотрим пример того, как НЕ НАДО поступать. В нем участвуют изменяемые объекты Date, которые более не меняются, после
 * того как их значение вычислено. Класс Person моделирует человека и имеет метод isBabyBoomer, который говорит, является
 * ли человек рожденным вследствие "бэйби-бума" 1946-1964 годов. Мы видимо, что метод isBabyBoomer при каждом вызове без
 * всякой надобности создает новые экземпляры Calindar, TimeZone и два объекта Date. В классе PersonEdited подобная
 * расточистельность пресекается с помощью статического инициализатора. В исправленной версии объекты Calendar, TimeZone
 * и Date создаются один раз во время инициализации, вместо того, чтобы создавать их при каждом вызове метода isBabyBoomer.
 *
 *
 * */

package ch2.article5;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class UselessObjects {
}

/**
 * ТАК ДЕЛАТЬ НЕЛЬЗЯ
 * */
class Person {

    private final Date birthDate;

    Person(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isBabyBoomer() {
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomStart = gmtCal.getTime();
        gmtCal.set(1964, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomEnd = gmtCal.getTime();
        return this.birthDate.compareTo(boomStart) >= 0 && this.birthDate.compareTo(boomEnd) < 0;
    }

}

/**
 * ТАК ПРАВИЛЬНО
 * */
class PersonEdited {

    private final Date birthDate;

    public PersonEdited(Date birthDate) {
        this.birthDate = birthDate;
    }

    private static final Date BOOM_START;
    private static final Date BOOM_END;
    static {
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_START = gmtCal.getTime();
        gmtCal.set(1964, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_END = gmtCal.getTime();
    }

    public boolean isBabyBoomer() {
        return this.birthDate.compareTo(BOOM_START) >= 0 && this.birthDate.compareTo(BOOM_END) < 0;
    }

}
