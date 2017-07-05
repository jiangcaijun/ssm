package yy2017.mm01.factory;


/**
* @Description: 具体产品角色：工厂类所创建的对象就是此角色的实例。在Java中由一个具体类实现
* @author jiangcaijun
* @date 2017年1月2日 下午3:44:20 
*/
public class BMW320 implements BMW{

	@Override
	public void create() {
		System.out.println("----------create BMW320---------");
	}

}
