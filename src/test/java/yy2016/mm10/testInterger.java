package yy2016.mm10;

/**
 * Integer valueOf 方法中注明：“本方法对范围在 -128和127之间的值总会缓存。含-128和127这两个数。也可能对超过这个范围的数值进行缓存”
 * 即首先判断是否在这个范围，如果是，则返回缓存中的对象，否则 创建一个对象(享元模式)
 * 
 * @author jiangCaiJun
 *
 */
public class testInterger {
	public static void main(String[] args) {
		
		Integer a = 200; //等同于		Integer a = Integer.valueOf(200);
		Integer b = 200;
		System.out.println(a == b);  //输出false
		
		Integer c = 100;
		Integer d = 100;
		System.out.println(c == d);  //输出true
	}
}
