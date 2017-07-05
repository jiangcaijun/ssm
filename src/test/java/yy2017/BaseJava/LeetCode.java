package yy2017.BaseJava;

import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;


import yy2017.BaseJava.util.ListNode;

/**
 * @Description: LeetCode刷题
 * @author jiangcaijun
 * @date 2017年1月3日 下午2:43:26
 */
public class LeetCode {

	@Test
	public void mainTest() {
		int nums[] = new int[] { 2, 7, 11, 15 };
		int result[] = twoSum(nums, 9);
		System.out.println("----------twoSum-----------");
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
		System.out.println("----------Add Two Numbers-----------");
		ListNode l1 = new ListNode(2);
		l1.next = new ListNode(5);
		l1.next.next = new ListNode(3);
		
		
		int[] temp = new int[] {5,6,4};
		ListNode l2 = null; 
		for(int i = 0; i < temp.length-1; i++){
			
		}
		ListNode l3 = addTwoNumbers(l1,l1); 
		while ((l2 != null)){
			if (l1 != null) {
//				System.out.println(l1.val);
				l1 = l1.next;
			}
			if (l2 != null) {
//				System.out.println(l2.val);
				l2 = l2.next;
			}
		}
		while ((l3 != null)){
			System.out.println(l3.val);
			l3 = l3.next;
		}

	}

	@Test
	public void exchange() {
		System.out.println("----------数值交换-----------");
		int A = 21, B = 44;
		A = A + B;
		B = A - B;
		A = A - B;
		System.out.println(A + "," + B);
	}

	/**
	 * @Title: twoSum
	 * @Description: https://leetcode.com/problems/two-sum/
	 * 
	 *               Given an array of integers, return indices of the two
	 *               numbers such that they add up to a specific target.
	 * 
	 *               You may assume that each input would have exactly one
	 *               solution.
	 * 
	 *               Example: Given nums = [2, 7, 11, 15], target = 9, Because
	 *               nums[0] + nums[1] = 2 + 7 = 9, return [0, 1].
	 * 
	 * @param @param nums
	 * @param @param target
	 * @param @return 参数
	 * @return int[] 返回类型
	 * @throws
	 */
	public static int[] twoSum(int[] nums, int target) {
		HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
		int[] res = new int[2];
		for (int i = 0; i < nums.length; ++i) {
			if (m.containsKey(target - nums[i])) {
				res[0] = i;
				res[1] = m.get(target - nums[i]);
				// 数值交换
				if (res[1] < res[0]) {
					res[0] = res[0] + res[1];
					res[1] = res[0] - res[1];
					res[0] = res[0] - res[1];
				}
				break;
			}
			m.put(nums[i], i);
		}
		return res;
	}

	
	/**
	* @Title: addTwoNumbers
	* @Description: 
	* 				https://leetcode.com/problems/add-two-numbers/
	* 
	* 				You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

					Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
					Output: 7 -> 0 -> 8
					
	* @param @param l1
	* @param @param l2
	* @param @return    参数
	* @return ListNode    返回类型
	* @throws
	*/
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int carry = 0;

		ListNode newHead = new ListNode(0);
		ListNode p1 = l1, p2 = l2, p3 = newHead;

		while (p1 != null || p2 != null) {
			if (p1 != null) {
				carry += p1.val;
				p1 = p1.next;
			}

			if (p2 != null) {
				carry += p2.val;
				p2 = p2.next;
			}

			p3.next = new ListNode(carry % 10);
			p3 = p3.next;
			carry /= 10;
		}

		if (carry == 1)
			p3.next = new ListNode(1);

		return newHead.next;
	}
    
    /**
    * @Title: longestSubstring
    * @Description: https://leetcode.com/problems/longest-substring-without-repeating-characters/
    * 
    * 
    * 				Longest Substring Without Repeating Characters
    * 
					Given a string, find the length of the longest substring without repeating characters.
					
					Examples:
					
					Given "abcabcbb", the answer is "abc", which the length is 3.
					
					Given "bbbbb", the answer is "b", with the length of 1.
					
					Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, 
					"pwke" is a subsequence and not a substring.
    * @param @param s
    * @param @return    参数
    * @return int    返回类型
    * @throws
    */
    public int longestSubstring(String s) {
    	 int length = s.length();  
         if (length == 0) {  
             return 0;  
         }  
         int [] countTable = new int[256];
//       将指定的 int 值分配给指定 int 型数组的每个元素
         Arrays.fill(countTable, -1);  
         int max = 1;  
         int start = 0;  
         int end = 1;  
//       此方法返回这个字符串的指定索引处的char值。第一个char值的索引为0
         countTable[s.charAt(0)] = 0;  
         while (end < length) {  
             //Has not reached a duplicate char
             if (countTable[s.charAt(end)] >= start) {  
                 start = countTable[s.charAt(end)] + 1;                
             }  
             max = Math.max(max, end - start + 1);  
             countTable[s.charAt(end)] = end;  
             end++;  
         }  
         return max;  
        
    }
}
