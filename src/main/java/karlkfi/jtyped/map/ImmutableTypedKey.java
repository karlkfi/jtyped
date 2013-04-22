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
