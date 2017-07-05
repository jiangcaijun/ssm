package yy2017.mm01.factory.factoryMethod;


import yy2017.mm01.factory.BMW;
import yy2017.mm01.factory.BMW320;

/**
* @Description: 工厂方法模式 :具体工厂角色：它含有和具体业务逻辑有关的代码。由应用程序调用以创建对应的具体产品的对象。 
* @author jiangcaijun
* @date 2017年1月2日 下午3:57:13 
*/
public class FactoryBMW320 implements Factory{

	@Override
	public BMW createBMW() {
		return new BMW320();
	}
}
