package ru.nsu.lopatkin.dis.manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.lopatkin.dis.manager.service.CrackingLifeCycleService;
import ru.nsu.lopatkin.dis.models.worker.request.CrackingTaskStatusUpdateRequest;
import ru.nsu.lopatkin.dis.models.worker.response.CrackingTaskStatusUpdateResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/api/manager/hash")
public class HashCrackingInternalController {

    private final CrackingLifeCycleService crackingLifeCycleService;

    @PostMapping("/crack/request")
    public CrackingTaskStatusUpdateResponse startHashCracking(@RequestBody CrackingTaskStatusUpdateRequest request) {
        return crackingLifeCycleService.updateCrackTaskState(request);
    }

}
