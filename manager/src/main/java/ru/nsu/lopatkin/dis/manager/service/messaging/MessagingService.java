package ru.nsu.lopatkin.dis.manager.service.messaging;

import org.springframework.scheduling.annotation.Async;
import ru.nsu.lopatkin.dis.models.worker.request.PartialHashCrackingRequest;
import ru.nsu.lopatkin.dis.models.worker.response.PartialHashCrackingResponse;

import java.util.concurrent.CompletableFuture;

public interface MessagingService {

    @Async
    void sendTaskToWorker(PartialHashCrackingRequest request);

}
