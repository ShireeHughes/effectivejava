package effectivejava.item29;

import java.util.Arrays;
import java.util.EmptyStackException;

//Option 1 for using Generics
public class MyStackGeneric<E> {
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPTIVITY = 16;

    /* The elements array will contain on ly E instances from push(E)
     * This is sufficient to ensure type safety, but the runtime type of the array
     * won't be E[] it will be Object[]
     */
    @SuppressWarnings("unchecked")
    public MyStackGeneric() {
        // The following creates an error
        // elements = new E[DEFAULT_INITIAL_CAPTIVITY];
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPTIVITY]; //unchecked cast error
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop() {
        if(size == 0)
            throw new EmptyStackException();
        E result = elements[--size];
        elements[size] = null; //Eliminate obsolete reference
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if(elements.length == size)
            elements = Arrays.copyOf(elements, 2*size+1); //expands array
    }
}
