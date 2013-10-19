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
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;

import karlkfi.jtyped.ImmutableTypedSupplier;
import karlkfi.jtyped.TypeTokens;
import karlkfi.jtyped.TypedSupplier;
import karlkfi.jtyped.TypedSuppliers;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * Immutable TypedMap that is backed by an {@link ImmutableMap}.
 * 
 * This implementation hopefully provides all of the friendliness of an ImmutableMap, despite the added complexity of a
 * TypedMap. 
 * 
 * Because values are stored as {@link TypedSupplier}s they can be memoized for cached performance.
 * 
 * While keys are immutable, values may not be and thus this map is only shallowly immutable.
 * 
 * @param <ID> the key ID type
 */
public abstract class ImmutableTypedMap<ID> extends AbstractTypedMap<ID> {

	/**
	 * Returns the empty typed map.
	 */
	// Casting to any type is safe because the set will never hold any elements.
	@SuppressWarnings("unchecked")
	public static <I> ImmutableTypedMap<I> of() {
		return (ImmutableTypedMap<I>) EmptyImmutableTypedMap.INSTANCE;
	}

	/**
	 * Returns an immutable typed map containing a single entry. This map behaves and performs comparably to
	 * {@link Collections#singletonMap} but will not accept a null key or value. It is preferable mainly for consistency
	 * and maintainability of your code.
	 */
	public static <I, T1> ImmutableTypedMap<I> of(
			@Nonnull TypedKey<T1, I> k1, @Nonnull T1 v1) {
		Preconditions.checkNotNull(k1, "k1 is null");
		return new StandardImmutableTypedMap<I>(ImmutableMap.of(
				k1.getId(), TypedSuppliers.nonnull(k1.getType(), v1)));
	}

	/**
	 * Returns an immutable map containing the given entries, in order.
	 * 
	 * @throws IllegalArgumentException if duplicate keys are provided
	 */
	public static <I, T1, T2> ImmutableTypedMap<I> of(
			@Nonnull TypedKey<T1, I> k1, @Nonnull T1 v1, 
			@Nonnull TypedKey<T2, I> k2, @Nonnull T2 v2) {
		Preconditions.checkNotNull(k1, "k1 is null");
		Preconditions.checkNotNull(k2, "k2 is null");
		return new StandardImmutableTypedMap<I>(ImmutableMap.of(
				k1.getId(), TypedSuppliers.nonnull(k1.getType(), v1),
				k2.getId(), TypedSuppliers.nonnull(k2.getType(), v2)));
	}

	/**
	 * Returns an immutable map containing the given entries, in order.
	 * 
	 * @throws IllegalArgumentException if duplicate keys are provided
	 */
	public static <I, T1, T2, T3> ImmutableTypedMap<I> of(
			@Nonnull TypedKey<T1, I> k1, @Nonnull T1 v1, 
			@Nonnull TypedKey<T2, I> k2, @Nonnull T2 v2,
			@Nonnull TypedKey<T3, I> k3, @Nonnull T3 v3) {
		Preconditions.checkNotNull(k1, "k1 is null");
		Preconditions.checkNotNull(k2, "k2 is null");
		Preconditions.checkNotNull(k3, "k3 is null");
		return new StandardImmutableTypedMap<I>(ImmutableMap.of(
				k1.getId(), TypedSuppliers.nonnull(k1.getType(), v1),
				k2.getId(), TypedSuppliers.nonnull(k2.getType(), v2),
				k3.getId(), TypedSuppliers.nonnull(k3.getType(), v3)));
	}

	/**
	 * Returns an immutable map containing the given entries, in order.
	 * 
	 * @throws IllegalArgumentException if duplicate keys are provided
	 */
	public static <I, T1, T2, T3, T4> ImmutableTypedMap<I> of(
			@Nonnull TypedKey<T1, I> k1, @Nonnull T1 v1, 
			@Nonnull TypedKey<T2, I> k2, @Nonnull T2 v2,
			@Nonnull TypedKey<T3, I> k3, @Nonnull T3 v3, 
			@Nonnull TypedKey<T4, I> k4, @Nonnull T4 v4) {
		Preconditions.checkNotNull(k1, "k1 is null");
		Preconditions.checkNotNull(k2, "k2 is null");
		Preconditions.checkNotNull(k3, "k3 is null");
		Preconditions.checkNotNull(k4, "k4 is null");
		return new StandardImmutableTypedMap<I>(ImmutableMap.of(
				k1.getId(), TypedSuppliers.nonnull(k1.getType(), v1),
				k2.getId(), TypedSuppliers.nonnull(k2.getType(), v2),
				k3.getId(), TypedSuppliers.nonnull(k3.getType(), v3),
				k4.getId(), TypedSuppliers.nonnull(k4.getType(), v4)));
	}

	/**
	 * Returns an immutable map containing the given entries, in order.
	 * 
	 * @throws IllegalArgumentException if duplicate keys are provided
	 */
	public static <I, T1, T2, T3, T4, T5> ImmutableTypedMap<I> of(
			@Nonnull TypedKey<T1, I> k1, @Nonnull T1 v1, 
			@Nonnull TypedKey<T2, I> k2, @Nonnull T2 v2,
			@Nonnull TypedKey<T3, I> k3, @Nonnull T3 v3, 
			@Nonnull TypedKey<T4, I> k4, @Nonnull T4 v4,
			@Nonnull TypedKey<T5, I> k5, @Nonnull T5 v5) {
		Preconditions.checkNotNull(k1, "k1 is null");
		Preconditions.checkNotNull(k2, "k2 is null");
		Preconditions.checkNotNull(k3, "k3 is null");
		Preconditions.checkNotNull(k4, "k4 is null");
		Preconditions.checkNotNull(k5, "k5 is null");
		return new StandardImmutableTypedMap<I>(ImmutableMap.of(
				k1.getId(), TypedSuppliers.nonnull(k1.getType(), v1),
				k2.getId(), TypedSuppliers.nonnull(k2.getType(), v2),
				k3.getId(), TypedSuppliers.nonnull(k3.getType(), v3),
				k4.getId(), TypedSuppliers.nonnull(k4.getType(), v4),
				k5.getId(), TypedSuppliers.nonnull(k5.getType(), v5)));
	}

	// looking for of() with > 5 entries? Use the builder instead.
	
	/**
	 * Returns an immutable typed map containing a single entry. This map behaves and performs comparably to
	 * {@link Collections#singletonMap} but will not accept a null key or value. It is preferable mainly for consistency
	 * and maintainability of your code.
	 */
	public static <I> ImmutableTypedMap<I> ofRaw(
			@Nonnull I id1, @Nonnull Object v1) {
		Preconditions.checkNotNull(id1, "id1 is null");
		return new StandardImmutableTypedMap<I>(ImmutableMap.of(
				id1, TypedSuppliers.nonnull(TypeTokens.raw(v1), v1)));
	}

	/**
	 * Returns an immutable map containing the given entries, in order.
	 * 
	 * @throws IllegalArgumentException if duplicate keys are provided
	 */
	public static <I> ImmutableTypedMap<I> ofRaw(
			@Nonnull I id1, @Nonnull Object v1, 
			@Nonnull I id2, @Nonnull Object v2) {
		Preconditions.checkNotNull(id1, "id1 is null");
		Preconditions.checkNotNull(id2, "id2 is null");
		return new StandardImmutableTypedMap<I>(ImmutableMap.of(
				id1, TypedSuppliers.nonnull(TypeTokens.raw(v1), v1),
				id2, TypedSuppliers.nonnull(TypeTokens.raw(v2), v2)));
	}

	/**
	 * Returns an immutable map containing the given entries, in order.
	 * 
	 * @throws IllegalArgumentException if duplicate keys are provided
	 */
	public static <I> ImmutableTypedMap<I> ofRaw(
			@Nonnull I id1, @Nonnull Object v1, 
			@Nonnull I id2, @Nonnull Object v2,
			@Nonnull I id3, @Nonnull Object v3) {
		Preconditions.checkNotNull(id1, "id1 is null");
		Preconditions.checkNotNull(id2, "id2 is null");
		Preconditions.checkNotNull(id3, "id3 is null");
		return new StandardImmutableTypedMap<I>(ImmutableMap.of(
				id1, TypedSuppliers.nonnull(TypeTokens.raw(v1), v1),
				id2, TypedSuppliers.nonnull(TypeTokens.raw(v2), v2),
				id3, TypedSuppliers.nonnull(TypeTokens.raw(v3), v3)));
	}

	/**
	 * Returns an immutable map containing the given entries, in order.
	 * 
	 * @throws IllegalArgumentException if duplicate keys are provided
	 */
	public static <I> ImmutableTypedMap<I> ofRaw(
			@Nonnull I id1, @Nonnull Object v1, 
			@Nonnull I id2, @Nonnull Object v2,
			@Nonnull I id3, @Nonnull Object v3,
			@Nonnull I id4, @Nonnull Object v4) {
		Preconditions.checkNotNull(id1, "id1 is null");
		Preconditions.checkNotNull(id2, "id2 is null");
		Preconditions.checkNotNull(id3, "id3 is null");
		Preconditions.checkNotNull(id4, "id4 is null");
		return new StandardImmutableTypedMap<I>(ImmutableMap.of(
				id1, TypedSuppliers.nonnull(TypeTokens.raw(v1), v1),
				id2, TypedSuppliers.nonnull(TypeTokens.raw(v2), v2),
				id3, TypedSuppliers.nonnull(TypeTokens.raw(v3), v3),
				id4, TypedSuppliers.nonnull(TypeTokens.raw(v4), v4)));
	}

	/**
	 * Returns an immutable map containing the given entries, in order.
	 * 
	 * @throws IllegalArgumentException if duplicate keys are provided
	 */
	public static <I> ImmutableTypedMap<I> ofRaw(
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
		return new StandardImmutableTypedMap<I>(ImmutableMap.of(
				id1, TypedSuppliers.nonnull(TypeTokens.raw(v1), v1),
				id2, TypedSuppliers.nonnull(TypeTokens.raw(v2), v2),
				id3, TypedSuppliers.nonnull(TypeTokens.raw(v3), v3),
				id4, TypedSuppliers.nonnull(TypeTokens.raw(v4), v4),
				id5, TypedSuppliers.nonnull(TypeTokens.raw(v5), v5)));
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
		 * {@link ImmutableTypedMap#builder}.
		 */
		public Builder() {
		}

		/**
		 * Associates the typed {@code key} with the untyped {@code value} in the built map. Duplicate keys are not
		 * allowed, and will cause {@link #build} to fail.
		 */
		public <TT> Builder<K> put(TypedKey<TT, K> key, TT value) {
			builder.put(key.getId(), ImmutableTypedSupplier.of(key.getType(), value));
			return this;
		}
		
		/**
		 * 
		 * Associates the {@code id} with the non-generic {@code value} in the built map. Duplicate keys are not
		 * allowed, and will cause {@link #build} to fail. {@code value} class generics will NOT be preserved.
		 * Recommended for raw value types only!
		 */
		public Builder<K> putRaw(K id, Object value) throws IllegalArgumentException {
			builder.put(id, ImmutableTypedSupplier.of(TypeTokens.raw(value), value));
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
		 * Returns a newly-created immutable map.
		 * 
		 * @throws IllegalArgumentException if duplicate keys were added
		 */
		public ImmutableTypedMap<K> build() {
			return fromEntryList(builder.build());
		}

		private static <I> ImmutableTypedMap<I> fromEntryList(Map<I, TypedSupplier<?>> m) {
			int size = m.size();
			switch (size) {
			case 0:
				return of();
			default:
				return new StandardImmutableTypedMap<I>(m);
			}
		}

	}

	/**
	 * Delegate accessor (read-only).
	 * 
	 * @return the immutable map that this typed map delegates to
	 */
	protected abstract ImmutableMap<? extends ID, ? extends TypedSupplier<?>> delegate();
	
	private transient ImmutableSet<? extends Entry<? extends TypedKey<?, ? extends ID>, ?>> entrySet;

	/**
	 * Gets the immutable set of all typed key value pairs.
	 * This only includes keys with the types as set, not all assignable types.
	 * 
	 * @return the immutable set of typed key value pairs
	 */
	@Nonnull
	public ImmutableSet<? extends Entry<? extends TypedKey<?, ? extends ID>, ?>> entries() {
		ImmutableSet<? extends Entry<? extends TypedKey<?, ? extends ID>, ?>> result = entrySet;
		return (result == null) ? entrySet = ImmutableSet.copyOf(createEntrySet()) : result;
	}
	
	private transient ImmutableSet<? extends Entry<? extends ID, ? extends TypedSupplier<?>>> entrySupplierSet;

	/**
	 * Returns an immutable set of the mappings in this map. The entries are in the same order as the parameters used to
	 * build this map.
	 * TODO: should this be exposed?
	 */
	@Nonnull
	ImmutableSet<? extends Entry<? extends ID, ? extends TypedSupplier<?>>> entrySuppliers() {
		ImmutableSet<? extends Entry<? extends ID, ? extends TypedSupplier<?>>> result = entrySupplierSet;
		return (result == null) ? entrySupplierSet = ImmutableSet.copyOf(createEntrySupplierSet()) : result;
	}

	private transient ImmutableSet<? extends TypedKey<?, ? extends ID>> typedKeySet;

	/**
	 * Gets the immutable set of typed keys that have corresponding values in this map.
	 * This only includes keys with the types as set, not all assignable types.
	 * 
	 * @return the immutable set of typed keys that have corresponding values in this map
	 */
	@Nonnull
	public ImmutableSet<? extends TypedKey<?, ? extends ID>> keys() {
		ImmutableSet<? extends TypedKey<?, ? extends ID>> result = typedKeySet;
		return (result == null) ? typedKeySet = ImmutableSet.copyOf(createKeySet()) : result;
	}

}
