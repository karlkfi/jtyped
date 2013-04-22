package karlkfi.jtyped.map;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import karlkfi.jtyped.TypedObject;
import karlkfi.jtyped.TypedSupplier;

/**
 * An object that maps typed keys to typed values. A map cannot contain duplicate keys; each key can map to at most one
 * value.
 * 
 * @param <K> the key type
 */
public interface MutableTypedMap<K> extends TypedMap<K> {

	/**
	 * Associates the specified value with the specified key in this map. If the map previously contained a mapping for
	 * the key, the old value is replaced by the specified value. (A map m is said to contain a mapping for a key k if
	 * and only if m.containsKey(k) would return true.)
	 * 
	 * As constrained by the genrics, the value must be assignable to the key type.
	 * 
	 * Type Consistency: Once a type has been set for a value it cannot be changed. Thus any key used before will still
	 * be usable after a new value is put into the map.
	 * 
	 * @param <TT> the most specific type that can be requested for this value
	 * @param typedKey a typed key with which the specified value is to be associated
	 * @param value a new value to be associated with the specified key
	 * @return the previous value associated with key, or <code>null</code> if this map contains no mapping for the key
	 * @throws NullPointerException if the specified <code>typedKey</code> or <code>value</code> are null
	 * @throws ClassCastException if the key corresponds to a value but the new key type does not match the value type
	 *             or if the key is of an inappropriate type for this map
	 */
	@Nullable
	<TT> TypedSupplier<TT> put(@Nonnull TypedKey<TT, K> typedKey, @Nonnull TT value) throws NullPointerException,
			ClassCastException;

	@Nullable
	<TT> TypedSupplier<TT> putSupplier(@Nonnull TypedKey<TT, K> typedKey, @Nonnull TypedSupplier<TT> valueSupplier)
			throws NullPointerException, ClassCastException;

	/**
	 * Sets the type of an entry so that it cannot be changed.
	 * 
	 * This allows for performing type consistency checks on future {@link #put(TypedObject, Object)} calls without
	 * defining a value.
	 * 
	 * @param <TT> the most specific type that can be requested for this value
	 * @param key the new typed key
	 */
	<TT> void setType(@Nonnull TypedKey<TT, K> typedKey) throws NullPointerException, ClassCastException;

	@Nullable
	<TT> TypedSupplier<TT> remove(@Nonnull TT typedKey);

	@Nullable
	<TT> TypedSupplier<TT> removeTyped(@Nonnull TypedKey<TT, K> typedKey) throws NullPointerException,
			ClassCastException;

	@Nullable
	void putAll(@Nonnull Map<?, ? extends TypedSupplier<?>> m) throws NullPointerException, IllegalArgumentException,
			ClassCastException;

	/**
	 * Removes all of the mappings from this map. The map will be empty after this call returns.
	 */
	void clear();

}
