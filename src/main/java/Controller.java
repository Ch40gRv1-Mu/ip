import operations.*;
import operations.OperationFactory;

import java.util.Scanner;

import ui.ChatBox;

/**
 * Controller serves for orders
 */
public class Controller {
    private String helloWords = " Hello! I'm Duke\n" + " What can I do for you?";
    private String exitWords = "Bye. Hope to see you again soon!";
    private static String EXIT_COMMAND = "bye";
    ChatBox chatBox;

    /**
     *   Initialize an controller
     */
    public Controller()
    {
        chatBox = new ChatBox();
        sayHello();
    }

    /**
     *  Say hi when the controller is created
     */
    public void sayHello() {
        chatBox.contendsSetter(helloWords);
        chatBox.printChatBox();
    }


    /**
     *  Say good bye before the controller end
     */
    public void sayGoodBye() {
        chatBox.contendsSetter(exitWords);
        chatBox.printChatBox();
        chatBox.endChatBox();
    }


    /**
     *  Listen to the i/o and pass the instruction to operationFactory
     */
    public void listen(){

        Scanner sc = new Scanner(System.in);

        OperationFactory operationFactory = new OperationFactory();

        while(true){
            try{
                String order = sc.nextLine();
                operationFactory.setOrder(order);
                Operation operation = operationFactory.makeOperation();
                ChatBox.printChatBox(operation.getResult());
                if (operation.getOperationName().equals(EXIT_COMMAND)){
                    break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
