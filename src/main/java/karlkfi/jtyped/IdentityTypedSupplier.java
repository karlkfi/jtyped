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
package karlkfi.jtyped;

import com.google.common.base.Preconditions;
import com.google.common.reflect.TypeToken;

public final class IdentityTypedSupplier<T> implements TypedSupplier<T> {

	private final TypeToken<T> type;
	private final T value;

	IdentityTypedSupplier(TypeToken<T> type, T value) {
		this.type = Preconditions.checkNotNull(type, "type is null");
		this.value = Preconditions.checkNotNull(value, "value is null");
		;
	}

	public static <TT> IdentityTypedSupplier<TT> create(TypeToken<TT> type, TT value) {
		return new IdentityTypedSupplier<TT>(type, value);
	}

	public TypeToken<T> getType() {
		return type;
	}

	public T get() {
		return value;
	}

}
