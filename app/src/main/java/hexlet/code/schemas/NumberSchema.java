package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {
    @Override
    public boolean isValid(Object parameter) {
        return (parameter == null || parameter instanceof Integer) && super.isValid(parameter);
    }

    public NumberSchema required() {
        addRule(x -> x != null);
        return this;
    }

    public NumberSchema positive() {
        addRule(x -> x == null || x > 0);
        return this;
    }

    public NumberSchema range(final int value1, final int value2) {
        addRule(x -> x >= value1 && x <= value2);
        return this;
    }
}
