package com.i18next;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class ResourcesBackendTest {

    @Test
    public void writeYourOwnUnitTest() throws BackendException {
        ResourcesBackend backend = new ResourcesBackend();
        Map<String, String> resources = backend.read("en", "translation");
        Assertions.assertNotNull(resources);
        Assertions.assertEquals( "value", resources.get("key"));
    }

}
