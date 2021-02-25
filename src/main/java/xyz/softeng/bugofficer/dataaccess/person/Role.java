package xyz.softeng.bugofficer.dataaccess.person;

import lombok.Getter;

public enum Role {
    BACKEND(2),
    FRONTEND(1);

    Role(int maxSelection) {
        this.maxSelection = maxSelection;
    }

    @Getter
    private final int maxSelection;
}
