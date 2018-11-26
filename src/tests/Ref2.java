package tests;

class Ref2 {

    private Ref2Inner inner;

    private Ref2() {
        inner = new Ref2Inner();
        inner.a = 4;
    }

    private Ref2(Ref2Inner inner) {
        this.inner = inner;
    }

    public static void main(String[] args) {
        Ref2 ref1 = new Ref2();
        ref1.inner = ref1.new Ref2Inner();
        Ref2 ref2 = new Ref2(ref1.inner);
        ref1.inner.a = 1;
        ref1.inner.a = ++ref1.new Ref2Inner().a;
        //ref1 = new Ref2(ref2.inner);

        System.out.println(ref1.inner.a);
        System.out.println(++ref2.inner.a);
    }

    private class Ref2Inner {
        private int a = 6;
    }
}
