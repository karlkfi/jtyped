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
import com.google.common.base.Joiner;
import com.google.common.base.Joiner.MapJoiner;
import com.google.common.primitives.Ints;

final class TypedMaps {

	private TypedMaps() {
	}

	/**
	 * An implementation of {@link TypedMap#equals}.
	 */
	static boolean equalsImpl(TypedMap<?> map, Object object) {
		if (map == object) {
			return true;
		}
		if (object instanceof TypedMap) {
			TypedMap<?> o = (TypedMap<?>) object;
			return map.entries().equals(o.entries());
		}
		return false;
	}

	/**
	 * An implementation of {@link TypedMap#toString}.
	 */
	static String toStringImpl(TypedMap<?> map) {
		StringBuilder sb = newStringBuilderForCollection(map.size()).append('{');
		STANDARD_JOINER.appendTo(sb, map.entries());
		return sb.append('}').toString();
	}

	/**
	 * Returns best-effort-sized StringBuilder based on the given collection size.
	 * 
	 * Based on {@link com.google.common.collect.Collections2}
	 */
	static StringBuilder newStringBuilderForCollection(int size) {
		Preconditions.checkArgument(size >= 0, "size must be non-negative");
		return new StringBuilder((int) Math.min(size * 8L, Ints.MAX_POWER_OF_TWO));
	}

	static final MapJoiner STANDARD_JOINER = Joiner.on(", ").useForNull("null").withKeyValueSeparator("=");

}
