package s1riys.lab6.common.network.responses;

public class NoSuchCommandResponse extends Response {
    public NoSuchCommandResponse(String name) {
        super(name, "Такой команды не существует");
    }
}
