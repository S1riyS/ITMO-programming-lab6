package s1riys.lab6.common.network.requests;

import s1riys.lab6.common.constants.Commands;

public class MaxByCreationDateRequest extends Request {
    public MaxByCreationDateRequest() {
        super(Commands.MAX_BY_CREATION_DATE);
    }
}
