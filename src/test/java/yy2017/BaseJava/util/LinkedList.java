package yy2017.BaseJava.util;


/**
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiangcaijun
* @date 2017年1月8日 下午5:39:32 
*/
public class LinkedList {
	Node head=null;//创建一个空链表,头结点
	Node last=head;//尾结点
	  
    /* 非递归方法有一个致命的缺陷，打印的同时改变了头结点的位置，所以我们应该倾向于使用递归方法。*/
     
     /** 
     * 非递归打印元素的方法 
     */
     public void print(Node head){  
         while(head!=null){  
             System.out.println(head.obj);  
             head=head.next;//索引向后移位  
         }  
     }  
     /** 
      * 递归打印链表元素的方法 
      */
     public void printNode(Node head){  
         if(head!=null){  
             System.out.println(head.obj);  
             Node node=head.next;  
             printNode(node);//递归调用  
         }  
     }
     /** 
      * 向指定链表添加元素的方法 
      * @param obj   插入的元素 
     */
     public void add(Object obj){  
         Node node=new Node(obj);//新建结点   
         if(head==null){//如果链表为空  
             head = node;  
         }else{  
             last.next=node;//先把新增结点放在最后  
         }  
         last=node;//再把最后一个结点向后移位  
     }
     /** 
      * 向链表中插入新元素的方法 
      * 如果我们要在结点A之后插入一个结点，那么就还需要修改结点A的next引用，
      * 实际上就是让A结点的next引用指向新增结点的元素域，然后再让新增结点的next引用指向A原本next结点(B)的元素域，
     */
     public void insert(int index,Object obj){  
         Node node=head;  
         int j=0;  
         while(node!=null&&j<index-2){  
             //查找到第index-1个元素  
             node=node.next;  
             j++;  
         }  
         Node sert=new Node(obj);//被插入的结点  
         sert.next=node.next;  
         node.next=sert;  
     }
     /** 
      * 删除指定位置的结点 
      * 比如说我们要删除一个结点b，就是要使这个结点失去引用，但是注意不要直接写b=b.next,这样的话b的引用还是存在
      * @param index 索引 
      */
     public void delete(int index){  
         Node node=head;  
         int j=0;  
         while(node!=null&&j<index-2){  
             //查找到第i-1个元素  
             node=node.next;  
             j++;  
         }  
         node.next=node.next.next;//删除第index个元素  
     }
     /** 
     * 改变指定位置的元素 
      * @param index 索引 
      * @param obj    
      */
     public void modify(int index,Object obj){  
         Node node=head;  
         int j=0;  
         while(node!=null&&j<index-1){  
             //找到第index个结点  
             node=node.next;  
             j++;  
         }  
         node.obj=obj;  
     }
}
