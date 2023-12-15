
package org.launchCode.procrastiNOT.controllers;
import jakarta.validation.Valid;
import org.launchCode.procrastiNOT.data.TaskRepository;
//import org.launchCode.procrastiNOT.models.Job;
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
    public String ShowTaskFormIndex (@ModelAttribute @Valid Task newTask, Errors errors, Model model){
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

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable List<Integer> taskIds, Model model) {
        List<Task> optTask = (List<Task>) taskRepository.findAll();
//        .orElseThrow(() - > new IllegalArgumentException("Invalid Task Id:" + taskId));
        model.addAttribute("task", optTask);
        return "update";
    }



/*
    @PostMapping("edit/{id}")
    public String displayViewTask(@ModelAttribute Model model,@Valid Task newTask, Errors errors, @RequestParam(required = false) List<Integer>  taskIds) {

       if (taskIds !=null) {
           List<Task> selectedTask = (List<Task>) taskRepository.findAllById(taskIds);
           Job job = new Job();
           job.setTasks(selectedTask);
       }
            if(errors.hasErrors()){
                System.out.println(errors.getAllErrors());
       List<Task> tasks = (List<Task>) taskRepository.findAll();

                return "update";
            }
            else {
                taskRepository.save(newTask);
                model.addAttribute("tasks", taskRepository.findAll());// passing task object's values


                return "redirect:/";
            }
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
*/
//    @GetMapping("edit/{id}")
//    public String DisplayUpdateForm(@PathVariable("id") long id, @Valid Task newTask,
//                                    Model model) {
//        if (result.hasErrors()) {
//            task.setId(id);
//            return "update";
//        }
//        taskRepository.save(newTask);
//        model.addAttribute("task", studentRepository.findAll());
//        return "index";
//    }

    @GetMapping("/delete")
    public String renderDeleteArtForm(Model model) {
        model.addAttribute("jobs", taskRepository.findAll());
        return "delete";
    }

    @PostMapping("/delete")
    public String processDeleteTaskForm(@RequestParam(required = false) int[] jobId) {
        for (int id : jobId) {
            taskRepository.deleteById(id);
        }
        return "redirect:/";
    }

}
