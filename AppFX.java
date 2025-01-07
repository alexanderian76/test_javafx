
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AppFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                var client = HttpClient.newHttpClient();
                var request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:1234/home"))
                    .build();

                var alert = new Alert(Alert.AlertType.ERROR);

                try {
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    alert.setContentText(response.body());
                } catch (InterruptedException | IOException e) {
                    throw new RuntimeException(e);
                }

                alert.setTitle("qwe");

                alert.show();
                alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
                    @Override
                    public void handle(DialogEvent dialogEvent) {
                        primaryStage.setTitle("Hello World!");
                    }
                });
            }
        });
        TextField txt = new TextField();
        txt.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Enter!");
            }
        });
        txt.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                System.out.println(txt.getText());
                primaryStage.setTitle(txt.getText());
            }
        });


        StackPane root = new StackPane();
        var children = root.getChildren();
        StackPane node = new StackPane();
        node.setAlignment(Pos.BOTTOM_CENTER);
        node.getChildren().add(txt);
        node.setStyle("-fx-padding: 10");
        children.add(node);
        btn.setStyle("-fx-color: red");
        children.add(btn);

        root.setStyle("-fx-background-color: blue");
        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}