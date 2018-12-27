package me.robbie.fbl.common;

public class DefaultDataPipeline implements DataPipeline {

	private final AbstractDataHandlerContext headContext;
	private final AbstractDataHandlerContext tailContext;
	
	public DefaultDataPipeline() {
		headContext = new HeadContext(this, "HEAD_CONTEXT");
		tailContext = new TailContext(this, "TAIL_CONTEXT");
		headContext.setNext(tailContext);
		tailContext.setPrev(headContext);
	}

	@Override
	public void addLast(String name, DataHandler handler) {
		
		if(handler != null) {
			DefaultDataHandlerContext newCtx = new DefaultDataHandlerContext(
					this, 
					name.isEmpty()?handler.getClass().getName():name, 
					handler
					);
			
			newCtx.setPrev(tailContext.getPrev());
			newCtx.setNext(tailContext);
			tailContext.getPrev().setNext(newCtx);
			tailContext.setPrev(newCtx);
			
			handler.handlerAdded(newCtx);
		}
	}

	@Override
	public void addFirst(String name, DataHandler handler) {
		if(handler != null) {
			DefaultDataHandlerContext newCtx = new DefaultDataHandlerContext(
					this, 
					name.isEmpty()?handler.getClass().getName():name, 
					handler
					);
			
			newCtx.setPrev(headContext);
			newCtx.setNext(headContext.getNext());
			headContext.getNext().setPrev(newCtx);
			headContext.setNext(newCtx);
			
			handler.handlerAdded(newCtx);
		}
	}

	@Override
	public void execute(Object[] data) {
		this.headContext.fireDataProcessed(data);
		
	}

}
