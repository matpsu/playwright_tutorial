package model;

import lombok.Getter;

public enum LanguageType {
    JAVA(),
    C_SHARP("c#"),
    PYTHON(),
    SQL();

    @Getter
    final String type;

    LanguageType(String type) {
        this.type = type;
    }

    LanguageType() {
        this.type = this.name().toLowerCase();
    }
}
