package ru.nsu.lopatkin.dis.manager.service.messaging;

import ru.nsu.lopatkin.dis.models.worker.request.PartialHashCrackingRequest;

public interface MessagingService {

    void sendTaskToWorker(PartialHashCrackingRequest request);

}
