package ru.maria.willey.testing.model;

import java.io.Serializable;

public class User implements Serializable {
          private int id;
          private String name;
          private String surname;

          public int getId() {
                    return id;
          }

          public String getName() {
                    return name;
          }

          public String getSurname() {
                    return surname;
          }

          public User(int id, String name, String surname) {

                    this.id = id;
                    this.name = name;
                    this.surname = surname;
          }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return surname != null ? surname.equals(user.surname) : user.surname == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
