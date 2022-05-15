package hexlet.code.schemas;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Predicate;

public class BaseSchema<T> {
    private List<Predicate<T>> rules = new LinkedList<>();

    public final void addRule(Predicate<T> predicate) {
        rules.add(predicate);
    }

    public boolean isValid(Object parameter) {
        for (Predicate rule: rules) {
            if (!rule.test(parameter)) {
                return false;
            }
        }
        return true;
    }
}
