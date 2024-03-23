package s1riys.lab6.common.network.responses;

import s1riys.lab6.common.constants.Commands;
import s1riys.lab6.common.models.Product;

import java.util.List;

public class PrintDescendingResponse extends Response {
    public final List<Product> products;

    public PrintDescendingResponse(List<Product> products, String error) {
        super(Commands.PRINT_DESCENDING, error);
        this.products = products;
    }
}
