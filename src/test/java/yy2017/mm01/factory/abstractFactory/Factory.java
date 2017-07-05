package yy2017.mm01.factory.abstractFactory;

import yy2017.mm01.factory.BMW;
/**
* @Description: 抽象工厂模式：抽象工厂类
* @author jiangcaijun
* @date 2017年1月2日 下午4:08:33 
*/
public abstract class Factory {
	public abstract BMW createBMW320();

	public abstract BMW createBMW523();
}
