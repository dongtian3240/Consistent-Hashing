package hash;

/***
 * 模拟节点信息实体类
 * @author gaoyuandong
 * @date   2015年8月4日 下午4:39:00
 * @mail   466862016@qq.com
 */
public class Node {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj  instanceof Node) {
			
			Node n = (Node) obj;
			if (n.getName().equals(this.getName())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		
	}
}
