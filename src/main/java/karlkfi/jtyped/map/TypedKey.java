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

import javax.annotation.Nonnull;

import karlkfi.jtyped.TypedReference;

/**
 * A composite key with a generic ID and a generic type.
 * 
 * @param <T> the key type
 * @param <ID> the key id type
 */
public interface TypedKey<T, ID> extends TypedReference<T> {

	@Nonnull
	ID getId();

}
