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
import javax.annotation.Nullable;

import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.reflect.TypeToken;

public class TypedSuppliers {

	@Nonnull
	public static <TT> TypedSupplier<TT> nullable(@Nonnull TypeToken<TT> type, @Nullable TT value) {
		return new ImmutableTypedSupplier<TT>(type, value);
	}
	
	@Nonnull
	public static <TT> TypedSupplier<TT> nonnull(@Nonnull TypeToken<TT> type, @Nonnull TT value) {
		return new ImmutableTypedSupplier<TT>(type, Preconditions.checkNotNull(value, "value is null"));
	}
	
	@Nonnull
	public static <TT> DelegatingTypedSupplier<TT> delegate(@Nonnull TypeToken<TT> type, @Nonnull Supplier<TT> delegate) {
		return new DelegatingTypedSupplier<TT>(type, delegate);
	}
	
	@Nonnull
	public static <TT> DelegatingTypedSupplier<TT> memoize(@Nonnull TypeToken<TT> type, @Nonnull Supplier<TT> delegate) {
		return delegate(type, Suppliers.memoize(delegate));
	}
	
}
