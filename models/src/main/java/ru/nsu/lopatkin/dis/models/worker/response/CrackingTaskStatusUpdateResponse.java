package ru.nsu.lopatkin.dis.models.worker.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrackingTaskStatusUpdateResponse {
    private String requestId;
    private String taskId;
}
