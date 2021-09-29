/**
 *
 *                                                  Использовать Wildcard.
 * Так как дженерики инвариантны (то есть, как-бы, независимы), то есть для типов A и B extends A несправедливо высказывание,
 * что List<B> является подтипом List<A>. Вообще, желательно следовать правилу PECS, что означает producer - extends,
 * consumer - super. Другими словами, если тип с параметрами представляет производителя T - надо использовать <? extends T>,
 * а если он представляет потребителя T - используем <? super T>. Но надо помнить, что ни в коем случае нельзя использовать
 * wildcard-тип, как возвращаемый.
 *
 * */


package ch5.article28;

import java.util.ArrayList;
import java.util.List;

public class Wildcards {

    static void test(List<B> l1) {
        l1.add(new C());
    }

    public static void main(String[] args) {
        List<B> bb = new ArrayList<>();
        bb.add(new C());
        test(bb);


        A<B> aa = new A<>();
        aa.add(new C());
        System.out.println(aa.getVal(0).toString());
        System.out.println(1);
    }

}

class A<T> {

    T v1;
    List<T> al = new ArrayList<T>();

    /*public A(T v1) {
        this.v1 = v1;
    }*/
    <T> T getVal(int i) {
        return (T)al.get(i);
    }

    void add(T el){
        this.al.add(el);
    }


}

class B {

    public B() {

    }
}

class C extends B {

    public String toString() {
        return "q";
    }

}