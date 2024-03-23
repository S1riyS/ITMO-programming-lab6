package s1riys.lab6.client.commands;

import static s1riys.lab6.client.commands.utils.SignatureHelper.defineSignature;

import s1riys.lab6.client.commands.utils.ValidationHelper;
import s1riys.lab6.client.console.IConsole;
import s1riys.lab6.client.network.UDPClient;
import s1riys.lab6.common.constants.Commands;
import s1riys.lab6.common.exceptions.APIException;
import s1riys.lab6.common.models.Product;
import s1riys.lab6.common.network.requests.ShowRequest;
import s1riys.lab6.common.network.responses.ShowResponse;

import java.io.IOException;

public class Show extends ServersideCommand {
    public Show(IConsole console, UDPClient client) {
        super(
                defineSignature(Commands.SHOW),
                "Выводит в стандартный поток вывода все элементы коллекции в строковом представлении",
                console,
                client
        );
    }

    @Override
    public Boolean execute(String[] data) {
        try {
            ShowResponse response = (ShowResponse) client.sendAndReceiveCommand(new ShowRequest());
            ValidationHelper.validateResponse(response);

            if (response.products.isEmpty()) {
                console.println("Коллекция пуста");
            } else {
                for (Product product : response.products) {
                    console.println(product);
                }
            }
            return true;
        } catch (IOException e) {
            console.printError("Ошибка взаимодействия с сервером");
        } catch (APIException e) {
            console.printError(e.getMessage());
        }
        return false;
    }
}
