package s1riys.lab6.common.network.requests;

import s1riys.lab6.common.constants.Commands;
import s1riys.lab6.common.models.Product;

public class RemoveLowerRequest extends Request {
    public final Product product;
    public RemoveLowerRequest(Product product) {
        super(Commands.REMOVE_LOWER);
        this.product = product;
    }
}
