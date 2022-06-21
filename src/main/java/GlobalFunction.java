import java.util.Scanner;

public final class GlobalFunction {

    static Scanner scan = new Scanner(System.in);

    public static void successMessage(String subject, String message){
        System.out.println();
        System.out.println(subject +" has been successfully " + message + "!");
        System.out.println("Press enter to continue");
        scan.nextLine();
    }

    public static void showMessage(String message){
        System.out.println();
        System.out.println(message);
    }

    public static void pressContinue(){
        System.out.println("Press enter to continue");
        scan.nextLine();
    }

    public static void cls(){
        for(int i = 0; i < 50; i++){
            System.out.println();
        }
    }
}
