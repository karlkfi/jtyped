/*******************************************************************************
 * Copyright 2013 Karl Isenberg
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package karlkfi.jtyped.map;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import karlkfi.jtyped.TypedSupplier;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;

/**
 * Abstract TypedMap that is backed by an {@link Map}.
 * 
 * This implementation hopefully provides all of the friendliness of an ImmutableMap, despite the added complexity of a
 * TypedMap. 
 * 
 * Because values are stored as {@link TypedSupplier}s they can be memoized for cached performance.
 * 
 * @param <ID> the key ID type
 */
public abstract class AbstractTypedMap<ID> implements TypedMap<ID> {

	/**
	 * Delegate accessor (read-only).
	 * 
	 * @return the immutable map that this typed map delegates to
	 */
	protected abstract Map<? extends ID, ? extends TypedSupplier<?>> delegate();

	/**
	 * @return the number of entries in this typed map (up to Integer.MAX_VALUE)
	 */
	public int size() {
		return delegate().size();
	}

	/**
	 * @return true if this typed map contains no entries
	 */
	public boolean isEmpty() {
		return delegate().isEmpty();
	}

	/**
	 * @returns true for all keys().contains(typedKey) where the provided key type is assignable from the contained key type.
	 */
	public <T> boolean contains(@Nonnull TypedKey<T, ? extends ID> typedKey) {
		TypedSupplier<?> valueSupplier = delegate().get(typedKey.getId());
		if (valueSupplier == null) {
			return false;
		}
		return typedKey.getType().isAssignableFrom(valueSupplier.getType());
	}
	
	/**
	 * @returns true if there exists a key in the map with the provided ID.
	 */
	public <T> boolean contains(@Nonnull ID id) {
		TypedSupplier<?> valueSupplier = delegate().get(id);
		return valueSupplier != null;
	}

	/** {@inheritDoc} */
	@Nonnull
	public <T> T get(@Nonnull TypedKey<T, ? extends ID> typedKey) throws EntryNotFoundException, ClassCastException {
		TypedSupplier<?> valueSupplier = delegate().get(typedKey.getId());
		if (valueSupplier == null) {
			throw new EntryNotFoundException("Value does not exist for the key: " + typedKey);
		}
		TypedSupplier<T> typedSupplier = checkValueType(typedKey.getType(), valueSupplier);
		return typedSupplier.get();
	}
	
	/** {@inheritDoc} */
	@Nonnull
	public <T> T get(@Nonnull Class<T> valueType, ID keyId) throws EntryNotFoundException, ClassCastException {
		TypedSupplier<?> valueSupplier = delegate().get(keyId);
		if (valueSupplier == null) {
			throw new EntryNotFoundException("Value does not exist for the id: " + keyId);
		}
		TypedSupplier<T> typedSupplier = checkValueType(TypeToken.of(valueType), valueSupplier);
		return typedSupplier.get();
	}
	
	/** {@inheritDoc} */
	@Nonnull
	public Object get(@Nonnull ID keyId) throws EntryNotFoundException {
		TypedSupplier<?> valueSupplier = delegate().get(keyId);
		if (valueSupplier == null) {
			throw new EntryNotFoundException("Value does not exist for the key ID: " + keyId);
		}
		return valueSupplier.get();
	}
	
	private transient Set<? extends Entry<? extends TypedKey<?, ? extends ID>, ?>> entrySet;

	/**
	 * Gets the immutable set of all typed key value pairs.
	 * This only includes keys with the types as set, not all assignable types.
	 * 
	 * @return the immutable set of typed key value pairs
	 */
	@Nonnull
	public Set<? extends Entry<? extends TypedKey<?, ? extends ID>, ?>> entries() {
		Set<? extends Entry<? extends TypedKey<?, ? extends ID>, ?>> result = entrySet;
		return (result == null) ? entrySet = createEntrySet() : result;
	}
	
	@Nonnull
	Set<? extends Entry<? extends TypedKey<?, ? extends ID>, ?>> createEntrySet() {
		//based on the cachable entrySupplierSet
		return ImmutableSet.copyOf(Collections2.transform(entrySuppliers(), new EntryToKeyedEntryTransform()));
	}
	
	class EntryToKeyedEntryTransform implements Function<Entry<? extends ID, ? extends TypedSupplier<?>>, Entry<? extends TypedKey<?, ? extends ID>, ?>> {
		public Entry<? extends TypedKey<?, ? extends ID>, ?> apply(Entry<? extends ID, ? extends TypedSupplier<?>> input) {
			TypedSupplier<?> valueSupplier = input.getValue();
			return Maps.immutableEntry(ImmutableTypedKey.of(valueSupplier.getType(), input.getKey()), valueSupplier.get());
		}
	};
	
	private transient Set<? extends Entry<? extends ID, ? extends TypedSupplier<?>>> entrySupplierSet;

	/**
	 * Returns an immutable set of the mappings in this map. The entries are in the same order as the parameters used to
	 * build this map.
	 * TODO: should this be exposed?
	 */
	@Nonnull
	Set<? extends Entry<? extends ID, ? extends TypedSupplier<?>>> entrySuppliers() {
		Set<? extends Entry<? extends ID, ? extends TypedSupplier<?>>> result = entrySupplierSet;
		return (result == null) ? entrySupplierSet = createEntrySupplierSet() : result;
	}

	@Nonnull
	Set<? extends Entry<? extends ID, ? extends TypedSupplier<?>>> createEntrySupplierSet() {
		return delegate().entrySet();
	}

	private transient Set<? extends TypedKey<?, ? extends ID>> typedKeySet;

	/**
	 * Gets the immutable set of typed keys that have corresponding values in this map.
	 * This only includes keys with the types as set, not all assignable types.
	 * 
	 * @return the immutable set of typed keys that have corresponding values in this map
	 */
	@Nonnull
	public Set<? extends TypedKey<?, ? extends ID>> keys() {
		Set<? extends TypedKey<?, ? extends ID>> result = typedKeySet;
		return (result == null) ? typedKeySet = createKeySet() : result;
	}

	@Nonnull
	Set<? extends TypedKey<?, ? extends ID>> createKeySet() {
		//based on the cachable entrySupplierSet
		return ImmutableSet.copyOf(Collections2.transform(entrySuppliers(), new EntryToTypedKeyTransform()));
	}

	class EntryToTypedKeyTransform implements Function<Entry<? extends ID, ? extends TypedSupplier<?>>, TypedKey<?, ID>> {
		public TypedKey<?, ID> apply(Entry<? extends ID, ? extends TypedSupplier<?>> input) {
			return ImmutableTypedKey.of(input.getValue().getType(), (ID) input.getKey());
		}
	};

	@Nonnull
	static <TT> TypedSupplier<TT> checkValueType(@Nonnull TypeToken<TT> type, @Nonnull TypedSupplier<?> valueSupplier)
			throws ClassCastException {
		if (!type.isAssignableFrom(valueSupplier.getType())) {
			throw new ClassCastException("Key type is not assignable from the existing value type.");
		}
		@SuppressWarnings("unchecked")
		TypedSupplier<TT> typedSupplier = (TypedSupplier<TT>) valueSupplier;
		return typedSupplier;
	}

	@Override
	public boolean equals(@Nullable Object object) {
		return TypedMaps.equalsImpl(this, object);
	}

	/**
	 * @return the hash code of the entry set
	 */
	@Override
	public int hashCode() {
		// not caching hash code since it could change if map values are mutable in a way that modifies their hash codes
		return entries().hashCode();
	}

	/**
	 * @return a string that represents the typed map entries
	 */
	@Override
	public String toString() {
		return TypedMaps.toStringImpl(this);
	}

}
