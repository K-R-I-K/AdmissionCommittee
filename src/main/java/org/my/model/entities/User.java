package org.my.model.entities;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private long id;
    private String login;
    private String password;
    private Role role;

    private String name;
    private String email;
    private String city;
    private String region;
    private String educationalInstitution;

    public User() {
    }

    public User(User user) {
        this.id = user.id;
        this.login = user.login;
        this.password = user.password;
        this.role = user.role;
        this.name = user.name;
        this.email = user.email;
        this.city = user.city;
        this.region = user.region;
        this.educationalInstitution = user.educationalInstitution;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEducationalInstitution() {
        return educationalInstitution;
    }

    public void setEducationalInstitution(String educationalInstitution) {
        this.educationalInstitution = educationalInstitution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && login.equals(user.login) && password.equals(user.password) && role == user.role && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(city, user.city) && Objects.equals(region, user.region) && Objects.equals(educationalInstitution, user.educationalInstitution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, role, name, email, city, region, educationalInstitution);
    }

    public static User.Builder builder() {
        return new User().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public User.Builder id(Long id) {
            User.this.id = id;
            return this;
        }

        public User.Builder login(String login) {
            User.this.login = login;
            return this;
        }

        public User.Builder password(String password) {
            User.this.password = password;
            return this;
        }

        public User.Builder role(String role) {
            User.this.role = Role.valueOf(role);
            return this;
        }

        public User.Builder name(String name) {
            User.this.name = name;
            return this;
        }

        public User.Builder email(String email) {
            User.this.email = email;
            return this;
        }

        public User.Builder city(String city) {
            User.this.city = city;
            return this;
        }

        public User.Builder region(String region) {
            User.this.region = region;
            return this;
        }

        public User.Builder educationalInstitution(String educationalInstitution) {
            User.this.educationalInstitution = educationalInstitution;
            return this;
        }

        public User build() {
            return User.this;
        }
    }

}