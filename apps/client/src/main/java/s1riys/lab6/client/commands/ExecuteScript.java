package s1riys.lab6.client.commands;

import static s1riys.lab6.client.commands.utils.SignatureHelper.defineSignature;

import s1riys.lab6.client.commands.utils.ValidationHelper;
import s1riys.lab6.client.console.IConsole;
import s1riys.lab6.common.constants.Commands;
import s1riys.lab6.common.exceptions.WrongAmountOfElementsException;

public class ExecuteScript extends Command {
    public ExecuteScript(IConsole console) {
        super(
                defineSignature(Commands.EXECUTE_SCRIPT, "<filename>"),
                "Считывает и исполняет скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.",
                console
        );
    }

    @Override
    public Boolean execute(String[] data) {
        try {
            ValidationHelper.validateArgsLength(data, 1);
            return true;

        } catch (WrongAmountOfElementsException e) {
            console.printError("Неверное количество аргументов");
        }
        return false;
    }
}
