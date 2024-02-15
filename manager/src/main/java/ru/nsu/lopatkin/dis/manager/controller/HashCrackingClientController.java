package ru.nsu.lopatkin.dis.manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.lopatkin.dis.manager.service.CrackingService;
import ru.nsu.lopatkin.dis.models.manager.entity.CrackStatus;
import ru.nsu.lopatkin.dis.models.manager.request.HashCrackRequest;
import ru.nsu.lopatkin.dis.models.manager.response.HashCrackResponse;
import ru.nsu.lopatkin.dis.models.manager.response.HashCrackStatusResponse;

import java.util.Collections;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hash")
public class HashCrackingClientController {

    private final CrackingService crackingService;

    @PostMapping("/crack")
    public HashCrackResponse startHashCracking(@RequestBody HashCrackRequest request) {
        return crackingService.startHashCracking(request);
    }

    @GetMapping("/status")
    public HashCrackStatusResponse getHashCrackingStatusByRequestId(@RequestParam String requestId) {
        return crackingService.getCrackStatusByRequestId(requestId);
    }
}
