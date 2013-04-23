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
 * @param <K> the key type
 */
final class EmptyImmutableTypedMap<K> extends ImmutableTypedMap<K> {
	static final EmptyImmutableTypedMap<Object> INSTANCE = new EmptyImmutableTypedMap<Object>();

	private ImmutableMap<? extends K, ? extends TypedSupplier<?>> delegate = ImmutableMap.of();

	private EmptyImmutableTypedMap() {
	}

	@Override
	ImmutableMap<? extends K, ? extends TypedSupplier<?>> delegate() {
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
	public <TT> boolean containsKeyId(TT key) {
		return false;
	}

	@Override
	public <TT> boolean containsTypedKey(TypedKey<TT, ? extends K> typedKey) {
		return false;
	}

	@Override
	public <TT> boolean containsValue(TT value) {
		return false;
	}

	@Override
	public <TT> boolean containsValueSupplier(TypedSupplier<TT> valueSupplier) {
		return false;
	}

	@Override
	public <TT> TT get(TypedKey<TT, ? extends K> typedKey) throws EntryNotFoundException, ClassCastException {
		throw new EntryNotFoundException("Value does not exist for the key: " + typedKey);
	}

	@Override
	public <TT> TypedSupplier<TT> getSupplier(TypedKey<TT, ? extends K> typedKey) throws EntryNotFoundException,
			ClassCastException {
		throw new EntryNotFoundException("Value supplier does not exist for the key: " + typedKey);
	}

	@Override
	ImmutableSet<? extends Entry<? extends K, ? extends TypedSupplier<?>>> createEntrySet() {
		return ImmutableSet.of();
	}

	@Override
	ImmutableSet<? extends K> createKeySet() {
		return ImmutableSet.of();
	}

	@Override
	ImmutableSet<? extends TypedKey<?, ? extends K>> createTypedKeySet() {
		return ImmutableSet.of();
	}

	@Override
	ImmutableSet<?> createValues() {
		return ImmutableSet.of();
	}

	@Override
	ImmutableSet<? extends TypedSupplier<?>> createValueSuppliers() {
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
