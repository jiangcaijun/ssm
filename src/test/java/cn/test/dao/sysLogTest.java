package cn.test.dao;

import org.junit.Test;

import com.alibaba.druid.filter.config.ConfigTools;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
* 
* 明文密码+私钥(privateKey)加密=加密密码
* 
* 加密密码+公钥(publicKey)解密=明文密码
* @author jiangcaijun
* @date 2016年12月16日 下午3:49:35 
*/
public class sysLogTest {
	public static void main(String args[]) throws Exception
	{
		//监听9876端口
		DatagramSocket serverSocket = new DatagramSocket(514);
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		while(true)
		{
			//构造数据包接收数据
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			//接收数据
			serverSocket.receive(receivePacket);
			//解析数据
			String sentence = new String( receivePacket.getData());
			System.out.println("RECEIVED: " + sentence);
		}
	}
}
