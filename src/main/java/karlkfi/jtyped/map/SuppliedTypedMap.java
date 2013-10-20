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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import karlkfi.jtyped.MutableTypedSupplier;
import karlkfi.jtyped.TypeTokens;
import karlkfi.jtyped.TypedSupplier;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.reflect.TypeToken;

/**
 * TypedMap that is backed by a {@link HashMap} with TypedSupplier values.
 * 
 * Because it is backed by a HashMap, it is NOT thread-safe.
 * 
 * @param <ID> the key ID type
 */
@NotThreadSafe
public abstract class SuppliedTypedMap<ID> extends AbstractTypedMap<ID> implements MutableTypedMap<ID> {

	/**
	 * Returns the empty typed map.
	 */
	public static <I> SuppliedTypedMap<I> of() {
		return new StandardSuppliedTypedMap<I>(HashMaps.<I, TypedSupplier<?>>of());
	}

	/**
	 * Returns an typed map containing a single entry. This map behaves and performs comparably to
	 * {@link Collections#singletonMap} but will not accept a null key or value. It is preferable mainly for consistency
	 * and maintainability of your code.
	 */
	public static <I> SuppliedTypedMap<I> of(I k1, TypedSupplier<?> v1) {
		return new StandardSuppliedTypedMap<I>(HashMaps.of(k1, v1));
	}

	/**
	 * Returns an typed map containing the given entries, in order.
	 * 
	 * @throws IllegalArgumentException if duplicate keys are provided
	 */
	public static <I> SuppliedTypedMap<I> of(I k1, TypedSupplier<?> v1, I k2, TypedSupplier<?> v2) {
		return new StandardSuppliedTypedMap<I>(HashMaps.of(k1, v1, k2, v2));
	}

	/**
	 * Returns an typed map containing the given entries, in order.
	 * 
	 * @throws IllegalArgumentException if duplicate keys are provided
	 */
	public static <I> SuppliedTypedMap<I> of(I k1, TypedSupplier<?> v1, I k2, TypedSupplier<?> v2, I k3,
			TypedSupplier<?> v3) {
		return new StandardSuppliedTypedMap<I>(HashMaps.of(k1, v1, k2, v2, k3, v3));
	}

	/**
	 * Returns an typed map containing the given entries, in order.
	 * 
	 * @throws IllegalArgumentException if duplicate keys are provided
	 */
	public static <I> SuppliedTypedMap<I> of(I k1, TypedSupplier<?> v1, I k2, TypedSupplier<?> v2, I k3,
			TypedSupplier<?> v3, I k4, TypedSupplier<?> v4) {
		return new StandardSuppliedTypedMap<I>(HashMaps.of(k1, v1, k2, v2, k3, v3, k4, v4));
	}

	/**
	 * Returns an typed map containing the given entries, in order.
	 * 
	 * @throws IllegalArgumentException if duplicate keys are provided
	 */
	public static <I> SuppliedTypedMap<I> of(I k1, TypedSupplier<?> v1, I k2, TypedSupplier<?> v2, I k3,
			TypedSupplier<?> v3, I k4, TypedSupplier<?> v4, I k5, TypedSupplier<?> v5) {
		return new StandardSuppliedTypedMap<I>(HashMaps.of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5));
	}

	// looking for of() with > 5 entries? Use the builder instead.
	

	/**
	 * Returns an typed map containing a single entry. This map behaves and performs comparably to
	 * {@link Collections#singletonMap} but will not accept a null key or value. It is preferable mainly for consistency
	 * and maintainability of your code.
	 */
	public static <I> SuppliedTypedMap<I> ofRaw(
			@Nonnull I id1, @Nonnull Object v1) {
		Preconditions.checkNotNull(id1, "id1 is null");
		return new StandardSuppliedTypedMap<I>(HashMaps.of(
				id1, MutableTypedSupplier.nonnull(TypeTokens.raw(v1), v1)));
	}

	/**
	 * Returns an typed map containing the given entries, in order.
	 * 
	 * @throws IllegalArgumentException if duplicate keys are provided
	 */
	public static <I> SuppliedTypedMap<I> ofRaw(
			@Nonnull I id1, @Nonnull Object v1, 
			@Nonnull I id2, @Nonnull Object v2) {
		Preconditions.checkNotNull(id1, "id1 is null");
		Preconditions.checkNotNull(id2, "id2 is null");
		return new StandardSuppliedTypedMap<I>(HashMaps.of(
				id1, MutableTypedSupplier.nonnull(TypeTokens.raw(v1), v1),
				id2, MutableTypedSupplier.nonnull(TypeTokens.raw(v2), v2)));
	}

	/**
	 * Returns an typed map containing the given entries, in order.
	 * 
	 * @throws IllegalArgumentException if duplicate keys are provided
	 */
	public static <I> SuppliedTypedMap<I> ofRaw(
			@Nonnull I id1, @Nonnull Object v1, 
			@Nonnull I id2, @Nonnull Object v2,
			@Nonnull I id3, @Nonnull Object v3) {
		Preconditions.checkNotNull(id1, "id1 is null");
		Preconditions.checkNotNull(id2, "id2 is null");
		Preconditions.checkNotNull(id3, "id3 is null");
		return new StandardSuppliedTypedMap<I>(HashMaps.of(
				id1, MutableTypedSupplier.nonnull(TypeTokens.raw(v1), v1),
				id2, MutableTypedSupplier.nonnull(TypeTokens.raw(v2), v2),
				id3, MutableTypedSupplier.nonnull(TypeTokens.raw(v3), v3)));
	}

	/**
	 * Returns an typed map containing the given entries, in order.
	 * 
	 * @throws IllegalArgumentException if duplicate keys are provided
	 */
	public static <I> SuppliedTypedMap<I> ofRaw(
			@Nonnull I id1, @Nonnull Object v1, 
			@Nonnull I id2, @Nonnull Object v2,
			@Nonnull I id3, @Nonnull Object v3,
			@Nonnull I id4, @Nonnull Object v4) {
		Preconditions.checkNotNull(id1, "id1 is null");
		Preconditions.checkNotNull(id2, "id2 is null");
		Preconditions.checkNotNull(id3, "id3 is null");
		Preconditions.checkNotNull(id4, "id4 is null");
		return new StandardSuppliedTypedMap<I>(HashMaps.of(
				id1, MutableTypedSupplier.nonnull(TypeTokens.raw(v1), v1),
				id2, MutableTypedSupplier.nonnull(TypeTokens.raw(v2), v2),
				id3, MutableTypedSupplier.nonnull(TypeTokens.raw(v3), v3),
				id4, MutableTypedSupplier.nonnull(TypeTokens.raw(v4), v4)));
	}

	/**
	 * Returns an typed map containing the given entries, in order.
	 * 
	 * @throws IllegalArgumentException if duplicate keys are provided
	 */
	public static <I> SuppliedTypedMap<I> ofRaw(
			@Nonnull I id1, @Nonnull Object v1, 
			@Nonnull I id2, @Nonnull Object v2,
			@Nonnull I id3, @Nonnull Object v3,
			@Nonnull I id4, @Nonnull Object v4,
			@Nonnull I id5, @Nonnull Object v5) {
		Preconditions.checkNotNull(id1, "id1 is null");
		Preconditions.checkNotNull(id2, "id2 is null");
		Preconditions.checkNotNull(id3, "id3 is null");
		Preconditions.checkNotNull(id4, "id4 is null");
		Preconditions.checkNotNull(id5, "id5 is null");
		return new StandardSuppliedTypedMap<I>(HashMaps.of(
				id1, MutableTypedSupplier.nonnull(TypeTokens.raw(v1), v1),
				id2, MutableTypedSupplier.nonnull(TypeTokens.raw(v2), v2),
				id3, MutableTypedSupplier.nonnull(TypeTokens.raw(v3), v3),
				id4, MutableTypedSupplier.nonnull(TypeTokens.raw(v4), v4),
				id5, MutableTypedSupplier.nonnull(TypeTokens.raw(v5), v5)));
	}

	/**
	 * Returns a new builder. The generated builder is equivalent to the builder created by the {@link Builder}
	 * constructor.
	 */
	@Nonnull
	public static <I> Builder<I> builder() {
		return new Builder<I>();
	}

	public static class Builder<K> {
		final ImmutableMap.Builder<K, TypedSupplier<?>> builder = ImmutableMap.builder();

		/**
		 * Creates a new builder. The returned builder is equivalent to the builder generated by
		 * {@link SuppliedTypedMap#builder}.
		 */
		public Builder() {
		}

		/**
		 * Associates the untyped {@code key} with the typed {@code value} in the built map. Duplicate keys are not
		 * allowed, and will cause {@link #build} to fail.
		 */
		public Builder<K> put(K key, TypedSupplier<?> value) {
			builder.put(key, value);
			return this;
		}

		/**
		 * Adds the given {@code entry} to the map. Duplicate keys are not allowed,
		 * and will cause {@link #build} to fail.
		 */
		public Builder<K> put(Entry<K, ? extends TypedSupplier<?>> entry) {
			builder.put(entry);
			return this;
		}
		
		/**
		 * 
		 * Associates the {@code id} with the non-generic {@code value} in the built map. Duplicate keys are not
		 * allowed, and will cause {@link #build} to fail. {@code value} class generics will NOT be preserved.
		 * Recommended for raw value types only!
		 */
		public Builder<K> putRaw(K id, Object value) throws IllegalArgumentException {
			builder.put(id, MutableTypedSupplier.of(TypeTokens.raw(value), value));
			return this;
		}

		/**
		 * Associates all of the given map's keys and values in the built map. Duplicate keys are not allowed, and will
		 * cause {@link #build} to fail.
		 * 
		 * Note: there is no {@code putAll} for a {@code Map<TypedKey<?>, Object>} because the required generic type
		 * checking cannot be done at runtime.
		 * 
		 * @throws NullPointerException if any key or value in {@code map} is null
		 */
		public Builder<K> putAll(Map<K, ? extends TypedSupplier<?>> map) {
			builder.putAll(map);
			return this;
		}

		/**
		 * Returns a newly-created typed map.
		 * 
		 * @throws IllegalArgumentException if duplicate keys were added
		 */
		public SuppliedTypedMap<K> build() {
			return fromEntryList(builder.build());
		}

		private static <I> SuppliedTypedMap<I> fromEntryList(Map<I, TypedSupplier<?>> m) {
			int size = m.size();
			switch (size) {
			case 0:
				return of();
			default:
				return new StandardSuppliedTypedMap<I>(m);
			}
		}
		
	}
	
	/** {@inheritDoc} */
	@Override
	public <TT> void setType(@Nonnull TypedKey<TT, ? extends ID> typedKey) throws NullPointerException, ClassCastException {
		Map<ID, TypedSupplier<Object>> delegate = delegate();
		TypedSupplier<?> oldValueSupplier = delegate.get(typedKey.getId());
		if (oldValueSupplier == null) {
			//supplier is missing, replace it with a mutable one
			@SuppressWarnings("unchecked")
			TypeToken<Object> type = (TypeToken<Object>) typedKey.getType();
			delegate.put(typedKey.getId(), MutableTypedSupplier.of(type, null));
			//TODO: do we care that we may have lost some intermediate TypedSupplier populated by an unsynchronized put call?
		}
		
		checkValueType(typedKey.getType(), oldValueSupplier);
	}
		
	/** {@inheritDoc} */
	@Override
	@Nullable
	public <TT> TT put(@Nonnull TypedKey<TT, ? extends ID> typedKey, @Nonnull TT value) throws NullPointerException, ClassCastException, ImmutableEntryException {
		Map<ID, TypedSupplier<Object>> delegate = delegate();
		TypedSupplier<?> oldValueSupplier = delegate.get(typedKey.getId());
		if (oldValueSupplier == null) {
			//supplier is missing, replace it with a mutable one
			@SuppressWarnings("unchecked")
			TypeToken<Object> type = (TypeToken<Object>) typedKey.getType();
			delegate.put(typedKey.getId(), MutableTypedSupplier.of(type, value));
			//TODO: do we care that we may have lost some intermediate TypedSupplier populated by an unsynchronized put call?
			return null;
		}
		
		TypedSupplier<TT> typedSupplier = checkValueType(typedKey.getType(), oldValueSupplier);
		
		if (oldValueSupplier instanceof MutableTypedSupplier) {
			// if supplier is mutable, update it
			MutableTypedSupplier<TT> supplier = (MutableTypedSupplier<TT>) typedSupplier;
			supplier.set(value);
			//TODO: do we care that some unsynchronized put call may have replaced this supplier?
		} else {
			//supplier is immutable, can't update it
			throw new ImmutableEntryException("Entry is immutable for the key: " + typedKey);
		}
		
		return typedSupplier.get();
	}
	
	/** {@inheritDoc} */
	@Override
	@Nullable
	public void putAll(@Nonnull TypedMap<? extends ID> m) throws NullPointerException, IllegalArgumentException, ClassCastException {
		//TODO: validate that we CAN update before trying? or revert transaction?
		Map<ID, TypedSupplier<Object>> delegate = delegate();
		for (Entry<? extends TypedKey<Object, ? extends ID>, Object> entry : m.entries()) {
			TypedKey<Object, ? extends ID> typedKey = entry.getKey();
			TypeToken<Object> type = typedKey.getType();
			Object value = entry.getValue();
			
			TypedSupplier<?> oldValueSupplier = delegate.get(typedKey.getId());
			if (oldValueSupplier == null) {
				//supplier is missing, replace it with a mutable one
				delegate.put(typedKey.getId(), MutableTypedSupplier.of(type, value));
				//TODO: do we care that we may have lost some intermediate TypedSupplier populated by an unsynchronized put call?
			}
			
			TypedSupplier<Object> typedSupplier = checkValueType(type, oldValueSupplier);
			
			if (oldValueSupplier instanceof MutableTypedSupplier) {
				// if supplier is mutable, update it
				MutableTypedSupplier<Object> supplier = (MutableTypedSupplier<Object>) typedSupplier;
				supplier.set(value);
				//TODO: do we care that some unsynchronized put call may have replaced this supplier?
			} else {
				//supplier is immutable, can't update it
				throw new ImmutableEntryException("Entry is immutable for the key: " + typedKey);
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	@Nullable
	public<TT> TT remove(@Nonnull TypedKey<TT, ? extends ID> typedKey) throws NullPointerException, EntryNotFoundException, ClassCastException, ImmutableEntryException {
		TypedSupplier<?> oldValueSupplier = delegate().get(typedKey.getId());
		if (oldValueSupplier == null) {
			throw new EntryNotFoundException("Value does not exist for the key: " + typedKey);
		}
		
		TypedSupplier<TT> typedSupplier = checkValueType(typedKey.getType(), oldValueSupplier);
		
		if (typedSupplier instanceof MutableTypedSupplier) {
			// if supplier is mutable, update it
			MutableTypedSupplier<TT> supplier = (MutableTypedSupplier<TT>) typedSupplier;
			supplier.set(null);
		} else {
			//supplier is immutable, can't update it
			throw new ImmutableEntryException("Entry is immutable for the key: " + typedKey);
		}
		
		return typedSupplier.get();
	}

	/** {@inheritDoc} */
	@Override
	@Nullable
	public <TT> TT remove(@Nonnull Class<TT> valueType, @Nonnull ID keyId) throws NullPointerException, ClassCastException, ImmutableEntryException {
		TypedSupplier<?> oldValueSupplier = delegate().get(keyId);
		if (oldValueSupplier == null) {
			throw new EntryNotFoundException("Value does not exist for the key ID: " + keyId);
		}
		
		TypedSupplier<TT> typedSupplier = checkValueType(TypeToken.of(valueType), oldValueSupplier);
		
		if (typedSupplier instanceof MutableTypedSupplier) {
			// if supplier is mutable, update it
			MutableTypedSupplier<TT> supplier = (MutableTypedSupplier<TT>) typedSupplier;
			supplier.set(null);
		} else {
			//supplier is immutable, can't update it
			throw new ImmutableEntryException("Entry is immutable for the key ID: " + keyId);
		}
		
		return typedSupplier.get();
	}

	/** {@inheritDoc} */
	@Override
	@Nullable
	public Object remove(@Nonnull ID keyId) throws NullPointerException, ClassCastException, ImmutableEntryException {
		TypedSupplier<?> oldValueSupplier = delegate().get(keyId);
		if (oldValueSupplier == null) {
			throw new EntryNotFoundException("Value does not exist for the key ID: " + keyId);
		}
		
		if (oldValueSupplier instanceof MutableTypedSupplier) {
			// if supplier is mutable, update it
			MutableTypedSupplier<?> supplier = (MutableTypedSupplier<?>) oldValueSupplier;
			supplier.set(null);
		} else {
			//supplier is immutable, can't update it
			throw new ImmutableEntryException("Entry is immutable for the key ID: " + keyId);
		}
		
		return oldValueSupplier.get();
	}

	/** {@inheritDoc} */
	@Override
	public void clear() {
		//TODO: validate that we CAN clear before trying? or revert transaction?
		for (Entry<ID, TypedSupplier<Object>> entry : delegate().entrySet()) {
			TypedSupplier<?> valueSupplier = entry.getValue();
			if (valueSupplier instanceof MutableTypedSupplier) {
				// if supplier is mutable, update it
				MutableTypedSupplier<?> supplier = (MutableTypedSupplier<?>) valueSupplier;
				supplier.set(null);
			} else {
				//supplier is immutable, can't update it
				throw new ImmutableEntryException("Entry is immutable for the key ID: " + entry.getKey());
			}
		}
	}

}
