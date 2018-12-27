package me.robbie.fbl.common;

public class HeadContext extends AbstractDataHandlerContext {

	public HeadContext(DataPipeline pipeline, String name) {
		super(pipeline, name);
	}

	@Override
	public DataHandler handler() {
		return null;
	}

	@Override
	public void fireDataProcessed(Object[] data) {
		AbstractDataHandlerContext next = this.getNext();
		if(next != null && !(next instanceof TailContext))
			next.fireDataProcessed(data);
	}

}
