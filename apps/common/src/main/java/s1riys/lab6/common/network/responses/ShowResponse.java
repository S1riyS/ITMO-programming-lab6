package s1riys.lab6.common.network.responses;

import s1riys.lab6.common.constants.Commands;
import s1riys.lab6.common.models.Product;

import java.util.HashMap;
import java.util.List;

public class ShowResponse extends Response {
    public final List<Product> products;

    public ShowResponse(List<Product> products, String error) {
        super(Commands.SHOW, error);
        this.products = products;
    }
}
