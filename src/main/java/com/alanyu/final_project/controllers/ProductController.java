package com.alanyu.final_project.controllers;

import com.alanyu.final_project.Enum.ProductCategory;
import com.alanyu.final_project.models.Product;
import com.alanyu.final_project.service.ProductService;
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
public class ProductController {

	private ProductService productService = new ProductServiceImpl();

	@FXML
	private TextField productIdField;
	@FXML
	private TextField productNameField;
	@FXML
	private TextField categoryField;
	@FXML
	private TextField imageUrlField;
	@FXML
	private TextField priceField;
	@FXML
	private TextField stockField;
	@FXML
	private TextField descriptionField;


	//	Product Table
	@FXML
	private TableView<Product> productTable;
	@FXML
	private TableColumn<Product, Integer> productId;
	@FXML
	private TableColumn<Product, String> productName;
	@FXML
	private TableColumn<Product, String> category;
	@FXML
	private TableColumn<Product, String> image_url;
	@FXML
	private TableColumn<Product, Integer> price;
	@FXML
	private TableColumn<Product, Integer> stock;
	@FXML
	private TableColumn<Product, String> description;
	@FXML
	private TableColumn<Product, Date> createDate;
	@FXML
	private TableColumn<Product, Date> amendDate;

	@FXML
	public void getProduct(Integer productId) {

		try {
			Product product = productService.getProductById(productId);
			if (product != null) {
				displayProductDetails(product);
			} else {
				System.out.println("Product not found.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid Product ID format.");
		}
	}

	@FXML
	public void listAllProduct() {

		// Configure Table Columns
		productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
		productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
		category.setCellValueFactory(new PropertyValueFactory<>("category"));
		image_url.setCellValueFactory(new PropertyValueFactory<>("image_url"));
		price.setCellValueFactory(new PropertyValueFactory<>("price"));
		stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
		description.setCellValueFactory(new PropertyValueFactory<>("description"));
		createDate.setCellValueFactory(new PropertyValueFactory<>("createDate"));
		amendDate.setCellValueFactory(new PropertyValueFactory<>("amendDate"));

		List<Product> products = productService.getAllProduct();

		ObservableList<Product> productList = FXCollections.observableArrayList(products);
		productTable.setItems(productList);

	}

	@FXML
	public void insertProduct(ActionEvent event) {

		Product product = new Product();
		product.setProductName(productNameField.getText());
		product.setCategory(ProductCategory.valueOf(categoryField.getText()));
		product.setImageUrl(imageUrlField.getText());
		product.setPrice(Integer.valueOf(priceField.getText()));
		product.setStock(Integer.valueOf(stockField.getText()));
		product.setDescription(descriptionField.getText());

		productService.insertProduct(product);

		showAlert("Success", "Product insert successfully.");
		loadPage("/views/admin/AdminProductView.fxml", "Product List", event);
	}

	@FXML
	private void deleteSelectedProduct() {
		// Get selected product
		Product selectedProduct = productTable.getSelectionModel().getSelectedItem();

		if (selectedProduct != null) {
			// Remove from the ObservableList
			productService.deleteProductById(selectedProduct.getProductId());

			showAlert("Success", "Product deleted successfully.");
		} else {
			showAlert("Error", "No product selected.");
		}
	}

	@FXML
	private void updateSelectedProduct(ActionEvent event) {

		if (productIdField != null) {
			Product product = new Product();
			product.setProductId(Integer.valueOf(productIdField.getText()));
			product.setProductName(productNameField.getText());
			product.setCategory(ProductCategory.valueOf(categoryField.getText()));
			product.setImageUrl(imageUrlField.getText());
			product.setPrice(Integer.valueOf(priceField.getText()));
			product.setStock(Integer.valueOf(stockField.getText()));
			product.setDescription(descriptionField.getText());

			// Update from the ObservableList
			productService.updateProductById(product);

			showAlert("Success", "Product update successfully.");
			loadPage("/views/admin/AdminProductView.fxml", "Product List", event);
		} else {
			showAlert("Error", "No product selected.");
		}
	}

	@FXML
	public void custListAllProduct() {

		// Configure Table Columns
		productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
		category.setCellValueFactory(new PropertyValueFactory<>("category"));
		image_url.setCellValueFactory(new PropertyValueFactory<>("image_url"));
		price.setCellValueFactory(new PropertyValueFactory<>("price"));
		stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
		description.setCellValueFactory(new PropertyValueFactory<>("description"));

		List<Product> products = productService.getAllProduct();

		ObservableList<Product> productList = FXCollections.observableArrayList(products);
		productTable.setItems(productList);
	}

	// Admin navigator
	@FXML
	private void directToAddProduct(ActionEvent event) {
		loadPage("/views/admin/AdminAddProductView.fxml", "Product List", event);
	}

	@FXML
	private void directToAdminMenu(ActionEvent event) {
		loadPage("/views/admin/AdminMenuView.fxml", "Menu", event);
	}

	@FXML
	private void directToAdminProductList(ActionEvent event) {
		loadPage("/views/admin/AdminProductView.fxml", "Product List", event);
	}

	@FXML
	private void directToUpdateProduct(ActionEvent event) {
		try {
			// Get selected product
			Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/views/admin/AdminUpdateProductView.fxml"));
			Parent root = loader.load();

			// Get Controller
			ProductController controller = loader.getController();

			// Transform data
			controller.displayProductDetails(selectedProduct);

			// Get the current stage from the button event
			Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Edit Product");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void directOrderProduct(ActionEvent event) {
		try {
			// Get selected product
			Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/views/admin/AdminAddOrderView.fxml"));
			Parent root = loader.load();

			// Get Controller
			OrderController controller = loader.getController();

			// Transform Data
			controller.displayOrderDetails(selectedProduct);

			// Get the current stage from the button event
			Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Order Product");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

// Cust Navigator
@FXML
private void directToCustMenu(ActionEvent event) {
	loadPage("/views/customer/CustMenuView.fxml", "Menu", event);
}

	@FXML
	private void directCustOrderProduct(ActionEvent event) {
		try {
			// Get selected product
			Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/views/customer/CustAddOrderView.fxml"));
			Parent root = loader.load();

			// Get Controller
			OrderController controller = loader.getController();

			// Transform Data
			controller.displayOrderDetails(selectedProduct);

			// Get the current stage from the button event
			Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Order Product");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Display product details in the UI
	private void displayProductDetails(Product product) {
		productIdField.setText(String.valueOf(product.getProductId()));
		productNameField.setText(product.getProductName());
		categoryField.setText(product.getCategory().toString());
		imageUrlField.setText(product.getImageUrl());
		priceField.setText(String.valueOf(product.getPrice()));
		stockField.setText(String.valueOf(product.getStock()));
		descriptionField.setText(product.getDescription());
	}

	private void showAlert(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
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
