package com.alanyu.final_project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

	@FXML
	private void navigateToProduct(ActionEvent event) {
		loadPage("/views/customer/CustProductView.fxml", "Product Page", event);
	}

	@FXML
	private void navigateToOrder(ActionEvent event) {
		loadPage("/views/customer/CustOrderView.fxml", "Order Page", event);
	}

	@FXML
	private void navigateToLogOut(ActionEvent event) {
		loadPage("/views/LoginView.fxml", "Login Page", event);
	}

	private void loadPage(String fxmlPath, String title, ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
			Parent root = loader.load();

			// Get the current stage from the button event
			Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle(title);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
