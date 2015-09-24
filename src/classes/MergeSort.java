package classes;

import java.util.*;
import classes.Individual;

public class MergeSort
{
	private static void mergeSort(Individual [ ] a, Individual [ ] tmp, int left, int right)
	{
		if( left < right )
		{
			int center = (left + right) / 2;
			mergeSort(a, tmp, left, center);
			mergeSort(a, tmp, center + 1, right);
			merge(a, tmp, left, center + 1, right);
		}
	}
	
    public static void mergeSort(Individual[] a) {
        Individual tmp[];
        tmp = new Individual[a.length];
        mergeSort(a,tmp, 0, a.length - 1);
    }


    private static void merge(Individual[ ] a, Individual[ ] tmp, int left, int right, int rightEnd )
    {
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;

        while(left <= leftEnd && right <= rightEnd)
            if((a[left].getFitness()) < (a[right].getFitness()))
                tmp[k++] = a[left++];
            else
                tmp[k++] = a[right++];

        while(left <= leftEnd)    // Copy rest of first half
            tmp[k++] = a[left++];

        while(right <= rightEnd)  // Copy rest of right half
            tmp[k++] = a[right++];

        // Copy tmp back
        for(int i = 0; i < num; i++, rightEnd--)
            a[rightEnd] = tmp[rightEnd];
    }
 }