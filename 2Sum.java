// Given an array of integers, find two numbers such that they add up to a specific target number.

import java.util.HashMap;

public class 2Sum {
    
  public static void main(String[] args) {
    
     int[] arr = {3,2,5,12,7,15,2,7,2 };
     int target = 9, value;  
     int[] result = new int[2];

     HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
     for(int i=0; i<arr.length; i++) {

        value = target - arr[i]; 

    if (value>0) {
             if (map.containsKey(value)) {
                 map.remove(arr[i]);
                 result[0] =  map.get(value) + 1;
                 result[1] =  i + 1;
                 System.out.println("(" + result[0] +  "," + result[1] +  ")" );
             } else {
                map.put(arr[i], i);
             } 
        }
     }  
    
  }
  
}
  
