package entity;

import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Optional;

public enum Role {
    USER(1),
    ADMIN(2);

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

