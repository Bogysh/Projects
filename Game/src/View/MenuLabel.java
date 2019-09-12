package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MenuLabel extends Label {

    String FONT_PATH = "/View/Resources/kenvector_future.ttf";
    String Background_PATH = "/View/Resources/blue_background.png";

    public MenuLabel(String txt) {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 23));
        }
        setPrefWidth(280);
        setPrefHeight(50);
        setText(txt);
        setPadding(new Insets(40, 40, 40, 40));
        setWrapText(true);
        setAlignment(Pos.TOP_CENTER);

        BackgroundImage backgroundImg = new BackgroundImage(new Image(Background_PATH, 280, 50, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);
        setBackground(new Background(backgroundImg));

    }


}
