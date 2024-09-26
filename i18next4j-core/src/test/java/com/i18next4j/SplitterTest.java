package com.i18next4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


public class SplitterTest {


    @Test
    public void splitSameLengthToken() throws BackendException {
        Splitter splitter = new Splitter(List.of("{{", "}}", "$t(", ")"));
        String[] split = splitter.split("{{value}}");
        Assertions.assertEquals(3, split.length);
    }

    @Test
    public void splitMultipleLengthToken() throws BackendException {
        Splitter splitter = new Splitter(List.of("{{", "}}", "$t(", ")"));
        String[] split = splitter.split("$t(value)");
        Assertions.assertEquals(3, split.length);
    }

    @Test
    public void splitNestedToken() throws BackendException {
        Splitter splitter = new Splitter(List.of("{{", "}}", "$t(", ")"));
        String[] split = splitter.split("{$t({");
        Assertions.assertEquals(3, split.length);
    }

    @Test
    public void splitSingleToken() throws BackendException {
        Splitter splitter = new Splitter(List.of("{{", "}}", "$t(", ")"));
        String[] split = splitter.split("}}");
        Assertions.assertEquals(1, split.length);
        Assertions.assertEquals("}}", split[0]);
    }

    @Test
    public void splitTokenInTheMiddle() throws BackendException {
        Splitter splitter = new Splitter(List.of("{{", "}}", "$t(", ")"));
        String[] split = splitter.split("prefix }} suffix");
        Assertions.assertEquals(3, split.length);
    }
}
