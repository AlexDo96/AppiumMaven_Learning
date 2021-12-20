package flags;

import io.appium.java_client.service.local.flags.ServerArgument;

// Extend ServerArgument interface to add one more flag: ALLOW_INSECURE("--allow-insecure")
public enum AndroidServerFlagEx implements ServerArgument {
    ALLOW_INSECURE("--allow-insecure"),
    CHROMEDRIVER_EXECUTABLE("--chromedriver-executable");

    private final String arg;

    AndroidServerFlagEx(String arg) {
        this.arg = arg;
    }

    public String getArgument() {
        return this.arg;
    }
}
