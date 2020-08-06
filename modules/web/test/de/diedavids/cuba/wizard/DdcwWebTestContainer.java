package de.diedavids.cuba.wizard;

import com.haulmont.cuba.web.testsupport.TestContainer;

import java.util.Arrays;

public class DdcwWebTestContainer extends TestContainer {

    public DdcwWebTestContainer() {
        appComponents = Arrays.asList(
            "com.haulmont.cuba"
            // add CUBA add-ons and custom app components here
        );
        appPropertiesFiles = Arrays.asList(
            // List the files defined in your web.xml
            // in appPropertiesConfig context parameter of the web module
            "de/diedavids/cuba/wizard/web-app.properties",
            // Add this file which is located in CUBA and defines some properties
            // specifically for test environment. You can replace it with your own
            // or add another one in the end.
            "com/haulmont/cuba/web/testsupport/test-web-app.properties"
        );
    }

    public static class Common extends DdcwWebTestContainer {

        // A common singleton instance of the test container which is initialized once for all tests
        public static final DdcwWebTestContainer.Common INSTANCE = new DdcwWebTestContainer.Common();

        private static volatile boolean initialized;

        private Common() {
        }

        @Override
        public void before() throws Throwable {
            if (!initialized) {
                super.before();
                initialized = true;
            }
            setupContext();
        }

        @Override
        public void after() {
            cleanupContext();
            // never stops - do not call super
        }
    }
}