package s1riys.lab6.common.network.requests;

import s1riys.lab6.common.constants.Commands;
import s1riys.lab6.common.models.Product;

public class UpdateRequest extends Request {
    public final long id;
    public final Product product;
    public UpdateRequest(long id, Product product) {
        super(Commands.UPDATE);
        this.id = id;
        this.product = product;
    }
}
