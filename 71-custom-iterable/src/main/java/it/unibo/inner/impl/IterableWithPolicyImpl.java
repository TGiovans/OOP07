package it.unibo.inner.impl;

import it.unibo.inner.api.IterableWithPolicy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{
   
    private final List<T> array;
    public IterableWithPolicyImpl(T[] arr){
        this.array = new ArrayList<>();
        for(T el : arr){
            array.add(el);
        }
    }

    public void setIterationPolicy(Predicate<T> filter){


    }

    public final ArrIterator iterator(){
        return new ArrIterator();
    }
    
    private final class ArrIterator implements Iterator<T>{
        private int currPlace;
        public ArrIterator(){
            this.currPlace=0;
        }
        public final T next(){

            if(this.hasNext()){
                T valtoReturn = IterableWithPolicyImpl.this.array.get(currPlace);
                this.currPlace++;
                return valtoReturn;
            }else{
                throw new IndexOutOfBoundsException("Iterator has exceeded length.");
            }
            
        }

        public final boolean hasNext(){
            return array.size()>this.currPlace;
        }

    }
}
