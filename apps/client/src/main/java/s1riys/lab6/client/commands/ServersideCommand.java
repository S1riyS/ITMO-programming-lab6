package s1riys.lab6.client.commands;

import s1riys.lab6.client.console.IConsole;
import s1riys.lab6.client.network.UDPClient;

public abstract class ServersideCommand extends Command {
    protected final UDPClient client;
    public ServersideCommand(String name, String description, IConsole console, UDPClient client) {
        super(name, description, console);
        this.client = client;
    }
}
