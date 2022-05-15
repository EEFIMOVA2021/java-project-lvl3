package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {
    private final int ten = 10;
    private final int five = 5;
    private final int tenSubZero = -10;
    private final int four = 4;
    private final int eleven = 11;
    private static Validator v;

    @BeforeAll
    static void beforeAll() {
        v = new Validator();
    }

    @Test
    public void testRequiredRuleSchema() {
        NumberSchema schema = v.number();
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(ten));
        assertFalse(schema.isValid("5"));
    }

    @Test
    public void testPositiveRuleSchema() {
        NumberSchema schema = v.number();
        schema.positive();
        assertTrue(schema.isValid(ten));
        assertFalse(schema.isValid(tenSubZero));
    }

    @Test
    public void testRangeRuleSchema() {
        NumberSchema schema = v.number();
        schema.range(five, ten);
        assertFalse(schema.isValid(four));
        assertTrue(schema.isValid(five));
        assertTrue(schema.isValid(ten));
        assertFalse(schema.isValid(eleven));
    }

}
