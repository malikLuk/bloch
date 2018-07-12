/**
 *
 *                                                  Уничтожать устаревшие ссылки.
 * Несмотря на то, что в Java есть сборщик мусора, не стоит пренебрежительно относиться к возможным утечкам памяти. Возьмем
 * нижепредставленный класс Stack, в котором есть утечка памяти, хоть она и не бросается в глаза. Где же происходит утечка?
 * Если стек растет, а потом уменьшается, то объекты, которые были pop() из стека не могут быть удалены, даже если программа,
 * пользующаяся этим стеком уже не имеет ссылок на них. Все едло в том, что сам стек сохранит устаревшие ссылки на эти
 * объекты. В нашем случае - устаревшие ссылки - это ссылки оказавшиеся за пределами активной части массива, то есть больше
 * чем индекс size. Активная часть стека - это все объекты, индексы которых меньше значения переменной size. Решается
 * такая утечка очень просто, как только ссылка становится не нужна - ее надо обнулять. В нашем случае - ссылка становится
 * устаревшей, как только был вытолкнут (pop()) объект из стека. Исправленный вариант находится в функции popEdited().
 * Однако, обнуление ссылок должно быть не правилом, а исключением. Лучший способ избавиться от устаревшей ссылки - вновь
 * использовать переменную, в которой она находилась, или выйти из области видимости этой переменной, так как локальные
 * переменные очень быстро уничтожаются сборщиком мусора. То есть, если классы управляют своей памятью - то они потенциально
 * подвержены утечкам.
 * Другие распространенные источники утечек памяти - это кэши. Поместив однажды в кэш ссылку на некий объект, легко можно
 * забыть о том, что она там вообще есть. Решить эту проблему поможет WeakHashMap (прочитать) - когда записи в ней устареют,
 * они будут автоматичеки удалены.
 * Третий источник утечек памяти - приложения в режиме ожидания и прочие обратные вызовы.
 *
 * */


package article6;

import java.util.Arrays;
import java.util.EmptyStackException;

public class DeleteObjects {

    public static void main(String[] args) {
        Stack s = new Stack();
        s.push("kekeke");
//        System.out.println(s.getElements().length);
        s.push("ukuku");
//        System.out.println(s.getElements().length);
//        System.out.println(s.pop());
//        System.out.println(s.pop());
        s.popEdited();
    }


}

class Stack {

    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 0;

    public Stack() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return elements[--size];
    }

    public Object popEdited() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        /**
         * Тут все правильно. Так как изначально size = 0, то при добавлении элемента она станет равной 1
         * Однако индекс добавленного элемента будет 0, поэтому Object result = elements[--size];
         * */
        Object result = elements[--size];
        elements[size] = null; //Убираем оставшуюся ссылку
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    public int getSize() {
        return size;
    }

    public Object[] getElements() {
        return elements;
    }
}