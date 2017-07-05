package yy2017.mm01.factory.abstractFactory;

import yy2017.mm01.factory.BMW;
import yy2017.mm01.factory.BMW320;
import yy2017.mm01.factory.BMW523;

/**
* @Description: 抽象工厂模式：具体工厂类
* @author jiangcaijun
* @date 2017年1月2日 下午4:09:45 
*/
public class DefaultFactory extends Factory{

	@Override
	public BMW createBMW320() {
		return new BMW320();
	}

	@Override
	public BMW createBMW523() {
		return  new BMW523();
	}

}
