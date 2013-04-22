package karlkfi.jtyped.map;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.base.Joiner;
import com.google.common.base.Joiner.MapJoiner;
import com.google.common.primitives.Ints;

public final class TypedMaps {

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
			return map.entrySet().equals(o.entrySet());
		}
		return false;
	}

	/**
	 * An implementation of {@link TypedMap#toString}.
	 */
	static String toStringImpl(TypedMap<?> map) {
		StringBuilder sb = newStringBuilderForCollection(map.size()).append('{');
		STANDARD_JOINER.appendTo(sb, map.entrySet());
		return sb.append('}').toString();
	}

	/**
	 * Returns best-effort-sized StringBuilder based on the given collection size.
	 * 
	 * Based on {@link com.google.common.collect.Collections2}
	 */
	static StringBuilder newStringBuilderForCollection(int size) {
		checkArgument(size >= 0, "size must be non-negative");
		return new StringBuilder((int) Math.min(size * 8L, Ints.MAX_POWER_OF_TWO));
	}

	static final MapJoiner STANDARD_JOINER = Joiner.on(", ").useForNull("null").withKeyValueSeparator("=");

}
