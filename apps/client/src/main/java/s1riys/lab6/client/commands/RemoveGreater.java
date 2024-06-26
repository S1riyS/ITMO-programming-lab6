package s1riys.lab6.client.commands;

import static s1riys.lab6.client.commands.utils.SignatureHelper.defineSignature;

import s1riys.lab6.client.commands.utils.ValidationHelper;
import s1riys.lab6.client.console.IConsole;
import s1riys.lab6.client.forms.ProductForm;
import s1riys.lab6.client.network.UDPClient;
import s1riys.lab6.common.constants.Commands;
import s1riys.lab6.common.exceptions.APIException;
import s1riys.lab6.common.exceptions.InvalidFormException;
import s1riys.lab6.common.models.Product;
import s1riys.lab6.common.network.requests.RemoveGreaterRequest;
import s1riys.lab6.common.network.responses.RemoveGreaterResponse;

import java.io.IOException;

public class RemoveGreater extends ServersideCommand {
    public RemoveGreater(IConsole console, UDPClient client) {
        super(
                defineSignature(Commands.REMOVE_GREATER, "{product}"),
                "Удаляет из коллекции все элементы, превышающие заданный",
                console,
                client
        );
    }

    @Override
    public Boolean execute(String[] data) {
        try {
            Product product = new ProductForm(console).build();

            RemoveGreaterRequest request = new RemoveGreaterRequest(product);
            RemoveGreaterResponse response = (RemoveGreaterResponse) client.sendAndReceiveCommand(request);
            ValidationHelper.validateResponse(response);

            if (response.count == 0) {
                console.println("Продукты для удаления не найдены");
            } else {
                console.println("Продукты удалены (%s шт.)".formatted(response.count));
            }
            return true;

        } catch (InvalidFormException e) {
            console.printError("Поля продукта не валидны");
        } catch (IOException e) {
            console.printError("Ошибка взаимодействия с сервером");
        } catch (APIException e) {
            console.printError(e.getMessage());
        }
        return false;
    }
}
