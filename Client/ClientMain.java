import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * contains main method of client side and is responsible for user login and signup
 * creates a socket connection with server side
 */
public class ClientMain {

    private static Socket socket;
    private static Socket fileSocket;
    private static ObjectOutputStream out;
    private static ObjectOutputStream fout;
    private static ObjectInputStream fin;
    private static ObjectInputStream in;
    private static InputHandler inputHandler;
    private static String currentUsername;
    private static Command cmd;
    private static Data data;

    public static void main(String[] args) {

        File f1 = new File("C:\\discord");
        boolean bool = f1.mkdir();
        int choice;
        inputHandler = new Console();

        try {
            socket = new Socket("localhost", 8643);
            System.out.println(socket.getInetAddress());
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            fileSocket = new Socket("localhost", 8999);
            System.out.println(socket.getInetAddress());
            fout = new ObjectOutputStream(fileSocket.getOutputStream());
            fin = new ObjectInputStream(fileSocket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

        do {
            choice = inputHandler.showMenu("""
                    1) signup
                    2) login
                    press 0 to exit""", 2);

            if (choice == 1 && signup() == 1 || choice == 2 && login() == 1) {
                Discord discord = new Discord(currentUsername, out, fout, in, fin);
                discord.startDiscord();
            }
        } while (choice != 0);

        //here you must exit the program for client, close the connection
    }

    /**
     * logs in the user
     *
     * @return 1 if the login was successful and 0 if the user decides to quit
     */
    public static int login() {
        while (true) {
            ArrayList<String> info = inputHandler.login();
            if (info == null)
                return 0;
            else {
                // create command
                // if found the condition is true
                cmd = Command.login(info.get(0), info.get(1));
                transfer();
                if (data.getKeyword().equals("checkLogin") && (boolean) data.getPrimary()) {
                    currentUsername = data.getUser();
                    return 1;
                } else
                    inputHandler.printMsg("incorrect username or password, try again!\npress 0 to exit");
            }
        }
    }

    /**
     * signs up a new user
     *
     * @return 1 if the signup was successful and 0 if the user decides to quit
     */
    public static int signup() {

        while (true) {
            String input = inputHandler.usernameValidation();
            if (input.equals("0"))
                return 0;
            else {
                // create command, if not found condition is true
                cmd = Command.getUser(input);
                transfer();

                if (data.getPrimary() == null) {
                    currentUsername = input;
                    break;
                } else
                    inputHandler.printMsg("this username already exists please try another one.");
            }
        }

        User userInfo = inputHandler.signup();
        if (userInfo == null)
            return 0;
        else {
            // command to add new user to database
            userInfo.setUsername(currentUsername);
            cmd = Command.newUser(userInfo);
            transfer();
            if (data.getKeyword().equals("checkSignUp") && (boolean) data.getPrimary()) {
                inputHandler.printMsg("successfully signed up.");
                return 1;
            } else
                return 0;
        }
    }

    /**
     * sends commands to server via objectOutputStream
     * receives datas from server via objectInputStream
     */
    public static void transfer() {
        try {
            out.writeObject(cmd);
            data = (Data) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            inputHandler.printError(e);
        }
    }
}
