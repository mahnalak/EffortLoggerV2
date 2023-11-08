package application;
	
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Main extends Application {
	
	private AdminDatabase adminDatabase;
	private UserDatabase userDatabase; 

	public Main() {
    	adminDatabase = new AdminDatabase();
        userDatabase = new UserDatabase(); 
    }

	@Override
	public void start(Stage primaryStage) {
		// try {
		// 	URL url = new File("src/application/fxmlUI/MainConsole.fxml").toURI().toURL();
		// 	Parent root = FXMLLoader.load(url);
			
		// 	Scene scene = new Scene(root,600,400);

		// 	primaryStage.setScene(scene);
		// 	primaryStage.show();
		// } catch(Exception e) {
		// 	e.printStackTrace();
		// }
		try {
            primaryStage.setTitle("Login");

            TextField usernameField = new TextField();
            usernameField.setPromptText("Username");

            usernameField.setText("joshinsam");
            
            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Password");
            
            passwordField.setText("playstation5"); 
            
            Button login = new Button("LOGIN");

            CheckBox admin_cb = new CheckBox("admin") ; 
            admin_cb.setIndeterminate(false);
            
            admin_cb.setSelected(true); 
            
            InputStream UIstream = new FileInputStream("src/application/user.png");
            Image userImage = new Image(UIstream);
            
            InputStream PIstream = new FileInputStream("src/application/padlock.png");
            Image passwordImage = new Image(PIstream);

            
            ImageView userImageView = new ImageView(userImage);
            userImageView.setFitWidth(15); 
            userImageView.setFitHeight(15); 

            ImageView passwordImageView = new ImageView(passwordImage);
            passwordImageView.setFitWidth(15); 
            passwordImageView.setFitHeight(15);

            GridPane root = new GridPane();
            root.setHgap(10); 
            root.setVgap(10); 
            root.setPadding(new Insets(10)); 

            
            root.add(userImageView, 0, 0); 
            root.add(usernameField, 1, 0); 
            root.add(passwordImageView, 0, 1); 
            root.add(passwordField, 1, 1); 
            
            VBox in_root = new VBox(10); 
            
            in_root.getChildren().add(admin_cb); 
            in_root.getChildren().add(login); 
            
            in_root.setAlignment(Pos.CENTER); 
            root.add(in_root,1,2); 
            
            root.setAlignment(Pos.CENTER); 
            
            
            login.setOnAction(event -> {
            
            	//Perform authentication
            	String userName = usernameField.getText();
            	String password = passwordField.getText(); 
            	
            	
            	
            	if (admin_cb.isSelected()) {
            		File adminD = new File("AdminDatabase.txt");
//	            		 if (adminD.createNewFile()) {
//	                 		Boolean check = adminDatabase.checkAdminAuthentication(userName, password,adminD);
//	                 		if (check) {            		
//	                			openAdminWindow(); 
//	                		}
//	                		else {
//	                			showAlert("The password or username you entered is incorrect");
//	                		}
//	            		 } 
//	            		 if {
						 Boolean check = adminDatabase.checkAdminAuthentication(userName, password,adminD);
					 		if (check) {            		
								openAdminWindow(); 
							}
							else {
								showAlert("The password or username you entered is incorrect");
							}
//	            		 }
            	}
            	
            	else {
            		
            		try {
            			
            			File UserD = new File("UserDatabase.txt");
	            		 if (UserD.createNewFile()) {
	                 		Boolean check = userDatabase.checkUserAuthentication(userName, password, UserD);
	                 		if (check) {            		
	                 			openUserWindow(); 
	                		}
	                		else {
	                			showAlert("The password or username you entered is incorrect");
	                		}
	            		 } 
	            		 else {
	            		        System.out.println("File already exists.");
	            		 }
            		 } catch (IOException e) {
            		      System.out.println("An error occurred.");
            		      e.printStackTrace();
            		 }
            	}
            }); 
            
            Scene scene = new Scene(root, 400, 400);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private void showAlert(String message) {
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("");
    	alert.setContentText(message);
        alert.showAndWait();
    }

	private void openAdminWindow() {
    	AdminWindow adminWindow = new AdminWindow(); 
    	adminWindow.show() ;
    }
    
    private void openUserWindow() {
    	UserWindow userWindow = new UserWindow(); 
    	userWindow.show(); 
    }

	public static void main(String[] args) {
		launch(args);
	}

	
}
