package hash;

import java.security.MessageDigest;
/***
 * MD5相关算法
 * @author gaoyuandong
 * @date   2015年8月4日 下午4:22:43
 * @mail   466862016@qq.com
 */
public class MD5HashFunction implements HashFunction {

	/***
	 * 获取hashcode 
	 */
	public int hash(String key) throws Exception {
		
		MessageDigest md5 = null;
		md5 = MessageDigest.getInstance("MD5");
		md5.reset();
		byte[] by = key.getBytes("UTF-8");
		md5.update(by);
		byte[] digest = md5.digest();
		return bytes2Int(digest);
		
	}

	/***
	 * 字节数组转换成整型
	 * @param by
	 * @return
	 */
	private int bytes2Int(byte[] by) {
		int a = 0;
		for(int i =0; i< by.length; i++){
		      a += (by[i]&0xff) << (24-8*i);
		}
		return a;
	}

	
}
