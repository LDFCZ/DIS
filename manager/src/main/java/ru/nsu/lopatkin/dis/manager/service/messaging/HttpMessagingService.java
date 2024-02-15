package ru.nsu.lopatkin.dis.manager.service.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.nsu.lopatkin.dis.manager.service.CrackingLifeCycleService;
import ru.nsu.lopatkin.dis.manager.util.exception.MessagingException;
import ru.nsu.lopatkin.dis.models.manager.entity.TaskStatus;
import ru.nsu.lopatkin.dis.models.worker.request.PartialHashCrackingRequest;
import ru.nsu.lopatkin.dis.models.worker.response.PartialHashCrackingResponse;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(value = "interaction.interface", havingValue = "http", matchIfMissing = true)
public class HttpMessagingService implements MessagingService{

    private final RestTemplateWrapper restTemplate;
    private final CrackingLifeCycleService crackingLifeCycleService;

    @Override
    public void sendTaskToWorker(PartialHashCrackingRequest request) {
        ResponseEntity<PartialHashCrackingResponse> responseEntity;
        try {
            responseEntity = restTemplate
                    .post("/internal/api/worker/hash/crack/task", request, PartialHashCrackingResponse.class);

            if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
                log.error("Response {} on {} requestId: {}, taskId: {}", responseEntity.getBody(), request, request.getRequestId(), request.getTaskId());
                throw new MessagingException("Error during http request send: " + responseEntity.getStatusCode()
                        + " requestId: " + request.getRequestId() + " taskId: " + request.getTaskId());
            }

        } catch (Exception e) {
            log.error("Error during http request send, requestId: " + request.getRequestId() + " taskId: " + request.getTaskId(), e);
            crackingLifeCycleService.updateCrackTaskState(request.getRequestId(), request.getTaskId(), TaskStatus.ERROR,
                    "Error during http request send,"
                            + " requestId: " + request.getRequestId() + " taskId: " + request.getTaskId()
                            + " exception:" + e.getMessage());
        }

        crackingLifeCycleService.updateCrackTaskState(request.getRequestId(), request.getTaskId(), TaskStatus.IN_PROGRESS);
    }
}
