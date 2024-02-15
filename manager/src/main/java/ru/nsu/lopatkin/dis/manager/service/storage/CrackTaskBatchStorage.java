package ru.nsu.lopatkin.dis.manager.service.storage;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.nsu.lopatkin.dis.models.manager.entity.CrackStatus;
import ru.nsu.lopatkin.dis.models.manager.entity.TaskStatus;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CrackTaskBatchStorage {

    private Map<String, CrackTasksBatch> tasksBatchStorage = new HashMap<>();

    public void addCrackTasksBatch(String requestId, CrackTasksBatch crackTasksBatch) {
        tasksBatchStorage.put(requestId, crackTasksBatch);
    }

    public void updateTaskStatus(String requestId, String taskId, TaskStatus taskStatus) {
        tasksBatchStorage.get(requestId).updateCrackTaskStatus(taskStatus, taskId);
    }

    public void updateTaskStatus(String requestId, String taskId, TaskStatus taskStatus, String errorMessage) {
        tasksBatchStorage.get(requestId).updateCrackTaskStatus(taskStatus, taskId, errorMessage);
    }

    public CrackTasksBatch getCrackTasksBatchByRequestId(String requestId) {
        return tasksBatchStorage.get(requestId);
    }

}
