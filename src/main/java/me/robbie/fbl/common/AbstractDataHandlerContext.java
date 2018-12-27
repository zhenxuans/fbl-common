package me.robbie.fbl.common;

public abstract class AbstractDataHandlerContext implements DataHandlerContext {

	protected String name;
	protected AbstractDataHandlerContext prev;
	protected AbstractDataHandlerContext next;
	protected DataPipeline pipeline;
	protected boolean isDataProcessed = false;
	
	public AbstractDataHandlerContext(DataPipeline pipeline, String name) {
		this.name = name;
		this.pipeline = pipeline;
	}
	
	public AbstractDataHandlerContext getPrev() {
		return prev;
	}

	public void setPrev(AbstractDataHandlerContext prev) {
		this.prev = prev;
	}

	public AbstractDataHandlerContext getNext() {
		return next;
	}

	public void setNext(AbstractDataHandlerContext next) {
		this.next = next;
	}
	
	@Override
	public String name() {
		return name;
	}

	@Override
	public abstract DataHandler handler();

	@Override
	public void fireDataProcessed(Object[] data) {
		if(!isDataProcessed) {
			isDataProcessed = true;
			try {
				this.doDataProcessing(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		AbstractDataHandlerContext next = this.getNext();
		if(next != null && !(next instanceof TailContext)) {
			next.fireDataProcessed(data);
		}
	}
	
	private void doDataProcessing(Object[] data) throws Exception {
		if(this.handler() != null) {
			this.handler().dataProcessed(this, data);
		}
	}
}
