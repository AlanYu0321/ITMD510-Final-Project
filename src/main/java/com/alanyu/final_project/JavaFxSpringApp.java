package com.alanyu.final_project;

import static com.alanyu.final_project.dao.DBConnect.initialize;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.alanyu.final_project")
public class JavaFxSpringApp extends Application {

	private ConfigurableApplicationContext springContext;

	@Override
	public void init() {
		springContext = SpringApplication.run(FinalProjectApplication.class);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		initialize();

		FXMLLoader loader = new FXMLLoader(getClass().getResource(
				"/views/LoginView.fxml"));
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();

	}


	@Override
	public void stop() {
		springContext.close();
	}

}
