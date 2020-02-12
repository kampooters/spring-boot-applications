package com.org.assignment.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author abdul.rehman4
 * Contains the utility methods
 */
public abstract class Util {
    private static final ObjectMapper oMapper = new ObjectMapper();

    public static String convertWithStream(Map<String, ?> map) {
        String mapAsString = map.keySet().stream()
                .map(key -> key + "=" + map.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
        return mapAsString;
    }

}
