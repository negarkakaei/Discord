import java.util.ArrayList;
import java.util.Scanner;

public class Console extends InputHandler{

    private String input;
    private final Scanner scanner = new Scanner(System.in);

    protected int checkInput(int numberOfChoices) throws Exception {
        int choice;
        choice = scanner.nextInt();
        if (choice == 0)
            return 0;
        if (choice < 1 || choice > numberOfChoices)
            throw new Exception("input out of range");
        return choice;
    }

    protected String checkInput (String regex){
        String input = scanner.nextLine();
        if (!input.matches(regex) && !input.equals("0"))
            return null;
        else
            return input;

    }

    public void printMsg(String msg){
        System.out.println(msg);
    }

    public int showMenu(String menu, int options){

        System.out.println(menu);
        return getUserOption(options);
    }

    public int showMenu(ArrayList<String> menu, int options){
        for (String option: menu) {
            System.out.println(option);
        }
        return getUserOption(options);
    }

    private int getUserOption(int options) {
        boolean successful = false;
        int choice = 0;
        while (!successful){
            try {
               choice = checkInput(options);
               successful = true;
            } catch (Exception e){
                System.err.println("you must choose one of the numbers in the menu");
            }
        }
        return choice;
    }

    public ArrayList<String> login(){

        ArrayList<String> info = new ArrayList<>();

        System.out.print("username -> ");
        String username = scanner.nextLine();
        if (username.equals("0"))
            return null;
        info.add(username);

        System.out.print("password -> ");
        String password = scanner.nextLine();
        if (password.equals("0"))
            return null;
        info.add(password);
        return info;
    }

    public ArrayList<String> signup() {
        ArrayList<String> info = new ArrayList<>();
        System.out.println("password must be at least 8 characters and must contain capital and small english alphabets and numbers");
        System.out.println("press 0 to exit");
        System.out.print("password -> ");

        String password = checkInput("");
        // یه کاری کن به حای ریترن استیت منت ترد داشته باشی
        while (password == null) {
            System.out.println("invalid password format. try again!");
            password = checkInput("");
        }
        info.add(password);

        System.out.println("press 0 to exit");
        System.out.print("email address -> ");

        String email = checkInput("");
        while (email == null) {
            System.out.println("invalid email format. try again!");
            email = checkInput("");
        }
        info.add(email);

        System.out.println("would yu like to add a phone number as well?");
        System.out.println("1) yes 2) no");
        int option = scanner.nextInt();
        if (option == 1) {
            System.out.println("press 0 to exit");
            System.out.print(" phone number -> ");

            String phoneNum = checkInput("");
            while (phoneNum == null) {
                System.out.println("invalid phone number format. try again!");
                phoneNum = checkInput("");
            }

            info.add(phoneNum);
        }

        return info;
    }

    public String usernameValidation(){
        System.out.println("username must be at least 6 characters and only containing english alphabet and numbers");
        System.out.println("press 0 to exit");
        System.out.print("username -> ");

        input = checkInput("");
        while (input == null){
            System.out.println("invalid username format. try again!");
            input = checkInput("");
        }
        return input;
    }

}
