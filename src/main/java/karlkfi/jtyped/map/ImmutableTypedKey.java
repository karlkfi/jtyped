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

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;
import com.google.common.reflect.TypeToken;

@Immutable
public final class ImmutableTypedKey<T, ID> implements TypedKey<T, ID> {

	final TypeToken<T> type;
	final ID id;

	ImmutableTypedKey(TypeToken<T> type, ID id) {
		this.type = Preconditions.checkNotNull(type, "type is null for id=%s", id);
		this.id = Preconditions.checkNotNull(id, "id is null for type=%s", type);
	}

	public static <TT, I> ImmutableTypedKey<TT, I> of(TypeToken<TT> type, I id) {
		return new ImmutableTypedKey<TT, I>(type, id);
	}
	
	public static <TT, I> ImmutableTypedKey<TT, I> of(Class<TT> type, I id) {
		return new ImmutableTypedKey<TT, I>(TypeToken.of(type), id);
	}

	public TypeToken<T> getType() {
		return type;
	}

	public ID getId() {
		return id;
	}

}
