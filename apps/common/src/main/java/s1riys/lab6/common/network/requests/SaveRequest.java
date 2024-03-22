package s1riys.lab6.common.network.requests;

import s1riys.lab6.common.constants.Commands;

public class SaveRequest extends Request {
    public SaveRequest() {
        super(Commands.SAVE);
    }
}
