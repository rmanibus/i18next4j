package com.i18next;

import java.util.Map;

public interface I18Next {
    String t(String key, Map<String, String> options);
}
