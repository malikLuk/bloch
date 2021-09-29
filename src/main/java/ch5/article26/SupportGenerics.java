/**
 *
 *                                              Поддерживать обобщенные типы.
 *                                      Посмотреть, как реализовано в AraayList.java.
 * Рассмотрим простую реализацию стека в классе Stack. Данный класс основной претендент для обощения, то есть, идеально
 * подходит для применения преимуществ средств обобщенного программирования. В первую очередь, мы можем добавить один
 * Параметр Типа, представляющий тип элемента стека. Следующий шаг - заменить все использования типа Object нашим
 * параметром типа. Класс GenericStack. Тут есть два варианта. Первый - обходит запрет на совместное использование
 * дженериков и массивов - нужно создать массив для Object и передать его обобщенному типу массива. То есть просто
 * приводим массив к типу дженерика. Второй - нихуя не понятно.
 *
 * */

package ch5.article26;

import java.util.Arrays;
import java.util.Collection;
import java.util.EmptyStackException;

public class SupportGenerics {

    public static void main(String[] args) {
        GenericStack<String> stack = new GenericStack<String>();
        stack.push("aaaa");
        stack.push("wwww");
        stack.push("dddd");
        stack.push("qqqq");
        // Нельзя
        //stack.push(new Integer(1));
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

}

class Stack {

    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object o ) {
        ensureCapasity();
        elements[size++] = o;
    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object result = elements[--size];
        elements[size] = null;
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapasity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

}

class GenericStack<E> {

    //private E[] elements;
    // Первый вариант
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public GenericStack() {
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E o ) {
        ensureCapasity();
        elements[size++] = o;
    }

    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        E result = (E) elements[--size];
        elements[size] = null;
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapasity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

}