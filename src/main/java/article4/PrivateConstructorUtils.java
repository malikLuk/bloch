/**
 *
 *                                              Отсутствие экземпляров обеспечивает
 *                                                      закрытый конструктор.
 * Время от времени нам будет необходимо написать класс, который является просто собранием статических методов и статических
 * полей. Такие классы приобрели дурную репутацию, поскольку некоторые люди неправильно пользуются ими, чтобы с помощью
 * ОО-языков писать процедурные программы. Подобные классы требуют правильного использовнаия. Их можно использовать для
 * того, чтобы собирать вместе связанные друг с другом методы обработки простых значений или массивов, как это сделано в
 * java.lang.Math или java.util.Arrays. Такие классы можно назвать классами утилит, они разрабатываются не для того, чтобы
 * для них создавать экземпляры - это было бы абсурдом.
 * Однако, если у класса нет явных конструкторов, компилятор сам создаст открытый конструктор по умолчанию(без параметров).
 * Попытки запретить классу создавать экземпляры, сделав его абстрактным тоже бесперспективны, так как такой класс может
 * иметь подкласс, для которого можно будет создать экземпляры. Есть, однако, простая идиома, гарантирующая отсуствие
 * экземпляров. Запретить классу создание объектов можно создав закрытый (private) конструкторв. Также, эта идиома не
 * позволяет наследоваться от этого класса, так как явно или неявно, но должен вызываться доступный конструктор суперкласса,
 * но если он закрытый, то доступных констуркторов нет и следовательно - наследование невозможно.
 *
 * */

package article4;

public class PrivateConstructorUtils {

    // throw new AssertionError() можно опустить
    private PrivateConstructorUtils() {
        throw new AssertionError();
    }

    // Различные static поля и методы

}
