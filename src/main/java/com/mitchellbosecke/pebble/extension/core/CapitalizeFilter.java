/*******************************************************************************
 * This file is part of Pebble.
 * 
 * Copyright (c) 2014 by Mitchell Bösecke
 * 
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.mitchellbosecke.pebble.extension.core;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.extension.escaper.RawString;

public class CapitalizeFilter implements Filter {

    @Override
    public List<String> getArgumentNames() {
        return null;
    }

    @Override
    public Object apply(Object input, Map<String, Object> args) {
        if (input == null) {
            return null;
        }
        String value = input instanceof RawString ? input.toString() : (String) input;

        if (value.length() == 0) {
            return value;
        }

        StringBuilder result = new StringBuilder();

        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            if (Character.isWhitespace(c)) {
                result.append(c);
            } else {
                result.append(Character.toTitleCase(c));
                result.append(Arrays.copyOfRange(chars, i + 1, chars.length));
                break;
            }
        }

        return result.toString();
    }

}
