package s1riys.lab6.server.commands;

import s1riys.lab6.common.constants.Commands;
import s1riys.lab6.common.network.requests.Request;
import s1riys.lab6.common.network.requests.SaveRequest;
import s1riys.lab6.common.network.responses.Response;
import s1riys.lab6.common.network.responses.SaveResponse;
import s1riys.lab6.server.managers.CollectionManager;

public class Save extends RepositoryCommand {
    public Save(CollectionManager collectionManager) {
        super(Commands.SAVE, collectionManager);
    }

    @Override
    public Response execute(Request data) {
        try {
            collectionManager.writeToCSV();
            return new SaveResponse(null);
        } catch (Exception e) {
            return new SaveResponse(e.getMessage());
        }
    }
}
