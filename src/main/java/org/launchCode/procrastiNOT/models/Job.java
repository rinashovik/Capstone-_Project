package org.launchCode.procrastiNOT.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public class Job extends AbstractEntity{

@ManyToOne(cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();


    public Job() {
    }

    public Job(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}
