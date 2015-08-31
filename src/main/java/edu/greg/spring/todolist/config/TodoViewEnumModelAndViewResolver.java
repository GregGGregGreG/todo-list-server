package edu.greg.spring.todolist.config;

import edu.greg.spring.todolist.todo.util.TodoView;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Created by greg on 31.07.15.
 */
public class TodoViewEnumModelAndViewResolver implements HandlerMethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return TodoView.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public void handleReturnValue(Object returnValue,
                                  MethodParameter returnType,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {
        if (returnValue == null) {
            return;
        }
        if (returnValue instanceof TodoView) {
            String viewName = ((TodoView) returnValue).getLogicalViewName();
            mavContainer.setViewName(viewName);
        } else {
            // should not happen
            throw new UnsupportedOperationException("Unexpected return type: " +
                    returnType.getParameterType().getName() + " in method: " + returnType.getMethod());
        }
    }
}
