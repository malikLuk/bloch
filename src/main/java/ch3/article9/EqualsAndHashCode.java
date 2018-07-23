/**
 *
 *                                          Переопределяя equals нужно переопределять
 *                                                         и hashCode().
 * Метод hashCode() нужно переопределять в каждом классе, в котором переопределен equals(). Невыполнение этого условия
 * приведет к нарушению общих соглашений для метода Object.hashCode(), а это не позволит нашему классу правильно работать
 * в сочетании с любыми коллекциями, построенными на использовании хэш-таблиц, в т. ч. HashMap, HashSet, HashTable.
 * Для Java 6 текст соглашений выглядит так:
 *      Если во ремя работы приложения несколько раз обратиться к одному и тому же объекту, метода hashCode должен постоянно
 *  возвращать одно и то же целое число, показывая тем самым, что информация, которая используется при сравнении этого
 *  объекта с другими(метод equals), не поменялась.
 *      Если метод equals показывает, что два объекта равны друг другу, то, вызвав для каждого из них метод hashCode, мы
 *  должны получать в обоих случаях одно и тоже целое число.
 *      Если метод equals показывает, что два объекта друг другу не равны - то вовсе не обязательно, что метод hashCode
 *  возвратит для них разные числа. Между тем, нужно понимать, что генерация разных чисел для неравных объектов может
 *  повысить эффективность хэш-таблиц.
 * Главным условием здесь является второе - у одинаковых объектов должен быть одинаковый хэш-код. Поэтому, если мы не
 * переопределим метод hashCode - это соглашение будет нарушено, так как hashCode класса Object возврщает целочисленное
 * представление адреса данного объекта в памяти, поэтому Object.hashCode будет одинаковым только у двух ссылок, указывающих
 * на один и тот же объект. Для примера возьмем класс PhoneNumber. И, допустим, мы попытались использовать этот класс с
 * HashMap (psvm) в качесвте ключа(!!!). Выполнив код из метода main, мы ожидаем получить Jenny, но получаем null, хотя
 * достаем по точно такому же объекту (equals == true). Такое поведение обусловленно отсутствием переопределенного метода
 * hashCode - и, конечно, же двум разным объектам с идентичным содержимым соответствует разный хэш-код. Соответственно,
 * разрешить эту ситуацию можно, поместив в класс PhoneNumber метод hashCode. Idea переопределяет метод hashCode по всем
 * правилам, описанным в книге.
 * Если класс является неизменным и при этом важны затраты на вычисление хэш-кода, мы можем сохранять хэш-код в самом
 * объекте вместо того, чтобы вычислять его каждый раз, когда это будет необходимо. Если предполагается, что объекты этого
 * неизменяемого класса будут ключами в хэш-таблице, что лучше вычислять хэш-код прямо в момент создания экземпляра, или
 * же можно выбрать инициализацию, отложенную до первого обращения к методу hashCode. Ниже, в классе PhoneNumberDeferred,
 * показано, как это делается.
 *
 * */


package ch3.article9;

import java.util.HashMap;

public class EqualsAndHashCode {

    public static void main(String[] args) {
        HashMap<PhoneNumber, String> map = new HashMap<PhoneNumber, String>();
        map.put(new PhoneNumber(707,867,5309), "Jenny");
        System.out.println(map.get(new PhoneNumber(707,867,5309)));
    }

}

class PhoneNumber {

    private final short areaCode;
    private final short prefix;
    private final short lineNumber;

    public PhoneNumber(int areaCode, int prefix, int lineNumber) {
        rangeCheck(areaCode, 999, "area code");
        rangeCheck(prefix, 999, "prefix");
        rangeCheck(lineNumber, 9999, "line number");

        this.areaCode = (short) areaCode;
        this.prefix = (short) prefix;
        this.lineNumber = (short) lineNumber;
    }

    private static void rangeCheck(int arg, int max, String name) {
        if (arg < 0 || arg > max) {
            throw new IllegalArgumentException(name + ": " + arg);
        }
    }

    // Ошибка, метод hashCode не переопределен


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneNumber that = (PhoneNumber) o;

        if (areaCode != that.areaCode) return false;
        if (prefix != that.prefix) return false;
        return lineNumber == that.lineNumber;
    }

    @Override
    public int hashCode() {
        int result = (int) areaCode;
        result = 31 * result + (int) prefix;
        result = 31 * result + (int) lineNumber;
        return result;
    }
}

class PhoneNumberDeferred {

    private short areaCode;
    private short prefix;
    private short lineNumber;

    // Отложенная инициализация, кэшируемый hashCode
    private volatile int hashCode;


    @Override
    public int hashCode() {
        int result = hashCode;
        if (hashCode == 0) {
            result = 17;
            result = 31 * result + areaCode;
            result = 31 * result + prefix;
            result = 31 * result + lineNumber;
            hashCode = result;
        }
        return result;
    }

}
