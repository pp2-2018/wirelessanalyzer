package negocio.iface;

public interface Obserbable<T extends Observer> {

	public void addObserver(T o);
	
	public void notifyAllObservers();
	
}
