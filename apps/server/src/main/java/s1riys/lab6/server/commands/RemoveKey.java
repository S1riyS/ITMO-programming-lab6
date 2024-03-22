package s1riys.lab6.server.commands;

import s1riys.lab6.common.constants.Commands;
import s1riys.lab6.common.network.requests.RemoveKeyRequest;
import s1riys.lab6.common.network.requests.Request;
import s1riys.lab6.common.network.responses.RemoveKeyResponse;
import s1riys.lab6.common.network.responses.Response;
import s1riys.lab6.common.network.responses.UpdateResponse;
import s1riys.lab6.server.managers.CollectionManager;

public class RemoveKey extends RepositoryCommand {
    public RemoveKey(CollectionManager collectionManager) {
        super(Commands.REMOVE_KEY, collectionManager);
    }

    @Override
    public Response execute(Request data) {
        RemoveKeyRequest request = (RemoveKeyRequest) data;
        try {
            if (!collectionManager.getDefaultCollection().containsKey(request.id)) {
                return new RemoveKeyResponse("Не существует продукта с таким id");
            }
            collectionManager.remove(request.id);
            return new RemoveKeyResponse(null);

        } catch (Exception e) {
            return new UpdateResponse("Не удалось удалить продукт");
        }
    }
}
