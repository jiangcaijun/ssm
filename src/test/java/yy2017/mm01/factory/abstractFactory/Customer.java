package yy2017.mm01.factory.abstractFactory;

import yy2017.mm01.factory.BMW;

/**
* @Description: 抽象工厂模式：客户类
* @author jiangcaijun
* @date 2017年1月2日 下午4:13:20 
*/
public class Customer {
	public static void main(String[] args) {
		Factory factory = new DefaultFactory();
		BMW BbW320 = factory.createBMW320();
		BbW320.create();
		BMW BbW523 = factory.createBMW523();
		BbW523.create();
	}
}
