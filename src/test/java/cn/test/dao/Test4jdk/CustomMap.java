package cn.test.dao.Test4jdk;

/**
 * Created by jiangcaijun on 2017/6/8.
 * 定义一个map的接口
 */
public interface CustomMap<K,V> {
    V put(K key,V value);

    V get (K key);

    int size();

    //定义一个内部接口
    //可以根据Entry对象拿到这个对象的key和value
    interface Entry<K,V>{
        K getKey();
        V getValue();
    }
}