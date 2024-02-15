package ru.nsu.lopatkin.dis.manager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.lopatkin.dis.manager.service.storage.CrackTaskBatchStorage;
import ru.nsu.lopatkin.dis.models.manager.entity.TaskStatus;

@Service
@RequiredArgsConstructor
public class CrackingLifeCycleService {

    private final CrackTaskBatchStorage crackTaskBatchStorage;

    public void updateCrackTaskState(String requestId, String taskId, TaskStatus taskStatus) {
        updateCrackTaskState(requestId, taskId, taskStatus, null);
    }

    public void updateCrackTaskState(String requestId, String taskId, TaskStatus taskStatus, String errorMessage) {
        crackTaskBatchStorage.updateTaskStatus(requestId, taskId, taskStatus, errorMessage);
    }
}
