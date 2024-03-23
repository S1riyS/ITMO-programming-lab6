package s1riys.lab6.server.commands;

import s1riys.lab6.common.constants.Commands;
import s1riys.lab6.common.network.requests.RemoveGreaterKeyRequest;
import s1riys.lab6.common.network.requests.Request;
import s1riys.lab6.common.network.responses.RemoveGreaterKeyResponse;
import s1riys.lab6.common.network.responses.Response;
import s1riys.lab6.server.managers.CollectionManager;

import java.util.List;

public class RemoveGreaterKey extends RepositoryCommand {
    public RemoveGreaterKey(CollectionManager collectionManager) {
        super(Commands.REMOVE_GREATER_KEY, collectionManager);
    }

    @Override
    public Response execute(Request data) {
        try {
            RemoveGreaterKeyRequest request = (RemoveGreaterKeyRequest) data;

            List<Long> keysToRemove = collectionManager.getDefaultCollection().keySet().stream()
                    .filter(key -> key > request.id)
                    .toList();
            keysToRemove.forEach(collectionManager::remove);

            return new RemoveGreaterKeyResponse(keysToRemove.size(), null);
        } catch (Exception e) {
            return new RemoveGreaterKeyResponse(-1, "Во время удаления произошла ошибка");
        }
    }
}
