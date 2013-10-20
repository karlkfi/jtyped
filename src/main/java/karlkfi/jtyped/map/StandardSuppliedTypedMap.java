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

import javax.annotation.concurrent.NotThreadSafe;

import karlkfi.jtyped.TypedSupplier;

import com.google.common.collect.ImmutableMap;

/**
 * Reference ImmutableTypedMap implementation, backed by an ImmutableMap.
 * 
 * @param <ID> the key ID type
 */
@NotThreadSafe
final class StandardSuppliedTypedMap<ID> extends SuppliedTypedMap<ID> {

	private Map<ID, TypedSupplier<Object>> delegate;

	/**
	 * Constructs a new ImmutableTypedMap with the same mappings as the specified Map. If <code>m</code> is an
	 * {@link ImmutableMap} it will be used as the delegate map instead of being copied.
	 * 
	 * @param m the map
	 */
	@SuppressWarnings("unchecked")
	StandardSuppliedTypedMap(Map<? extends ID, ? extends TypedSupplier<?>> m) {
		this.delegate = HashMaps.copyOf((Map<ID, TypedSupplier<Object>>) m);
	}

	/**
	 * Delegate accessor (read-only).
	 * 
	 * @return the immutable map that this typed map delegates to
	 */
	@Override
	protected Map<ID, TypedSupplier<Object>> delegate() {
		return delegate;
	}

}
