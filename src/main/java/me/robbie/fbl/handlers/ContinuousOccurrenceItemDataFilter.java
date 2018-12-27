package me.robbie.fbl.handlers;

import java.util.ArrayList;
import java.util.List;

import me.robbie.fbl.common.DataHandler;
import me.robbie.fbl.common.DataHandlerContext;

public class ContinuousOccurrenceItemDataFilter implements DataHandler {

	private Object itemData;
	private int occuranceTimes;
	
	public ContinuousOccurrenceItemDataFilter(Object itemData, int occuranceTimes) {
		this.itemData = itemData;
		this.occuranceTimes = occuranceTimes;
	}

	@Override
	public void handlerAdded(DataHandlerContext context) {
		
	}

	@Override
	public void handlerRemoved(DataHandlerContext context) {
		
	}

	@Override
	public void dataProcessed(DataHandlerContext context, Object[] data) {
		
		if(itemData == null) return;
		
		if(data == null || data.length <= 0) return;
		
		if(!data[0].getClass().isArray()) return;
		
		List<Integer> indexList = new ArrayList<Integer>();
		
		//find the continubous occuring data. eg. 33333333333311111
		for(int m=0; m<data.length; m++) {
			
			Object[] valueArr = (Object[])data[m];
			
			for(int i=0; i<valueArr.length; i++) {
				int counter = 0;
				for(int j=0; j<occuranceTimes; j++) {
					
					if(i+j<valueArr.length && valueArr[i+j] == itemData) {
						counter ++;
					}
				}
				
				if(counter >= occuranceTimes) {
					indexList.add(m);
					break;
				}
			}
		}
		//do filtering
		List<Object> dataList = new ArrayList<Object>();
		
		for(int o=0; o<data.length; o++) {
			if(!indexList.contains(o))
				dataList.add(data[o]);
		}
		
		//foward to next handler
		context.fireDataProcessed(dataList.toArray());
		
	}
}
