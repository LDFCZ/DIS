package ru.nsu.lopatkin.dis.worker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.lopatkin.dis.models.worker.request.PartialHashCrackingRequest;
import ru.nsu.lopatkin.dis.models.worker.response.PartialHashCrackingResponse;
import ru.nsu.lopatkin.dis.worker.service.CrackingService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/api/worker/hash")
public class HashCrackingInternalController {

    private final CrackingService crackingService;

    @PostMapping("/crack/task")
    public PartialHashCrackingResponse updateHashCrackingState(@RequestBody PartialHashCrackingRequest request) {
        log.info("Partial crack Request={}", request);
        return crackingService.startCrackingRoutine(request);
    }

}