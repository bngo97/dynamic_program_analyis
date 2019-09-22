import java.util.Arrays;

public class SortTools {
	/**
	  * This method tests to see if the given array is sorted.
	  * @param x is the array
	  * @param n is the size of the input to be checked
	  * @return true if array is sorted
	  */
	public static boolean isSorted(int[]x, int n){
		if(n <= 0 || x.length == 0 || x.length < n){
			return false;
		}

		for(int i = 1; i<n;i++){
			if(x[i]<x[i-1]){
				return false;
			}
		}
		return true;
	}
	/**
	 * This method returns the index of value v within array x.
	 * @param x is the array
	 * @param n is the size of the input to be checked
	 * @param v is the value to be searched for within x
	 * @return index of v if v is contained within x. -1 if v is not contained within x
	 */
	public static int find(int[] x, int n, int v) {
		int left = 0;
		int right = n-1;

		while(left <= right){
			int mid = left + (right - left)/2;
			int midVal = x[mid];
			if(midVal == v)
				return mid;
			if(midVal < v){
				left = mid + 1;
			}else{
				right = mid - 1;
			}
		}
		return -1;

	}
	/**
	 * This method returns a newly created array containing the first n elements of x, and v.
	 * @param x is the array
	 * @param n is the number of elements to be copied from x
	 * @param v is the value to be added to the new array
	 * @return a new array containing the first n elements of x, and v
	 */
	public static int[] insertGeneral(int[] x, int n, int v){
		int index = 0;
		// find index
		while((index < n) && (x[index] < v)){
			index++;
		}
		// already in
		if(index != n && x[index] == v){
			return Arrays.copyOf(x,n);
		}

		int[] result = new int[n+1];
		result[index] = v;

		// i -> index of x ;
		// j -> index of result ;
		for(int i = 0, j = 0; i < n; i++, j++){

			if(j == index){
				j++;
			}
			result[j] = x[i];
		}

		return result;
	}
	/**
	 * This method inserts a value into the array and ensures it's still sorted
	 * @param x is the array
	 * @param n is the number of elements in the array
	 * @param v is the value to be added
	 * @return n if v is already in x, otherwise returns n+1
	 */
	public static int insertInPlace(int[] x, int n, int v){
		int index = 0;
		// find index
		while((index < n) && (x[index] < v)){
			index++;
		}
		// val already in
		if(x[index] == v){
			return n;
		}

		// inplace insert backwards
		for(int i = n ; i >= 0; i--) {

			if(i > index){
				x[i] = x[i-1];
			}else if(i == index){
				x[index] = v;
			}else{
				break;
			}
		}

		return n+1;
	}
	/**
	 * This method sorts a given array using insertion sort
	 * @param x is the array to be sorted
	 * @param n is the number of elements of the array to be sorted
	 */
	public static void insertSort(int[] x, int n){
		if(x.length < n){
			return;
		}

		for(int i = 1; i < n; i++){
			int j = i - 1;
			int val = x[i];

			// insert into previous array
			while( j >= 0 && x[j] > val){
				x[j+1] = x[j];
				j--;
			}

			x[j+1] = val;
		}
	}
}
