package org.my.model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Faculty implements Serializable {
    private long id;
    private String name;
    private long budgetPlaces;
    private long totalPlaces;

    public Faculty() {
    }

    public Faculty(Faculty faculty) {
        this.id = faculty.id;
        this.name = faculty.name;
        this.budgetPlaces = faculty.budgetPlaces;
        this.totalPlaces = faculty.totalPlaces;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBudgetPlaces() {
        return budgetPlaces;
    }

    public void setBudgetPlaces(long budgetPlaces) {
        this.budgetPlaces = budgetPlaces;
    }

    public long getTotalPlaces() {
        return totalPlaces;
    }

    public void setTotalPlaces(long totalPlaces) {
        this.totalPlaces = totalPlaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return id == faculty.id && budgetPlaces == faculty.budgetPlaces && totalPlaces == faculty.totalPlaces && name.equals(faculty.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, budgetPlaces, totalPlaces);
    }

    public static Faculty.Builder builder() {
        return new Faculty().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder id(Long id) {
            Faculty.this.id = id;
            return this;
        }

        public Builder name(String name) {
            Faculty.this.name = name;
            return this;
        }

        public Builder budgetPlaces(long budgetsPlaces) {
            Faculty.this.budgetPlaces = budgetsPlaces;
            return this;
        }

        public Builder totalPlaces(long totalPlaces) {
            Faculty.this.totalPlaces = totalPlaces;
            return this;
        }

        public Faculty build() {
            return Faculty.this;
        }
    }
}
