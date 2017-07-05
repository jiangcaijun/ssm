package yy2017.BaseJava.util;

public class ListNode {

	public int val;

	public ListNode next;

	public ListNode(int x) {
		val = x;
	}

	public static void main(String[] args) {
		ListNode head = null; // 头节点

		int[] temp = new int[] { 5, 6, 4};

		for (int i = 0; i < temp.length; i++) {
			ListNode newNode = new ListNode(temp[i]);// 实例化一个节点
			if (head == null) {
				head = newNode;
				continue;
			}
			ListNode tmp = head;
			while (tmp.next != null) {
				tmp = tmp.next;
			}
			tmp.next = newNode;
		}

		ListNode tmp2 = head;
		while (tmp2 != null) {
			System.out.println(tmp2.val);
			tmp2 = tmp2.next;
		}
	}
}
