package edu.greg.spring.todo.persistence.service;

import edu.greg.spring.todo.persistence.model.TaskDtoBuilder;
import edu.greg.spring.todo.persistence.dto.TaskDto;
import edu.greg.spring.todo.persistence.model.Task;
import edu.greg.spring.todo.persistence.model.User;
import edu.greg.spring.todo.persistence.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

/**
 * Created by greg on 03.09.15.
 */
@RunWith(MockitoJUnitRunner.class)
public class TaskServicesTest {

    private static final Long ID = 1L;
    private static final String DESCRIPTION = "description";
    private static final String DESCRIPTION_UPDATED = "updatedDescription";
    private static final String TITLE = "title";
    private static final String TITLE_UPDATED = "updatedTitle";

    private static final String USER_NAME = "GREG";
    private static final String USER_EMAIL = "1@mail.ru";
    private static final String USER_PASSWORD = "123";

    @Mock
    private TaskRepository taskRepositoryMock;

    @InjectMocks
    private DefaultTaskService taskService;

    @Test
    public void add_NewTaskEntry_ShouldSaveTaskEntry() {

        TaskDto dto = new TaskDtoBuilder()
                .text(DESCRIPTION)
                .performer("")
                .build();

        taskService.addTaskToUser(dto, User.getBuilder(USER_NAME).build());

        ArgumentCaptor<Task> toDoArgument = ArgumentCaptor.forClass(Task.class);
        verify(taskRepositoryMock, times(1)).save(toDoArgument.capture());
        verifyNoMoreInteractions(taskRepositoryMock);

        Task model = toDoArgument.getValue();

        assertNull(model.getId());
        assertThat(model.getText(), is(dto.getText()));
    }
}
