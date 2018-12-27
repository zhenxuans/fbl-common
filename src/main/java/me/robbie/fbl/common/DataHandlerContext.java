package me.robbie.fbl.common;

public interface DataHandlerContext {
	
	String name();
	
	DataHandler handler();
	
	void fireDataProcessed(Object[] data);
}
