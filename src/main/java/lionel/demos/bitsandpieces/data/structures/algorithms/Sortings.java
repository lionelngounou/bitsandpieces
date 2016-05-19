package lionel.demos.bitsandpieces.data.structures.algorithms;

import java.util.Arrays;

/**
 * @author lionel.ngounou
 */
public final class Sortings {
	
	public static final void sort(int[] numbers, boolean asc){
		if(numbers==null || numbers.length==1)
			return; //no need to sort
		final int order = asc? 1 : -1; //asc or descending
		for(int i = 0; i < numbers.length; i++) {
			final int n0 = numbers[i];
			int swapIndex = i;
			for (int j = i+1; j < numbers.length; j++) {
				if((numbers[j] * order) < (numbers[swapIndex] * order))
					swapIndex = j;
			}
			numbers[i] = numbers[swapIndex];
			numbers[swapIndex] = n0;
		}
	}
    	
	public static final void sort(int[] numbers){
		sortAsc(numbers);
	}
	
	public static final void sortDesc(int[] numbers){
		sort(numbers, false);
	}
	
	public static final void sortAsc(int[] numbers){
		sort(numbers, true);
	}
	
	public static void main(String[] args) {
		int[] numbers = {7,1,2,34,0,5,2,1,6,6,6,4,3};
		System.out.println(Arrays.toString(numbers));
		sort(numbers);
		System.out.println(Arrays.toString(numbers));
	}
}
