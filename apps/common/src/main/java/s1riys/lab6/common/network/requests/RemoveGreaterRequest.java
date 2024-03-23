package s1riys.lab6.common.network.requests;

import s1riys.lab6.common.constants.Commands;
import s1riys.lab6.common.models.Product;

public class RemoveGreaterRequest extends Request {
    public final Product product;
    public RemoveGreaterRequest(Product product) {
        super(Commands.REMOVE_GREATER);
        this.product = product;
    }
}
