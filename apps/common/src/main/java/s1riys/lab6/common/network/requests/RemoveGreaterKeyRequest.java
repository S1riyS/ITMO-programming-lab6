package s1riys.lab6.common.network.requests;

import s1riys.lab6.common.constants.Commands;
import s1riys.lab6.common.models.Product;

public class RemoveGreaterKeyRequest extends Request {
    public final Long id;
    public RemoveGreaterKeyRequest(Long id) {
        super(Commands.REMOVE_GREATER_KEY);
        this.id = id;
    }
}
