package nuno.monteiro.controllers;

import com.sun.java.swing.action.AlignCenterAction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import nuno.monteiro.services.MockUserService;
import nuno.monteiro.services.UserService;

import java.net.URL;
import java.util.ResourceBundle;

public class PageController implements Initializable{

    private UserService userService;


    @FXML
    private VBox vbox;

    @FXML
    private MenuBar bar;

    @FXML
    private Menu barUsername;

    @FXML
    private ImageView image;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setBarUsername(String username){

        barUsername.setText(username);
    }
    
}
