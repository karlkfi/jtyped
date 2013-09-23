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

import karlkfi.jtyped.TypedSupplier;

import com.google.common.collect.ImmutableMap;

/**
 * 
 * @author Karl
 *
 * @param <ID> the key ID type
 */
final class RegularImmutableTypedMap<ID> extends ImmutableTypedMap<ID> {

	private ImmutableMap<? extends ID, ? extends TypedSupplier<?>> delegate;

	/**
	 * Constructs a new ImmutableTypedMap with the same mappings as the specified Map. If <code>m</code> is an
	 * {@link ImmutableMap} it will be used as the delegate map instead of being copied.
	 * 
	 * @param m the m
	 */
	RegularImmutableTypedMap(Map<? extends ID, ? extends TypedSupplier<?>> m) {
		if (m instanceof ImmutableMap) {
			this.delegate = (ImmutableMap<? extends ID, ? extends TypedSupplier<?>>) m;
		} else {
			this.delegate = ImmutableMap.copyOf(m);
		}
	}

	/**
	 * Delegate accessor (read-only).
	 * 
	 * @return the immutable map that this typed map delegates to
	 */
	@Override
	ImmutableMap<? extends ID, ? extends TypedSupplier<?>> delegate() {
		return delegate;
	}

}
