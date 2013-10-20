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

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.google.common.reflect.TypeToken;

public final class MutableTypedSupplier<T> implements TypedSupplier<T> {

	private final TypeToken<T> type;
	private T value;

	public MutableTypedSupplier(@Nonnull TypeToken<T> type) {
		this.type = Preconditions.checkNotNull(type, "type is null");
	}

	public MutableTypedSupplier(@Nonnull TypeToken<T> type, T value) {
		this.type = Preconditions.checkNotNull(type, "type is null");
		this.value = value;
	}

	@Nonnull
	public TypeToken<T> getType() {
		return type;
	}

	public T get() {
		return value;
	}

	public void set(T newValue) {
		this.value = newValue;
	}

	@Nonnull
	public static <VV> MutableTypedSupplier<VV> of(@Nonnull TypeToken<VV> type, VV value) {
		return new MutableTypedSupplier<VV>(type, value);
	}

	@Nonnull
	public static <VV> MutableTypedSupplier<VV> nonnull(@Nonnull TypeToken<VV> type, @Nonnull VV value) {
		return new MutableTypedSupplier<VV>(type, Preconditions.checkNotNull(value, "value is null"));
	}

}
