package karlkfi.jtyped;

public interface Sized {
	
	/**
	 * @return the number of elements in this collection (up to Integer.MAX_VALUE)
	 */
	int size();

	/**
	 * @return true if this collection contains no elements
	 */
	boolean isEmpty();

}
