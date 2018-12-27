package me.robbie.fbl.common;

public interface DataHandler {
	
	void handlerAdded(DataHandlerContext context);
	
	void handlerRemoved(DataHandlerContext context);
	
	void dataProcessed(DataHandlerContext context, Object[] data) throws Exception;
}
