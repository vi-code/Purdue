import java.util.Arrays;
import java.util.Random;

/**
 * Merge.java, you MUST SUBMIT this file.
 *
 * Multiple Implementations of merge sort involving Magic Boxes
 *
 * TODO: VIHAR PATEL
 * TODO: P17
 * TODO: 10/12/2016
 */

public class Merge{
	public MagicBox magicBox = new MagicBox();  
	public int count; //Counter for the number of comparisons in the standard algorithm

	
	/**
	 * sortMerge
	 * A standard merge sort
	 *
	 * @param array: The array to be sorted.
	 */
	public void sortMerge(int[] array) {
		//Implemented merge sort and increment "count" 
		//to keep track of the number of pairwise comparisons
		if (array.length <= 1) return;

		// Split the array in half
		int[] first = new int[array.length / 2];
		int[] second = new int[array.length - first.length];
		System.arraycopy(array, 0, first, 0, first.length);
		System.arraycopy(array, first.length, second, 0, second.length);

		// Sort each half
		sortMerge(first);
		sortMerge(second);

		// Merge the halves together, overwriting the original array
		standardMerge(first, second, array);
	}
   
	/**
	 * standardMerge
	 *
	 * merges two arrays back together for merge sort
	 *
	 * @param a the first array being merged
	 * @param b the second array being merged
	 * @param result the target array where the other two arrays are being merged
	 */
	private void standardMerge(int[] a, int[] b, int[] result) {
		// Merge both halves into the result array
		// Next element to consider in the first array
		int aIndex = 0;
		// Next element to consider in the second array
		int bIndex = 0;

		// Next open position in the result
		int j = 0;
		// As long as neither iFirst nor iSecond is past the end, move the
		// smaller element into the result.

		while (aIndex < a.length && bIndex < b.length) {
			count++;
			if (a[aIndex] < b[bIndex]) {
				result[j] = a[aIndex];
				aIndex++;
			} else {
				result[j] = b[bIndex];
				bIndex++;
			}
			j++;
		}
		// copy what's left
		System.arraycopy(a, aIndex, result, j, a.length - aIndex);
		System.arraycopy(b, bIndex, result, j, b.length - bIndex);
    }




    /**
     * Created a wrapper to define an array 'A' that is passed by reference and used globally throughout the recursive mergesort
     */
    public class ArrayWrapper {
        int[] A; //The array to be sorted
        public ArrayWrapper(int array[]) {
            this.A = array;
        }
    }

	
	
	/**
	 * TODO: sortMerge8Sort
	 * Merge Sort the array using the 8-Sort Box
	 *
	 * @param array: The array to be sorted.
	 */
	public void sortMerge8Sort(int[] array) {
		int start = 0;
		int end = array.length - 1;

        /**
         * If Array is length 8, only 1 call needs to be made
         */
		if (array.length == 8){
            int[] duplicate = new int[8];
            for(int i = 0; i < array.length; i++)
                duplicate[i] = array[i];
			int[] result = magicBox.eightSort(array);
            for(int i = 0; i < result.length; i++)
                array[i] = duplicate[result[i]];
            return;
		}

        /**
         * If Array is less that length 8, only 1 call needs to be made to a padded array of length 8
         */
		if (array.length < 8) {
            int[] slicedArray = new int[8];
            for(int i = 0; i < 8; i++)
            {
                if(i >= array.length)
                    slicedArray[i] = Integer.MAX_VALUE;
                else
                    slicedArray[i] = array[i];

            }
            int[] indexes = new int[8];
            int[] newArray = new int[array.length];
            indexes = magicBox.eightSort(slicedArray);
            for(int i = 0; i < slicedArray.length; i++)
            {
                if(slicedArray[indexes[i]] != Integer.MAX_VALUE){
                    array[i] = slicedArray[indexes[i]];
                }

            }
            return;

        }
        /**
         * If Array is greater than length 8, a recursive mergesort is called
         */
		ArrayWrapper arr = new ArrayWrapper(array);
		standardMerge8(arr, start, end);
        for(int i = 0; i < arr.A.length; i++)
            array[i] = arr.A[i];
		//standardMerge8(array, start, end);

	}

    /**
     * Slice an array and pad it with MAX_VALUE if necessary
     *
     * @param A: The array to be sliced.
     * @param start: The start index of the slice
     * @param end: The end index of the slice
     */
	public int[] slicingVariable(int A[], int start, int end) {

		int[] first = new int[(end - start)+1];
        int z = 0;
		if(((end - start)+1) < 8) {
			for(int i = start ; i <= end; i++)
			{
				if(i >= ((end-start)+1))
					first[z] = Integer.MAX_VALUE;
				else
					first[z] = A[i];
                z++;

			}
			return first;
			// return padded slice
		} else {
			for(int i = start; i < end; i++) {
                first[z] = A[i];
                z++;
            }
			return first;
			//return normal slice
		}

	}

    /**
     * Recursive function that sorts the array using mergesort
     *
     * @param arr: Wrapper object for array 'A'
     * @param start: The start index position
     * @param end: The end index position
     */
	public void standardMerge8(ArrayWrapper arr, int start, int end) {
		if((end - start) > 8) {
			int mid = (start + end) / 2;
            /**
             * The recursive calls
             */
			standardMerge8(arr, start, mid);
			standardMerge8(arr, mid+1, end);

			int[] first = slicingVariable(arr.A, start, mid);
			int[] second = slicingVariable(arr.A, mid+1, end);


			combine8(arr, first, second, start, end);
		}
	}

    /**
     * Function to combine two children array to a parent array
     *
     * @param Wrap: Wrapper object for Array A[]
     * @param aPrime: First half of the sliced array
     * @param bPrime: Second half of the sliced array
     * @param start: The start index position
     * @param end: The end index position
     */
	public void combine8(ArrayWrapper Wrap, int[] aPrime, int[] bPrime, int start, int end)
	{
		int[] newA = new int[aPrime.length];
		int[] newB = new int[bPrime.length];
		if(aPrime.length == 8 && bPrime.length == 8)
		{
			int[] arrayA = new int[aPrime.length];

			arrayA = magicBox.eightSort(aPrime);
			for(int i = 0; i < arrayA.length; i++)
				newA[i] = aPrime[arrayA[i]];

			int[] arrayB = new int[bPrime.length];

			arrayB = magicBox.eightSort(bPrime);
			for(int i = 0; i < arrayB.length; i++)
				newB[i] = bPrime[arrayB[i]];
			myRoutine(Wrap, newA, newB, start, end);
		}

		else
			myRoutine(Wrap, newA, newB, start, end);

	}

    /**
     * Helper function that helps combining two sorted arrays to a single sorted Array
     *
     * @param Wrap: Wrapper object for Array A[]
     * @param first: First half of the sliced array
     * @param second: Second half of the sliced array
     * @param start: The start index position
     * @param end: The end index position
     */

	public void myRoutine(ArrayWrapper Wrap, int[] first, int[] second, int start, int end) {
		int count8one = 0, count8two = 0;
		int firstLen = first.length;
		int secondLen = second.length;

		if (firstLen % 4 != 0)
			firstLen = ((firstLen / 4) * 4) + 4;
		if (secondLen % 4 != 0)
			secondLen = ((secondLen / 4) * 4) + 4;

		int first_toCombine[] = Arrays.copyOf(first, firstLen);
		int second_toCombine[] = Arrays.copyOf(second, secondLen);

		//padding
		for (int i = first.length; i < firstLen; i++) {
			first_toCombine[i] = Integer.MAX_VALUE;
		}

		for (int i = second.length; i < secondLen; i++) {
			second_toCombine[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < second_toCombine.length && i < first_toCombine.length; i += 4) {
			int[] sortingArr = new int[8];
			int ind = 0;

			for (int j = i; j < i + 4; j++) {
				sortingArr[ind] = first_toCombine[j];
				ind++;
				count8one++;
			}
			for (int j = i; j < i + 4; j++) {
				sortingArr[ind] = second_toCombine[j];
				ind++;
				count8two++;
			}

			int sortedIndicies[] = magicBox.eightSort(sortingArr);

			for (int z = 0; z < sortedIndicies.length; z++) {
				if (sortingArr[sortedIndicies[z]] != Integer.MAX_VALUE) {
					Wrap.A[start] = sortingArr[sortedIndicies[z]];
					start++;
				}
			}
		}
		if (count8one < first_toCombine.length) {
			for (int i = count8one; i < first_toCombine.length; i++) {
				if (start == end)
					break;
				Wrap.A[start] = first_toCombine[i];
				start++;
			}
		}

		if (count8two < second_toCombine.length) {
			for (int i = count8two; i < first_toCombine.length; i++) {
				if (start == end)
					break;
				Wrap.A[start] = second_toCombine[i];
				start++;
			}
		}
	}
}
 /* Merge.java, you MUST SUBMIT this file. 
  * Do not modify any variable definition
  */
