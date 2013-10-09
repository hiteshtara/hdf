package hdf;

public abstract class BaseCheese implements Cheese { 
    public void accept(Visitor v) throws Exception { v.visit(this); } 
}