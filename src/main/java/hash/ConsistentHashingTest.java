package hash;

import java.util.ArrayList;
import java.util.List;

/**
 * 一致hash测试类
 * @author gaoyuandong
 * @date   2015年8月4日 下午4:49:45
 * @mail   466862016@qq.com
 */
public class ConsistentHashingTest {

	
	public static void main(String[] args) {
		 
		/***
		 * 模拟添加10个节点
		 */
		List<Node> nodeList = new ArrayList<Node>();
		for (int i = 0; i < 10; i++) {
			
			Node node = new Node();
			node.setName("localhost:" + i);
			nodeList.add(node);
		}
		
		ConsistentHashing<Node> consistentHashing = new ConsistentHashing<Node>(4, new MD5HashFunction(), nodeList);
		/***
		 * 模拟200次获取并操作节点情况
		 */
		for (int i = 0; i < 200; i++) {
			Node node = consistentHashing.getNode(i+"");
			if (i == 20) {
				consistentHashing.removeNode(node);
				System.err.println(i + "【删除节点:" + node.getName() +"】");
			} else if(i == 50) {
				consistentHashing.removeNode(node);
				System.err.println(i + "【删除节点:" + node.getName() +"】");
			} else {
				
				System.err.println(i + "【获取节点信息:" + node.getName() +"】");
			}
		}
	}
}
