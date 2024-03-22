package s1riys.lab6.server.commands;

import s1riys.lab6.common.interfaces.Executable;
import s1riys.lab6.common.network.requests.Request;
import s1riys.lab6.common.network.responses.Response;

public abstract class Command implements Executable<Request, Response> {
    private final String name;

    public Command(String keyword) {
        this.name = keyword;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Command{" +
                "name='" + name + '\'' +
                '}';
    }
}
