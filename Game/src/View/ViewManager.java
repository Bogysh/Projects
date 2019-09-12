package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ViewManager {

    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    List<GameButton> menuButtons;
    private MenuSubscenes credits;
    private MenuSubscenes start;
    private MenuSubscenes help;
    private MenuSubscenes scores;

    private MenuSubscenes sceneToHide;


    List<ShipContainer> ShipList;
    private Ship choosenShip;


    public ViewManager(int Width, int Height) {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, Width, Height);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createButtons();
        createBackground();
        createLogo();
        createSubscenes();

    }

    public Stage getMainStage() {
        return mainStage;
    }


    public HBox Add() {
        HBox container = new HBox();
        container.setSpacing(20);
        ShipList = new ArrayList<>();
        for (Ship ship : Ship.values()) {
            ShipContainer sp = new ShipContainer(ship);
            container.getChildren().add(sp);
            ShipList.add(sp);
            sp.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    for (ShipContainer ship : ShipList) {
                        ship.setCircleChoosen(false);
                    }
                    sp.setCircleChoosen(true);
                    choosenShip = sp.getShip();
                }
            });
        }
        container.setLayoutX(25);
        container.setLayoutY(150);
        return container;

    }

    private GameButton createButton() {
        GameButton btn = new GameButton("CLICK!!!");
        mainPane.getChildren().add(btn);
        btn.setLayoutY(300);
        btn.setLayoutX(160);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (choosenShip != null) {
                    GameViewManager gvm = new GameViewManager();
                    gvm.createGame(mainStage, choosenShip);
                }
            }
        });
        return btn;
    }

    private void createBackground() {
        Image backgroundImage = new Image("View/Resources/blue_background.png", 256, 256, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }

    private void addMenuButtons(GameButton gb) {

        gb.setLayoutX(100);
        gb.setLayoutY(150 + menuButtons.size() * 100);
        menuButtons.add(gb);
        mainPane.getChildren().add(gb);

    }

    private void createSubscenes() {
        credits = new MenuSubscenes();
        //start = new MenuSubscenes();
        help = new MenuSubscenes();
        scores = new MenuSubscenes();
        mainPane.getChildren().addAll(credits, help, scores);

        createChooserSubScene();
    }

    private void createChooserSubScene() {
        start = new MenuSubscenes();
        mainPane.getChildren().add(start);

        MenuLabel startLabel = new MenuLabel("PICK YOUR SHIP");
        startLabel.setLayoutX(110);
        startLabel.setLayoutY(25);
        start.getPane().getChildren().add(startLabel);
        start.getPane().getChildren().add(Add());
        start.getPane().getChildren().add(createButton());

    }


    private void createButtons() {
        GameButton startButton = new GameButton("START");
        addMenuButtons(startButton);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                hideRest(start);
            }
        });

        GameButton scoreButton = new GameButton("SCORES");
        addMenuButtons(scoreButton);
        scoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                hideRest(scores);
            }
        });

        GameButton helpButton = new GameButton("HELP");
        addMenuButtons(helpButton);
        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                hideRest(help);
            }
        });

        GameButton creditsButton = new GameButton("CREDITS");
        addMenuButtons(creditsButton);
        creditsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                hideRest(credits);
            }
        });

        GameButton exitButton = new GameButton("EXIT");
        addMenuButtons(exitButton);
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.close();
            }
        });
    }

    public void createLogo() {
        ImageView logo = new ImageView("View/Resources/Logo.png");
        logo.setLayoutX(600);
        logo.setLayoutY(25);
        logo.setPreserveRatio(true);
        logo.setFitHeight(200);
        mainPane.getChildren().add(logo);
    }

    public void hideRest(MenuSubscenes sub) {
        if (sceneToHide != null) {
            sceneToHide.moveSubscene();
        }
        sub.moveSubscene();
        sceneToHide = sub;
    }
}
