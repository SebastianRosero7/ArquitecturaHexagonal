package com.hexagonal.tasks.infrastructure.controllers;

import com.hexagonal.tasks.application.services.TaskServices;
import com.hexagonal.tasks.domain.models.AdditionalTaskInfo;
import com.hexagonal.tasks.domain.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")

public class TaskController {
    private final TaskServices taskServices;

    public TaskController(TaskServices taskServices) {
        this.taskServices = taskServices;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskServices.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);

    }

    @GetMapping("/{taskid}")
    public ResponseEntity<Task> getTaskById(@PathVariable long taskid) {
        return taskServices.getTask(taskid)
                .map(task -> new ResponseEntity<>(task,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskServices.getAllTasks();
        return new ResponseEntity<>(tasks,HttpStatus.OK);
    }

    @PutMapping("/{taskid}")
    public ResponseEntity<Task> updateTask(@PathVariable long taskid, @RequestBody Task taskUpdate) {
        return taskServices.updateTask(taskid,taskUpdate)
                .map(task -> new ResponseEntity<>(task,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable long taskId) {
        if(taskServices.deleteTask(taskId)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{taskId}/additionalInfo")
    public ResponseEntity<AdditionalTaskInfo> getTaskAdditionalInfo(@PathVariable long taskId) {
        AdditionalTaskInfo additionalTaskInfo = taskServices.getAdditionalTaskInfo(taskId);
        return new ResponseEntity<>(additionalTaskInfo,HttpStatus.OK);
    }
}
