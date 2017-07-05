package cn.test.dao.Test4jdk;

/**
 * Created by jiangcaijun on 2017/6/11.
 */
public class CustomLinkedList {
    private MyNode head=null;
    private MyNode tail=null;
    //增加
    public void add(Object obj)
    {
        if(head==null)
        {
            head=tail=new MyNode(null,obj);
        }
        else
        {
            MyNode temp=new MyNode(null,obj);
            tail.setNext(temp);
            tail=temp;
        }
    }
    public void addFirst(Object obj)
    {
        MyNode temp=new MyNode(head,obj);
        head=temp;
    }
    public void addLast(Object obj)
    {
        add(obj);
    }
    //删除并返回删除的元素
    public Object removeFirst()
    {
        if(head==null)return null;
        Object temp=head.getObj();
        head=head.getNext();
        return temp;
    }
    public Object removeLast()
    {
        if(tail==null)return null;
        Object temp=tail.getObj();
        MyNode node =head;
        while(node.getNext()!=tail)//查询到倒数第二个节点的位置
        {
            node=node.getNext();
        }
        tail=node;
        tail.setNext(null);//将该节点设为尾节点
        return temp;
    }
    //清空链表
    public void clear()
    {
        while(head!=null)
        {
            MyNode temp=head;
            head=head.getNext();
            temp.setNext(null);
        }
        tail=head;
    }
    //获取元素但不删除
    public Object getFirst()
    {
        return head==null?null:head.getObj();
    }
    public Object getLast()
    {
        return tail==null?null:tail.getObj();
    }
    //修改元素
    public Object setFirst(Object obj)
    {
        if(head!=null)
            head.setObj(obj);
        return head==null?null:obj;
    }
    public Object setLast(Object obj)
    {
        if(tail!=null)
            tail.setObj(obj);
        return tail==null?null:obj;
    }
    //复写了Object的toString方法，按照自定义方式打印输出
    public String toString()
    {
        String str="【";
        MyNode temp=head;
        while(temp!=null)
        {
            if(temp!=tail)
                str+=(temp.getObj()+",");
            else
                str+=temp.getObj();
            temp=temp.getNext();
        }
        str+="】";
        return str;
    }
}
