package ru.nsu.lopatkin.dis.worker.service.messaging;

import ru.nsu.lopatkin.dis.models.worker.request.CrackingTaskStatusUpdateRequest;

public interface MessagingService {

    void sendTaskToManager(CrackingTaskStatusUpdateRequest request);

}
