package hexlet.code.schemas;

import java.util.Objects;

public final class StringSchema extends BaseSchema<String> {
    @Override
    public boolean isValid(Object parameter) {
        return (parameter == null || parameter instanceof String) && super.isValid(parameter);
    }

    public StringSchema required() {
        addRule(x -> (x != null && !(Objects.equals(x, ""))));
        return this;
    }

    public StringSchema contains(String strToCompare) {
        addRule(x -> (x != null && x.contains(strToCompare)));
        return this;
    }

    public StringSchema minLength(int minLength) {
        addRule(x -> (x != null && x.length() >= minLength));
        return this;
    }
}
