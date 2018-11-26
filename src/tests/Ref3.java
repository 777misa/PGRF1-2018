package tests;

class Ref3 {

    private Ref3Inner inner;

    private Ref3() {
        inner = new Ref3Inner();
        inner.a = 2;
    }

    private Ref3(Ref3Inner inner) {
        this.inner = inner;
    }

    public static void main(String[] args) {
        Ref3 ref1 = new Ref3();
        ref1.inner = ref1.new Ref3Inner();
        Ref3 ref2 = new Ref3();
        ref1.inner.a = 3;
        ref2.inner = new Ref3().new Ref3Inner();
        ref1 = new Ref3(ref2.inner);

        System.out.println(ref1.inner.a++);
        System.out.println(ref2.inner.a);
    }

    private class Ref3Inner {
        private int a = 7;
    }
}
