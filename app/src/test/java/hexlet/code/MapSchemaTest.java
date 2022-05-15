package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {
    private final int hundred = 100;
    private final int fiveSubZero = -5;
    private static Validator v;

    @BeforeAll
    static void beforeAll() {
        v = new Validator();
    }

    @Test
    public void testRequiredRuleSchema() {
        MapSchema schema = v.map();
        assertTrue(schema.isValid(null));
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap()));
    }

    @Test
    public void testSizeofRuleSchema() {
        MapSchema schema = v.map();
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));
        schema.sizeof(2);
        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    public void testShapeRuleSchema() {
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("price", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> product1 = new HashMap<>();
        product1.put("name", "Milk");
        product1.put("price", hundred);
        assertTrue(schema.isValid(product1));

        Map<String, Object> product2 = new HashMap<>();
        product2.put("name", "Bread");
        product2.put("price", null);
        assertTrue(schema.isValid(product2));

        Map<String, Object> product3 = new HashMap<>();
        product3.put("name", "");
        product3.put("price", null);
        assertFalse(schema.isValid(product3));

        Map<String, Object> product4 = new HashMap<>();
        product4.put("name", "Butter");
        product4.put("price", fiveSubZero);
        assertFalse(schema.isValid(product4));
    }
}
