package s1riys.lab6.common.network.responses;

import s1riys.lab6.common.constants.Commands;
import s1riys.lab6.common.models.Product;

import java.util.List;

public class FilterByManufacturerResponse extends Response {
    public final List<Product> products;

    public FilterByManufacturerResponse(List<Product> products, String error) {
        super(Commands.FILTER_BY_MANUFACTURER, error);
        this.products = products;
    }
}
