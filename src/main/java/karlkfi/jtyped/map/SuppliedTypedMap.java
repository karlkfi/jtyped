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

import karlkfi.jtyped.ImmutableTypedSupplier;
import karlkfi.jtyped.TypeTokens;
import karlkfi.jtyped.TypedSupplier;
import karlkfi.jtyped.TypedSuppliers;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;

/**
 * TypedMap that is backed by a {@link HashMap} with TypedSupplier values.
 * 
 * This implementation hopefully provides all of the friendliness of an ImmutableMap, despite the added complexity of a
 * TypedMap. 
 * 
 * Because values are stored as {@link TypedSupplier}s they can be memoized for cached performance.
 * 
 * @param <ID> the key ID type
 */
public abstract class SuppliedTypedMap<ID> extends AbstractTypedMap<ID> {

	/**
	 * Returns the empty typed map.
	 */
	public static <I> SuppliedTypedMap<I> of() {
		return new StandardSuppliedTypedMap<I>(new HashMap<I, TypedSupplier<?>>());
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
				id1, TypedSuppliers.nonnull(TypeTokens.raw(v1), v1)));
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
				id1, TypedSuppliers.nonnull(TypeTokens.raw(v1), v1),
				id2, TypedSuppliers.nonnull(TypeTokens.raw(v2), v2)));
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
				id1, TypedSuppliers.nonnull(TypeTokens.raw(v1), v1),
				id2, TypedSuppliers.nonnull(TypeTokens.raw(v2), v2),
				id3, TypedSuppliers.nonnull(TypeTokens.raw(v3), v3)));
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
				id1, TypedSuppliers.nonnull(TypeTokens.raw(v1), v1),
				id2, TypedSuppliers.nonnull(TypeTokens.raw(v2), v2),
				id3, TypedSuppliers.nonnull(TypeTokens.raw(v3), v3),
				id4, TypedSuppliers.nonnull(TypeTokens.raw(v4), v4)));
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

}
