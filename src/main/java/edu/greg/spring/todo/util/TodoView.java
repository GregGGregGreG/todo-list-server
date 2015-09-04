package edu.greg.spring.todo.util;

import java.util.Objects;

/**
 * Created by greg on 30.07.15.
 */
public enum TodoView {

    LOGIN("login", "/login"),
    JOIN("join", "/join"),
    ACCOUNT("account", "/account"),
    P404("p404", "/error/404"),
    DEFAULT_ERROR("error", "/error");

    private final String logicalViewName;
    private final String logicalViewUrl;

    TodoView(String logicalViewName, String logicalViewUrl) {
        this.logicalViewName = Objects.requireNonNull(logicalViewName);
        this.logicalViewUrl = Objects.requireNonNull(logicalViewUrl);
    }

    public static String getView(TodoView view) {
        return view.getLogicalViewName();
    }

    public static String redirectTo(TodoView view) {
        return "redirect:/" + view.getLogicalViewName();
    }

    public String getLogicalViewName() {
        return logicalViewName;
    }

    public String getLogicalViewUrl() {
        return logicalViewUrl;
    }
}
