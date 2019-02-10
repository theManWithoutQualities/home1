package com.example.home1konstantinov.settings;

import java.io.Serializable;

public class Settings implements Serializable {
    private Theme theme;
    private Density density;

    public Theme getTheme() {
        return theme;
    }

    public Settings setTheme(Theme theme) {
        this.theme = theme;
        return this;
    }

    public Density getDensity() {
        return density;
    }

    public Settings setDensity(Density density) {
        this.density = density;
        return this;
    }
}
