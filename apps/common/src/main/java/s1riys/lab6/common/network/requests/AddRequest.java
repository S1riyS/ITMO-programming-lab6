package s1riys.lab6.common.network.requests;

import s1riys.lab6.common.models.Product;
import s1riys.lab6.common.constants.Commands;

public class AddRequest extends Request {
    public final Product product;

    public AddRequest(Product product) {
        super(Commands.INSERT);
        this.product = product;
    }
}
