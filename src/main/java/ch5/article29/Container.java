/**
 *
 *                                          Использование неоднородных контейнеров.
 * Наиболее часто дженерики используются с коллекциями или с однородными контейнерами типа ThreadLocal и AtomicReference.
 * Во всех этих случаях параметры присваиваются именно контейнерам. Это ограничивает нас до определенного количества
 * параметров на один контейнер. Обычно, это то, что нам нужно. Когда у Set<E> мы уверены, что ничего кроме E нам не
 * встретится. Тем не менее, иногда нам требуется большая свобода действий. Например, строка БД может содержать большое
 * количество столбцов, и было бы неплохо получить к ним типобезопасный доступ. Способ есть - идея его в том, чтобы
 * присвоить параметры КЛЮЧУ, а не самому КОНТЕЙНЕРУ. Рассмотрим класс Favorites. В нем объект типа Class будет играть
 * роль ключа с параметрами. Это сработает потому что Class был обобщен в версии 1.5. Тип литерала Class теперь выглядит
 * как Class<T>. То есть, напрмиер, String.class относится к типу Class<String>. Абстрактный пример импользования класса
 * Favorites в классе Container. В нем мы используем типы, как ключи, и если мы попросим вернуть String - нам никогда
 * не вернется Integer. Другими словами - класс Favorites является безопавсным неоднородным контейнером. Неоднородность
 * класса Fovaorites заключена в Map<Class<?>, Object> favorites, а если конкретнее, то в Class<?>, которое может принять вид
 * Class<String>, Class<Integer> и т д. Ограничение класса Favorites в том, что там не могут храниться нематериальные типы.
 * То есть, там может хранится String или String[], но никак не List<String>. Причина в том, что невозможно получить объект
 * Class для List<String>, то есть в List<String>.class есть синтаксическая ошибка, в отличие от List.class, но это уже
 * не типобезопасно.
 *
 * */


package ch5.article29;

import java.util.HashMap;
import java.util.Map;

public class Container {

    public static void main(String[] args) {
        Favorites f = new Favorites();
        f.putFavorite(String.class, "Java");
        f.putFavorite(Integer.class, 0xcafebabe);
        f.putFavorite(Class.class, Favorites.class);

        String favString = f.getFavorite(String.class);
        int intFav = f.getFavorite(Integer.class);
        Class<?> favClass = f.getFavorite(Class.class);
    }

}

class Favorites {

    private Map<Class<?>, Object> favorites = new HashMap<Class<?>, Object>();

    public <T> void putFavorite(Class<T> type, T instance) {
        if (type == null) {
            throw new NullPointerException();
        }

        favorites.put(type, type.cast(instance));
    }
    public <T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));
    }
}
