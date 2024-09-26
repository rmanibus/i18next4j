package com.i18next4j;

import java.util.Objects;

public class I18NextOptions {

    private final String defaultNs;
    private final BackendModule backend;
    private final LanguageDetectorModule languageDetector;
    private final InterpolationOptions interpolationOptions;

    private I18NextOptions(String defaultNs, BackendModule backend, LanguageDetectorModule languageDetector, InterpolationOptions interpolationOptions) {
        this.defaultNs = defaultNs;
        this.backend = backend;
        this.languageDetector = languageDetector;
        this.interpolationOptions = interpolationOptions;
    }

    public String getDefaultNs() {
        return defaultNs;
    }

    public BackendModule getBackend() {
        return backend;
    }

    public LanguageDetectorModule getLanguageDetector() {
        return languageDetector;
    }

    public InterpolationOptions getInterpolationOptions() {
        return interpolationOptions;
    }

    public static class Builder {

        private String defaultNs = "translation";
        private BackendModule backend;
        private LanguageDetectorModule languageDetector;
        private InterpolationOptions interpolationOptions = InterpolationOptions.DEFAULT_INTERPOLATION_OPTIONS;

        /**
         * Default namespace used if not passed to translation function
         */
        public Builder defaultNs(String defaultNs) {
            this.defaultNs = defaultNs;
            return this;
        }

        public Builder backend(BackendModule backend) {
            this.backend = backend;
            return this;
        }

        public Builder languageDetector(LanguageDetectorModule languageDetector) {
            this.languageDetector = languageDetector;
            return this;
        }

        public Builder interpolationOptions(InterpolationOptions interpolationOptions) {
            this.interpolationOptions = interpolationOptions;
            return this;
        }

        /**
         * Language to use (overrides language detection)
         */
        public Builder lng(String lng) {
            this.languageDetector = () -> lng;
            return this;
        }

        public I18NextOptions build() {
            Objects.requireNonNull(backend, "backend must be set");
            Objects.requireNonNull(languageDetector, "languageDetector must be set");

            return new I18NextOptions(defaultNs, backend, languageDetector, interpolationOptions);
        }
    }

    public static class InterpolationOptions {

        private final static InterpolationOptions DEFAULT_INTERPOLATION_OPTIONS;

        private final String prefix;
        private final String suffix;

        static {
            DEFAULT_INTERPOLATION_OPTIONS = InterpolationOptions.builder().build();
        }

        static InterpolationOptions defaultInstance() {
            return DEFAULT_INTERPOLATION_OPTIONS;
        }

        static InterpolationOptions.Builder builder() {
            return new Builder();
        }

        public InterpolationOptions(String prefix, String suffix) {
            this.prefix = prefix;
            this.suffix = suffix;
        }

        public String getPrefix() {
            return prefix;
        }

        public String getSuffix() {
            return suffix;
        }

        public static class Builder {

            private final static String DEFAULT_PREFIX = "{{";
            private final static String DEFAULT_SUFFIX = "}}";

            private String prefix = DEFAULT_PREFIX;
            private String suffix = DEFAULT_SUFFIX;

            public Builder prefix(String prefix) {
                this.prefix = prefix;
                return this;
            }

            public Builder suffix(String suffix) {
                this.suffix = suffix;
                return this;
            }

            public InterpolationOptions build() {
                return new InterpolationOptions(prefix, suffix);
            }
        }
    }
}
