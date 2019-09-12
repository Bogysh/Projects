package View;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameButton extends Button {

    String FONT_PATH = "/View/Resources/kenvector_future.ttf";
    String CLICKED_BUTTON_PATH = "-fx-background-color: transparent;" +
            " -fx-background-image: url('/View/Resources/clicked_yellow_button.png');";
    String BUTTON_PATH = "-fx-background-color: transparent;" +
            " -fx-background-image: url('/View/Resources/yellow_button.png');";


    public GameButton(String text) {
        setText(text);
        setButtonFont();
        setPrefHeight(45);
        setPrefWidth(190);
        setStyle(BUTTON_PATH);
        initializeButtonListeners();
    }


    public void setButtonFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 23));
        }
    }

    public void setButtonPressed() {
        setStyle(CLICKED_BUTTON_PATH);
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4);
    }

    public void setButtonReleased() {
        setStyle(BUTTON_PATH);
        setPrefHeight(45);
        setLayoutY(getLayoutY() - 4);
    }


    public void initializeButtonListeners() {

        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonPressed();
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonReleased();
                }
            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(new DropShadow());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(null);
            }
        });

    }
}
