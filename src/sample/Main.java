package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Notepad");
        TextArea note = new TextArea();
        note.setVisible(false);
        Button bCreate = new Button("Create note");
        Button bOpen = new Button("Open note");
        Button bSave = new Button("Save note");
        Button bName = new Button("OK");
        Button bMusic = new Button("Play Music");
        //Image img = new Image();
        bMusic.setLayoutX(225);
        bMusic.setLayoutY(0);
        bMusic.setOnAction(actionEvent -> {
                    try {
                        File sf = new File("E:/Загрузки/*NameOfMusic*.wav");//Path to music you need(.wav is required)
                        AudioInputStream ais = AudioSystem.getAudioInputStream(sf);
                        Clip clip = AudioSystem.getClip();
                        clip.open(ais);
                        clip.setFramePosition(0);
                        clip.start();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
        TextField name = new TextField();
        name.setLayoutY(30);
        name.setLayoutX(0);
        name.setVisible(false);
        name.setPrefColumnCount(11);
        bSave.setLayoutX(80);
        bOpen.setLayoutX(150);
        bName.setLayoutX(150);
        bName.setLayoutY(30);
        bName.setVisible(false);
        Label description = new Label();
        description.setText("1-create the note\n2-Put some text in your note,\n press the save button and name the note \n(OK will save your note)");
description.setLayoutX(5);
description.setLayoutY(250);

        bCreate.setOnAction(actionEvent -> {
            note.setVisible(true);
            note.setMaxSize(600, 200);
            note.setLayoutY(65);//YY

        });
        bSave.setOnAction(actionEvent -> {
            String s = note.getText();
            bName.setVisible(true);
            name.setVisible(true);

            bName.setOnAction(actionEvent1 -> {
                String NameofNote = name.getText();
                if (NameofNote == null) {
                    System.exit(54);
                }

                try (FileWriter fw = new FileWriter("C:/Users/Мышь/Desktop/" + NameofNote.trim() + ".npz")) {//you can replace .npz with simple .txt

                    fw.write(s);
                    fw.append('\n');

                    fw.flush();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                String s2 = " ";
note.deleteText(0,s.length());

            });


        });
        bOpen.setOnAction(actionEvent -> {

            note.setVisible(true);
            name.setVisible(true);
            name.setLayoutY(30);
            name.setLayoutX(0);
            note.setLayoutY(65);//YY
            bName.setVisible(true);
            name.setPrefColumnCount(11);
            bSave.setLayoutX(80);
            bOpen.setLayoutX(150);
            bName.setLayoutX(150);
            bName.setLayoutY(30);

            bName.setOnAction(actionEvent1 -> {
                String name1 = name.getText();

                TextField nameOffile = new TextField();
                nameOffile.setPrefColumnCount(11);
                nameOffile.setLayoutY(30);


                try (FileReader reader = new FileReader("C:/Users/Мышь/Desktop/" + name1.trim() + ".npz")) {//you can replace .np for simple .txt
                    Scanner sc = new Scanner(reader);
                    String st = note.getText();
                    while (sc.hasNextLine()) {
                        String s = sc.nextLine();
                        note.appendText(s);

                        note.deleteText(0,st.length());

                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            });
        });
        Group gr = new Group(bCreate, bOpen, bSave, note, bName, name,description, bMusic);


        primaryStage.setScene(new Scene(gr, 600, 400));
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}

