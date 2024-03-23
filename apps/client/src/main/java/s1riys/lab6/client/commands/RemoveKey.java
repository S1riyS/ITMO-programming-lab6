package s1riys.lab6.client.commands;

import static s1riys.lab6.client.commands.utils.SignatureHelper.defineSignature;

import s1riys.lab6.client.commands.utils.ValidationHelper;
import s1riys.lab6.client.console.IConsole;
import s1riys.lab6.client.network.UDPClient;
import s1riys.lab6.common.constants.Commands;
import s1riys.lab6.common.exceptions.APIException;
import s1riys.lab6.common.exceptions.WrongAmountOfElementsException;
import s1riys.lab6.common.network.requests.RemoveKeyRequest;
import s1riys.lab6.common.network.responses.RemoveKeyResponse;

import java.io.IOException;

public class RemoveKey extends ServersideCommand {
    public RemoveKey(IConsole console, UDPClient client) {
        super(
                defineSignature(Commands.REMOVE_KEY, "<id>"),
                "Удаляет элемент из коллекции по его ключу",
                console,
                client
        );
    }

    @Override
    public Boolean execute(String[] data) {
        try {
            ValidationHelper.validateArgsLength(data, 1);
            long id = Long.parseLong(data[0]);

            RemoveKeyResponse response = (RemoveKeyResponse) client.sendAndReceiveCommand(new RemoveKeyRequest(id));
            ValidationHelper.validateResponse(response);

            console.println("Продукт успешно удален.");
            return true;

        } catch (WrongAmountOfElementsException e) {
            console.printError("Неверное количество аргументов");
        } catch (NumberFormatException e) {
            console.printError("id должен быть представлен числом");
        } catch (IOException e) {
            console.printError("Ошибка взаимодействия с сервером");
        } catch (APIException e) {
            console.printError(e.getMessage());
        }
        return false;
    }
}
