package cn.test.dao.Test4jdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jiangcaijun on 2017/6/11.
 */
public class CustomArrayList{
    List list = new ArrayList();
    //设置arrayList默认容量
    private static final int DEFAULT_CAPACITY = 10;
    //保存数据的数组
    private Object[] value=null;
    //arrayList的实际元素数量
    private int size=0;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    CustomArrayList()
    {
        value=new Object[DEFAULT_CAPACITY];
    }
    //增加
    public boolean add(Object obj)
    {
        if(size==value.length){  //超出了数组可容纳的长度，需要进行动态扩展
            grow(size + 1);
        }
        value[size++]=obj;
        return true;
    }
    //删除
    public boolean remove(int index)
    {
        if(index<0||index>=size)
            return false;
        Object[] temp=new Object[value.length];//使用到数组长度的地方要由size来替代，因为size才是真实的元素个数
        for(int i=0,j=0;i<size;i++)
        {
            if(i!=index)
            {
                temp[j]=value[i];
                j++;
            }
        }
        value=temp;
        size--;
        return true;
    }
    //内部调用remove(int index)
    public boolean remove(Object obj)
    {
        for(int i=0;i<size;i++)
        {
            if(value[i].equals(obj))
            {
                remove(i);
                return true;
            }
        }
        return false;
    }
    //修改
    public boolean set(int index,Object obj)
    {
        if(index<0||index>=size)
            return false;
        value[index]=obj;
        return true;
    }
    //获取
    public Object get(int index)
    {
        if(index<0||index>=size)
            return null;
        return value[index];
    }
    //长度
    public int length()
    {
        return size;
    }

    //复写toString方法，使得该类被打印时按照自定义方式输出
    public String toString()
    {
        String str="【";
        for(int i=0;i<size;i++)
        {
            if(i!=size-1)
                str+=(value[i]+",");
            else
                str+=value[i];
        }
        str+="】";
        return str;
    }
    //扩容
    private void grow(int minCapacity){
        System.out.println("扩容");
        int oldCapacity = value.length;
        int newCapacity = (oldCapacity*3)/2 + 1;
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        value = Arrays.copyOf(value, newCapacity);
    }
    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }
}
