package cn.yam.backmanage.utils;

/**
 * 功能：
 * 日期：2024/7/6 上午9:57
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for mapping programming language names to their respective types.
 */
public class ReflectionUtils {

    private static final Map<String, String> typeMap = new HashMap<>();

    static {

        typeMap.put("javascript", "typescript");
        typeMap.put("x-java", "java");
        typeMap.put("x-c++src", "cpp");
        typeMap.put("x-c", "c");
        typeMap.put("x-python", "python3");
        typeMap.put("x-sql", "sql");
        typeMap.put("x-shell", "shell");
        typeMap.put("x-powershell", "powershell");
        typeMap.put("x-php", "php");
    }

    /**
     * Maps a simple language name to its type.
     *
     * @param language The simple name of the language.
     * @return The mapped type of the language.
     */
    public static String mapType(String language) {
        String typeMapOrDefault = typeMap.getOrDefault(language.toLowerCase(), "unknown");
        if (typeMapOrDefault.equals("unknown")) {
            throw new IllegalArgumentException("Unsupported language: " + language);
        }
        return typeMapOrDefault;
    }
}