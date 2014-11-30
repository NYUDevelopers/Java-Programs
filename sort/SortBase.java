public class SortBase {
	
	public static int calledTimes = 0;
	public static long runTime = 0L;

	public static void swap(Object [] a, int x, int y) {
		if (x != y) {
			Object o = a[x];
			a[x] = a[y];
			a[y] = o;
		}
	}
}
