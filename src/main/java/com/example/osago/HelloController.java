package com.example.osago;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import animation.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    private Button authSignInButton;
    @FXML
    private TextField login_field;
    @FXML
    private TextField password_field;
    @FXML

    void initialize() {

        authSignInButton.setOnAction(event -> {
            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();

            if (!loginText.equals("") && (!loginPassword.equals(""))) {
                try {
                    loginUser(loginText, loginPassword);
                } catch (NoSuchAlgorithmException | SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else System.out.println("Login and password are empty");
        });
    }

    private void loginUser(String loginText, String loginPassword) throws NoSuchAlgorithmException, SQLException, ClassNotFoundException {
        //хэширование логина и пароля в md5
        String LP = loginPassword;
        String LT = loginText;
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] bytesPassword = md5.digest(LP.getBytes());
        byte[] bytesLogin = md5.digest(LT.getBytes());
        StringBuilder passwordHash = new StringBuilder();
        StringBuilder loginHash = new StringBuilder();
        for (byte b : bytesPassword){
            passwordHash.append(String.format("%02X",b));
        }
        for (byte b : bytesLogin){
            loginHash.append(String.format("%02X",b));
        }
        //подключение к таблице логинов и паролей
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String query = "SELECT * FROM sosago.authorization";
        Statement statement = databaseHandler.getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        String login = "", password = "";
        while (resultSet.next()) {
            login= resultSet.getString(2);
            password = resultSet.getString(3);
            //если хэши совпадают, то авторизируем и выходим из цикла
            if ((loginHash.toString()).equals(login) && (passwordHash.toString()).equals(password)) { //если введенные логин и пароль верны, перейти на главную страницу
                openNewScene("/com/example/osago/homepage.fxml");
                break;
            }
            else {
                //иначе анимация, поля трясутся
                Shake userLoginAnim = new Shake(login_field);
                Shake userPassAnim = new Shake(password_field);
                userLoginAnim.playAnim();
                userPassAnim.playAnim();
            }
        }
    }

    public void openNewScene(String window) {
        authSignInButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
