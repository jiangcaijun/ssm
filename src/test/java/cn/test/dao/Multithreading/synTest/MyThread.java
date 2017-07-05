package cn.test.dao.Multithreading.synTest;

import org.junit.Test;

/**
 * Created by jiangcaijun on 2017/5/26.
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        Counter counter = new Counter();
        counter.test();
    }
    public static void main(String []args){
        for (int i = 0 ; i < 3 ;i++ ){
            Thread thread = new MyThread();
            thread.start();
        }
        /*输出：
        1
        1
        1
        2
        2
        2*/

        //这是因为synchronized锁住的是持有该方法的对象，即counter
    }
}
