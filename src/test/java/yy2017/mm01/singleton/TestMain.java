package yy2017.mm01.singleton;


/**
* @Description: 对单例模式TestStream的测试
* @author jiangcaijun
* @date 2016年12月29日 上午10:45:01 
*/
public class TestMain {
    
    /**
    * @Title: main
    * @Description: 输出
    * 					
    * 					张孝祥
						非张孝祥
						output message 非张孝祥
						output message 非张孝祥
						创建的是同一个实例
						
		结论：由结果可以得知单例模式为一个面向对象的应用程序提供了对象惟一的访问点，
				不管它实现何种功能，整个应用程序都会同享一个实例对象
    * @param @param args    参数
    * @return void    返回类型
    * @throws
    */
    public static void main(String [] args){
        int pMap[] = new int[26];
        String b = "abcdefg";
        int c = 'a' + 'b';
        pMap[b.charAt(3)-'a']++;
        TestStream s=TestStream.getTest();
        s.setName("张孝祥");
        System.out.println(s.getName());
        TestStream s1=TestStream.getTest();
        s1.setName("非张孝祥");
        System.out.println(s1.getName());
        s.getInfo();
        s1.getInfo();
        if(s==s1){
            System.out.println("创建的是同一个实例");
        }else if(s!=s1){
            System.out.println("创建的不是同一个实例");
        }else{
            System.out.println("application error");
        }
    }
}
