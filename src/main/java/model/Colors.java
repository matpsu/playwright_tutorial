package model;

import lombok.Getter;

public enum Colors {
    GREEN,
    BLUE,
    YELLOW,
    ORANGE,
    PURPLE;

    @Getter
    final String type;

    Colors() {
        this.type = this.name().toLowerCase();
    }
}
