package org.launchCode.procrastiNOT.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
public class Task extends AbstractEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;

    @NotNull
    @NotBlank
    @Size(min=8, max=90, message = "Must be longer than 8 characters")
    private String description;

    public Task() {
    }

    public Task(String description) {
        this.description = description;
    }

//    public int getId() {
//        return id;
//    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    @Override
//    public String toString() {
//        return "description";
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Task that)) return false;
//        return id == that.id && Objects.equals(description, that.description);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, description);
//    }

}
