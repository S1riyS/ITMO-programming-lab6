package s1riys.lab6.client.commands;

import org.checkerframework.checker.units.qual.A;
import s1riys.lab6.client.commands.utils.ValidationHelper;
import s1riys.lab6.client.console.IConsole;
import s1riys.lab6.client.network.UDPClient;
import s1riys.lab6.common.constants.Commands;
import s1riys.lab6.common.exceptions.APIException;
import s1riys.lab6.common.exceptions.WrongAmountOfElementsException;
import s1riys.lab6.common.network.requests.AdvancedAggregationRequest;
import s1riys.lab6.common.network.responses.AdvancedAggregationResponse;

import java.io.IOException;

import static s1riys.lab6.client.commands.utils.SignatureHelper.defineSignature;


public class AdvancedAggregation extends ServersideCommand {
    public AdvancedAggregation(IConsole console, UDPClient client) {
        super(
                defineSignature(Commands.ADVANCED_AGGREGATION, "<minPrice> <minAnnualTurnover> <maxAnnualTurnover>"),
                "Выводит средние расстояние до продуктов, удовлетворяющих условию",
                console,
                client
        );
    }

    @Override
    public Boolean execute(String[] data) {
        try {
            ValidationHelper.validateArgsLength(data, 3);
            long minPrice = Long.parseLong(data[0]);
            Double minAnnualTurnover = Double.parseDouble(data[1]);
            Double maxAnnualTurnover = Double.parseDouble(data[2]);

            AdvancedAggregationRequest request = new AdvancedAggregationRequest(minPrice, minAnnualTurnover, maxAnnualTurnover);
            AdvancedAggregationResponse response = (AdvancedAggregationResponse) client.sendAndReceiveCommand(request);
            ValidationHelper.validateResponse(response);

            console.println("Среднее расстояние: %s".formatted(response.averageDistance));
            return true;

        } catch (WrongAmountOfElementsException e) {
            console.printError("Неверное количество аргументов");
        } catch (NumberFormatException e) {
            console.printError("Ошибка при чтении аргументов");
        } catch (IOException e) {
            console.printError("Ошибка взаимодействия с сервером");
        } catch (APIException e) {
            console.printError(e.getMessage());
        }
        return false;
    }
}
