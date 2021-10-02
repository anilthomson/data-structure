package sort;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Java program for implementation of QuickSort 
class QuickSort 
{ 
	/* This function takes last element as pivot, 
	places the pivot element at its correct 
	position in sorted array, and places all 
	smaller (smaller than pivot) to left of 
	pivot and all greater elements to right 
	of pivot */
	int getActualPivotIndex(int arr[], int low, int high) 
	{ 
		int pivotValue = arr[high]; 
		int pivotIndex = low; // index of smaller element 
		for (int i=low; i<high; i++) 
		{ 
			// If current element is smaller than the pivot 
			if (arr[i] < pivotValue) 
			{

				// swap value in pivotIndex and current Element
				int temp = arr[pivotIndex]; 
				arr[pivotIndex] = arr[i];  //swap current Element with element in pindex
                arr[i] = temp; 
                pivotIndex++;
			} 
		} 

		//Place the pivotValue at pivotIndex by swapping 
		int temp = arr[pivotIndex]; 
		arr[pivotIndex] = pivotValue; 
		arr[high] = temp; 

		return pivotIndex; 
	} 


	/* The main function that implements QuickSort() 
	arr[] --> Array to be sorted, 
	low --> Starting index, 
	high --> Ending index */
	void sort(int arr[], int low, int high) 
	{ 
		if (low < high) 
		{ 
			/* pi is partitioning index, arr[pi] is 
            now at right place */
           
			int pi = getActualPivotIndex(arr, low, high); 
            System.out.println(pi);
			// Recursively sort elements before 
			// partition and after partition 
			sort(arr, low, pi-1); 
			sort(arr, pi+1, high); 
		} 
	} 

	/* A utility function to print array of size n */
	static void printArray(int arr[]) 
	{ 
		int n = arr.length; 
		for (int i=0; i<n; ++i) 
			System.out.print(arr[i]+" "); 
		System.out.println(); 
	} 

	// Driver program 
	public static void main(String args[]) 
	{ 
		int arr[] = {10, 7, 8, 9, 1, 5,8}; 
		int n = arr.length; 

		QuickSort ob = new QuickSort(); 
		ob.sort(arr, 0, n-1); 

		System.out.println("sorted array"); 
		printArray(arr); 
		 
		 
	} 
} 
/*This code is contributed by Rajat Mishra */
