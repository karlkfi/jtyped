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

import com.google.common.reflect.TypeToken;

/**
 * A reference to an object of a certain generic type.
 * Note that because the class is generically typed it cannot refer to its own type (because of generic looping).
 * This parent type does not specify how the reference is linked for flexibility of interface extension.
 * 
 * @param <T> the generic type of the referenced object
 */
public interface TypedReference<T> {

	@Nonnull
	TypeToken<T> getType();

}
