package thread;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

@SuppressWarnings("serial")
public class ForkSort extends RecursiveAction
{
	final int threshold = 100;
	final int[] array;
	final int low, high;
	
	public ForkSort(int[] array, int low, int high) 
	{
		this.array = array;
		this.low = low;
		this.high = high;
	}
	
	@Override
	protected void compute() 
	{
		if((high - low) <= threshold)
		{
			SortSequentially(array, low, high);
		}
		else
		{
			int mid = low + (high-low)/2;
			invokeAll(new ForkSort(array, low, mid), new ForkSort(array, mid+1, high));
			merge(low,mid,high);
		}
	}
	
	private void merge(int low, int mid, int high)
	{
		int[] tempArray = Arrays.copyOfRange(array, low, mid+1);
		for(int i=0, j=mid+1, k=low;i<tempArray.length;k++)
		{
			array[k] = (j==high || tempArray[i] < array[j]) ? tempArray[i++] : array[j++]; 
		}
	}
	
	private void SortSequentially(int[] array, int low, int high)
	{
		Arrays.sort(array,low,high);
	}
	
	public static void main(String[] args)
	{
		int[] array = new int[10000000];
		
		for (int i = 0; i < array.length; i++)
			array[i] = (int)(Math.random()*10000000);
		
		ForkJoinPool pool = new ForkJoinPool(3);
		
		System.out.println(LocalDateTime.now());
		
		pool.invoke(new ForkSort(array, 0, array.length));
		//Arrays.sort(array);
		
		System.out.println(LocalDateTime.now());

	}
}