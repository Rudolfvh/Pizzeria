package spring.database.entity;

import jakarta.annotation.Nullable;

import java.util.Arrays;
import java.util.Optional;

public enum Role {
    USER(0),
    ADMIN(1);

    public static Optional<Role> find(String role) {
        return Arrays.stream(values())
                .filter(it -> it.name().equals(role))
                .findFirst();
    }

    private Integer id;

    Role(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }
    @Nullable
    public static Role fromId(Integer id) {
        for (Role at : Role.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}

