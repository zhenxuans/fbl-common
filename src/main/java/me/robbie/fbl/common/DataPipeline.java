package me.robbie.fbl.common;

public interface DataPipeline {
	void addLast(String name, DataHandler handler);
	void addFirst(String name, DataHandler handler);
	void execute(Object[] data);
}
