public class BubbleSort extends SortBase {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void sort(Object[] a) {
		calledTimes = 0;
		runTime = System.currentTimeMillis();
				
		if (a == null || a.length <= 1) return;
		
		boolean stop = false;
		
		while (!stop) {
			stop = true;
			for (int i = 0; i < a.length - 1; i++) {
				calledTimes++;
				if (((Comparable)a[i]).compareTo(a[i + 1]) > 0) {
					swap(a, i, i + 1);
					stop = false;
				}
			}
		}
		
		runTime = System.currentTimeMillis() - runTime;
	}
}
