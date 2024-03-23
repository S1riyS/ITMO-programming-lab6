package s1riys.lab6.common.network.requests;

import s1riys.lab6.common.constants.Commands;

public class PrintDescendingRequest extends Request {
    public PrintDescendingRequest() {
        super(Commands.PRINT_DESCENDING);
    }
}
