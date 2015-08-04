package hash;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/***
 * 
 * @author gaoyuandong
 * @date   2015年8月4日 下午4:09:49
 * @mail   466862016@qq.com
 * @param <T>
 */
public class ConsistentHashing<T> {
	
	/**
	 * 
	 * 虚拟节点默认为4个
	 * 
	 */
	 private  int numberOfReplicas = 4;
	 
	 /***
	  * hash环中的1~2^31的hash环
	  */
	 private final SortedMap<Integer, T> circle = new TreeMap<Integer, T>();
	 
	 private final HashFunction hashFunction;
	 
	 /**
	  * 初始化
	  * @param numberOfReplicas 虚拟节点
	  * @param hashFunction 一致hash的算法
	  * @param nodes hash 环中的节点列表
	  */
	public ConsistentHashing(int numberOfReplicas,HashFunction hashFunction,Collection<T> nodes) {
		
		this.numberOfReplicas = numberOfReplicas;
		this.hashFunction = hashFunction;
		
		for (T t : nodes) {
			addNode(t);
		}
	}
	
	
	/***
	 * 添加一个新的节点
	 * @param t
	 */
	public  void addNode(T t) {
		
		for (int i = 0; i < numberOfReplicas; i++) {
			
			try {
				circle.put(hashFunction.hash(t.toString() +i), t);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/***
	 * 移除已经节点
	 * @param t
	 */
	public void removeNode(T t) {
		
		for (int i = 0; i < numberOfReplicas; i++) {
			try {
				circle.remove(hashFunction.hash(t.toString() +i ));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/***
	 * 获取对应的node
	 * @param key
	 * @return
	 */
	public T getNode(String key) {
		
		if (circle.isEmpty()) {
			return null;
		}
		
		try {
			int hash = hashFunction.hash(key);
			//是否碰撞到
			if (!circle.containsKey(hash)) {
				  SortedMap<Integer, T> tailMap = circle.tailMap(hash);
				  //如果不在碰撞不在范围内将取第一个节点为命中
				 hash = tailMap.isEmpty() == true?circle.firstKey():tailMap.firstKey();
			}
			
			return circle.get(hash);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
