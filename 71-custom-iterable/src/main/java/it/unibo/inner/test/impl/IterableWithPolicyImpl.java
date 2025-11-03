package it.unibo.inner.test.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;



public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private List<T> listaArray;
    private Predicate<T> filter;


    /**
     * costruttore a un parametro
     * @param elem
     */

    public IterableWithPolicyImpl (final T[] elem){ 
        
        this(elem, new Predicate<T>(){
             public boolean test(final T elem) {return true;}
            });
    }
    
    public IterableWithPolicyImpl (final T[] elem, Predicate<T> exist){ 
        this.listaArray = List.of(elem);
        this.filter = exist;
    }

    @Override
     public void setIterationPolicy(Predicate<T> filter){
        this.filter = filter;

    }

    @Override
    public Iterator<T> iterator() {
        return new Iteratore();

    }
   
    private class Iteratore implements Iterator<T> {
        int i = 0;
        

        @Override
        public boolean hasNext() {
            boolean control = false;

            while( control == false && i < listaArray.size()){
                control = filter.test(listaArray.get(i));
                if(!control){
                    i++;
                }
            }
            return control ;
        }

        @Override
        public T next() {
            
            if (hasNext()) {
                return listaArray.get(i++);  
            }
            throw new NoSuchElementException("you are out ");
            
        }
        /*
        [1, 1, 4, 1]

        t % 2 == elemento pari 
        */


       
    }



 



}






     


