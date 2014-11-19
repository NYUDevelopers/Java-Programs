// A Set contains no duplicate elements. That is one of the major reasons to use a set. 
// There are 3 commonly used implementations of Set: HashSet, TreeSet and LinkedHashSet. 
// When and which to use is an important question. In brief, if you need a fast set, you should use HashSet; 
// if you need a sorted set, then TreeSet should be used; if you need a set that can be store the insertion order, 
// LinkedHashSet should be used.


import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class Set {
    
  public static void main(String[] args) {
    
    int[] number = {40, 10, 20 ,20, 30};
    
    System.out.print("Input Array: " + Arrays.toString(number));
    
    Set s = new Set();
    
    // TreeSet example
    TreeSet tree = s.uniqueNO(number);  
    Iterator iterator = tree.iterator();
    System.out.print("\n\nTree set data (Sorted order): ");
 
    while (iterator.hasNext()) {
        System.out.print(iterator.next() + " ");
    }
 
    // HashSet example
    HashSet hs = s.uniqueNO1(number);
    
    System.out.print("\n\nHash set Data (Random Order): ");
    Iterator itor = hs.iterator();
    while (itor.hasNext()) {
        System.out.print(itor.next() + " ");
    }
    
    LinkedHashSet lhs = s.uniqueNO2(number);
    Iterator its = lhs.iterator();
    
    System.out.print("\n\nLinkedHashset Data (Insertion order): ");
    while (its.hasNext()) {
        System.out.print(its.next() + " ");
    }
    
  }
  
  public TreeSet uniqueNO(int[] number) {
    TreeSet<Integer> set = new TreeSet<Integer>();
    
    for (int i =0; i< number.length; i++) {
      
      set.add(new Integer(number[i]));
    }
    
    return set;
  }
  
  public HashSet uniqueNO1(int[] number) {
    HashSet<Integer> hs = new HashSet<Integer>();
    
    for (int i= 0; i<number.length; i++) {
        hs.add(number[i]);
    }
    return hs;
  }
  
  
  public LinkedHashSet uniqueNO2(int[] number) {
    
    LinkedHashSet<Integer> lhs = new LinkedHashSet<Integer>();
    
    for (int i= 0; i<number.length; i++) {
        lhs.add(number[i]);
    }
  return lhs;
  }
}
  
