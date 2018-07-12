/**
 *
 *                                                      Синглтон.
 * Синглтон - это просто класс, экземпляр для которого создается только один раз. Синглтоны обычно представляют некоторые
 * компоненты системы, которые действительно являются уникальными, например видеодисплей или файловая система. До Java 1.5
 * для реализации синглтона использовались два подхода. Оба они основаны на создании закрытого(private) конструктора и
 * открытого(public) статического члена, который позволяет клиентам иметь доступ к единственному экземпляру этого класса.
 * первый вариант использует final-поле(класс SingletonFinalField). Здесь закрытый конструктор вызывается только один раз,
 * чтобы инициализировать поле INSTANCE. Отсутствие public конструкторов гарантирует "вселенную с одним SingletonFinalField":
 * то есть после инициализации класса SingletonFinalField будет существовать ровно один экземпляр SingletonFinalField.
 * Но возможно исключение: клиент с расширенными правами может запустить приватный конструкторо с помощью
 * AccessibleObject.setAccessible. Если нужна защита от такого рода атак, то нужно изменить конструктор так, чтобы он
 * выводил сообщение об ошибке, если поступит запрос на создание второго экземпляра.
 * Во втором варианте, вместо открытого статического поля  final создается открытый статический метод генерации: класс
 * SingletonGenMethod. Все вызовы статического метода getInstance() возвращают ссылку на один и тот же объект, поэтому
 * новые созданы не будут, за исключением, опять же AccessibleObject.setAccessible.
 * Наиболее же предпочтительным способом задать синглтон является использование перечисления. Класс SingletonEnum. О
 * преимуществах этого подхода - в другой статье.
 *
 * */


package ch2.article3;

class SingletonFinalField {

    public static SingletonFinalField INSTANCE = new SingletonFinalField();

    private SingletonFinalField() {
        /**
         * Some code here
         * */
    }

}

class SingletonGenMethod {

    private static final SingletonGenMethod INSTANCE = new SingletonGenMethod();

    private SingletonGenMethod() {
        /**
         * Some code here
         * */
    }

    public static SingletonGenMethod getInstance() {
        return INSTANCE;
    }

}

enum SingletonEnum {

    INSTANCE;

    public void someMethod() {
        /**
         * Some code here
         * */
    }
}

public class Singleton {
}
