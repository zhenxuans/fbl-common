package me.robbie.fbl.handlers;

import me.robbie.fbl.common.DataHandler;
import me.robbie.fbl.common.DataHandlerContext;

public class LogHandler implements DataHandler {

	public LogHandler() {
	}

	@Override
	public void handlerAdded(DataHandlerContext context) {
		
	}

	@Override
	public void handlerRemoved(DataHandlerContext context) {
		
	}

	@Override
	public void dataProcessed(DataHandlerContext context, Object[] data) {
		System.out.println("Data logging begins");
		for(Object item : data) {
			if(item.getClass().isArray()) {
				Object[] itemArr = (Object[])item;
				for(Object val : itemArr) {
					System.out.print(val + ",");
				}
				System.out.println("");
			} else {
				System.out.println(item);
			}
		}
		
		System.out.println("Total size of data is: " + data.length);
	}

}
