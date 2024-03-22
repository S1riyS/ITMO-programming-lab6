package s1riys.lab6.client.commands;

import s1riys.lab6.common.interfaces.Executable;
import s1riys.lab6.client.console.IConsole;

public abstract class Command implements Executable<String[], Boolean> {
    private final String signature;
    private final String description;
    protected final IConsole console;

    public Command(String name, String description, IConsole console) {
        this.signature = name;
        this.description = description;
        this.console = console;
    }

    public String getSignature() {
        return signature;
    }

    public String getDescription() {
        return description;
    }

    public abstract Boolean execute(String[] data);
}