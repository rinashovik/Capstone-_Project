package org.launchCode.procrastiNOT.controllers.api;

import org.launchCode.procrastiNOT.data.TaskRepository;
import org.launchCode.procrastiNOT.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/api/task")
public class ApiTaskController {
    @Autowired
    private TaskRepository taskRepository;// injecting  CRUDRepository

//    @PostMapping("/")
//    Task newTask(@RequestBody Task newTask) {
//
//        return taskRepository.save(newTask);
//
//    }

    @PostMapping("/")
    public ResponseEntity postTheTask(@RequestBody Task newTask) {
//        return Event.createEvent(eventDto.getName(), eventDto.getDescription());
        return new ResponseEntity<>(taskRepository.save(newTask), HttpStatus.CREATED);
    }

    @GetMapping("/")
//    List<Task> getAllTask() {
    public ResponseEntity getAllTasks() {

        List<Task> tasks = (List<Task>) taskRepository.findAll();
        return new ResponseEntity<>(tasks, HttpStatus.OK);

    }


    @GetMapping("/{id}")//ResponseEntity is class
    public ResponseEntity getTaskById(@PathVariable Integer id) {

        //  if (taskId !=null) {
//        List<Task> optTask = taskRepository.findById(taskId);
        Optional<Task> optTask = taskRepository.findById(id);

        if (optTask.isPresent()) {
//            Task task = (Task) optTask.get(taskId);// Get task by ID
            return new ResponseEntity<>(optTask.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(optTask.get(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTask(@PathVariable int id, @RequestBody Task newTask) {
        Optional<Task> optTask = taskRepository.findById(id);
//        List<Task> optTask = taskRepository.findById(id);
//        Task theTask = taskRepository.findById(id);

////        List<Task> optTask = taskRepository.findById(Id);
//      if (optTask.isPresent()) {
//        if (id != null) {


            if (!taskRepository.existsById(id)) {
//                optTask.set(getTaskById(id));
                return new ResponseEntity(taskRepository.save(newTask), HttpStatus.ACCEPTED);
        }


        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTheTask(@PathVariable int id) {
//        boolean deleted = Event.deleteItem(id);
        taskRepository.deleteById(id);
        if (!taskRepository.existsById(id)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}