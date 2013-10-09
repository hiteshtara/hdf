package hdf;

import java.lang.reflect.Method;

public class VisitorImpl implements Visitor {
    private Method getPolymorphicMethod(Cheese cheese) throws Exception {
        Class cl = cheese.getClass();  // the bottom-most class
        // Check through superclasses for matching method
        while(!cl.equals(Object.class)) {
            try {
                return this.getClass().getDeclaredMethod("visit", new Class[] { cl });
            } catch(NoSuchMethodException ex) {
                cl = cl.getSuperclass();
            }
        }
        // Check through interfaces for matching method
        Class[] interfaces = cheese.getClass().getInterfaces();
        for (int i=0; i<interfaces.length; i++) {
            try {
                return this.getClass().getDeclaredMethod("visit", new Class[] { interfaces[i] });
            } catch(NoSuchMethodException ex) {
            }
        }
        return null;
    }

    public void visit(Cheese c) throws Exception {
        Method downPolymorphic = getPolymorphicMethod(c);
        if (downPolymorphic == null) {
            defaultVisit(c);
        } else {
            downPolymorphic.invoke(this, new Object[] {c});
        }
    }

    void defaultVisit(Cheese c) { System.out.println("A cheese"); }
    void visit(Wensleydale w) { System.out.println(w.wensleydaleName()); }
    void visit(Gouda g) { System.out.println(g.goudaName()); }
    void visit(Brie b) { System.out.println(b.brieName()); }
    void visit(AnotherCheese a) { System.out.println(a.otherCheeseName()); }
}
