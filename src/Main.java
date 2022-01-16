import controllers.AbstractController;
import controllers.FileSystemController;
import controllers.MainEntryController;
import controllers.UseCaseBundle;

import java.io.IOException;
import java.util.Stack;

/**
 * The type Main.
 */
public class Main {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws ClassNotFoundException the class not found exception
     * @throws IOException            the io exception
     */
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        UseCaseBundle bundle = new UseCaseBundle();
        Stack<AbstractController> controllerStack = new Stack<>();
        AbstractController currentController;
        controllerStack.push(new MainEntryController(bundle));

        currentController = controllerStack.peek();

        while (!controllerStack.empty()) {
            AbstractController c = currentController.run();
            int popNum = currentController.getPopNum();
            if (popNum == -2) {
                // Pop all stacks and exit the program
                while (!controllerStack.empty()) {
                    controllerStack.pop();
                }
            } else if (popNum == -1) {
                // Pop all the stacks except for the mainEntryController
                while (!controllerStack.empty()) {
                    controllerStack.pop();
                }
                controllerStack.push(new MainEntryController(bundle));
            } else {
                for (int i = 0; i < popNum; i++) {
                    controllerStack.pop();
                }
            }

            if (c != null) {
                controllerStack.push(c);
            }

            if (!controllerStack.empty()) {
                currentController = controllerStack.peek();
            }
        }
        FileSystemController fileSystemController = new FileSystemController();
        fileSystemController.deload(bundle);

    }
}
