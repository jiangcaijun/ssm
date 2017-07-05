package cn.test.dao;

import org.junit.Test;

import com.alibaba.druid.filter.config.ConfigTools;


/**
* 
* 明文密码+私钥(privateKey)加密=加密密码
* 
* 加密密码+公钥(publicKey)解密=明文密码
* @author jiangcaijun
* @date 2016年12月16日 下午3:49:35 
*/
public class DruidTest {
	@Test
	public void hello() throws Exception{
		// 密码明文
		String password = "admin";

		System.out.println("密码[ " + password + " ]的加密信息如下：\n");

		String[] keyPair = ConfigTools.genKeyPair(512);
		// 私钥
		String privateKey = keyPair[0];
		// 公钥
		String publicKey = keyPair[1];
		// 用私钥加密后的密文
		password = ConfigTools.encrypt(privateKey, password);

		System.out.println("privateKey:" + privateKey);
		System.out.println("publicKey:" + publicKey);
		System.out.println("password:" + password);
		String decryptPassword = ConfigTools.decrypt(publicKey, password);
		System.out.println("decryptPassword：" + decryptPassword);
	}
}
