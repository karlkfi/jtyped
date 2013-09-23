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
import karlkfi.jtyped.TypedSupplier;

/**
 * A map of keys to {@link TypedSupplier}s.
 * 
 * Typed maps cannot contain duplicate keys IDs; each key ID can map to at most one value.
 * 
 * @param <ID> the key ID type
 */
public interface TypedMap<ID> extends Sized {

	<T> boolean contains(@Nonnull TypedKey<T, ? extends ID> typedKey);
	
	<T> boolean contains(@Nonnull ID id);

	/**
	 * Returns the value to which the specified key is mapped.
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
	
	Object get(@Nonnull ID id) throws EntryNotFoundException;

	@Nonnull
	Set<? extends TypedKey<?, ? extends ID>> keys();

	@Nonnull
	Set<? extends Entry<? extends TypedKey<?, ? extends ID>, ?>> entries();

}
