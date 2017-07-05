package cn.test.dao.Multithreading.synTest;

/**
 * Created by jiangcaijun on 2017/5/26.
 */
public class Counter implements Runnable {
    private int count = 0;
    public int getCount(){
        return count;
    }
    public synchronized void addCount(){
        count ++;
    }

    public synchronized void test() {
        System.out.println("1");
        try {
            Thread.sleep(1000);  //模拟实际线程执行时间。sleep表示依然持有该线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("2");
    }

    @Override
    public void run() {
        System.out.println("Counter的run()");
    }
}
