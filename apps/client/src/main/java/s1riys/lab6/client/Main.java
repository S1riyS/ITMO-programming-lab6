package s1riys.lab6.client;

import s1riys.lab6.client.console.StandardConsole;
import s1riys.lab6.client.managers.RuntimeManager;
import s1riys.lab6.client.network.UDPClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static s1riys.lab6.common.constants.Network.PORT;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello client!");
        var console = new StandardConsole();

        try {
            var client = new UDPClient(InetAddress.getLocalHost(), PORT);
            var cli = new RuntimeManager(client, console);

            cli.interactiveMode();
        } catch (UnknownHostException e) {
            System.out.println("Неизвестный хост");
        } catch (IOException e) {
//            logger.info("Невозможно подключиться к серверу.", e);
            System.out.println("Невозможно подключиться к серверу!");
        }
    }
}
