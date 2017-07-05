package cn.test.dao.Test4jdk;

/**
 * Created by jiangcaijun on 2017/6/11.
 */
public class MyNode
{
    private MyNode next=null;
    private Object obj=null;
    MyNode(MyNode next,Object obj)
    {
        this.next=next;
        this.obj=obj;
    }
    public Object getObj()
    {
        return obj;
    }
    public MyNode getNext()
    {
        return next;
    }
    public void setObj(Object obj)
    {
        this.obj=obj;
    }
    public void setNext(MyNode next)
    {
        this.next=next;
    }
}