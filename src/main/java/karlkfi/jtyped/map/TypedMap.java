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

import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Nonnull;

import karlkfi.jtyped.Sized;

/**
 * A map of unique key IDs to typed values that can be retrieved using typed keys.
 * This is similar to a standard Map except that each entry can have its own type.
 * 
 * Typed maps cannot contain duplicate keys IDs; each key ID can map to at most one value.
 * 
 * The map cannot contain null values. 
 * 
 * @param <ID> the key ID type
 */
public interface TypedMap<ID> extends Sized {

	/**
	 * Checks whether the map contains a value for the specified key that is assignable to the key type.
	 * 
	 * @param typedKey the typed key
	 * @return true if the map contains a value corresponding to the specified key ID that is assignable to the key type
	 */
	<T> boolean contains(@Nonnull TypedKey<T, ? extends ID> typedKey);
	
	/**
	 * Checks whether the map contains a value for the specified key ID.
	 * 
	 * @param keyId the key ID
	 * @return true if the map contains a value corresponding to the specified key ID 
	 */
	<T> boolean contains(@Nonnull ID keyId);

	/**
	 * Gets the value to which the specified key is mapped.
	 * 
	 * The type of the key indicates the type expected of the value. If the key type is assignable from the value type
	 * the value will be cast to it.
	 * 
	 * @param <T> the requested type
	 * @param typedKey the typed key
	 * @return the corresponding value cast to the requested type
	 * @throws EntryNotFoundException if the key does not correspond to a value
	 * @throws ClassCastException if the key type is not assignable from the value type
	 */
	@Nonnull
	<T> T get(@Nonnull TypedKey<T, ? extends ID> typedKey) throws EntryNotFoundException, ClassCastException;
	
	/**
	 * Gets the value to which the specified key is mapped.
	 * 
	 * The type of the valueType indicates the type expected of the value. If the key type is assignable from the value type
	 * the value will be cast to it.
	 * 
	 * @param <T> the requested type
	 * @param valueType the value type
	 * @param keyId the key id
	 * @return the corresponding value cast to the requested type
	 * @throws EntryNotFoundException if the key does not correspond to a value
	 * @throws ClassCastException if the key type is not assignable from the value type
	 */
	@Nonnull
	<T> T get(@Nonnull Class<T> valueType, @Nonnull ID keyId) throws EntryNotFoundException, ClassCastException;
	
	/**
	 * Gets the value to which the specified key ID is mapped.
	 * 
	 * This bypasses type checking.
	 * 
	 * TODO: is it really necessary to expose this?
	 * 
	 * @param keyId the key ID
	 * @return the corresponding value
	 * @throws EntryNotFoundException if the key does not correspond to a value
	 */
	@Nonnull
	Object get(@Nonnull ID keyId) throws EntryNotFoundException;

	/**
	 * Gets the set of typed keys that have corresponding values in this map.
	 * This only includes keys with the types as set, not all assignable types.
	 * 
	 * @return the set of typed keys that have corresponding values in this map
	 */
	@Nonnull
	Set<? extends TypedKey<?, ? extends ID>> keys();

	/**
	 * Gets the set of all typed key value pairs.
	 * This only includes keys with the types as set, not all assignable types.
	 * 
	 * @return the set of typed key value pairs
	 */
	@Nonnull
	Set<? extends Entry<? extends TypedKey<?, ? extends ID>, ?>> entries();

}
