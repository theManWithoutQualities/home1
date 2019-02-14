package com.example.home1konstantinov;

import android.graphics.Color;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public final class ColorDataUtil {

    private static final Random random = new Random();
    public static final int INITIAL_LIST_SIZE = 1000;

    private ColorDataUtil() {}

    public static List<Integer> getRandomColorList() {
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < INITIAL_LIST_SIZE; i++) {
            int color = getRandomColor();
            result.add(color);
        }
        return result;
    }

    public static int getRandomColor() {
        return Color.rgb(
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256)
        );
    }
}
