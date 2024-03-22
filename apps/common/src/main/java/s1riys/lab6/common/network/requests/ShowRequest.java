package s1riys.lab6.common.network.requests;

import s1riys.lab6.common.constants.Commands;

public class ShowRequest extends Request {
    public ShowRequest() {
        super(Commands.SHOW);
    }
}
