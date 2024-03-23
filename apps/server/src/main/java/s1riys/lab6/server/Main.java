package s1riys.lab6.server;

import s1riys.lab6.common.managers.CommandManager;
import s1riys.lab6.common.constants.Commands;
import s1riys.lab6.server.commands.*;
import s1riys.lab6.server.handlers.CommandHandler;
import s1riys.lab6.server.managers.CollectionManager;
import s1riys.lab6.server.managers.DumpManager;
import s1riys.lab6.server.network.UDPServer;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import static s1riys.lab6.common.constants.Network.PORT;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Введите имя загружаемого файла как аргумент командной строки");
            System.exit(1);
        } else if (args.length > 1) {
            System.out.println("Программа принимает 1 аргумент командной строки - имя файла (передано " + args.length + ")");
            System.exit(1);
        }

        DumpManager dumpManager = new DumpManager(args[0]);
        CollectionManager collectionManager = new CollectionManager(dumpManager);
        CommandManager<Command> serverCommandManager = new CommandManager<>() {{
            register(Commands.INFO, new Info(collectionManager));
            register(Commands.SHOW, new Show(collectionManager));
            register(Commands.INSERT, new Insert(collectionManager));
            register(Commands.UPDATE, new Update(collectionManager));
            register(Commands.REMOVE_KEY, new RemoveKey(collectionManager));
            register(Commands.CLEAR, new Clear(collectionManager));
            register(Commands.SAVE, new Save(collectionManager));
            register(Commands.REMOVE_GREATER, new RemoveGreater(collectionManager));
            register(Commands.REMOVE_LOWER, new RemoveLower(collectionManager));
            register(Commands.REMOVE_GREATER_KEY, new RemoveGreaterKey(collectionManager));
            register(Commands.MAX_BY_CREATION_DATE, new MaxByCreationDate(collectionManager));
            register(Commands.FILTER_BY_MANUFACTURER, new FilterByManufacturer(collectionManager));
        }};
        CommandHandler commandHandler = new CommandHandler(serverCommandManager);

        try {
            UDPServer server = new UDPServer(InetAddress.getLocalHost(), PORT, commandHandler);
            System.out.println("Starting server...");
            server.run();
        } catch (SocketException e) {
            System.out.println("Ошибка сокета");
        } catch (UnknownHostException e) {
            System.out.println("Неизвестный хост");
        }
    }
}