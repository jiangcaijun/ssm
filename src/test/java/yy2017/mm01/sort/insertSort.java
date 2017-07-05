package yy2017.mm01.sort;

/**
* @Description: 直接插入排序，时间复杂度O(n2) 
* 				参考网址：http://www.cnblogs.com/fanyong/archive/2012/03/23/2413553.html
* @author jiangcaijun
* @date 2017年1月4日 下午3:40:28 
*/
public class insertSort {
	public static void main(String[] args) {
		int[] temp = new int[] { 12, 5, 6, 4};
		InsertSort (temp);
		for (int i = 0; i < temp.length; i++) {
			System.out.println(temp[i]);
		}
	}
	public static void InsertSort(int[] arr)
	{
	    int i, j;
	    int n = arr.length;
	    int target;
	 
	    //假定第一个元素被放到了正确的位置上
	    //这样，仅需遍历1 - n-1
	    for (i = 1; i < n; i++)
	    {
	        j = i;
	        target = arr[i];
	 
	        while (j > 0 && target < arr[j - 1])
	        {
	            arr[j] = arr[j - 1];
	            j--;
	        }
	 
	        arr[j] = target;
	    }
	}
}
