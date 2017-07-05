package yy2017.mm01.factory.simpleFactory;

import yy2017.mm01.factory.BMW;
import yy2017.mm01.factory.BMW320;
import yy2017.mm01.factory.BMW523;


/**
* @Description: 简单工厂模式：工厂类，这是本模式的核心，含有一定的商业逻辑和判断逻辑，用来创建产品
* @author jiangcaijun
* @date 2017年1月2日 下午3:31:30 
*/
public class Factory {
	public BMW createBMW(String key){
		if("320".equals(key)){
			return new BMW320();
		}else if("523".equals(key)){
			return new BMW523();
		}
		return null;
	}
	
	public BMW createBMWByClass(String className){
		try {
			BMW bmw = (BMW) Class.forName(className).newInstance();
			return bmw;
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
