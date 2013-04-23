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

import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Nonnull;

import com.google.common.reflect.TypeToken;

import karlkfi.jtyped.TypedSupplier;

/**
 * A map of keys to {@link TypedSupplier}s.
 * 
 * Because values are stored as {@link TypedSupplier}s you can memoize them for cached performance.
 * 
 * Typed maps cannot contain duplicate keys IDs; each key ID can map to at most one value.
 * 
 */
public interface TypedMap<K> {

	int size();

	boolean isEmpty();

	<TT> boolean containsKeyId(@Nonnull TT key);

	<TT> boolean containsTypedKey(@Nonnull TypedKey<TT, ? extends K> typedKey);

	<TT> boolean containsValue(@Nonnull TT value);

	<TT> boolean containsValueSupplier(@Nonnull TypedSupplier<TT> valueSupplier);

	@Nonnull
	<TT> TT get(@Nonnull TypeToken<TT> type, @Nonnull K keyId) throws EntryNotFoundException, ClassCastException;

	/**
	 * Returns the value to which the specified key is mapped.
	 * 
	 * The type of the key indicates the type expected of the value. If the key type is assignable from the value type
	 * the value will be cast to it.
	 * 
	 * @param <TT> the requested type
	 * @param typedKey the typed key
	 * @return the corresponding value cast to the requested type
	 * @throws EntryNotFoundException if the key does not correspond to a value
	 * @throws ClassCastException if the key type is not assignable from the value type
	 */
	@Nonnull
	<TT> TT get(@Nonnull TypedKey<TT, ? extends K> typedKey) throws EntryNotFoundException, ClassCastException;

	@Nonnull
	<TT> TypedSupplier<TT> getSupplier(@Nonnull TypeToken<TT> type, @Nonnull K keyId) throws NullPointerException,
			EntryNotFoundException, ClassCastException;

	@Nonnull
	<TT> TypedSupplier<TT> getSupplier(@Nonnull TypedKey<TT, ? extends K> typedKey) throws NullPointerException,
			EntryNotFoundException, ClassCastException;

	@Nonnull
	Set<? extends K> keySet();

	@Nonnull
	Set<? extends TypedKey<?, ? extends K>> typedKeySet();

	@Nonnull
	Collection<?> values();

	@Nonnull
	Collection<? extends TypedSupplier<?>> valueSuppliers();

	@Nonnull
	Set<? extends Entry<? extends K, ? extends TypedSupplier<?>>> entrySet();

}
