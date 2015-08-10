package edu.greg.todolist.todo.util;

/**
 * Created by greg on 07.08.15.
 */
public enum TodoRole {

    USER("ROLE_USER");

    private final String name;

    TodoRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "TodoRole{" +
                "name='" + name + '\'' +
                '}';
    }
}
