package it.unibo.nestedenum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    public enum Month{
        JANUARY(1),
        FEBRUARY(2),
        MARCH(3),
        APRIL(4),
        MAY(5),
        JUNE(6),
        JULY(7),
        AUGUST(8),
        SEPTEMBER(9),
        OCTOBER(10),
        NOVEMBER(11),
        DECEMBER(12);

        private final int monthOrder;

        private Month(int order){
            this.monthOrder=order;
        }

        public static Month fromString(String str){ // READ MONTH SORTER INTERFACE JAVADOC !!!!!!!
            List<Month> months = new ArrayList<>();
            for(Month month : values()){
                if(month.name().toLowerCase().startsWith(str.toLowerCase())){
                    if(month.name().equals(str)){
                        return month;
                    }else{
                        months.add(month);
                    }
                }
            }
            switch(months.size()){
                case 0: throw new IllegalArgumentException("No months fits " + str + ".");
                case 1: return months.getFirst();
                default: String out2 = "";
                for(Month month : months){
                    out2.concat(month.name() + "or ");
                }
                out2.concat(".");
                out2.replace("or .", ".");
                throw new IllegalArgumentException(str + " could refer to either " + out2);
            }    
        }

        public final int getDays(){
            return (this.monthOrder==2)?28:(((this.monthOrder<8&&this.monthOrder%2==1)||(this.monthOrder>7&&this.monthOrder%2==0))?31:30);
        }
    }

    public class sortByDate implements Comparator<String>{
        public final int compare(String str1, String str2){
            Month m1 = Month.fromString(str1);
            Month m2 = Month.fromString(str2);
            return m1.getDays()<m2.getDays() ? -1 : ((m1.getDays()==m2.getDays())? 0 : 1);   
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        return new sortByDate();
    }

    public class sortByMonthOrder implements Comparator <String>{
        public final int compare(String str1, String str2){

            Month m1 = Month.fromString(str1);
            Month m2 = Month.fromString(str2);
            return m1.monthOrder-m2.monthOrder;
        }
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new sortByMonthOrder();
    }
}
