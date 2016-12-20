/*
 * WarmUp
 *
 * The class where you will implement all of 
 * the warm up methods for project 2. 
 *
 * You MUST SUBMIT this file.
 * Do NOT modify variable & method defintion.
 * TODO: Vihar Patel
 * TODO: P17
 * TODO: 10-12-2016
 */

import java.util.Arrays;

public class WarmUp {
	private MagicBox magicBox = new MagicBox();
	/**
	 * TODO: min8Min
	 *
	 * returns the minimum element of the given array as determined using 8Min
	 *
	 * @param array - the array of integers being checked
	 * @return min: the value of the least element in array
	 */
	public int arraySliceMin(int[] array, int from, int run)
	{

		int[] minArray = new int[run];
		//int j;
		for(int i = from, j = 0; i < from + run && j < 8; i++, j++)
		{
			if(i >= array.length)
				minArray[j] = Integer.MAX_VALUE;
			else
				minArray[j] = array[i];
		}
		return minArray[magicBox.eightMin(minArray)];
	}

	public int[] arraySliceIndex(int[] array, int from) {
		int[] minArray = new int[8];
		//int j;
		for(int i = from, j = 0; i < from + 8 && j < 8; i++, j++)
		{
			if(i >= array.length)
				minArray[j] = Integer.MAX_VALUE;
			else
				minArray[j] = array[i];
		}
		return minArray;
	}
	public int min8Min(int[] array){
		int min = Integer.MAX_VALUE	;
		int[] newArray = new int[8];
		int len = 0;
		if(array.length %8 != 0 && len >8)
			len = array.length + (8-(array.length%8));
		else
			len = array.length;
		if(array.length == 8) {
			min = array[magicBox.eightMin(array)];
		}
		else if(array.length < 8) {
			min = minLessThan8(array);
		}
		else if(len > 8) {
			int[] temp = new int[len/8];
			while(len != 1)
			{
				for(int i = 0, j = 0; i < len && j < temp.length; i+=8, j++)
					temp[j] = arraySliceMin(array, i, 8);
				len = temp.length;
				if (len != 1) {
					array = Arrays.copyOf(temp, len);
					if(len/8 == 0)
						temp = new int[1];
					else
						temp = new int[len/8];
				}
			}
			min = temp[0];
		}
		return min;
	}



	public int minLessThan8(int[] array)
	{
		int j = 0;
		int[] newArray = new int[8];
		//int min = Integer.MAX_VALUE;
		for(int i = 0; i < array.length; i++)
		{
			newArray[i] = array[i];
			j++;
		}
		for(int i = j; i < newArray.length; i++)
		{
			newArray[i] = Integer.MAX_VALUE;
		}
		return newArray[magicBox.eightMin(newArray)];
	}
	/**
	 * TODO: isSorted8Sort
	 *
	 * checks if the given array is sorted in increasing order using the 8-Sort Magic Box
	 *
	 * @param array - the array of integers being checked
	 * @return: true if sorted, false otherwise
	 */
	public boolean isSorted8Sort(int[] array) {
		int len = array.length;
		int[] min = new int[8];
		for (int i = 0; i < len; i++) {
			min = magicBox.eightSort(arraySliceIndex(array, i));
			for(int j = 0; j < 8; j++)
				if(!(min[j] == j))
					return false;

		}
		return true;
	}

	/**
	 * TODO: isSorted8Min
	 *
	 * checks if the given array is sorted in increasing order using the 8-Min Magic Box
	 *
	 * @param array - the array of integers being checked
	 * @return: true if sorted, false otherwise
	 */
	public boolean isSorted8Min(int[] array){
		int min = Integer.MIN_VALUE;
		int len = array.length;
		for(int i = 0; i < len; i++) {
			min = magicBox.eightMin(arraySliceIndex(array, i));
			if(!(min == 0)) {
				return false;
			}
		}
		return true;
	}


}
