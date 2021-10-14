/**
 *
 * Синглтон - это просто класс, который инстанциируется только один раз за жизненный цикл программы. Имеется два распространенных способа
 * реализации синглтонов, они основаны на создании закрытого конструктора и обращении к публичному статическому элементу для предоставления
 * доступа к единственному экземпляру. Первый подход - класс Elvis. Закрытый конструктор вызывается только один раз для инициализации публичного
 * статического final-поля INSTANCE. Отсутствие открытого или протектед конструктора гарантирует, что будет создан только один экземпляр
 * класса Elvis, по крайней мере в однопоточной среде. Ну если не брать в расчет рефлексию.
 * Второй подход - класс John, получаем инстанс через геттер. Все вызовы John.getInstance возвращают ссылку на один и тот же объект и никакой
 * другой экземпляр John никогда не будет создан (с теми же оговорками, что и выше).
 * Плюс первого подхода (Elvis) - он проще и для клиента очевидно, что класс является синглтоном.
 * Плюс второго подхода (John) - он обеспечивает гибкость для того, чтобы изменить синглтон на НЕ синглтон. По сути мы получаем объект из
 * фабрики и нам ничего не мешает в этой самой фабрике инстанциировать новые объекты. Еще одно преимущество - ссылка на метод getInstance
 * может использоваться для суплаера (John::getInstance ; Supplier<John>)
 * Чтобы сделать класс-синглтон, использующий один из этих подходов, сериализуемым - недостаточно просто дописать implements Serializable.
 * Для гарантии сохранения свойств синглтона надо объявить все поля экземпляра класса как transient и предоставить метод readResolve (раздел
 * 12.5). Иначе при каждой десериализации сериализованного объекта будет создаваться новый, что уже не есть синглтон.
 * Третий способ реализации синглтона - объявление одноэлементного перечисления - энум Chris. Этот подход компактный, автоматически предоставляет
 * механизм сериализации и обеспечивает твердую гарантию наличия только и только одного экземпляра даже в многопоточной среде, даже при
 * десериализации, даже при использовании рефлексии. Это наилучший способ реализации синглтона в Java. Однако, его нельзя использовать, если наш
 * синглтон должен расширять суперкласс, отличный от enum.
 *
 * */

package diff_articles;

public class SingletonEnumOrClosedConstructor {
}

class Elvis {
    public static final Elvis INSTANCE = new Elvis();
    private Elvis() {}
    //other methods
}

class John {
    private static final John INSTANCE = new John();
    public static John getJohn() {
        return INSTANCE;
    }
    //other methods
}

enum Chris {
    INSTANCE;
    //other methods
}