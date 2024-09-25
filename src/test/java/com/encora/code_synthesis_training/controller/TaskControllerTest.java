package com.encora.code_synthesis_training.controller;

import com.encora.code_synthesis_training.model.Task;
import com.encora.code_synthesis_training.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    void testGetAllTasks() throws Exception {
        Task task1 = new Task();
        Task task2 = new Task();
        when(taskService.getAllTasks()).thenReturn(Arrays.asList(task1, task2));

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testGetTaskById() throws Exception {
        Task task = new Task();
        when(taskService.getTaskById("1")).thenReturn(Optional.of(task));

        mockMvc.perform(get("/api/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(task.getId()));
    }

    @Test
    void testCreateTask() throws Exception {
        Task task = new Task();
        when(taskService.createTask(any(Task.class))).thenReturn(task);

        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Test Task\",\"description\":\"Test Description\",\"completed\":false}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(task.getId()));
    }

    @Test
    void testUpdateTask() throws Exception {
        Task task = new Task();
        when(taskService.updateTask(eq("1"), any(Task.class))).thenReturn(task);

        mockMvc.perform(put("/api/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated Task\",\"description\":\"Updated Description\",\"completed\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(task.getId()));
    }

    @Test
    void testDeleteTask() throws Exception {
        doNothing().when(taskService).deleteTask("1");

        mockMvc.perform(delete("/api/tasks/1"))
                .andExpect(status().isOk());

        verify(taskService, times(1)).deleteTask("1");
    }
}