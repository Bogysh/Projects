package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class infoLabel extends Label {

    private String font_Path = "View/Resources/kenvector_future.ttf";

    public infoLabel(String txt){
        setHeight(130);
        setWidth(50);
        BackgroundImage backgroundImage = new BackgroundImage(new Image("View/Resources/buttonRed.png",130,50,false,true),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null);
        setBackground(new Background(backgroundImage));
        setAlignment(Pos.CENTER);
        setPadding(new Insets(10,10,10,10));
        setFont();
        setText(txt);
    }

    private void setFont(){
        try {
            setFont(Font.loadFont(new FileInputStream(new File(font_Path)),15));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana",15));
        }
    }
}
