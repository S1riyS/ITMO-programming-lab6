package s1riys.lab6.common.network.requests;

import s1riys.lab6.common.constants.Commands;

public class ClearRequest extends Request {
    public ClearRequest() {
        super(Commands.CLEAR);
    }
}
