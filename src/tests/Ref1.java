package tests;

public class Ref1 {

    private Ref1Inner inner;

    private Ref1() {
        inner = new Ref1Inner();
        inner.a = 3;
    }

    private Ref1(Ref1Inner inner) {
        this.inner = inner;
    }

    public static void main(String[] args) {
        Ref1 ref1 = new Ref1();
        ref1.inner = ref1.new Ref1Inner();
        Ref1 ref2 = new Ref1();
        ref2.inner = ref1.inner;
        ref1.inner.a = 7;
        ref1.inner.a = ref2.new Ref1Inner().a++;
        ref1 = new Ref1(ref2.inner);

        System.out.println(ref1.inner.a);
        System.out.println(ref2.inner.a++);
    }

    private class Ref1Inner {
        private int a = 5;
    }
}
