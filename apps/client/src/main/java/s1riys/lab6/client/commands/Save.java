package s1riys.lab6.client.commands;

import static s1riys.lab6.client.commands.utils.SignatureHelper.defineSignature;
import s1riys.lab6.client.console.IConsole;
import s1riys.lab6.client.network.UDPClient;
import s1riys.lab6.common.constants.Commands;
import s1riys.lab6.common.exceptions.APIException;
import s1riys.lab6.common.network.requests.SaveRequest;
import s1riys.lab6.common.network.responses.SaveResponse;

import java.io.IOException;

public class Save extends ServersideCommand {
    public Save(IConsole console, UDPClient client) {
        super(defineSignature(Commands.SAVE), "Сохраняет коллекцию в файл", console, client);
    }

    @Override
    public Boolean execute(String[] data) {
        try {
            SaveResponse response = (SaveResponse) client.sendAndReceiveCommand(new SaveRequest());
            if (response.getError() != null && !response.getError().isEmpty()) {
                throw new APIException(response.getError());
            }

            console.println("Коллекция сохранена в файл");
            return true;

        } catch (IOException e) {
            console.printError("Ошибка взаимодействия с сервером");
        } catch (APIException e) {
            console.printError(e.getMessage());
        }
        return false;
    }
}
