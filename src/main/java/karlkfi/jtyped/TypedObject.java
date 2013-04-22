package karlkfi.jtyped;

import javax.annotation.Nonnull;

import com.google.common.reflect.TypeToken;

public interface TypedObject<T> {

	@Nonnull
	TypeToken<T> getType();
	
}
