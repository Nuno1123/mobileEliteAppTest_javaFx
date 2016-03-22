package nuno.monteiro.view;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by codecadet on 17/03/16.
 */
public class Navigation {


    private final int MIN_WIDTH = 1240; // window width
    private final int MIN_HEIGHT = 720; // window height
    private final String VIEW_PATH = "/nuno/monteiro/view";

    private LinkedList<Scene> scenes = new LinkedList<Scene>(); // Navigation History
    private Map<String, Initializable> controllers = new HashMap<>(); //Container of controllers

    private Stage stage; // reference to the application window


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Initializable getInitializable (String view) {
        return controllers.get(view);
    }

    // static instance of this class
    private static Navigation instance = null;

    // private constructor so its not possible to instantiate from outside
    private Navigation() {
    }

    // static method that returns the instance
    public static Navigation getInstance() {

        // the instance is created only the first time this method is called
        if(instance == null) {
            instance = new Navigation();
        }

        // it always return the same instance, there is no way to have another one
        return instance;
    }



    public void loadScreen(String view) {
        try {

            // Instantiate the view and the controller
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(getClass().getResource(VIEW_PATH + "/" + view + ".fxml"));
            Parent root = fxmlLoader.load();

            //Store the controller
            controllers.put(view, fxmlLoader.getController());

            // Create a new scene and add it to the stack
            Scene scene = new Scene(root, MIN_WIDTH, MIN_HEIGHT);
            scene.getStylesheets().add(view + ".css");
            scenes.push(scene);

            // Put the scene on the stage
            setScene(scene);

        } catch (IOException e) {
            System.out.println("Failure to load view " + view + " : " + e.getMessage());
        }
    }

    public void back() {

        if (scenes.isEmpty()) {
            return;
        }

        // remove the current scene from the stack
        scenes.pop();

        // load the scene at the top of the stack
        setScene(scenes.peek());
    }


    private void setScene(Scene scene) {



        // set the scene
        stage.setScene(scene);

        // show the stage to reload
        stage.show();
    }
}
