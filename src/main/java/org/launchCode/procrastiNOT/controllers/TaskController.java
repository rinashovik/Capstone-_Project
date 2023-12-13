
package org.launchCode.procrastiNOT.controllers;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import org.launchCode.procrastiNOT.data.TaskRepository;
import org.launchCode.procrastiNOT.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

@GetMapping("/")
public String index (Model model){
        model.addAttribute("title", "Apps");
        model.addAttribute("tasks", taskRepository.findAll());
    model.addAttribute(new Task());

    return "index";

    }
    @PostMapping("/")
    public String index (@ModelAttribute @Valid Task newTask, Errors errors, Model model){
        model.addAttribute("title", "Apps");
        model.addAttribute("tasks", taskRepository.findAll());
        model.addAttribute(new Task());

        if(errors.hasErrors()){
            return "index";
        }
        else {
            taskRepository.save(newTask);
            return "redirect:/";
        }
}


    @GetMapping("/add")
    public String displayCreateTaskForm (Model model){

        model.addAttribute("title", "Add Task");

        model.addAttribute("tasks", taskRepository.findAll());// Display all listed tasks
        model.addAttribute(new Task());
        return "add";
    }

    @PostMapping("/add")
    public String processCreateTaskForm (@ModelAttribute @Valid Task newTask, Errors errors, Model model  ){

        model.addAttribute("tasks", taskRepository.findAll());// Display all listed tasks


        if(errors.hasErrors()){
            return "add";
        }
        else {
            taskRepository.save(newTask);

            model.addAttribute("tasks", taskRepository.findAll());// Display all listed tasks

            return "redirect:/";
          //  return "tasks-list";
        }
    }

//    @PostMapping("edit/{id}")
//    public String updateTask(@PathVariable("id") long id, @Valid Task newTask,
//                                Model model) {
//        if (result.hasErrors()) {
//            task.setId(id);
//            return "update";
//        }
//                taskRepository.save(newTask);
//        model.addAttribute("task", studentRepository.findAll());
//        return "index";
//    }



    @GetMapping("edit/{taskId}")
    public String displayViewTask(Model model,@Valid Task newTask, Errors errors, @PathVariable int taskId) {

     //  if (taskId !=null) {
        List<Task> optTask = taskRepository.findById(taskId);
        if (optTask.isEmpty()) {
            Task task = (Task) optTask.get(taskId);// Get task by ID
            task.setId(taskId);
        }
            if(errors.hasErrors()){
                return "update";
            }
            else {
                taskRepository.save(newTask);
                return "redirect:/index";
            }
//            model.addAttribute("tasks", task);// passing task object's values
//
//   //        }
//            return "update";
//
//        } else {
//
//              model.addAttribute("tasks", taskRepository.findAll());// Optional code
//
//            return "redirect:/";

//        }

    }

}
