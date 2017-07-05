package cn.test.dao.Multithreading.synTest;

/**
 * Created by jiangcaijun on 2017/5/26.
 */
public class MyThread2 extends Thread {
    private Counter counter;
    @Override
    public void run() {
        counter.test();
    }
    MyThread2 (Counter counter){
        this.counter = counter ;
    }
    public static void main(String []args){
        Counter counter = new Counter();
        for (int i = 0 ; i < 3 ;i++ ){
            Thread thread = new MyThread2(counter);
            thread.start();
        }
        /*输出：
        1
        2
        1
        2
        1
        2*/

        //此时始终是同一个counter对象
    }
}
