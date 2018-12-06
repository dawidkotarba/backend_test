package com.dawidkotarba.backendtest.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * This class servers as a basis for entities allowing them to be
 * identified by both ID (assigned by the data store) and UUID
 * from the early beginning (used also to calculate the hashcode).
 */
public class Identifiable implements Serializable {

    private Long id;
    private final UUID uuid;

    protected Identifiable() {
        uuid = UUID.randomUUID();
    }

    public Long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public synchronized void setId(final Long id) {
        if (this.id != null) {
            throw new IllegalArgumentException("ID has already been set");
        }
        this.id = id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Identifiable)) {
            return false;
        }
        final Identifiable that = (Identifiable) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return "Identifiable{"
                + "id=" + id
                + ", uuid=" + uuid
                + '}';
    }
}
