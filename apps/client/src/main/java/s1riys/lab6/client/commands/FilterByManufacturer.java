package s1riys.lab6.client.commands;

import s1riys.lab6.client.commands.utils.ValidationHelper;
import s1riys.lab6.client.console.IConsole;
import s1riys.lab6.client.forms.OrganizationForm;
import s1riys.lab6.client.network.UDPClient;
import s1riys.lab6.common.constants.Commands;
import s1riys.lab6.common.exceptions.APIException;
import s1riys.lab6.common.exceptions.InvalidFormException;
import s1riys.lab6.common.models.Organization;
import s1riys.lab6.common.network.requests.FilterByManufacturerRequest;
import s1riys.lab6.common.network.responses.FilterByManufacturerResponse;

import java.io.IOException;

import static s1riys.lab6.client.commands.utils.SignatureHelper.defineSignature;

public class FilterByManufacturer extends ServersideCommand {
    public FilterByManufacturer(IConsole console, UDPClient client) {
        super(
                defineSignature(Commands.FILTER_BY_MANUFACTURER, "{organisation}"),
                "Выводит элементы, значение поля manufacturer которых равно заданному",
                console,
                client
        );
    }

    @Override
    public Boolean execute(String[] data) {
        try {
            Organization organization = new OrganizationForm(console).build();

            FilterByManufacturerRequest request = new FilterByManufacturerRequest(organization);
            FilterByManufacturerResponse response = (FilterByManufacturerResponse) client.sendAndReceiveCommand(request);
            ValidationHelper.validateResponse(response);

            if (response.products.isEmpty()) console.println("Продукты не найдены");
            else response.products.forEach(console::println);
            return true;

        } catch (InvalidFormException e) {
            console.printError("Поля организации не валидны");
        } catch (IOException e) {
            console.printError("Ошибка взаимодействия с сервером");
        } catch (APIException e) {
            console.printError(e.getMessage());
        }
        return false;
    }
}
