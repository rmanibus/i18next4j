package com.i18next4j;

import java.util.Map;

public interface BackendModule {
    Map<String, String> read(String language, String namespace) throws BackendException;
}
