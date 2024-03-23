package s1riys.lab6.client.commands;

import s1riys.lab6.client.commands.utils.ValidationHelper;
import s1riys.lab6.client.console.IConsole;
import s1riys.lab6.client.forms.ProductForm;
import s1riys.lab6.client.network.UDPClient;
import s1riys.lab6.common.constants.Commands;
import s1riys.lab6.common.exceptions.APIException;
import s1riys.lab6.common.exceptions.InvalidFormException;
import s1riys.lab6.common.exceptions.WrongAmountOfElementsException;
import s1riys.lab6.common.models.Product;
import s1riys.lab6.common.network.requests.RemoveGreaterKeyRequest;
import s1riys.lab6.common.network.requests.RemoveGreaterRequest;
import s1riys.lab6.common.network.responses.RemoveGreaterKeyResponse;
import s1riys.lab6.common.network.responses.RemoveGreaterResponse;

import java.io.IOException;

import static s1riys.lab6.client.commands.utils.SignatureHelper.defineSignature;

public class RemoveGreaterKey extends ServersideCommand {
    public RemoveGreaterKey(IConsole console, UDPClient client) {
        super(
                defineSignature(Commands.REMOVE_GREATER_KEY, "<id>"),
                "Удаляет из коллекции все элементы, ключ которых превышает заданный",
                console,
                client
        );
    }

    @Override
    public Boolean execute(String[] data) {
        try {
            ValidationHelper.validateArgsLength(data, 1);
            long id = Long.parseLong(data[0]);

            RemoveGreaterKeyRequest request = new RemoveGreaterKeyRequest(id);
            RemoveGreaterKeyResponse response = (RemoveGreaterKeyResponse) client.sendAndReceiveCommand(request);
            ValidationHelper.validateResponse(response);

            if (response.count == 0) console.println("Продукты для удаления не найдены");
            else console.println("Продукты удалены (%s шт.)".formatted(response.count));

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
