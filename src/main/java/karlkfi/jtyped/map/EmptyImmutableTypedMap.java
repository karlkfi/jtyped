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

import karlkfi.jtyped.TypedSupplier;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * TypedBiMap with no mappings.
 * 
 * @param <ID> the key ID type
 */
final class EmptyImmutableTypedMap<ID> extends ImmutableTypedMap<ID> {

	static final EmptyImmutableTypedMap<Object> INSTANCE = new EmptyImmutableTypedMap<Object>();

	private ImmutableMap<? extends ID, ? extends TypedSupplier<?>> delegate = ImmutableMap.of();

	private EmptyImmutableTypedMap() {
	}

	@Override
	protected ImmutableMap<? extends ID, ? extends TypedSupplier<?>> delegate() {
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
	public <T> boolean contains(TypedKey<T, ? extends ID> typedKey) {
		return false;
	}

	@Override
	public <T> T get(TypedKey<T, ? extends ID> typedKey) throws EntryNotFoundException, ClassCastException {
		throw new EntryNotFoundException("Value does not exist for the key: " + typedKey);
	}

	@Override
	ImmutableSet<? extends Entry<? extends TypedKey<?, ? extends ID>, ?>> createEntrySet() {
		return ImmutableSet.of();
	}
	
	@Override
	ImmutableSet<? extends Entry<? extends ID, ? extends TypedSupplier<?>>> createEntrySupplierSet() {
		return ImmutableSet.of();
	}

	@Override
	ImmutableSet<? extends TypedKey<?, ? extends ID>> createKeySet() {
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
