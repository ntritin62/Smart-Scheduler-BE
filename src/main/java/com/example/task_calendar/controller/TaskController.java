package com.example.task_calendar.controller;

import com.example.task_calendar.dto.CalendarDTO;
import com.example.task_calendar.dto.TaskDTO;
import com.example.task_calendar.entity.Calendar;
import com.example.task_calendar.entity.Task;
import com.example.task_calendar.exception.ApiRequestException;
import com.example.task_calendar.response.ResponseHandler;
import com.example.task_calendar.service.calendar.CalendarService;
import com.example.task_calendar.service.task.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/task")
    public ResponseEntity<?> addTask(@Valid @RequestBody TaskDTO taskDTO) {

        Task task = taskService.createTask(taskDTO);
        if(task == null) {
            throw new ApiRequestException("calendarID doesn't exist.");
        }

        return ResponseHandler.responseBuilder("Created task successfully", HttpStatus.OK, task);


    }
}