package s1riys.lab6.server.commands;

import s1riys.lab6.common.constants.Commands;
import s1riys.lab6.common.models.Product;
import s1riys.lab6.common.network.requests.PrintDescendingRequest;
import s1riys.lab6.common.network.requests.Request;
import s1riys.lab6.common.network.responses.PrintDescendingResponse;
import s1riys.lab6.common.network.responses.Response;
import s1riys.lab6.server.managers.CollectionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;

public class PrintDescending extends RepositoryCommand {
    public PrintDescending(CollectionManager collectionManager) {
        super(Commands.PRINT_DESCENDING, collectionManager);
    }

    @Override
    public Response execute(Request data) {
        try {
            NavigableSet<Product> productsSet = collectionManager.getSortedCollection().descendingSet();
            List<Product> products = new ArrayList<>(productsSet);
            return new PrintDescendingResponse(products, null);
        } catch (Exception e) {
            return new PrintDescendingResponse(null, e.toString());
        }
    }
}
