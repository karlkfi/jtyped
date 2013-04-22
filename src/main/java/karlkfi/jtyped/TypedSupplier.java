package karlkfi.jtyped;

import javax.annotation.Nonnull;

import com.google.common.base.Supplier;

public interface TypedSupplier<T> extends Supplier<T>, TypedObject<T> {

	/** {@inheritDoc} */
	@Nonnull
	T get();

}
