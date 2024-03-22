package s1riys.lab6.common.network.requests;

import s1riys.lab6.common.constants.Commands;

public class RemoveKeyRequest extends Request {
    public final long id;
    public RemoveKeyRequest(long id) {
        super(Commands.REMOVE_KEY);
        this.id = id;
    }
}
