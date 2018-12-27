package me.robbie.fbl.common;

import java.util.ArrayList;
import java.util.List;

import me.robbie.fbl.handlers.ContinuousOccurrenceItemDataFilter;
import me.robbie.fbl.handlers.LogHandler;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Node Gen Begins!" );
        
        Node root = new Node();
        
        Object[][] nodeValues = new Object[][] {
        	{3, 1, 0},
        	{null, null, 0},
        	{3, 1, 0},
        	{3, 1, null},
        	{3, 1, 0},
        	{3, 1, 0},
        	{3, 1, 0},
        	{3, null, null},
        	{3, 1, 0},
        	{3, 1, null},
        	{3, 1, 0},
        	{3, 1, 0},
        	{3, 1, 0},
        	{3, null, null}
        };
        
        int i = 0;
        root.addChildren(nodeValues, i);
        System.out.println(root);
        List<Node> leafs = root.getLeafNodes();
        
        List<Object> valList = new ArrayList<Object>();
        for(Node leaf : leafs) {
        	List<Object> values = leaf.getCascadingNodeValues();
        	
        	valList.add(values.toArray());
        	
            values.forEach(item -> System.out.print(item + ","));
            System.out.println("");
        }
        
        
        DataPipeline pl = new DefaultDataPipeline();
        pl.addLast("win-33333", new ContinuousOccurrenceItemDataFilter(3, 5));
        pl.addLast("lose-0000", new ContinuousOccurrenceItemDataFilter(0, 4));
        pl.addLast("draw-1111", new ContinuousOccurrenceItemDataFilter(1, 4));
        pl.addLast("log", new LogHandler());
        pl.execute(valList.toArray());
        System.out.println("Total leaf nodes:"+ leafs.size());
    }

//	private static void addChildren(Node node, Object[][] nodeValues,int counter) {
//		
//		if(counter >= nodeValues.length)
//			return;
//		
//		List<Node> subNodes = node.addChildren(nodeValues[counter]);
//		counter++;
//		for(Node subNode : subNodes) {
//			addChildren(subNode, nodeValues, counter);
//		}
//	}

	
}
