package model;

import lombok.Getter;

public enum IdeType {
    ECLIPSE(),
    MAVEN(),
    TESTNG(),
    JUNIT();

    @Getter
    final String type;

    IdeType() {
        this.type = this.name().toLowerCase();
    }
}
