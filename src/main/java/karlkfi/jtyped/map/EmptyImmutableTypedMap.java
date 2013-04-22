package karlkfi.jtyped.map;

import java.util.Map.Entry;

import karlkfi.jtyped.TypedSupplier;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * TypedBiMap with no mappings.
 *
 * @param <K> the key type
 */
final class EmptyImmutableTypedMap<K> extends ImmutableTypedMap<K> {
	static final EmptyImmutableTypedMap<Object> INSTANCE = new EmptyImmutableTypedMap<Object>();

	private ImmutableMap<? extends K, ? extends TypedSupplier<?>> delegate = ImmutableMap.of();

	private EmptyImmutableTypedMap() {
	}

	@Override
	ImmutableMap<? extends K, ? extends TypedSupplier<?>> delegate() {
		return delegate;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public <TT> boolean containsKey(TT key) {
		return false;
	}

	@Override
	public <TT> boolean containsTypedKey(TypedKey<TT,? extends K> typedKey) {
		return false;
	}

	@Override
	public <TT> boolean containsValue(TT value) {
		return false;
	}

	@Override
	public <TT> boolean containsTypedValue(TypedSupplier<TT> valueSupplier) {
		return false;
	}

	@Override
	public <TT> TT get(TypedKey<TT,? extends K> typedKey) throws EntryNotFoundException, ClassCastException {
		throw new EntryNotFoundException("Value does not exist for the key: " + typedKey);
	}

	@Override
	public <TT> TypedSupplier<TT> getSupplier(TypedKey<TT,? extends K> typedKey) throws EntryNotFoundException, ClassCastException {
		throw new EntryNotFoundException("Value supplier does not exist for the key: " + typedKey);
	}

	@Override
	public ImmutableSet<? extends Entry<? extends K, ? extends TypedSupplier<?>>> entrySet() {
		return ImmutableSet.of();
	}
	
	@Override
	public ImmutableSet<? extends K> keySet() {
		return ImmutableSet.of();
	}
	
	@Override
	public ImmutableSet<? extends TypedKey<?,? extends K>> typedKeySet() {
		return ImmutableSet.of();
	}

	@Override
	public ImmutableCollection<?> values() {
		return ImmutableList.of();
	}

	@Override
	public ImmutableCollection<? extends TypedSupplier<?>> valueSuppliers() {
		return ImmutableList.of();
	}

	Object readResolve() {
		return INSTANCE; // preserve singleton property
	}

}