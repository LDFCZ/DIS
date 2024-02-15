package ru.nsu.lopatkin.dis.manager.service.separator;

import ru.nsu.lopatkin.dis.manager.service.storage.CrackTasksBatch;

public interface TaskSeparator {

    CrackTasksBatch separateCrackTask(String hash, Integer maxLength);

}
