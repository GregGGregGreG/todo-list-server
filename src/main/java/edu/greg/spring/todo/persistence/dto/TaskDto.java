package edu.greg.spring.todo.persistence.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by greg on 07.07.15.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class TaskDto {

    private Integer id;
    private String text;
    private String created;
    private String performer;
    private Date publishedDate;
    private Boolean isExecuted;

}
