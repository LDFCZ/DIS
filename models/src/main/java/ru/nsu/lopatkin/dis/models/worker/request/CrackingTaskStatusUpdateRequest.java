package ru.nsu.lopatkin.dis.models.worker.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nsu.lopatkin.dis.models.manager.entity.TaskStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrackingTaskStatusUpdateRequest {

    private String requestId;
    private String taskId;
    private TaskStatus status;
    private String errorMessage;
    private List<String> data;

}
