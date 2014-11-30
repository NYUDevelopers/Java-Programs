public class SortTest {
	
	public static void main(String[] args) {
		Integer [] vs = {2, 5, 1, -7, 3, 9, 12, -5, 4, 8, 99, -2, -3, 55, 88, -9, 44, 13, 6, 7, -99, 100, -100};
		System.out.print("Array(" + vs.length + "): ");
		for (int v : vs) {
			System.out.print(v + " ");
		}
		System.out.println("\n");
		
		// Bubble Sort
//		 BubbleSort.sort(vs);
		
		// Selection Sort
//		 SelectionSort.sort(vs);
		
		// Insertion Sort
//		InsertionSort.sort(vs);
		
		// Merge Sort
//		MergeSort.sort(vs);
		
		// Quick Sort
		QuickSort.sort(vs);
		
		for (int v : vs) {
			System.out.print(v + " ");
		}
		
		System.out.println();
		System.out.println("calledTimes: " + SortBase.calledTimes + ", runTime: " + SortBase.runTime + "ms");
	}
}
