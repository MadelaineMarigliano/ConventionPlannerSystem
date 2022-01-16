package GUIApplication;

import GUIcontrollers.GUIMainEntryController;
import GUIviews.AbstractView;
import GUIviews.factory.GUIViewFactory;
import controllers.AbstractController;
import controllers.FileSystemController;
import controllers.UseCaseBundle;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Stack;

/**
 * The type Gui application.
 */
public class GUIApplication extends Application {
    /**
     * The View stack.
     */
    Stack<AbstractView> viewStack = new Stack<>();
    /**
     * The Bundle.
     */
    UseCaseBundle bundle;
    /**
     * The Window.
     */
    Stage window;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        bundle = new UseCaseBundle();
    }
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Convention Planner System");

        GUIMainEntryController mainEntryController = new GUIMainEntryController(bundle);
        GUIViewFactory viewFactory = new GUIViewFactory();
        AbstractView startView = viewFactory.generateView(mainEntryController);
        startView.setApplication(this);
        viewStack.push(startView);

        window.setScene(startView.createScene());
        window.show();
    }

    @Override
    public void stop() throws Exception {
        FileSystemController fileSystemController = new FileSystemController();
        fileSystemController.deload(bundle);
    }

    /**
     * Set next scene.
     *
     * @param oldController the old controller
     * @param newController the new controller
     */
    public void setNextScene(AbstractController oldController, AbstractController newController){
        //Sentinel
        if (viewStack.empty()){
            window.close();
            return;
        }
        int popNum = oldController.getPopNum();
        for (int i = 0; i < popNum; i++){
            viewStack.pop();
        }
        //Add new view onto stack
        if (newController != null){
            GUIViewFactory viewFactory = new GUIViewFactory();
            AbstractView newView = viewFactory.generateView(newController);
            newView.setApplication(this);
            viewStack.push(newView);
        }
        if (!viewStack.empty()){
            window.setScene(viewStack.peek().createScene());
        }
    }
}












