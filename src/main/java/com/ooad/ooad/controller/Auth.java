package com.ooad.ooad.controller;

import com.ooad.ooad.utils.AlertMessage;
import com.ooad.ooad.utils.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Auth implements Initializable {
    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phoneNum;

    @FXML
    private ComboBox<String> roles;

    @FXML
    private CheckBox showPass;

    @FXML
    private TextField showPassBox;

    private AlertMessage alert = new AlertMessage();

    private Connection connection;
    private PreparedStatement prepare;
    private ResultSet result;

    ObservableList<String> roleList = FXCollections.observableArrayList("manager", "leader", "employee");

    public void showAlert() {
        alert.errorMessage("Error");
    }

    public void login() {
        String phoneNumber = phoneNum.getText();
        String pass = password.getText();

        if (phoneNumber.length() == 0 || pass.length() == 0) {
            alert.errorMessage("You must fill all the required information!");
            return;
        }
        String role = roles.getValue();
        if (role == null || role.length() == 0) {
            alert.errorMessage("You must select role!");
            return;
        }

        connection = DBUtil.connectDB();
        String sql = "select password from " + role + " where phone=" + phoneNumber;

        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                String passwordDB = result.getString("password");
                System.out.println("Password from db: " + passwordDB);
                System.out.println("Password: " + pass);
                if (passwordDB.equals(pass)) {
                    alert.successMessage("Login successfully!");
                    return;
                }
                else {
                    alert.errorMessage("Password is incorrect!");
                }
            } else {
                alert.errorMessage("Phone number doesn't exist!");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showPassWord() {
        if (showPass.isSelected()) {
            showPassBox.setText(password.getText());
            showPassBox.setVisible(true);
            password.setVisible(false);
        } else {
            password.setText(showPassBox.getText());
            showPassBox.setVisible(false);
            password.setVisible(true);
        }
    }

    public void connectDb() {
        connection = DBUtil.connectDB();
        String sql = "SELECT * FROM ooad.manager";
        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            System.out.println(result);
            if (result.next()) {
                System.out.println(result.getInt(2));
                alert.successMessage("Successs");
            } else {
                alert.errorMessage("Error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle sb) {
        roles.setItems(roleList);
    }
}
