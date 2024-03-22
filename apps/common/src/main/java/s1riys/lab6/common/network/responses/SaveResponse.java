package s1riys.lab6.common.network.responses;

import s1riys.lab6.common.constants.Commands;

public class SaveResponse extends Response {
    public SaveResponse(String error) {
        super(Commands.SAVE, error);
    }
}
