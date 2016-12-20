/**
 * Selection.java, you must SUBMIT this file.
 * Do not modify any variable definition
 *
 * Multiple Implementations of Selection sort involving Magic Boxes
 *
 * TODO: Vihar Patel
 * TODO: P17
 * TODO: 10/14/2016
 */
import java.util.*;
public class Selection{
	public MagicBox magicBox = new MagicBox();
	public int count;
	
	/**
	 * sortSelection
	 * A standard selection sort
	 *
	 * @param array: The array to be sorted.
	 */
	public void sortSelection(int[] array) {
        // a for loop to control the number of elements that finish sorting

        for (int left = 0; left < array.length - 1; left++) {
            int right = array.length - 1;
            int min = right; //index of the minimum element
	    
	    	// from last to first unsorted element, find the min
            // and place it into the first unsorted position
            while (right >= left) {
                if(array[right] < array[min]){
				    min = right;
				}
				count++;
			right--;
	    	}
            
			if(min!=left){
				int temp = array[left];
				array[left] = array[min];
				array[min] = temp;
			}
        }

	}
	public int[] padArray(int[] array)
    {
        if(array.length < 8)
        {
            int temp[] = new int[8];
            for(int i = 0; i < temp.length; i++)
            {
                if(i >= array.length)
                    temp[i] = Integer.MAX_VALUE;
                else
                    temp[i] = array[i];
            }
            return temp;
        }
        return null;
    }

    public void eightSortValues(int[] array)
    {
        if (array.length == 8) {
            int[] duplicate = new int[8];
            for (int i = 0; i < array.length; i++)
                duplicate[i] = array[i];
            int[] result = magicBox.eightSort(array);
            for (int i = 0; i < result.length; i++)
                array[i] = duplicate[result[i]];
        }
        else
            return;
    }

    public void copyArray(int[] src, int[] dest)
    {
        if(src.length == dest.length)
        {
            for(int i = 0; i < src.length; i++)
            {
                dest[i] = src[i];
            }
        }
        return;
    }
	/**
	 * TODO: sortSelection8Sort
	 * Selection Sort the array using the 8-Sort Box
	 *
	 * @param array: The array to be sorted.
	 */
	public  void sortSelection8Sort(int[] array) {
        int[] answer = new int[array.length];
		if(array.length == 8)
        {
            eightSortValues(array);
        }

        else if(array.length < 8)
        {
            int[] pArray = padArray(array);
            int[] temp = new int[array.length];
            eightSortValues(pArray);
            for(int i = 0; i < pArray.length; i++)
            {
                if(pArray[i] != Integer.MAX_VALUE)
                    temp[i] = pArray[i];
            }
            copyArray(temp, array);
        }

        else if(array.length > 8)
        {
                int i, j;
                int[] temp;
                for (i=0; i < array.length/2; i++) {
                    for (j=0; j < array.length - 7; j++) {
                        temp = sliceArray(array, j);
                        int[] index = magicBox.eightSort(temp);
                        count++;
                        for (int k = 0; k < 8; k++) {
                            array[k + j] = temp[index[k]];
                        }
                    }
                }
        }
	}

	public int[] sliceArray(int[] src, int start)
    {
        int[] first = new int[8];
        int z = 0;
        for(int i = start; i < 8; i++) {
            first[z] = src[i];
            z++;
            //return normal slice
        }
    return first;
    }

	public void replaceValueAtIndex(int[] array, int index)
    {
        array[index] = Integer.MAX_VALUE;
    }
	/**
	 * TODO: sortSelection8Min
	 * Selection Sort the array using the 8-Min Box
	 *
	 * @param array: The array to be sorted.
	 */
	public void sortSelection8Min(int[] array) {
        if(array.length == 8) {
            int min;
            int answer[] = new int[array.length];
            for(int i = 0; i < array.length; i++)
            {
                min = magicBox.eightMin(array);
                answer[i] = array[min];
                replaceValueAtIndex(array, min);
            }
            copyArray(answer, array);
        }

        else if(array.length < 8) {
            int[] pArray = padArray(array);
            int min;
            int answer[] = new int[pArray.length];
            int temp[] = new int[array.length];
            for (int i = 0; i < pArray.length; i++)
            {
                min = magicBox.eightMin(pArray);
                answer[i] = pArray[min];
                replaceValueAtIndex(pArray, min);
            }
            for(int i = 0; i < answer.length; i++)
            {
                   if(answer[i] != Integer.MAX_VALUE)
                       temp[i] = answer[i];
            }
            copyArray(temp, array);

        }

        else if(array.length > 8)
        {
            int[] answer = new int[array.length];
            int[] minElements = new int[(array.length/8) + 1];
            int[] temp = new int[8];
            int[] slice = new int[8];
            int min;
            int count = 0;
            for(int i = 0; i < array.length; i++)
            {
                if(i % 8 == 0 && i != 0)
                {
                    slice = sliceArray(array, i-8);
                    min = magicBox.eightMin(slice);
                    //minElements[count] = slice[min];
                    //replaceValueAtIndex(array, i);
                    temp[count] = slice[min];

                }
            }
        }

	}
}


/**
 * Selection.java, you should SUBMIT this file.
 * Do not modify any variable definition
 */


