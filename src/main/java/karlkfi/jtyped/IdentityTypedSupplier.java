package karlkfi.jtyped;

import com.google.common.base.Preconditions;
import com.google.common.reflect.TypeToken;

public final class IdentityTypedSupplier<T> implements TypedSupplier<T> {

	private final TypeToken<T> type;
	private final T value;
	
	public IdentityTypedSupplier(TypeToken<T> type, T value) {
		this.type = Preconditions.checkNotNull(type, "type is null");
		this.value = Preconditions.checkNotNull(value, "value is null");;
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
