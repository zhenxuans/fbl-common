package me.robbie.fbl.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

public class Node {

	private String nodeID;
	private Object nodeValue;
	private boolean isRoot = false;
	private Node parent;
	private List<Node> children = new ArrayList<Node>();
	
	public String getNodeID() {
		return nodeID;
	}
	
	public void setNodeID(String nodeID) {
		this.nodeID = nodeID;
	}
	
	public Object getNodeValue() {
		return nodeValue;
	}
	
	public void setNodeValue(Object nodeValue) {
		this.nodeValue = nodeValue;
	}
	
	public boolean isRoot() {
		return isRoot;
	}
	
	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public Node getChild(int index) {
		if( this.children != null && index < this.children.size() ) {
			return this.children.get(index);
		}
		
		return null;
	}
	
	public void addChild(Node child) {
		this.children.add(child);
	}
	
	public Node addChild(Object nodeValue) {
		if(nodeValue != null) {
			Node child = new Node("",nodeValue, this, false, null);
			this.children.add(child);
			return child;
		}
		
		return null;
	}
	
	public List<Node> addChildren(Object[] nodeValues) {
		if( nodeValues != null && nodeValues.length >0 ) {
			List<Node> children = new ArrayList<Node>();
			for(Object nodeVal : nodeValues) {
				if(nodeVal != null) {
					children.add(new Node("", nodeVal, this, false, null));
				}
			}
			
			this.children.addAll(children);
			
			return children; 
			
		}
		
		return null;
	}
	
	public void addChildren( Object[][] nodeValues, int counter) {
		if( counter >= nodeValues.length )
			return;
		
		List<Node> subNodes = this.addChildren( nodeValues[counter]);
		counter++;
		for(Node subNode : subNodes) {
			subNode.addChildren( nodeValues, counter );
		}
	}
	
	public int getLevel() {
		if(this.parent != null)
			return this.parent.getLevel() + 1;
		else
			return 0;
	}
	
	public List<Node> getLeafNodes() {
		List<Node> results = new ArrayList<Node>();
		List<Node> children = this.children;
		if(children == null || children.size() == 0)
			results.add(this);
		else {
			for(Node child : children) {
				results.addAll(child.getLeafNodes());
			}
		}
		
		return results;
	}
	
	public List<Object> getCascadingNodeValues() {
		List<Object> values = getCascadingNodeValues0();
		Collections.reverse(values);
		return values;
	}
	
	private List<Object> getCascadingNodeValues0() {
		List<Object> values = new ArrayList<Object>();
		if( this.nodeValue != null ) {
			values.add(this.nodeValue);
		}
		
		if( this.parent != null ) {
			values.addAll( this.parent.getCascadingNodeValues0() );
		}
		
		return values;
	}
	
	public Node(Object nodeValue) {
		this("", nodeValue,null, false, null);
	}
	
	public Node(Object nodeValue, Node parent) {
		this("", nodeValue, parent, false, null);
	}
	
	public Node() {
		this("",null ,null, true, null);
	}
	
	private Node(String nodeID, Object nodeValue, Node parent, boolean isRoot, List<Node> children) {
		if(nodeID == null || nodeID.isEmpty()) {
			this.nodeID = UUID.randomUUID().toString();
		}
		
		this.nodeValue = nodeValue;
		this.parent = parent;
		this.isRoot = isRoot;
		if(children != null && children.size() > 0)
			this.children.addAll(children);
	}
	
	@Override
	public String toString() {
		
		int lvl = this.getLevel();
		
		
		String str = String.format("nodeID:%s,nodeValue:%s,isRoot:%b,level:%d,parenNodeID:%s,childrentNumber:%d\r\n", 
				this.nodeID, 
				this.nodeValue, 
				this.isRoot, 
				lvl,
				this.parent==null?"":this.parent.getNodeID(),
				this.children.size());
		
		String padding = StringUtils.leftPad("", lvl * 10, '-');
		String strAdd = padding + str;
		
		for(Node node : this.children) {
			strAdd += node.toString();
		}
		
		return strAdd;
	}

}
