package ru.nsu.lopatkin.dis.worker.service.messaging;

import ru.nsu.lopatkin.dis.models.worker.request.PartialHashCrackingRequest;

public interface MessagingService {

    void sendTaskToManager(PartialHashCrackingRequest request);

}
