package com.encora.code_synthesis_training.service;

import com.encora.code_synthesis_training.model.Task;
import com.encora.code_synthesis_training.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTasks() {
        Task task1 = new Task();
        Task task2 = new Task();
        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));

        List<Task> tasks = taskService.getAllTasks();
        assertEquals(2, tasks.size());
    }

    @Test
    void testGetTaskById() {
        Task task = new Task();
        when(taskRepository.findById("1")).thenReturn(Optional.of(task));

        Optional<Task> foundTask = taskService.getTaskById("1");
        assertTrue(foundTask.isPresent());
    }

    @Test
    void testCreateTask() {
        Task task = new Task();
        when(taskRepository.save(task)).thenReturn(task);

        Task createdTask = taskService.createTask(task);
        assertNotNull(createdTask);
    }

    @Test
    void testUpdateTask() {
        Task task = new Task();
        when(taskRepository.save(task)).thenReturn(task);

        Task updatedTask = taskService.updateTask("1", task);
        assertNotNull(updatedTask);
    }

    @Test
    void testDeleteTask() {
        doNothing().when(taskRepository).deleteById("1");

        taskService.deleteTask("1");
        verify(taskRepository, times(1)).deleteById("1");
    }
}