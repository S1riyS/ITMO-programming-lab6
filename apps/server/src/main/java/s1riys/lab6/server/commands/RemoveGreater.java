package s1riys.lab6.server.commands;

import s1riys.lab6.common.constants.Commands;
import s1riys.lab6.common.models.Product;
import s1riys.lab6.common.network.requests.RemoveGreaterRequest;
import s1riys.lab6.common.network.requests.Request;
import s1riys.lab6.common.network.responses.RemoveGreaterResponse;
import s1riys.lab6.common.network.responses.RemoveKeyResponse;
import s1riys.lab6.common.network.responses.Response;
import s1riys.lab6.server.managers.CollectionManager;

import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.stream.Collectors;

public class RemoveGreater extends RepositoryCommand {
    public RemoveGreater(CollectionManager collectionManager) {
        super(Commands.REMOVE_GREATER, collectionManager);
    }

    @Override
    public Response execute(Request data) {
        try {
            RemoveGreaterRequest request = (RemoveGreaterRequest) data;

            List<Long> idsToRemove = collectionManager.getSortedCollection().tailSet(request.product).stream()
                    .filter(Objects::nonNull)
                    .map(Product::getId)
                    .toList();
            idsToRemove.forEach(collectionManager::remove);

            return new RemoveGreaterResponse(idsToRemove.size(), null);
        } catch (Exception e) {
            return new RemoveGreaterResponse(-1, "Во время удаления произошла ошибка");
        }
    }
}
