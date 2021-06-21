package nikulin.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Algoruthm {
    public static void main(String[] args) {
        Random random=new Random( );
        int number=random.nextInt(20);
        List<Integer> photoList=new ArrayList<>();
        for (int i=1;i<=9;i++){
            photoList.add(i);
        }
        if (photoList.size()>7){
            ArrayList<Object> futureList = new ArrayList<>();
            int counter=0;
            while (counter< 3){
                futureList.add(photoList.get(counter));
                counter++;
            }
            int middle = photoList.size() / 2;
            if (middle-futureList.size()>=1){
                futureList.add(-1);
            }
            counter=0;
            while (counter<3){
                futureList.add(photoList.get(middle+counter));
                counter++;
            }
            if (photoList.size()-futureList.size()-1>=1){
                futureList.add(-1);
            }
            futureList.add(photoList.get(photoList.size()-1));
            for (Object i:
                 futureList) {
                System.out.print(i);
                System.out.print(" ");
            }

        }
        else{
            for (int i=1;i<number+1; i++) {
                System.out.print(i);
                System.out.print(" ");
            }
        }
    }
}
