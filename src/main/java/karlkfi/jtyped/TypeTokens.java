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

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;

/**
 * Static utilities for constructing TypeTokens of standard collections.
 */
public final class TypeTokens {

	private TypeTokens() {

	}

	@SuppressWarnings("unchecked")
	public static TypeToken<Object> raw(@Nonnull Object v1) {
		Preconditions.checkNotNull(v1, "v1 is null");
		return TypeToken.of((Class<Object>) v1.getClass());
	}

	public static TypeToken<?> listOf(Type elementType) {
		return listOf(TypeToken.of(elementType));
	}

	public static <T> TypeToken<List<T>> listOf(Class<T> elementType) {
		return listOf(TypeToken.of(elementType));
	}

	@SuppressWarnings("serial")
	public static <T> TypeToken<List<T>> listOf(TypeToken<T> elementType) {
		return new TypeToken<List<T>>() {
		}.where(new TypeParameter<T>() {
		}, elementType);
	}

	public static TypeToken<?> setOf(Type elementType) {
		return setOf(TypeToken.of(elementType));
	}

	public static <T> TypeToken<Set<T>> setOf(Class<T> elementType) {
		return setOf(TypeToken.of(elementType));
	}

	@SuppressWarnings("serial")
	public static <T> TypeToken<Set<T>> setOf(TypeToken<T> elementType) {
		return new TypeToken<Set<T>>() {
		}.where(new TypeParameter<T>() {
		}, elementType);
	}

	public static TypeToken<?> mapOf(Type keyType, Type valueType) {
		return mapOf(TypeToken.of(keyType), TypeToken.of(valueType));
	}

	public static <K, V> TypeToken<Map<K, V>> mapOf(Class<K> keyType, Class<V> valueType) {
		return mapOf(TypeToken.of(keyType), TypeToken.of(valueType));
	}

	@SuppressWarnings("serial")
	public static <K, V> TypeToken<Map<K, V>> mapOf(TypeToken<K> keyType, TypeToken<V> valueType) {
		return new TypeToken<Map<K, V>>() {
		}.where(new TypeParameter<K>() {
		}, keyType).where(new TypeParameter<V>() {
		}, valueType);
	}

}
