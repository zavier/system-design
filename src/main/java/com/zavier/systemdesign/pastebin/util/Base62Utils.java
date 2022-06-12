package com.zavier.systemdesign.pastebin.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class Base62Utils {

    private static final char[] data = new char[62];

    static {
        int index = 0;
        char c = 'A';
        for (int i = 0; i < 26; i++) {
            data[index++] = (char) (c + i);
        }
        c = 'a';
        for (int i = 0; i < 26; i++) {
            data[index++] = (char) (c + i);
        }
        c = '0';
        for (int i = 0; i < 10; i++) {
            data[index++] = (char) (c + i);
        }
    }

    public static String base62(Long num) {
        if (num <= 0) {
            throw new RuntimeException("num必须大于0");
        }
        long temp = num;
        List<Long> vals = new ArrayList<>();
        while (temp > 0) {
            long v = temp % 62;
            temp /= 62;
            vals.add(v);
        }
        return vals.stream()
                .map(v -> getValueByIndex(v.intValue()))
                .sorted(Comparator.reverseOrder())
                .reduce("", (s, character) -> s + character.toString(), (s, s2) -> s + s2);

    }

    private static char getValueByIndex(int index) {
        return data[index];
    }

}
