/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3_smart_home;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Ruba Balubaid
 */

public class Lab3_smart_home extends Application {
    
        //temperature
        private int temperature = 20;
     
        // time
        private Text timeText;
        

    @Override
    public void start(Stage primaryStage) {
        
        timeText = new Text();
        timeText.setFont(Font.font(12)); 
        
        
        // the main Pane
        BorderPane bPane = new BorderPane();
        
        // tempreature by keyboard
        Scene scene = new Scene(bPane, 200, 350);
        
        // name of company
        HBox hBox2 = new HBox();
        Text name =  new Text("ZID Company");
        name.setFont(Font.font("Calisto MT", FontWeight.NORMAL, FontPosture.REGULAR, 14));
        hBox2.getChildren().addAll(name);
        hBox2.setAlignment(Pos.CENTER);
        hBox2.setStyle("-fx-background-color: DARKGREY");
        bPane.setTop(hBox2);
        
              
        
        // temperature
//        Image plus = new Image("https://icons8.com/icon/r10O4lLbmm2A/plus");
        Button inbtn = new Button("➕");        
//        inbtn.setGraphic(new ImageView(plus));
        Button debtn = new Button("➖");       
        HBox hBox = new HBox();
        hBox.getChildren().addAll(inbtn, debtn);
        hBox.setSpacing(3);
        hBox.setAlignment(Pos.CENTER);
        
        
        // screen                      
        // fan icon
        Image fan = new Image("image/fan.png");        
        ImagePattern pattern = new ImagePattern(fan);         
        Circle fillCircle = new Circle(13);
        fillCircle.setFill(pattern);
        fillCircle.setStroke(Color.LIGHTGRAY);
        
        // rotate fan
        RotateTransition rotate = new RotateTransition(Duration.seconds(1)); 
        
        // top of screen    
        Label l1 = new Label();
        Font myFont = new Font("Calisto MT", 24);// font for all nodes
        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(l1, fillCircle);
        hbox2.setSpacing(10);
        hbox2.setAlignment(Pos.CENTER);
        l1.setText(Integer.toString(temperature)+"ْ");         
        l1.setFont(myFont);   
              
        // increase temperature
        inbtn.setOnAction((ActionEvent e) -> {
            setTemperature(getTemperature()+1);
            System.out.println("the tempreature: "+temperature);
            l1.setText(Integer.toString(temperature)+"ْ");
        });
        
        // decrease temperature
        debtn.setOnAction((ActionEvent e) -> {
            setTemperature(getTemperature()-1);
            System.out.println("the tempreature: "+temperature);
            l1.setText(Integer.toString(temperature)+"ْ");
        });
        
        // temperature control by keyboard
        scene.setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.UP){
                setTemperature(getTemperature()+1);
                System.out.println(temperature);
                l1.setText(Integer.toString(temperature)+"ْ");
            }
            else if(e.getCode() == KeyCode.DOWN){
                setTemperature(getTemperature()-1);
                System.out.println(temperature);
                l1.setText(Integer.toString(temperature)+"ْ");
            }
        });
        
//        // mouse event 
//        fillCircle.setOnMouseClicked(e -> {
//            rotate.setNode(fillCircle);
//            rotate.setByAngle(360);
//            rotate.setCycleCount(100);
//            rotate.play();
//        });
               
        // update on $ off button
        ToggleButton tb1 = new ToggleButton("ON"); 
        tb1.setFont(Font.font("Calisto MT", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        tb1.setStyle("-fx-base: #ffaa33");
        HBox hBox3 = new HBox();
        hBox3.setAlignment(Pos.CENTER);
        hBox3.getChildren().addAll(tb1);

        
        tb1.setOnMouseClicked(e -> {
            if(tb1.isSelected()){
                tb1.setText("OFF");
                rotate.setNode(fillCircle);
                rotate.setByAngle(360);
                rotate.setCycleCount(100);
                rotate.play();
                System.out.println("The air conditioner is on ");
            }
            else if(e.getClickCount()%2 == 1){
                tb1.setText("ON");
                rotate.pause();
                System.out.println("The air conditioner is off ");
            }               
        });
        
         
        // update master control button
        
        // cool        
        RadioButton rb1 = new RadioButton("COOL");
        rb1.setFont(Font.font("Calisto MT", FontWeight.NORMAL, FontPosture.REGULAR, 10));
        rb1.setOnAction(e-> {
            rb1.setText("COOL ✔");
            rb1.setFont(Font.font("Calisto MT", FontWeight.NORMAL, FontPosture.REGULAR, 10));
            System.out.println("cool was selected");
        });
        
        // dry
        RadioButton rb2 = new RadioButton("DRY"); 
        rb2.setFont(Font.font("Calisto MT", FontWeight.NORMAL, FontPosture.REGULAR, 10));
        rb2.setOnAction(e-> {
            rb2.setText("DRY ✔");
            System.out.println("dry was selected");
        });
        
        // fan
        RadioButton rb3 = new RadioButton("FAN");
        rb3.setFont(Font.font("Calisto MT", FontWeight.NORMAL, FontPosture.REGULAR, 10));
        rb3.setOnAction(e-> {
            rb3.setText("FAN ✔");
            System.out.println("fan was selected");
        });
        
        ToggleGroup toggle = new ToggleGroup();
        rb1.setToggleGroup(toggle);
        rb2.setToggleGroup(toggle);
        rb3.setToggleGroup(toggle);
        
        
        VBox vBox4 = new VBox();
        vBox4.setSpacing(8);
        Label lbl2 = new Label("MASTER\nCONTROL:");
        lbl2.setFont(Font.font("Calisto MT", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        vBox4.getChildren().addAll(lbl2, rb1, rb2, rb3);
        vBox4.setAlignment(Pos.CENTER);        

        // update fan control
        // high
        RadioButton rb4 = new RadioButton("HIGH");
        rb4.setFont(Font.font("Calisto MT", FontWeight.NORMAL, FontPosture.REGULAR, 10));
        rb4.setOnAction(e-> {
            rb4.setText("HIGH ✔");
            System.out.println("high was selected");
        });
        
        // mid
        RadioButton rb5 = new RadioButton("MID");       
        rb5.setFont(Font.font("Calisto MT", FontWeight.NORMAL, FontPosture.REGULAR, 10));
        rb5.setOnAction(e-> {
            rb5.setText("MID ✔");
            System.out.println("mid was selected");
        });
        
        // low
        RadioButton rb6 = new RadioButton("LOW");
        rb6.setFont(Font.font("Calisto MT", FontWeight.NORMAL, FontPosture.REGULAR, 10));
        rb6.setOnAction(e-> {
            rb6.setText("LOW ✔");
            System.out.println("low was selected");
        });
        
        ToggleGroup toggle1 = new ToggleGroup();
        rb4.setToggleGroup(toggle1);
        rb5.setToggleGroup(toggle1);
        rb6.setToggleGroup(toggle1);
        
        VBox vBox5 = new VBox();
        vBox5.setSpacing(8);
        Label lbl = new Label("FAN\nCONTROL:");
        lbl.setFont(Font.font("Calisto MT", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        vBox5.getChildren().addAll(lbl, rb4, rb5, rb6);
        vBox5.setAlignment(Pos.CENTER);
        
        
        // master & fan control
        HBox hBox5 = new HBox();
        hBox5.setSpacing(15);
        hBox5.setAlignment(Pos.CENTER);
        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        hBox5.getChildren().addAll(vBox4, separator, vBox5);
        
        
             
        // center
        VBox vBox = new VBox();
        vBox.getChildren().addAll(hbox2, hBox, hBox5, hBox3);
        vBox.setPadding(new Insets(30, 10 ,10, 10));
        vBox.setSpacing(25);
        vBox.setStyle("-fx-background-color: LIGHTGRAY");
        bPane.setCenter(vBox);
          
        // bottom of screen
        HBox hbox = new HBox();
        hbox.getChildren().addAll(timeText);
        hbox.setAlignment(Pos.CENTER);
        hbox.setStyle("-fx-background-color: DARKGREY");        
        bPane.setBottom(hbox);
        
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("Lab3_smart_home");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // time
        Timeline timeline = new Timeline(
                new KeyFrame(javafx.util.Duration.seconds(0),
                        event -> updateTime()),
                new KeyFrame(javafx.util.Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

  
    /**
     * this method for display time
     *  
     */
     private void updateTime() {
        LocalTime now = LocalTime.now();
        String formattedTime = now.format(DateTimeFormatter.ofPattern("HH:mm a"));
        timeText.setText(formattedTime);
        timeText.setFont(Font.font("Calisto MT", FontWeight.NORMAL, FontPosture.REGULAR, 12));
    }
      
     /**
      * this method for display temperature
      * @return temperature
      */
    public int getTemperature() {
        return temperature;
    }

    /**
     * this method for set or control temperature
     * @param temperature 
     */
    public void setTemperature(int temperature) {
        if(temperature<25 && temperature>17) // temperature must be between 15 & 24 
        this.temperature = temperature;
        else 
            System.out.println("the tempreature in this air conditioner must be between 18 and 24");
                
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
