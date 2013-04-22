package karlkfi.jtyped.map;

import java.util.Map.Entry;

import karlkfi.jtyped.TypedSupplier;

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
	public <TT> boolean containsKeyId(TT key) {
		return false;
	}

	@Override
	public <TT> boolean containsTypedKey(TypedKey<TT, ? extends K> typedKey) {
		return false;
	}

	@Override
	public <TT> boolean containsValue(TT value) {
		return false;
	}

	@Override
	public <TT> boolean containsValueSupplier(TypedSupplier<TT> valueSupplier) {
		return false;
	}

	@Override
	public <TT> TT get(TypedKey<TT, ? extends K> typedKey) throws EntryNotFoundException, ClassCastException {
		throw new EntryNotFoundException("Value does not exist for the key: " + typedKey);
	}

	@Override
	public <TT> TypedSupplier<TT> getSupplier(TypedKey<TT, ? extends K> typedKey) throws EntryNotFoundException,
			ClassCastException {
		throw new EntryNotFoundException("Value supplier does not exist for the key: " + typedKey);
	}

	@Override
	ImmutableSet<? extends Entry<? extends K, ? extends TypedSupplier<?>>> createEntrySet() {
		return ImmutableSet.of();
	}

	@Override
	ImmutableSet<? extends K> createKeySet() {
		return ImmutableSet.of();
	}

	@Override
	ImmutableSet<? extends TypedKey<?, ? extends K>> createTypedKeySet() {
		return ImmutableSet.of();
	}

	@Override
	ImmutableSet<?> createValues() {
		return ImmutableSet.of();
	}

	@Override
	ImmutableSet<? extends TypedSupplier<?>> createValueSuppliers() {
		return ImmutableSet.of();
	}

	Object readResolve() {
		return INSTANCE; // preserve singleton property
	}

	@Override
	public String toString() {
		return "ImmutableTypedMap.of()";
	}

}