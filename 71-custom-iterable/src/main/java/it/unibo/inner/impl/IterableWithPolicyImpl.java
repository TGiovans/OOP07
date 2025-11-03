package it.unibo.inner.impl;

import it.unibo.inner.api.IterableWithPolicy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{
   
    private final List<T> array;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(T[] arr, Predicate<T> filter){
        this.array = new ArrayList<>();
        for(T el : arr){
            this.array.add(el);
        }
        this.filter = filter;
    }

    public IterableWithPolicyImpl(T[] arr){
        this(arr,new Predicate<T>() {
            public boolean test(T elem){
                return true;
            }
        });
    }

    public void setIterationPolicy(Predicate<T> filter){
        this.filter = filter;
    }


    public final ArrIterator iterator(){
        return new ArrIterator();
    }
    
    private final class ArrIterator implements Iterator<T>{
        private int currPlace;

        public ArrIterator(){
            this.currPlace=0;
            this.incrToNextValid();
        }

        public final T next(){
            T valtoReturn = array.get(currPlace);
            this.currPlace++;
            if(this.hasNext()){    
                this.incrToNextValid();
            }
            return valtoReturn;
        }

        public final boolean hasNext(){
            return array.size()>this.currPlace;
        }

        private final void incrToNextValid(){
            while(this.hasNext() && !filter.test(array.get(currPlace))) {
                currPlace++;
            }
        }

    }

    
}
