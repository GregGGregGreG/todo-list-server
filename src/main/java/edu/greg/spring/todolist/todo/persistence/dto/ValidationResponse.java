package edu.greg.spring.todolist.todo.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by greg on 06.08.15.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationResponse {
    private Boolean status;
    private String error;

    public ValidationResponse(Boolean status) {
        this.status = status;
    }
}
