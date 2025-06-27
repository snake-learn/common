package com.snake.common.utils;

import lombok.experimental.UtilityClass;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@UtilityClass
public class KeyUtils {

  public static String getKey(String filename, boolean isPrivateKey) throws IOException {
    InputStream inputStream;
    try {
      inputStream = new FileInputStream(filename);
    } catch (Exception ignored) {
      inputStream = KeyUtils.class.getClassLoader().getResourceAsStream(filename);
    }
    assert inputStream != null;
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(
        inputStream,
        StandardCharsets.UTF_8
    ))) {
      String temp = reader.lines().collect(Collectors.joining(""));
      String pem;
      if (isPrivateKey) {
        pem = temp.replace("-----BEGIN RSA PRIVATE KEY-----", "")
            .replace("-----END RSA PRIVATE KEY-----", "")
            .replace("-----BEGIN ENCRYPTED PRIVATE KEY-----", "")
            .replace("-----END ENCRYPTED PRIVATE KEY-----", "")
            .replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("-----END PRIVATE KEY-----", "");
      } else {
        pem = temp.replace("-----BEGIN PUBLIC KEY-----", "")
            .replace("-----END PUBLIC KEY-----", "");
      }
      return pem;
    }
  }
}
