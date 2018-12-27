package me.robbie.fbl.common;

public class DefaultDataHandlerContext extends AbstractDataHandlerContext {

	private DataHandler handler;
	
	public DefaultDataHandlerContext(DataPipeline pipeline, String name, DataHandler handler) {
		super(pipeline, name);
		this.handler = handler;
	}

	@Override
	public DataHandler handler() {
		return handler;
	}

	

}
