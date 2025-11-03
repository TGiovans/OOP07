package it.unibo.inner.impl;

import it.unibo.inner.api.IterableWithPolicy;
import java.util.Iterator;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{
   
    private final T array[];
    public IterableWithPolicyImpl(T arr[]){
        this.array = arr;
    }

    public void setIterationPolicy(Predicate<T> filter){


    }
    
    private final class ArrIterator<T> implements Iterator<T>{
        private int currPlace;
        private final T arrToIter[];
        public ArrIterator(T arr[]){
            this.currPlace=0;
            this.arrToIter = new Array(arr);
        }
        public final T next(){

            if(this.hasNext()){
                this.currPlace++;
                return this.arrToIter[this.currPlace];
            }else{
                throw new IndexOutOfBoundsException("Iterator has exceeded length.");
            }
            return new T();
        }

        public final boolean hasNext(){
            return arrToIter.length==this.currPlace;
        }

    }
}
