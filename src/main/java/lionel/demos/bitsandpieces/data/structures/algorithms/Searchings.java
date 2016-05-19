package lionel.demos.bitsandpieces.data.structures.algorithms;

import java.util.Arrays;

/**
 * @author lionel ngounou
 */
public class Searchings {
	
    public static int binarySearch(int num, int... numbers){
        System.out.println("Binary search " + num);
        final int length = numbers.length, notfound = -1;
        if(length==0)
            return notfound;
        
        int minIndex = 0, maxIndex = length -1;
        Arrays.sort(numbers);
        System.out.println("Sort numbers ::: " + Arrays.toString(numbers));
        
        while(minIndex!=maxIndex){
            int minNum = numbers[minIndex], maxNum = numbers[maxIndex];
            if(num<minNum || num>maxNum) //not within range
                return notfound;
            if(minNum==num) //matches min
                return minIndex;
            if(maxNum==num) //matches max
                return maxIndex;
            int diff = maxIndex-minIndex;
            if(diff<=1) 
                return notfound; //should have been within range or matched min or max
            int midIndex = maxIndex - (diff/2);
            if(numbers[midIndex]>num)
                maxIndex = midIndex--;
            else minIndex = midIndex;
        }
        return notfound;
    }
}
