package com.alanyu.final_project.controllers;

import com.alanyu.final_project.models.User;
import com.alanyu.final_project.service.UserService;
import com.alanyu.final_project.service.impl.UserServiceImpl;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@FXML
	private TextField userIdField;
	@FXML
	private TextField accountField;
	@FXML
	private TextField passwordField;
	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField adminAccessField;

	//	User table
	@FXML
	private TableView<User> userTable;
	@FXML
	private TableColumn<User, String> userId;
	@FXML
	private TableColumn<User, String> account;
	@FXML
	private TableColumn<User, String> password;
	@FXML
	private TableColumn<User, String> first_name;
	@FXML
	private TableColumn<User, String> last_name;
	@FXML
	private TableColumn<User, Boolean> admin_access;
	@FXML
	private TableColumn<User, Date> createDate;
	@FXML
	private TableColumn<User, Date> amendDate;

	// Service
	UserService userService = new UserServiceImpl();

	@FXML
	public void listAllUser() {

		// Configure Table Columns
		userId.setCellValueFactory(new PropertyValueFactory<>("userId"));
		account.setCellValueFactory(new PropertyValueFactory<>("account"));
		password.setCellValueFactory(new PropertyValueFactory<>("password"));
		first_name.setCellValueFactory(new PropertyValueFactory<>("first_name"));
		last_name.setCellValueFactory(new PropertyValueFactory<>("last_name"));
		admin_access.setCellValueFactory(new PropertyValueFactory<>("admin_access"));
		createDate.setCellValueFactory(new PropertyValueFactory<>("createDate"));
		amendDate.setCellValueFactory(new PropertyValueFactory<>("amendDate"));

		List<User> users = userService.getUserList();

		ObservableList<User> userList = FXCollections.observableArrayList(users);
		userTable.setItems(userList);

	}

	@FXML
	private void addSelectedUser(ActionEvent event) {
		User user = new User();
		user.setAccount(accountField.getText());
		user.setPassword(passwordField.getText());
		user.setFirst_name(firstNameField.getText());
		user.setLast_name(lastNameField.getText());
		user.setAdmin_access(Boolean.valueOf(adminAccessField.getText()));

		userService.insertUser(user);

		showAlert("Success", "Product insert successfully.");
		loadPage("/views/admin/AdminUserView.fxml", "Product List", event);
	}

	@FXML
	private void deleteSelectedUser() {
		// Get selected product
		User selectedUser = userTable.getSelectionModel().getSelectedItem();

		if (selectedUser != null) {
			// Remove from the ObservableList
			userService.deleteUser(selectedUser.getUserId());

			showAlert("Success", "Product deleted successfully.");
		} else {
			showAlert("Error", "No product selected.");
		}
	}

	@FXML
	private void updateSelectUser(ActionEvent event) {

		if (userIdField != null) {
			User user = new User();
			user.setUserId(Integer.valueOf(userIdField.getText()));
			user.setAccount(accountField.getText());
			user.setPassword(passwordField.getText());
			user.setFirst_name(firstNameField.getText());
			user.setLast_name(lastNameField.getText());
			user.setAdmin_access(Boolean.valueOf(adminAccessField.getText()));

			// Update from the ObservableList
			userService.updateUserById(user);

			showAlert("Success", "User update successfully.");
			loadPage("/views/admin/AdminUserView.fxml", "User List", event);
		} else {
			showAlert("Error", "No product selected.");
		}
	}


	@FXML
	private void directToAdminUser(ActionEvent event) {
		loadPage("/views/admin/AdminUserView.fxml", "User Page", event);
	}

	@FXML
	private void directToAdminMenu(ActionEvent event) {
		loadPage("/views/admin/AdminMenuView.fxml", "Menu", event);
	}

	@FXML
	private void directToAdminAddUser(ActionEvent event) {
		loadPage("/views/admin/AdminAddUserView.fxml", "Add User", event);
	}

	@FXML
	private void directToAdminUpdateUser(ActionEvent event) {
		try {
			// Get selected product
			User selectedUser = userTable.getSelectionModel().getSelectedItem();
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/views/admin/AdminUpdateUserView.fxml"));
			Parent root = loader.load();

			// Get Controller
			UserController controller = loader.getController();

			// Transform Data
			controller.displayUserDetails(selectedUser);

			// Get the current stage from the button event
			Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Edit User");
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	private void showAlert(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}

	private void displayUserDetails(User user) {
		userIdField.setText(String.valueOf(user.getUserId()));
		accountField.setText(user.getAccount());
		passwordField.setText(user.getPassword());
		firstNameField.setText(user.getFirst_name());
		lastNameField.setText(user.getLast_name());
		adminAccessField.setText(String.valueOf(user.getAdmin_access()));
	}

}
