package myutil;

import java.util.ArrayList;

public class MyArrayList<E> extends ArrayList<E> implements ActuallyUsefulCloneable {
    @Override
    public Object clone() {
        return super.clone();
    }
}
