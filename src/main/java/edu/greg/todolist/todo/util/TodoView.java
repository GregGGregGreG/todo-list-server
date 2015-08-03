package edu.greg.todolist.todo.util;

import com.google.common.base.Preconditions;

/**
 * Created by greg on 30.07.15.
 */
public enum TodoView {

    LOGIN("login"),
    JOIN("join"),
    ACCOUNT("account"),
    P404("p404"),
    DEFAULT_ERROR("error");

    private final String logicalViewName;

    TodoView(String logicalViewName) {
        this.logicalViewName = Preconditions.checkNotNull(logicalViewName);
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
}
