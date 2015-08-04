package hash;

/***
 * hash算法，根据需要自己实现对应的算法
 * @author gaoyuandong
 * @date   2015年8月4日 下午4:14:28
 * @mail   466862016@qq.com
 */
public interface HashFunction {

	int hash(String key) throws Exception;
}
