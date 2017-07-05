package yy2016.mm10;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

/**
 * 文件内容修改，md5值将会改变；文件名修改（包括后缀），MD5不会变
 * 
 * @author jiangCaiJun
 * 
 */
public class md5 {
	
	 public static String getMd5ByFile(File file) throws FileNotFoundException {
	        String value = null;
	        FileInputStream in = new FileInputStream(file);
		try {
		    MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
		    MessageDigest md5 = MessageDigest.getInstance("MD5");
		    md5.update(byteBuffer);
		    BigInteger bi = new BigInteger(1, md5.digest());
		    value = bi.toString(16);
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
	            if(null != in) {
		            try {
			        in.close();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
		    }
		}
		return value;
	    }
	 
	public static void main(String[] args) throws IOException {
		
		String path="D:\\test2.txt";
		
		String v = getMd5ByFile(new File(path));
		System.out.println("MD5:"+v.toUpperCase());
		
		//test.java
		//49F0BAD299687C62334182178BFD75D8
		//68B0F8B78FC3C668A59434C1889DA270
		//D90670485B533048948B80C6D1819E4D
		
		//test2.java
		//D90670485B533048948B80C6D1819E4D
		
		//test2.txt
		//D90670485B533048948B80C6D1819E4D
		
	}

}

