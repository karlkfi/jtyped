package karlkfi.jtyped.map;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

import karlkfi.jtyped.TypeTokens;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

@RunWith(JUnit4.class)
public class ImmutableTypedMapTest {

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
		ImmutableTypedMap<String> tmap = ImmutableTypedMap.of(
				ImmutableTypedKey.of(String.class, "a"), "1");
		assertThat(tmap.size(), equalTo(1));
	}

	@Test
	public void testOf2() {
		ImmutableTypedMap<String> tmap = ImmutableTypedMap.of(
				ImmutableTypedKey.of(String.class, "a"), "1",
				ImmutableTypedKey.of(Integer.class, "b"), Integer.valueOf(2));
		assertThat(tmap.size(), equalTo(2));
	}

	@Test
	public void testOf3() {
		ImmutableTypedMap<String> tmap = ImmutableTypedMap.of(
				ImmutableTypedKey.of(String.class, "a"), "1",
				ImmutableTypedKey.of(Long.class, "b"), Long.valueOf(2L),
				ImmutableTypedKey.of(String.class, "c"), "3");
		assertThat(tmap.size(), equalTo(3));
	}

	@Test
	public void testOf4() {
		ImmutableTypedMap<String> tmap = ImmutableTypedMap.of(
				ImmutableTypedKey.of(String.class, "a"), "1",
				ImmutableTypedKey.of(String.class, "b"), "2",
				ImmutableTypedKey.of(TypeTokens.listOf(String.class), "c"), Lists.newArrayList("3a", "3b"),
				ImmutableTypedKey.of(String.class, "d"), "4");
		assertThat(tmap.size(), equalTo(4));
	}

	@Test
	public void testOf5() {
		ImmutableTypedMap<String> tmap = ImmutableTypedMap.of(
				ImmutableTypedKey.of(String.class, "a"), "1",
				ImmutableTypedKey.of(String.class, "b"), "2",
				ImmutableTypedKey.of(TypeTokens.mapOf(String.class, String.class), "c"), ImmutableMap.of("3k", "3v"),
				ImmutableTypedKey.of(String.class, "d"), "4",
				ImmutableTypedKey.of(String.class, "e"), "5");
		assertThat(tmap.size(), equalTo(5));
	}
	
	@Test
	public void testOfRaw1() {
		ImmutableTypedMap<String> tmap = ImmutableTypedMap.ofRaw("a", "1");
		assertNotNull(tmap.get("a"));
		assertNotNull(tmap.get(ImmutableTypedKey.of(String.class, "a")));
		assertThat(tmap.size(), equalTo(1));
	}
	
	@Test(expected=ClassCastException.class)
	public void testOfRaw1TooSpecific() {
		ImmutableTypedMap<String> tmap = ImmutableTypedMap.ofRaw("a", Lists.newArrayList("1a", "1b"));
		assertNotNull(tmap.get("a"));
		assertThat(tmap.size(), equalTo(1));
		// fail on too specific of a key
		tmap.get(ImmutableTypedKey.of(TypeTokens.listOf(String.class), "a"));
	}

	@Test
	public void testOfRaw2() {
		ImmutableTypedMap<String> tmap = ImmutableTypedMap.ofRaw("a", "1", "b", Integer.valueOf(2));
		assertThat(tmap.get(String.class, "a"), equalTo("1"));
		assertThat(tmap.get(ImmutableTypedKey.of(Integer.class, "b")), equalTo(2));
		assertThat(tmap.size(), equalTo(2));
	}

	@Test
	public void testOfRaw3() {
		ImmutableTypedMap<String> tmap = ImmutableTypedMap.ofRaw("a", "1", "b", Long.valueOf(2L), "c", "3");
		assertThat(tmap.get(String.class, "a"), equalTo("1"));
		assertThat(tmap.get(ImmutableTypedKey.of(Long.class, "b")), equalTo(2L));
		assertThat(tmap.get(String.class, "c"), equalTo("3"));
		assertThat(tmap.size(), equalTo(3));
	}

	@Test
	public void testOfRaw4() {
		ImmutableTypedMap<String> tmap = ImmutableTypedMap.ofRaw("a", "1", "b", "2", "c", Lists.newArrayList("3a", "3b"), "d", "4");
		assertThat(tmap.get(String.class, "a"), equalTo("1"));
		assertThat(tmap.get(String.class, "b"), equalTo("2"));
		assertThat(tmap.get(ImmutableTypedKey.of(List.class, "c")), equalTo((List)Lists.newArrayList("3a", "3b")));
		assertThat(tmap.get(String.class, "d"), equalTo("4"));
		assertThat(tmap.size(), equalTo(4));
	}

	@Test
	public void testOfRaw5() {
		ImmutableTypedMap<String> tmap = ImmutableTypedMap.ofRaw("a", "1", "b", "2", "c", ImmutableMap.of("3k", "3v"), "d", "4", "e", "5");
		assertThat(tmap.get(String.class, "a"), equalTo("1"));
		assertThat(tmap.get(String.class, "b"), equalTo("2"));
		assertThat(tmap.get(ImmutableTypedKey.of(Map.class, "c")), equalTo((Map)ImmutableMap.of("3k", "3v")));
		assertThat(tmap.get(String.class, "d"), equalTo("4"));
		assertThat(tmap.get(String.class, "e"), equalTo("5"));
		assertThat(tmap.size(), equalTo(5));
	}

	@Test
	public void testBuilder() {
		ImmutableTypedMap.<String>builder()
			.put(ImmutableTypedKey.of(String.class, "a"), "1")
			.put(ImmutableTypedKey.of(Long.class, "b"), Long.valueOf(2L))
			.put(ImmutableTypedKey.of(TypeTokens.mapOf(String.class, String.class), "c"), ImmutableMap.of("3k", "3v"))
			.build();
	}

	@Test
	public void testIsEmpty() {
		assertTrue(ImmutableTypedMap.of().isEmpty());
		assertTrue(ImmutableTypedMap.builder().build().isEmpty());
		assertFalse(ImmutableTypedMap.of(ImmutableTypedKey.of(String.class, "a"), "1").isEmpty());
	}

	@Test
	public void testContainsTypedKeyOfTQextendsID() {
		//fail("Not yet implemented");
	}

	@Test
	public void testContainsID() {
		TypedMap<String> m = ImmutableTypedMap.ofRaw("a", "1");
		assertTrue(m.contains("a"));
		assertFalse(m.contains("b"));
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
	public void testEntrySuppliers() {
		//fail("Not yet implemented");
	}

	@Test
	public void testKeys() {
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
