package com.alanyu.final_project.controllers;

import com.alanyu.final_project.models.User;
import com.alanyu.final_project.service.UserService;
import com.alanyu.final_project.service.impl.UserServiceImpl;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Label errorMessage;

	UserService userService = new UserServiceImpl();

	static User user;

	/*
		login method
		Validate the user password exist or not
	 * */
	@FXML
	public void login() {
		String username = usernameField.getText();
		String password = passwordField.getText();

		if (isValidCredentials(username, password)) {
			errorMessage.setText("Login successful!");
			errorMessage.setStyle("-fx-text-fill: green;");
			showAlert("Login", "Login successful!");

			try {

				if (true == user.getAdmin_access()) {
					FXMLLoader loader = new FXMLLoader(
							getClass().getResource("/views/admin/AdminMenuView.fxml"));
					Parent productRoot = loader.load();

					// Get the current stage
					Stage stage = (Stage) usernameField.getScene().getWindow();

					// Set the new scene
					Scene scene = new Scene(productRoot);
					stage.setScene(scene);
					stage.setTitle("Menu");

				} else {
					FXMLLoader loader = new FXMLLoader(
							getClass().getResource("/views/customer/CustMenuView.fxml"));
					Parent productRoot = loader.load();

					// Get the current stage
					Stage stage = (Stage) usernameField.getScene().getWindow();

					// Set the new scene
					Scene scene = new Scene(productRoot);
					stage.setScene(scene);
					stage.setTitle("Menu");
				}


			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			errorMessage.setText("Invalid username or password");
			errorMessage.setStyle("-fx-text-fill: red;");
			showAlert("Login", "Invalid username or password");
		}
	}

	private boolean isValidCredentials(String username, String password) {
		user = userService.getUserByAccount(username);
		if (null != user) {
			if (username.equals(user.getAccount()) && password.equals(user.getPassword())) {
				return true;
			}
		}

		return false;
	}

	private void showAlert(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}

}
