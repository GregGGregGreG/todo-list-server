package edu.greg.spring.todo.persistence.model;

import edu.greg.spring.todo.persistence.dto.TaskDto;

/**
 * Created by greg on 03.09.15.
 */
public class TaskDtoBuilder {

    private TaskDto dto;

    public TaskDtoBuilder() {
        this.dto = new TaskDto();
    }


    public TaskDtoBuilder text(String description) {
        dto.setText(description);
        return this;
    }

    public TaskDtoBuilder created(String created) {
        dto.setCreated(created);
        return this;
    }

    public TaskDtoBuilder performer(String performer) {
        dto.setPerformer(performer);
        return this;
    }

    public TaskDto build() {
        return dto;
    }

}
