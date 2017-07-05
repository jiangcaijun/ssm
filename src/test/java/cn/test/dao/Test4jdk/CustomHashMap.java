package cn.test.dao.Test4jdk;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiangcaijun on 2017/6/8.
 * 定义一个customMap的实现类
 */
public class CustomHashMap<K,V> implements CustomMap<K,V> {

    private static final int MAXIMUM_CAPACITY = 1 << 30;

    private static Integer defaultLength = 16;//定义数组长度（定义成2的倍数）

    private static float defaultLoad=0.75F;//定义负载因子(超过这个因子就会扩容)

    private Entry<K,V>[] table =null;//定一个数组，盛放Entry对象

    private int size=0;//定义一个常量，用来记录hashmap元素个数

    public CustomHashMap(){
        this(defaultLength,defaultLoad);
    }
    public CustomHashMap(int length, float load){
        if(length < 0){
            throw new IllegalArgumentException("Illegal length" + length);
        }
        if(load > 0.0F && !Float.isNaN(load)) {
            this.defaultLoad = load;
            this.defaultLength = length;
            table = new Entry[defaultLength];
        } else {
            throw new IllegalArgumentException("Illegal load: " + load);
        }
    }
    @Override
    public V put(K key, V value) {
        int index = getIndex(key,table.length);
        Entry<K,V> entry = table[index];
        if(entry == null){
            table[index] = new Entry(key,value,null,index);
        }else if(entry != null){
            table[index] = new Entry(key,value,entry,index);
        }
        size++;
        if(size > defaultLength*defaultLoad){
            resize();
        }
        return table[index].getValue();
    }


    @Override
    public V get(K key) {
        int index = getIndex(key,table.length);
        if(table[index] == null){
            return null;
        }
        return foundValueByKey(key,table[index]);
    }

    private V foundValueByKey(K key, Entry<K, V> entry) {
        if( key == entry.getKey() || key.equals(entry.getKey())){
            return entry.getValue();
        }else if(entry.next != null){
            return foundValueByKey(key,entry.next);
        }
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    class Entry<K,V> implements CustomMap.Entry<K, V>{
        K key;
        V value;
        Entry<K,V> next;

        int index;//记录下标

        Entry(K k,V v,Entry<K,V> n,int inx){
            key=k;
            value=v;
            index=inx;
            next=n;//数组第一个元素的下一个元素
        }

        public K getKey(){
            return key;
        }

        public V getValue(){
            return value;
        }
    }
    private int getIndex(K key, int length){
        int m = length -1 ;
        return key.hashCode() & m; //数值上等于key.hashCode() % length
    }
    //hashmap扩容
    private void resize() {
        Entry<K,V>[] newTable = new Entry[2*defaultLength];
        transfer(newTable);
    }

    private void transfer(Entry<K, V>[] newTable) {
        System.out.println("transfer 扩容");
        Entry[] src = table;                   //src引用了旧的Entry数组
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; j++) { //遍历旧的Entry数组
            Entry<K,V> e = src[j];             //取得旧Entry数组的每个元素
            if (e != null) {
                src[j] = null;//释放旧Entry数组的对象引用（for循环后，旧的Entry数组不再引用任何对象）
                do {
                    Entry<K,V> next = e.next;
                    int i = getIndex(e.key, newCapacity); //重新计算每个元素在数组中的位置
                    e.next = newTable[i]; //newTable[i]的引用赋给了e.next，也就是使用了单链表的头插入方式，同一位置上新元素总会被放在链表的头部位置；这样先放在一个索引上的元素终会被放到Entry链的尾部(如果发生了hash冲突的话)；
                    newTable[i] = e;      //将元素放在数组上
                    e = next;             //访问下一个Entry链上的元素
                } while (e != null);
            }
        }
        table = newTable;
        defaultLength = 2*defaultLength;  //更新数组长度
    }
    public static void main (String[] args){
//        Map map =new HashMap();
        CustomHashMap<String,String> map = new CustomHashMap<String,String>();
        for(int i = 0; i < 100; i++){
            map.put(i+"",i+"");
        }
        for(int i = 0; i < 100; i++){
            System.out.println("map取值： "+ i + " = "+map.get(i+""));
        }
    }
}