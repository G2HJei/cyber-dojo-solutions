package xyz.zlatanov.cyberdojo.wholikesit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LikesFormatterTest {

	LikesFormatter likesFormatter = new LikesFormatter();

	static Stream<Arguments> shouldFormatLikesLabel() {
		return Stream.of(
				Arguments.of(new String[] {}, "no one likes this"),
				Arguments.of(new String[] { "Jon" }, "Jon likes this"),
				Arguments.of(new String[] { "Jon", "Arya" }, "Jon and Arya like this"),
				Arguments.of(new String[] { "Jon", "Arya", "Robb" }, "Jon, Arya and Robb like this"),
				Arguments.of(new String[] { "Jon", "Arya", "Robb", "Sansa" }, "Jon, Arya and 2 others like this"));
	}

	@ParameterizedTest
	@MethodSource("shouldFormatLikesLabel")
	void shouldFormatLikesLabel(String[] param, String expected) {
		assertEquals(expected, likesFormatter.label(param));
	}
}