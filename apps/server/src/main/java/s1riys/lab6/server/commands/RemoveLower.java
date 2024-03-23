package s1riys.lab6.server.commands;

import s1riys.lab6.common.constants.Commands;
import s1riys.lab6.common.models.Product;
import s1riys.lab6.common.network.requests.RemoveGreaterRequest;
import s1riys.lab6.common.network.requests.RemoveLowerRequest;
import s1riys.lab6.common.network.requests.Request;
import s1riys.lab6.common.network.responses.RemoveGreaterResponse;
import s1riys.lab6.common.network.responses.RemoveLowerResponse;
import s1riys.lab6.common.network.responses.Response;
import s1riys.lab6.server.managers.CollectionManager;

import java.util.List;
import java.util.Objects;

public class RemoveLower extends RepositoryCommand {
    public RemoveLower(CollectionManager collectionManager) {
        super(Commands.REMOVE_LOWER, collectionManager);
    }

    @Override
    public Response execute(Request data) {
        try {
            RemoveLowerRequest request = (RemoveLowerRequest) data;

            List<Long> idsToRemove = collectionManager.getSortedCollection().headSet(request.product).stream()
                    .filter(Objects::nonNull)
                    .map(Product::getId)
                    .toList();
            idsToRemove.forEach(collectionManager::remove);

            return new RemoveLowerResponse(idsToRemove.size(), null);
        } catch (Exception e) {
            return new RemoveLowerResponse(-1, "Во время удаления произошла ошибка");
        }
    }
}
