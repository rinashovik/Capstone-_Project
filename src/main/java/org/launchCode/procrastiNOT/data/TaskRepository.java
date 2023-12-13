package org.launchCode.procrastiNOT.data;

import org.launchCode.procrastiNOT.models.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
    List<Task> findByDescription(String description);
    List<Task> findById(int id);

}
