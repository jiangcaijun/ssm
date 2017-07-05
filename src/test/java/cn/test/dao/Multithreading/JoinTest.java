package cn.test.dao.Multithreading;

/**
 * Created by jiangcaijun on 2017/5/25.
 * 参考链接：http://www.cnblogs.com/jfqiu/p/3264045.html
 * t1、t2线程并行
 */
public class JoinTest
{

    public static void main(String[] args) throws InterruptedException
    {
        System.out.println("main start");

        Thread t1 = new Thread(new Worker("thread-1"));
        Thread t2 = new Thread(new Worker("thread-2"));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("main end");
    }
}

class Worker implements Runnable
{

    private String name;

    public Worker(String name)
    {
        this.name = name;
    }

    @Override
    public void run()
    {
        for (int i = 0; i < 10; i++)
        {
            try
            {
                Thread.sleep(1l);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println(name + " " + i);
        }
    }

}
