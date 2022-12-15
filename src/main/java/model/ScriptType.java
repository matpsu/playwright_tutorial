package model;

import lombok.Getter;

public enum ScriptType {
    HTML(),
    CSS(),
    JAVA_SCRIPT("javascript"),
    JQUERY();

    @Getter
    final String type;

    ScriptType(String type) {
        this.type = type;
    }

    ScriptType() {
        this.type = this.name().toLowerCase();
    }
}
