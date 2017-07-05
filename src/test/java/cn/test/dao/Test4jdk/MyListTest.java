package cn.test.dao.Test4jdk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangcaijun on 2017/6/11.
 */
public class MyListTest {
    public static void main(String[] args)
    {
        List list =new ArrayList();
        //MyArrayList测试
        //增加
        printLine();
        sop("CustomArrayList方法测试：");
        CustomArrayList mal=new CustomArrayList();
        mal.add(123);
        mal.add("234");
        mal.add(23.23);
        mal.add("345");
        sop(mal.length());
        sop(mal.toString());
        //删除时，如果传入int，那默认是调用remove(int index)而不是remove(Object obj)
        mal.remove(23.23);
        sop(mal.length());
        sop(mal.toString());
        mal.remove("345");
        sop(mal.length());
        sop(mal.toString());
        //按照索引位置修改元素
        mal.set(0,"修改这里");
        sop(mal.toString());
        //获取元素
        sop("索引为0的元素："+mal.get(0));
        sop("索引为1的元素："+mal.get(1));
//        printLine();
        for(int i = 0; i < 100; i++){
            mal.add(i);
        }
        //============================================
        //MyLinkedList测试
        //增加
//        sop("CustomLinkedList方法测试：");
//        CustomLinkedList mll=new CustomLinkedList();
//        mll.add("123");
//        mll.add(23.45);
//        mll.add(true);
//        sop(mll);
//        mll.addFirst("start");
//        mll.addLast("end");
//        sop(mll);
//        //删除头结点，尾节点
//        sop("删除元素："+mll.removeFirst());
//        sop(mll);
//        sop("删除元素："+mll.removeLast());
//        sop(mll);
//        //获取元素
//        sop("头元素："+mll.getFirst());
//        sop("尾元素："+mll.getLast());
//        //清空链表
//        sop("清空链表");
//        mll.clear();
//        sop(mll);
//        //当链表为空时，再取其头尾结点，此处定义成不会报异常会返回null
//        sop("头元素："+mll.getFirst());
//        sop("尾元素："+mll.getLast());
//        //修改头尾结点值，注意此时链表并无数据，因此返回null
//        sop("修改后头元素："+mll.setFirst("head"));
//        sop("修改后尾元素："+mll.setLast("tail"));
//        mll.add("ldy001");mll.add("ldy002");mll.add("ldy003");mll.add("ldy004");mll.add("ldy005");
//        sop(mll);
//        sop("修改后头元素："+mll.setFirst("head"));
//        sop("修改后尾元素："+mll.setLast("tail"));
//        sop(mll);

        ArrayList<String> al = new ArrayList<String>();
        al.add("a");
        al.add("b");
        al.add("b");
        al.add("c");
        al.add("d");

        for (String s : al) {
            if (s.equals("a")) {
                al.remove(s);
            }
        }
    }
    private static void sop(Object obj)
    {
        System.out.println(obj);
    }
    private static void printLine()
    {
        sop("============================================");
    }
}
