package com.loick.graphql.api_graphql;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseTests {
    public static String expected(String fileName) throws IOException {
        Path path = Paths.get("src/test/resources/" + fileName + "_expected_response.json");
        return new String(Files.readAllBytes(path));
    }
}
