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

import com.google.common.base.Preconditions;
import com.google.common.reflect.TypeToken;

public final class ImmutableTypedKey<T, K> implements TypedKey<T, K> {

	final TypeToken<T> type;
	final K key;

	ImmutableTypedKey(TypeToken<T> type, K key) {
		this.type = Preconditions.checkNotNull(type, "type is null for key=%s", key);
		this.key = Preconditions.checkNotNull(key, "key is null for type=%s", type);
	}

	public static <TT, KK> ImmutableTypedKey<TT, KK> create(TypeToken<TT> type, KK key) {
		return new ImmutableTypedKey<TT, KK>(type, key);
	}

	public TypeToken<T> getType() {
		return type;
	}

	public K getId() {
		return key;
	}

}
