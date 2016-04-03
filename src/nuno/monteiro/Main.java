package nuno.monteiro;

import javafx.application.Application;
import javafx.stage.Stage;
import nuno.monteiro.controllers.LoginController;
import nuno.monteiro.services.MockUserService;
import nuno.monteiro.services.UserService;
import nuno.monteiro.view.Navigation;
import org.omg.CORBA.NamedValue;

/**
 * Created by codecadet on 15/03/16.
 */
public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {



        primaryStage.setOpacity(0.8);
        Navigation.getInstance().setStage(primaryStage);
        Navigation.getInstance().loadScreen("login");

        UserService userService = new MockUserService();
        LoginController loginController = (LoginController) Navigation.getInstance().getInitializable("login");

        loginController.setUserService(userService);

    }


}
