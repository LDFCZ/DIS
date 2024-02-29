package ru.nsu.lopatkin.dis.worker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.lopatkin.dis.models.manager.entity.CrackTask;
import ru.nsu.lopatkin.dis.models.manager.entity.TaskStatus;
import ru.nsu.lopatkin.dis.models.worker.request.PartialHashCrackingRequest;
import ru.nsu.lopatkin.dis.models.worker.response.PartialHashCrackingResponse;
import ru.nsu.lopatkin.dis.worker.service.combination.BaseCombinator;
import ru.nsu.lopatkin.dis.worker.service.messaging.MessagingService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
@RequiredArgsConstructor
public class CrackingService {

    private static final List<Character> ALPHABET = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');

    private static final ExecutorService executorService = Executors.newFixedThreadPool(2);

    private final MessagingService messagingService;

    public PartialHashCrackingResponse startCrackingRoutine(PartialHashCrackingRequest request) {
        CompletableFuture.runAsync(() -> crack(request), executorService);
        return new PartialHashCrackingResponse(request.getRequestId(), request.getTaskId());
    }

    private void crack(PartialHashCrackingRequest request) {
        CrackTask crackTask = request.getCrackTask();
        Integer partCount = crackTask.getPartCount();


        BaseCombinator<Character> baseCombinator = new BaseCombinator(ALPHABET, crackTask.getPartNumber(), partCount);

        List<String> result = new ArrayList<>();

        for (int i = 0; i < partCount; i++) {
            String combination = getStringRepresentation(baseCombinator.getNextCombination());

            if (getMD5Hash(combination).equals(request.getCrackTask().getHash())) {
                result.add(combination);
            }
        }


        if (!result.isEmpty()) {
            crackTask.setStatus(TaskStatus.SUCCESS);
            crackTask.setData(result);
        } else {
            crackTask.setStatus(TaskStatus.FAILED);
        }

        messagingService.sendTaskToManager(new PartialHashCrackingRequest(request.getRequestId(), request.getTaskId(), crackTask));

    }

    private String getStringRepresentation(List<Character> list) {
        StringBuilder builder = new StringBuilder(list.size());
        for (Character ch : list) {
            builder.append(ch);
        }

        return builder.toString();
    }

    private String getMD5Hash(String str) {
        byte[] bytesOfMessage = str.getBytes(StandardCharsets.US_ASCII);

        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        byte[] theMD5digest = md.digest(bytesOfMessage);
        return new String(theMD5digest);
    }
}
