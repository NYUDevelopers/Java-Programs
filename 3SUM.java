// Given an array of integers, return 3 number in the list that add upto 0.

import java.util.Arrays;

class threeSum {

    int[] arr = {-2,5,9,8,-7,3,-3,-3,-2,5};
    int[] helper = new int[arr.length];
  
    // main method - application start method
    public static void main(String[] args){
        threeSum obj = new threeSum();
        
        // print original array
        System.out.println("Original Array : " + Arrays.toString(obj.arr));
        obj.mergesort(0 ,obj.arr.length-1);
        
        // print sorted array
        System.out.println("Sorted Array : " + Arrays.toString(obj.arr));

        obj.findNumbers(obj.arr);
    }
  
     public void findNumbers(int []arr) {
        int resZero = 0;
        int temp;
        int endIndex = arr.length - 1;
    
        for (int startIndex = 0;startIndex<endIndex - 1; startIndex++) {
            int j = startIndex + 1;
            int k = endIndex;
            
            while (k>=j) {
                if (arr[startIndex] + arr[j] + arr[k] == resZero) {
                    System.out.println("Output:" + arr[startIndex] + "," + arr[j] + "," + arr[k]);
                    k--;
                    j++;
                    while (k>=j && arr[k] == arr[k+1]) {
                        k--;
                    }
                    while (k>=j && arr[j] == arr[j-1]) {
                        j++;
                    }
                } else {
                  temp = ((arr[startIndex] + arr[j] + arr[k]) > 0) ? k-- : j++;
                }
            }
        }
     }
  
    public void mergesort(int low, int high){
        if(low<high){
            int mid = (low+high)/2;
            mergesort(low, mid);
            mergesort(mid+1,high);
            merge(low,high,mid);
        }
    }

    private void merge(int low, int high, int mid) {
        for (int i = low; i <= high; i++) {
              helper[i] = arr[i];
        }
        int i = low;
        int j= mid+1;
        int k = low;
        while(i<=mid && j<=high){
            if(helper[i] < helper[j]){
                arr[k] = helper[i];
                i++;
            }else{
                arr[k] = helper[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
              arr[k] = helper[i];
              k++;
              i++;
            }
    }

}