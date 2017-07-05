package yy2017.mm01.singleton;


/**
* @Description: 单例模式
* 				参考网址;http://www.cnblogs.com/whgw/archive/2011/10/05/2199535.html
* @author jiangcaijun
* @date 2016年12月29日 上午10:44:24 
*/
public class TestStream {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    } 
    //该类只能有一个实例
    private TestStream(){}    //私有无参构造方法
    //该类必须自行创建
    //有2种方式
    /*private static final TestStream ts=new TestStream();*/
    private static TestStream ts1=null;
    //这个类必须自动向整个系统提供这个实例对象
    public static TestStream getTest(){
        if(ts1==null){
            ts1=new TestStream();
        }
        return ts1;
    }
    public void getInfo(){
        System.out.println("output message "+name);
    }
}
