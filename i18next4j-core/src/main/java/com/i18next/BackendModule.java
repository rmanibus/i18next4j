package com.i18next;

import java.util.Map;

public interface BackendModule {
    Map<String, String> read(String language, String namespace) throws BackendException;
}
