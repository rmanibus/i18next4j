package com.i18next;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Map;

public class ResourcesBackend implements BackendModule {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Map<String, String> read(String language, String namespace) throws BackendException {
        try {
            return objectMapper.readValue(read(Path.of("i18n", language, namespace + ".json")), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new BackendException(e);
        }
    }

    static InputStream read(Path path) {
        return Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(path.toString());

    }
}
