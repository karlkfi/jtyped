package karlkfi.jtyped.map;

import java.util.Map;

import karlkfi.jtyped.TypedSupplier;

import com.google.common.collect.ImmutableMap;

final class RegularImmutableTypedMap<K> extends ImmutableTypedMap<K> {

	private ImmutableMap<? extends K, ? extends TypedSupplier<?>> delegate;

	/**
	 * Constructs a new ImmutableTypedMap with the same mappings as the specified Map. If <code>m</code> is an
	 * {@link ImmutableMap} it will be used as the delegate map instead of being copied.
	 * 
	 * @param m the m
	 */
	RegularImmutableTypedMap(Map<? extends K, ? extends TypedSupplier<?>> m) {
		if (m instanceof ImmutableMap) {
			this.delegate = (ImmutableMap<? extends K, ? extends TypedSupplier<?>>) m;
		} else {
			this.delegate = ImmutableMap.copyOf(m);
		}
	}

	/**
	 * Delegate accessor (read-only).
	 * 
	 * @return the immutable map that this typed map delegates to
	 */
	@Override
	ImmutableMap<? extends K, ? extends TypedSupplier<?>> delegate() {
		return delegate;
	}

}
