package ru.nsu.lopatkin.dis.manager.service.separator;

import org.springframework.stereotype.Service;
import ru.nsu.lopatkin.dis.manager.service.storage.CrackTasksBatch;
import ru.nsu.lopatkin.dis.models.manager.entity.CrackTask;
import ru.nsu.lopatkin.dis.models.manager.entity.TaskStatus;

import java.util.Collections;
import java.util.UUID;

@Service
public class SimpleTaskSeparator implements TaskSeparator {
    @Override
    public CrackTasksBatch separateCrackTask(String hash, Integer maxLength) {
        CrackTasksBatch crackTasksBatch = new CrackTasksBatch();

        for (int i = 1; i <= maxLength; i++) {
            crackTasksBatch.addCrackTask(new CrackTask(UUID.randomUUID().toString(), hash, i, TaskStatus.NEW, null, Collections.emptyList()));
        }

        return crackTasksBatch;
    }
}
