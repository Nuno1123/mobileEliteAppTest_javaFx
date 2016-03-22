/**
 * Sample Skeleton for 'login.fxml' Controller Class
 */

package nuno.monteiro.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import nuno.monteiro.models.User;
import nuno.monteiro.services.UserService;
import nuno.monteiro.view.Navigation;


public class LoginController implements Initializable{


    private UserService userService;

    @FXML
    private VBox vBox;



    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="usernameField"
    private TextField usernameField; // Value injected by FXMLLoader

    @FXML // fx:id="passwordField"
    private PasswordField passwordField; // Value injected by FXMLLoader

    @FXML // fx:id="emailLabel"
    private Label emailLabel; // Value injected by FXMLLoader

    @FXML // fx:id="emailField"
    private TextField emailField; // Value injected by FXMLLoader

    @FXML // fx:id="registerButton"
    private Button registerButton; // Value injected by FXMLLoader

    @FXML // fx:id="cancelHyperlink"
    private Hyperlink cancelHyperlink; // Value injected by FXMLLoader

    @FXML // fx:id="confirmationLabel"
    private Label confirmationLabel; // Value injected by FXMLLoader

    private boolean loginMode = false;

    private static final String PLAY_BUTTON = "Play";
    private static final String REGISTER_LINK = "Register";
    private static final String REGISTER_BUTTON = "Register";
    private static final String CANCEL_LINK = "Cancel";
    private static final String EMPTY = "";
    private static final String LOGIN_SUCCESSFUL = "You have successfully logged in";
    private static final String FONT = "System";
    private static final String LOGIN_FAILED = "You are not registered, please register";
    private static final String REGISTER_SUCCESSFUL = "You have successfully registered";
    private static final String REGISTER_FAILED = "You can't register with blank insertions";
    private static final String EMAIL_INCORRECT = "Your email is invalid";

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @FXML
    void cancel(ActionEvent event) {

        if (!loginMode) {
            changeToLogin();
        } else {

            changeToRegister();
        }
        clearField();
    }

    private void clearField(){
        // set user ui fields
        usernameField.setText(EMPTY);
        passwordField.setText(EMPTY);
        emailField.setText(EMPTY);

       // confirmationLabel.setVisible(false);
    }

    private void changeToRegister() {
        registerButton.setText(REGISTER_BUTTON);
        cancelHyperlink.setText(CANCEL_LINK);
        emailField.setVisible(true);
        emailLabel.setVisible(true);
        loginMode = false;
    }

    private void changeToLogin() {
        registerButton.setText(PLAY_BUTTON);
        cancelHyperlink.setText(REGISTER_LINK);
        emailField.setVisible(false);
        emailLabel.setVisible(false);
        loginMode = true;
    }

    private boolean checkFieldInput(String username, String password, String email) {

        return (username.equals(EMPTY) || password.equals(EMPTY) || email.equals(EMPTY));
    }

    @FXML
    void onRegister(ActionEvent event) {


        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();


        if (!loginMode) {

            register(username, password, email);
        } else {
            authenticate(username, password);
        }
    }

    private void authenticate(String username, String password) {

        if (userService.authenticate(username, password)) {
            confirmationLabel.setText(LOGIN_SUCCESSFUL);
            confirmationLabel.setTextFill(Color.DARKCYAN);
            confirmationLabel.setFont(Font.font(FONT, FontWeight.BOLD, 12));
            Navigation.getInstance().loadScreen("page");
            PageController pageController = (PageController) Navigation.getInstance().getInitializable("page");
            pageController.setUserService(userService);
            pageController.setBarUsername(username);
            clearField();

        } else {

            confirmationLabel.setText(LOGIN_FAILED);
            confirmationLabel.setTextFill(Color.DARKRED);
            confirmationLabel.setFont(Font.font(FONT, FontWeight.BOLD, 12));
            clearField();

        }
    }

    private void register(String username, String password, String email) {
        int counter = userService.count();


        if (!checkFieldInput(username, password, email)) {

            if (!(email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))) {
                confirmationLabel.setText(EMAIL_INCORRECT);
                confirmationLabel.setTextFill(Color.DARKRED);
                confirmationLabel.setFont(Font.font(FONT, FontWeight.BOLD, 12));
                clearField();
                return;
            }

            User user = new User(username, password, email);
            userService.addUser(user);

            if (userService.count() == ++counter) {

                confirmationLabel.setText(REGISTER_SUCCESSFUL);
                confirmationLabel.setTextFill(Color.DARKGREEN);
                confirmationLabel.setFont(Font.font(FONT, FontWeight.BOLD, 12));
                clearField();
                loginMode = true;
                changeToLogin();

            }

        }  else {
            confirmationLabel.setText(REGISTER_FAILED);
            confirmationLabel.setTextFill(Color.DARKRED);
            confirmationLabel.setFont(Font.font(FONT, FontWeight.BOLD, 12));
        }
    }


        // This method is called by the FXMLLoader when initialization is complete
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
