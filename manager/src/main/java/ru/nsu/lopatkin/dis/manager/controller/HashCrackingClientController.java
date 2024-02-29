package ru.nsu.lopatkin.dis.manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.lopatkin.dis.manager.service.CrackingService;
import ru.nsu.lopatkin.dis.models.manager.request.HashCrackRequest;
import ru.nsu.lopatkin.dis.models.manager.response.HashCrackResponse;
import ru.nsu.lopatkin.dis.models.manager.response.HashCrackStatusResponse;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    @GetMapping("/convert-to-md5")
    public String convertToMD5(@RequestParam String str) {
        return getMD5Hash(str);
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
