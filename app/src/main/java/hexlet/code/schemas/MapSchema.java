package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map> {
    @Override
    public boolean isValid(Object parameter) {
        return (parameter == null || parameter instanceof Map) && super.isValid(parameter);
    }

    public MapSchema required() {
        addRule(x -> x != null);
        return this;
    }

    public MapSchema sizeof(int count) {
        addRule(x -> x != null && x.size() == count);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schema) {
        addRule(x -> {
            for (Object key: x.keySet()) {
                if (!schema.get(key).isValid(x.get(key))) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }

}
