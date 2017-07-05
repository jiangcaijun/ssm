package yy2017.mm01.factory.simpleFactory;

import yy2017.mm01.factory.BMW;

/**
* @Description: 简单工厂模式：客户类
* @author jiangcaijun
* @date 2017年1月2日 下午3:42:35 
*/
public class Customer {
	public static void main(String[] args) {
		Factory factory = new Factory();
		BMW bmw320 = factory.createBMW("320");
		BMW bmw523 = factory.createBMW("523");
		bmw320.create();
		bmw523.create();
		System.out.println("######### 我是分割线 #########");
		bmw320 = factory.createBMWByClass("factory.BMW320");
		bmw320.create();
	}
}
