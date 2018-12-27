package me.robbie.fbl.common;

public class TailContext extends AbstractDataHandlerContext {

	public TailContext(DataPipeline pipeline, String name) {
		super(pipeline, name);
	}

	@Override
	public DataHandler handler() {
		return null;
	}

	@Override
	public void fireDataProcessed(Object[] data) {
		//do nothing
	}

}
