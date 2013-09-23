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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import karlkfi.jtyped.TypedSupplier;

/**
 * An object that maps typed keys to typed values. A map cannot contain duplicate keys; each key can map to at most one
 * value.
 * 
 * @param <ID> the key ID type
 */
public interface MutableTypedMap<ID> extends TypedMap<ID> {

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
	<TT> TypedSupplier<TT> put(@Nonnull TypedKey<TT, ? extends ID> typedKey, @Nonnull TT value) throws NullPointerException,
			ClassCastException;

	/**
	 * Sets the type of an entry so that it cannot be changed.
	 * 
	 * This allows for performing type consistency checks on future {@link #put(TypedKey, Object)} calls without
	 * defining a value.
	 *
	 * @param <TT> the most specific type that can be requested for this value
	 * @param typedKey the typed key
	 * @throws NullPointerException if the specified <code>typedKey</code> is null
	 * @throws ClassCastException if the key corresponds to a value but the new key type does not match the value type
	 *             or if the key is of an inappropriate type for this map
	 */
	<TT> void setType(@Nonnull TypedKey<TT, ? extends ID> typedKey) throws NullPointerException, ClassCastException;

	@Nullable
	<TT> TypedSupplier<TT> remove(@Nonnull TypedKey<TT, ? extends ID> typedKey) throws NullPointerException,
			ClassCastException;

	@Nullable
	void putAll(@Nonnull TypedMap<? extends ID> m) throws NullPointerException, IllegalArgumentException,
			ClassCastException;

	/**
	 * Removes all of the mappings from this map. The map will be empty after this call returns.
	 */
	void clear();

}
