package karlkfi.jtyped.map;

import javax.annotation.Nonnull;

import karlkfi.jtyped.TypedObject;

public interface TypedKey<T,K> extends TypedObject<T> {

	@Nonnull
	K getKey();

}
