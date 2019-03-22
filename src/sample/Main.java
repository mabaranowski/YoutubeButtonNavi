package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("YoutubeButtonNavi");
        primaryStage.setScene(new Scene(root, 1920, 1100));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.getScene().setFill(Color.BLACK);
        primaryStage.setOpacity(0.01);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();

        primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, e -> {

            //Add URL paths
            if(e.getCode().equals(KeyCode.DIGIT1)) {
                Controller.choosePlaylist("");
            } else if(e.getCode().equals(KeyCode.DIGIT2)) {
                Controller.choosePlaylist("");
            } else if(e.getCode().equals(KeyCode.DIGIT3)) {
                Controller.choosePlaylist("");
            } else if(e.getCode().equals(KeyCode.DIGIT4)) {
                Controller.choosePlaylist("");
            } else if(e.getCode().equals(KeyCode.DIGIT5)) {
                Controller.choosePlaylist("");
            } else if(e.getCode().equals(KeyCode.DIGIT6)) {
                Controller.choosePlaylist("");
            } else if(e.getCode().equals(KeyCode.DIGIT7)) {
                Controller.choosePlaylist("");
            } else if(e.getCode().equals(KeyCode.DIGIT8)) {
                Controller.choosePlaylist("");
            } else if(e.getCode().equals(KeyCode.DIGIT9)) {
                Controller.choosePlaylist("");
            } else if(e.getCode().equals(KeyCode.DIGIT0)) {
                Controller.maximizeView();
            } else if(e.getCode().equals(KeyCode.ENTER)) {
                Controller.stopPlay();
            } else if (e.getCode().equals(KeyCode.ESCAPE)) {
                if (!primaryStage.isIconified()) {
                    primaryStage.setIconified(true);
                }
            } else if(e.getCode().equals(KeyCode.BACK_SPACE)) {
                Controller.driver.quit();
                primaryStage.close();
            }
        });

        closeOnClose(primaryStage);

        primaryStage.setOnCloseRequest(event -> {
            Controller.driver.quit();
        });

    }

    private void closeOnClose(Stage stage) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                while(true) {
                    try {
                        Controller.driver.getTitle();
                    } catch (Exception exc) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                stage.close();
                                System.exit(0);
                            }
                        });
                    }

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(task).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
