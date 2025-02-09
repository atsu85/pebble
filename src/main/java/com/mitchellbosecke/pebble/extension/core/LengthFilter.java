package com.mitchellbosecke.pebble.extension.core;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.extension.escaper.RawString;

public class LengthFilter implements Filter {

    @Override
    public List<String> getArgumentNames() {
        return null;
    }

    @Override
    public Object apply(Object input, Map<String, Object> args) {
        if (input == null) {
            return 0;
        }
        if (input instanceof RawString) {
            return input.toString().length();
        } else if (input instanceof String) {
            return ((String) input).length();
        } else if (input instanceof Collection) {
            return ((Collection<?>) input).size();
        } else if (input.getClass().isArray()) {
            return Array.getLength(input);
        } else if (input instanceof Map) {
            return ((Map<?, ?>) input).size();
        } else if (input instanceof Iterable) {
            Iterator<?> it = ((Iterable<?>) input).iterator();
            int size = 0;
            while (it.hasNext()) {
                it.next();
                size++;
            }
            return size;
        } else if (input instanceof Iterator) {
            Iterator<?> it = (Iterator<?>) input;
            int size = 0;
            while (it.hasNext()) {
                it.next();
                size++;
            }
            return size;
        }
        else {
            return 0;
        }
    }

}
