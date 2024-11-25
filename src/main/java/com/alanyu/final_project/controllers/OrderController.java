package com.alanyu.final_project.controllers;

import com.alanyu.final_project.models.Order;
import com.alanyu.final_project.models.Product;
import com.alanyu.final_project.service.OrderService;
import com.alanyu.final_project.service.ProductService;
import com.alanyu.final_project.service.impl.OrderServiceImpl;
import com.alanyu.final_project.service.impl.ProductServiceImpl;
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
public class OrderController {

	private OrderService orderService = new OrderServiceImpl();
	private ProductService productService = new ProductServiceImpl();
	@FXML
	private TextField orderIdField;
	@FXML
	private TextField productNameField;
	@FXML
	private TextField productIdField;
	@FXML
	private TextField priceField;
	@FXML
	private TextField quantityField;
	@FXML
	private TextField custNameField;
	@FXML
	private TextField custIdField;

	//	Order Table
	@FXML
	private TableView<Order> orderTable;
	@FXML
	private TableColumn<Order, Integer> orderId;
	@FXML
	private TableColumn<Order, String> productName;
	@FXML
	private TableColumn<Order, String> productId;
	@FXML
	private TableColumn<Order, Integer> price;
	@FXML
	private TableColumn<Order, Integer> quantity;
	@FXML
	private TableColumn<Order, Integer> totalPrice;
	@FXML
	private TableColumn<Order, String> custName;
	@FXML
	private TableColumn<Order, Integer> custId;
	@FXML
	private TableColumn<Order, Date> createDate;
	@FXML
	private TableColumn<Order, Date> amendDate;

	@FXML
	public void listAllOrder() {

		// Configure Table Columns
		orderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
		productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
		productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
		price.setCellValueFactory(new PropertyValueFactory<>("price"));
		quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		totalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
		custName.setCellValueFactory(new PropertyValueFactory<>("custName"));
		custId.setCellValueFactory(new PropertyValueFactory<>("custId"));
		createDate.setCellValueFactory(new PropertyValueFactory<>("createDate"));
		amendDate.setCellValueFactory(new PropertyValueFactory<>("amendDate"));

		List<Order> orders = orderService.getAllOrder();

		ObservableList<Order> orderList = FXCollections.observableArrayList(orders);
		orderTable.setItems(orderList);

	}

	@FXML
	public void listCustAllOrder() {

		// Configure Table Columns
		orderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
		productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
		productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
		price.setCellValueFactory(new PropertyValueFactory<>("price"));
		quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		totalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
		custName.setCellValueFactory(new PropertyValueFactory<>("custName"));
		custId.setCellValueFactory(new PropertyValueFactory<>("custId"));
		createDate.setCellValueFactory(new PropertyValueFactory<>("createDate"));
		amendDate.setCellValueFactory(new PropertyValueFactory<>("amendDate"));

		List<Order> orders = orderService.getOrderByCustId(LoginController.user.getUserId());

		ObservableList<Order> orderList = FXCollections.observableArrayList(orders);
		orderTable.setItems(orderList);

	}

	@FXML
	public void insertOrder(ActionEvent event) {

		Order order = new Order();
		order.setProductName(productNameField.getText());
		order.setProductId(Integer.valueOf(productIdField.getText()));
		order.setPrice(Integer.valueOf(priceField.getText()));
		order.setQuantity(Integer.valueOf(quantityField.getText()));
		order.setCustName(custNameField.getText());
		order.setCustId(Integer.valueOf(custIdField.getText()));
		orderService.insertOrder(order);
		Product product = productService.getProductById(order.getProductId());
		product.setStock(product.getStock() - order.getQuantity());
		productService.updateProductById(product);

		showAlert("Success", "Product insert successfully.");
		loadPage("/views/admin/AdminOrderView.fxml", "Order List", event);
	}

	@FXML
	public void insertCustOrder(ActionEvent event) {

		Order order = new Order();
		order.setProductName(productNameField.getText());
		order.setProductId(Integer.valueOf(productIdField.getText()));
		order.setPrice(Integer.valueOf(priceField.getText()));
		order.setQuantity(Integer.valueOf(quantityField.getText()));
		order.setCustName(custNameField.getText());
		order.setCustId(Integer.valueOf(custIdField.getText()));
		orderService.insertOrder(order);
		Product product = productService.getProductById(order.getProductId());
		product.setStock(product.getStock() - order.getQuantity());
		productService.updateProductById(product);

		showAlert("Success", "Product insert successfully.");
		loadPage("/views/customer/CustOrderView.fxml", "Order List", event);
	}

	@FXML
	private void deleteSelectedOrder() {
		// Get selected order
		Order selectedOrder = orderTable.getSelectionModel().getSelectedItem();

		if (selectedOrder != null) {
			// Remove from the ObservableList
			orderService.deleteOrderById(selectedOrder.getOrderId());

			showAlert("Success", "Order deleted successfully.");
		} else {
			showAlert("Error", "No Order selected.");
		}
	}

	@FXML
	private void updateSelectedOrder(ActionEvent event) {
		Order order = new Order();
		order.setOrderId(Integer.valueOf(orderIdField.getText()));
		order.setProductName(productNameField.getText());
		order.setProductId(Integer.valueOf(productIdField.getText()));
		order.setPrice(Integer.valueOf(priceField.getText()));
		order.setQuantity(Integer.valueOf(quantityField.getText()));
		order.setCustName(custNameField.getText());
		order.setCustId(Integer.valueOf(custIdField.getText()));
		orderService.updateOrderById(order);

		showAlert("Success", "Product update successfully.");
		loadPage("/views/admin/AdminOrderView.fxml", "Product List", event);
	}

	@FXML
	private void updateSelectedCustOrder(ActionEvent event) {
		Order order = new Order();
		order.setOrderId(Integer.valueOf(orderIdField.getText()));
		order.setProductName(productNameField.getText());
		order.setProductId(Integer.valueOf(productIdField.getText()));
		order.setPrice(Integer.valueOf(priceField.getText()));
		order.setQuantity(Integer.valueOf(quantityField.getText()));
		order.setCustName(custNameField.getText());
		order.setCustId(Integer.valueOf(custIdField.getText()));
		orderService.updateOrderById(order);

		showAlert("Success", "Product update successfully.");
		loadPage("/views/customer/CustOrderView.fxml", "Product List", event);
	}

	@FXML
	private void directToUpdateOrder(ActionEvent event) {
		try {
			// Get selected product
			Order selectedOrder = orderTable.getSelectionModel().getSelectedItem();
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/views/admin/AdminUpdateOrderView.fxml"));
			Parent root = loader.load();

			// Get Controller
			OrderController controller = loader.getController();

			// Send Data to controller
			controller.displayOrderDetails(selectedOrder);

			// Get the current stage from the button event
			Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Edit Product");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void directToCustUpdateOrder(ActionEvent event) {
		try {
			// Get selected product
			Order selectedOrder = orderTable.getSelectionModel().getSelectedItem();
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/views/customer/CustUpdateOrderView.fxml"));
			Parent root = loader.load();

			// Get Controller
			OrderController controller = loader.getController();

			// Send Data to controller
			controller.displayOrderDetails(selectedOrder);

			// Get the current stage from the button event
			Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Edit Product");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void directToAdminMenu(ActionEvent event) {
		loadPage("/views/admin/AdminMenuView.fxml", "Menu", event);
	}

	@FXML
	private void directToAddOrder(ActionEvent event) {
		loadPage("/views/admin/AdminAddOrderView.fxml", "Add Order", event);
	}

	@FXML
	private void directToAdminOrderList(ActionEvent event) {
		loadPage("/views/admin/AdminOrderView.fxml", "Admin Order", event);
	}

	@FXML
	private void directToCustMenu(ActionEvent event){
		loadPage("/views/customer/CustMenuView.fxml", "Menu", event);
	}

	@FXML
	private void directToCustProduct(ActionEvent event) {
		loadPage("/views/customer/CustProductView.fxml", "Cust Product", event);
	}

	@FXML
	private void directToCustOrderList(ActionEvent event){
		loadPage("/views/customer/CustOrderView.fxml", "Cust Order", event);
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

	public void displayOrderDetails(Product order) {
		productIdField.setText(String.valueOf(order.getProductId()));
		productNameField.setText(order.getProductName());
		priceField.setText(String.valueOf(order.getPrice()));
		custNameField.setText(
				LoginController.user.getLast_name() + LoginController.user.getFirst_name());
		custIdField.setText(String.valueOf(LoginController.user.getUserId()));
	}

	public void displayOrderDetails(Order order) {
		orderIdField.setText(String.valueOf(order.getOrderId()));
		productNameField.setText(order.getProductName());
		productIdField.setText(String.valueOf(order.getProductId()));
		priceField.setText(String.valueOf(order.getPrice()));
		quantityField.setText(String.valueOf(order.getQuantity()));
		custNameField.setText(order.getCustName());
		custIdField.setText(String.valueOf(order.getCustId()));
	}

}
