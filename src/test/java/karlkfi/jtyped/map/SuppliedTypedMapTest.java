package karlkfi.jtyped.map;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import karlkfi.jtyped.TypeTokens;
import karlkfi.jtyped.TypedSuppliers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;

@RunWith(JUnit4.class)
public class SuppliedTypedMapTest {

	@Test
	public void testHashCode() {
		//fail("Not yet implemented");
	}

	@Test
	public void testOf0() {
		ImmutableTypedMap<String> tmap = ImmutableTypedMap.of();
		assertThat(tmap.size(), equalTo(0));
	}

	@Test
	public void testOf1() {
		SuppliedTypedMap<String> tmap = SuppliedTypedMap.of(
				"a", TypedSuppliers.nonnull(TypeToken.of(String.class), "1"));
		assertThat(tmap.size(), equalTo(1));
	}

	@Test
	public void testOf2() {
		SuppliedTypedMap<String> tmap = SuppliedTypedMap.of(
				"a", TypedSuppliers.nonnull(TypeToken.of(String.class), "1"),
				"b", TypedSuppliers.nonnull(TypeToken.of(Integer.class), Integer.valueOf(2)));
		assertThat(tmap.size(), equalTo(2));
	}

	@Test
	public void testOf3() {
		SuppliedTypedMap<String> tmap = SuppliedTypedMap.of(
				"a", TypedSuppliers.nonnull(TypeToken.of(String.class), "1"),
				"b", TypedSuppliers.nonnull(TypeToken.of(Long.class), Long.valueOf(2L)),
				"c", TypedSuppliers.nonnull(TypeToken.of(String.class), "3"));
		assertThat(tmap.size(), equalTo(3));
	}

	@Test
	public void testOf4() {
		SuppliedTypedMap<String> tmap = SuppliedTypedMap.of(
				"a", TypedSuppliers.nonnull(TypeToken.of(String.class), "1"),
				"b", TypedSuppliers.nonnull(TypeToken.of(String.class), "2"),
				"c", TypedSuppliers.nonnull(TypeTokens.listOf(String.class), Lists.newArrayList("3a", "3b")),
				"d", TypedSuppliers.nonnull(TypeToken.of(String.class), "4"));
		assertThat(tmap.size(), equalTo(4));
	}

	@Test
	public void testOf5() {
		SuppliedTypedMap<String> tmap = SuppliedTypedMap.of(
				"a", TypedSuppliers.nonnull(TypeToken.of(String.class), "1"),
				"b", TypedSuppliers.nonnull(TypeToken.of(String.class), "2"),
				"c", TypedSuppliers.nonnull(TypeTokens.mapOf(String.class, String.class), ImmutableMap.of("3k", "3v")),
				"d", TypedSuppliers.nonnull(TypeToken.of(String.class), "4"),
				"e", TypedSuppliers.nonnull(TypeToken.of(String.class), "5"));
		assertThat(tmap.size(), equalTo(5));
	}

	@Test
	public void testBuilder() {
		SuppliedTypedMap.<String>builder()
			.put("a", TypedSuppliers.nonnull(TypeToken.of(String.class), "1"))
			.put("b", TypedSuppliers.nonnull(TypeToken.of(String.class), "2"))
			.put("c", TypedSuppliers.nonnull(TypeTokens.mapOf(String.class, String.class), ImmutableMap.of("3k", "3v")))
			.build();
	}

	@Test
	public void testDelegate() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSize() {
		//fail("Not yet implemented");
	}

	@Test
	public void testIsEmpty() {
		//fail("Not yet implemented");
	}

	@Test
	public void testContainsTypedKeyOfTQextendsID() {
		//fail("Not yet implemented");
	}

	@Test
	public void testContainsID() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetTypedKeyOfTQextendsID() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetID() {
		//fail("Not yet implemented");
	}

	@Test
	public void testEntries() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCreateEntrySet() {
		//fail("Not yet implemented");
	}

	@Test
	public void testEntrySuppliers() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCreateEntrySupplierSet() {
		//fail("Not yet implemented");
	}

	@Test
	public void testKeys() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCreateKeySet() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCheckValueType() {
		//fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		//fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		//fail("Not yet implemented");
	}

}
