package talent.campus.examenRubenDiaz;

import org.junit.Assert;

import java.util.List;

public class AssertUtils {

    private AssertUtils() {}

    public static <T extends Object> void assertEquals(List<T> expected, List<T> real) {
        Assert.assertEquals(String.format("Expected size: %s Real size: %s",expected.size(), real.size()), expected.size(), real.size());
        expected.forEach(item -> {
            Assert.assertTrue(String.format("Item not found: %s", item) , real.stream().anyMatch(s -> s.equals(item)));
        });
    }
}
