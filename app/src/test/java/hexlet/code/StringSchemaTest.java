package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringSchemaTest {
    private final int twentytwo = 22;
    private static Validator v;

    @BeforeAll
    static void beforeAll() {
        v = new Validator();
    }

    @Test
    public void testEmptyRuleSchema() {
        StringSchema schema = v.string();
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid("fox say"));
    }

    @Test
    public void testRequiredRuleSchema() {
        StringSchema schema = v.string();
        schema.required();
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid("fox say"));
    }

    @Test
    public void testContainsRuleSchema() {
        StringSchema schema = v.string();
        schema.contains("fox");
        assertTrue(schema.isValid("fox say"));
        assertFalse(schema.isValid("what does the ...?"));
        schema.contains("what");
        assertFalse(schema.isValid("fox say"));
        assertTrue(schema.isValid("what does the fox say"));
    }

    @Test
    public void testMinLengthRuleSchema() {
        StringSchema schema = v.string();
        assertTrue(schema.minLength(2).isValid("what does the fox say"));
        assertFalse(schema.minLength(twentytwo).isValid("what does the fox say"));
    }
}
