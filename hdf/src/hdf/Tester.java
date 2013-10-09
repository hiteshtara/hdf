package hdf;

public class Tester {
    public static void main(String[] args) throws Exception {
        Cheese cheese1 = new Wensleydale();
        Cheese cheese2 = new Gouda();
        Cheese cheese3 = new Brie();
        Cheese cheese4 = new Gorgonzola();
        Cheese cheese5 = new SomeOtherCheese();

        Visitor v = new VisitorImpl();
        cheese1.accept(v);
        cheese2.accept(v);
        cheese3.accept(v);
        cheese4.accept(v);
        cheese5.accept(v);
    }
}
