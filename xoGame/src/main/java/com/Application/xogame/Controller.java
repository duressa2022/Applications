/*
 * DO NOT ALTER OR REMOVE THIS  NOTICES OR THIS FILE HEADER FROM THE CODE.
 * This code is free software you can redistribute it and/or modify it
   published by the Good will of the author.

 * This code is distributed in the hope that it will be useful,Therefore
   use this code for your need or purpose and you can inform Error or part
   to modify or add and using for educational purpose is encoureged.
 * Modifiying this code in a part or as Whole is possible and incremental modification is
   suggested please inform the author any modification you have done.
 * Please contact the Author,if you need additional information or have any questions.
     *Author:Duressa Shukuri
     *Email:duressashukuri2022@gmail.com
*/
package com.Application.xogame;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.input.GestureEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.*;

public class Controller {
    @FXML
    private Label scoreLabel1;
    @FXML
    private Label labelPos1;
    @FXML
    private Label labelPos2;
    @FXML
    private Label labelPos3;
    @FXML
    private Label labelPos4;
    @FXML
    private Label labelPos5;
    @FXML
    private Label labelPos6;
    @FXML
    private Label labelPos7;
    @FXML
    private Label labelPos8;
    @FXML
    private Label labelPos9;
    @FXML
    private Label scoreLabel2;
    @FXML
    private Label labelPos11;
    @FXML
    private Label labelPos12;
    @FXML
    private Label labelPos13;
    @FXML
    private Label labelPos14;
    @FXML
    private Label labelPos15;
    @FXML
    private Label labelPos16;
    @FXML
    private Label labelPos17;
    @FXML
    private Label labelPos18;
    @FXML
    private Label labelPos19;
    @FXML
    private Label scoreLabel3;
    @FXML
    private Label labelPos21;
    @FXML
    private Label labelPos22;
    @FXML
    private Label labelPos23;
    @FXML
    private Label labelPos24;
    @FXML
    private Label labelPos25;
    @FXML
    private Label labelPos26;
    @FXML
    private Label labelPos27;
    @FXML
    private Label labelPos28;
    @FXML
    private Label labelPos29;
    @FXML
    private Button startButton1;
    @FXML
    private Button quitButton1;
    @FXML
    private Button startButton2;
    @FXML
    private Button quitButton2;
    @FXML
    private Button startButton3;
    @FXML
    private Button quitButton3;
    @FXML
    private Label welcomeText;
    Character xplayer='X';
    Character oplayer='O';
    Character currentPlayer=xplayer;
    private ArrayList<Integer> gameBoard=initGameBoard();
    private  ArrayList<Integer>gameBoard1=initGameBoard();
    private ArrayList<Integer>gameBoard2=initGameBoard();

    private ArrayList<Integer>initGameBoard(){
        ArrayList<Integer>gameBoard=new ArrayList<>();
        for(int i=0;i<9;i++){
            gameBoard.add(0);
        }
        return gameBoard;
    }
    private ArrayList<Integer> evaluateGame(ArrayList<Integer> gameBoard){
        /*method that evaluate the current state of the game(gameBoard),or returns who win the game.
         params:gameBoard(current state of the game)
         return:arraylist of who wins and the winning position.
         */
        ArrayList<Integer>result=new ArrayList<>();
        if(gameBoard.get(0).equals(gameBoard.get(1)) && gameBoard.get(1).equals(gameBoard.get(2))){
            result.add(0);result.add(1);result.add(2);result.add(gameBoard.get(0));
        }else if(gameBoard.get(0).equals(gameBoard.get(3))&& gameBoard.get(3).equals(gameBoard.get(6))){
            result.add(0);result.add(3);result.add(6);result.add(gameBoard.get(0));
        }else if(gameBoard.get(1).equals(gameBoard.get(4))&&gameBoard.get(4).equals(gameBoard.get(7))){
            result.add(1);result.add(4);result.add(7);result.add(gameBoard.get(1));
        }else if(gameBoard.get(2).equals(gameBoard.get(5))&& gameBoard.get(5).equals(gameBoard.get(8))){
            result.add(2);result.add(5);result.add(8);result.add(gameBoard.get(2));
        }else if(gameBoard.get(3).equals(gameBoard.get(4))&& gameBoard.get(4).equals(gameBoard.get(5))){
            result.add(3);result.add(4);result.add(5);result.add(gameBoard.get(3));
        }else if(gameBoard.get(6).equals(gameBoard.get(7))&&gameBoard.get(7).equals(gameBoard.get(8))){
            result.add(6);result.add(7);result.add(8);result.add(gameBoard.get(6));
        }else if(gameBoard.get(0).equals(gameBoard.get(4))&&gameBoard.get(4).equals(gameBoard.get(8))){
            result.add(0);result.add(4);result.add(8);result.add(gameBoard.get(0));
        }else if(gameBoard.get(2).equals(gameBoard.get(4))&&gameBoard.get(4).equals(gameBoard.get(6))){
            result.add(2);result.add(4);result.add(6);result.add(gameBoard.get(2));
        }
        result.add(0);result.add(0);result.add(0);result.add(0);
        return result;
    }
    private int depthAgentBrain(ArrayList<Integer> gameBoard,int player){
        /*method that returns the maximum score of the gameBoard for player.
          params:gameBoard(current state of the game) and player that maximize the score
          return:max score of the player
         */
        if(evaluateGame(gameBoard).get(3)!=0){
            return evaluateGame(gameBoard).get(3)*player;
        }
        int score=-2;
        int move=-1;
        for(int i=0;i<gameBoard.size();i++){
            if(gameBoard.get(i).equals(0)){
                gameBoard.set(i,player);
                int current=-depthAgentBrain(gameBoard,-player);
                if(score<current){
                    score=current;
                    move=i;
                }
                gameBoard.set(i,0);
            }
        }
        if(move==-1){
            return 0;
        }
        return score;
    }
    private int depthAgentMove(ArrayList<Integer> gameBoard,int player){

        /*method that returns the move that produce max score for current player.
          params:gameBoard(current state of the game) and player that maximize the score
          return:move for the player
          */
        int score=-2;
        int move=-1;
        for(int i=0;i<gameBoard.size();i++){
            if(gameBoard.get(i)==0){
                gameBoard.set(i,player);
                int current=-depthAgentBrain(gameBoard,-player);
                if(score<current){
                    score=current;
                    move=i;
                }
                gameBoard.set(i,0);
            }
        }
        return move;
    }
    private int randomAgentMove(ArrayList<Integer> gameBoard,int player){
        /*method that returns the random possible move for the player.
          params:gameBoard(current state of the game) and player
          return:random move for player
         */
        ArrayList<Integer>move=new ArrayList<>();
        for(int i=0;i<9;i++){
            move.add(i+1);
        }
        Random random=new Random();
        int choice=0;
        while(true==true){
            choice=random.nextInt(1,10);
            break;
        }
        return choice;
    }
    @FXML
    private void mouseEntered(){
        /*method that handles the change of color when mouse enterd to the reigon.
          params:None
          return:None
         */
        labelPos1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos1.setBackground(Background.fill(Color.GRAY));
            }
        });
        labelPos2.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos2.setBackground(Background.fill(Color.GRAY));
            }
        });
        labelPos3.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos3.setBackground(Background.fill(Color.GRAY));
            }
        });
        labelPos4.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos4.setBackground(Background.fill(Color.GRAY));
            }
        });
        labelPos5.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos5.setBackground(Background.fill(Color.GRAY));
            }
        });
        labelPos6.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos6.setBackground(Background.fill(Color.GRAY));
            }
        });
        labelPos7.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos7.setBackground(Background.fill(Color.GRAY));
            }
        });
        labelPos8.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos8.setBackground(Background.fill(Color.GRAY));
            }
        });
        labelPos9.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos9.setBackground(Background.fill(Color.GRAY));
            }
        });
        labelPos11.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos11.setBackground(Background.fill(Color.GRAY));
            }
        });
        labelPos12.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos12.setBackground(Background.fill(Color.GRAY));
            }
        });
        labelPos13.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos13.setBackground(Background.fill(Color.GRAY));
            }
        });
        labelPos14.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos14.setBackground(Background.fill(Color.GRAY));
            }
        });
        labelPos15.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos15.setBackground(Background.fill(Color.GRAY));
            }
        });
        labelPos16.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos16.setBackground(Background.fill(Color.GRAY));
            }
        });
        labelPos17.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos17.setBackground(Background.fill(Color.GRAY));
            }
        });
        labelPos18.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos18.setBackground(Background.fill(Color.GRAY));
            }
        });
        labelPos19.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos19.setBackground(Background.fill(Color.GRAY));
            }
        });
        labelPos21.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos21.setBackground(Background.fill(Color.GRAY));
            }
        });
        labelPos22.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos22.setBackground(Background.fill(Color.GRAY));
            }
        });
        labelPos23.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos23.setBackground(Background.fill(Color.GRAY));
            }
        });
        labelPos24.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos24.setBackground(Background.fill(Color.GRAY));
            }
        });
        labelPos25.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos25.setBackground(Background.fill(Color.GRAY));
            }
        });
        labelPos26.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos26.setBackground(Background.fill(Color.GRAY));
            }
        });
        labelPos27.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos27.setBackground(Background.fill(Color.GRAY));
            }
        });
        labelPos28.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos28.setBackground(Background.fill(Color.GRAY));
            }
        });
        labelPos29.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos29.setBackground(Background.fill(Color.GRAY));
            }
        });


    }
    private void setOpenentPlayer(ArrayList<Integer>gameBoard,int player,int loc){
        /*method that set player X or O on the label for the practice case.
          params:gameBoard(current state of the game) and player,loc .
          return:None
         */
        Boolean isFull=false;
        int move=0;
        while(true){
            move=randomAgentMove(gameBoard,-1);
            if(gameBoard.contains(0)==false){
                isFull=true;
                break;
            }
            if(getLabelForm(move).getText().equals(String.valueOf(oplayer))|| getLabelForm(move).getText().equals(String.valueOf(xplayer))){
                continue;
            }
            if(move==loc){
                continue;
            }
            break;

        }
        if(!isFull){
            setLabelPlayer(move,String.valueOf(oplayer));
            gameBoard.set(move-1,1);
        }}
    private void setOpenentPlayer(ArrayList<Integer>gameBoard,int player){
        /*method that set player X or O on the label for the expert case.
          params:gameBoard(current state of the game) and player.
          return:None
         */
        int move=depthAgentMove(gameBoard,player);
        if(gameBoard.contains(0)==true){
            setLabelPlayerExpert(move+1,String.valueOf(oplayer));
            gameBoard.set(move,player);
        }


    }
    private void drawWinner(ArrayList<Integer>gameBoard){
        /*method that checks who won the game and draw the possible cases for practice case.
          params:gameBoard(current state of the game). .
          return:None
         */
        int result=evaluateGame(gameBoard).get(3);
        if(result==1|| result==-1){
            if(result==1){
                scoreLabel1.setText(scoreLabel1.getText()+"O");
                getLabelForm(evaluateGame(gameBoard).get(0)+1).setTextFill(Color.BLUE);
                getLabelForm(evaluateGame(gameBoard).get(1)+1).setTextFill(Color.BLUE);
                getLabelForm(evaluateGame(gameBoard).get(2)+1).setTextFill(Color.BLUE);
            }else{
                scoreLabel1.setText(scoreLabel1.getText()+"X");
                getLabelForm(evaluateGame(gameBoard).get(0)+1).setTextFill(Color.BLUE);
                getLabelForm(evaluateGame(gameBoard).get(1)+1).setTextFill(Color.BLUE);
                getLabelForm(evaluateGame(gameBoard).get(2)+1).setTextFill(Color.BLUE);
            }
            labelPos1.setDisable(true);labelPos2.setDisable(true);
            labelPos3.setDisable(true);labelPos4.setDisable(true);
            labelPos5.setDisable(true);labelPos6.setDisable(true);
            labelPos7.setDisable(true);labelPos8.setDisable(true);
            labelPos9.setDisable(true);labelPos9.setDisable(true);
        }
    }
    private void drawWinnerPlayWith(ArrayList<Integer>gameBoard){
        /*method that checks who won the game and draw the possible cases for playWith.
          params:gameBoard(current state of the game). .
          return:None
         */
        int result=evaluateGame(gameBoard).get(3);
        if(result==1|| result==-1){
            if(result==1){
                scoreLabel2.setText(scoreLabel2.getText()+"O");
                getLabelFormPlayWith(evaluateGame(gameBoard).get(0)+1).setTextFill(Color.BLUE);
                getLabelFormPlayWith(evaluateGame(gameBoard).get(1)+1).setTextFill(Color.BLUE);
                getLabelFormPlayWith(evaluateGame(gameBoard).get(2)+1).setTextFill(Color.BLUE);
            }else{
                scoreLabel2.setText(scoreLabel2.getText()+"X");
                getLabelFormPlayWith(evaluateGame(gameBoard).get(0)+1).setTextFill(Color.BLUE);
                getLabelFormPlayWith(evaluateGame(gameBoard).get(1)+1).setTextFill(Color.BLUE);
                getLabelFormPlayWith(evaluateGame(gameBoard).get(2)+1).setTextFill(Color.BLUE);
            }
            labelPos11.setDisable(true);labelPos12.setDisable(true);
            labelPos13.setDisable(true);labelPos14.setDisable(true);
            labelPos15.setDisable(true);labelPos16.setDisable(true);
            labelPos17.setDisable(true);labelPos18.setDisable(true);
            labelPos19.setDisable(true);labelPos19.setDisable(true);
        }
    }
    private void drawWinnerExpert(ArrayList<Integer>gameBoard){
        /*method that checks who won the game and draw the possible cases for expert case.
          params:gameBoard(current state of the game). .
          return:None
         */
        int result=evaluateGame(gameBoard).get(3);
        if(result==1|| result==-1){
            if(result==1){
                scoreLabel3.setText(scoreLabel3.getText()+"O");
                getLabelFormExpert(evaluateGame(gameBoard).get(0)+1).setTextFill(Color.BLUE);
                getLabelFormExpert(evaluateGame(gameBoard).get(1)+1).setTextFill(Color.BLUE);
                getLabelFormExpert(evaluateGame(gameBoard).get(2)+1).setTextFill(Color.BLUE);
            }else{
                scoreLabel3.setText(scoreLabel3.getText()+"X");
                getLabelFormExpert(evaluateGame(gameBoard).get(0)+1).setTextFill(Color.BLUE);
                getLabelFormExpert(evaluateGame(gameBoard).get(1)+1).setTextFill(Color.BLUE);
                getLabelFormExpert(evaluateGame(gameBoard).get(2)+1).setTextFill(Color.BLUE);
            }
            labelPos21.setDisable(true);labelPos22.setDisable(true);
            labelPos23.setDisable(true);labelPos24.setDisable(true);
            labelPos25.setDisable(true);labelPos26.setDisable(true);
            labelPos27.setDisable(true);labelPos28.setDisable(true);
            labelPos29.setDisable(true);labelPos29.setDisable(true);
        }
    }
    @FXML
    private void mouseClicked(){
        /*method that handles click of the mouse on label or button or responed the mouse click.
          params:None
          return:None
         */
        startButton1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos1.setDisable(false);labelPos2.setDisable(false);
                labelPos3.setDisable(false);labelPos4.setDisable(false);
                labelPos5.setDisable(false);labelPos6.setDisable(false);
                labelPos7.setDisable(false);labelPos8.setDisable(false);
                labelPos9.setDisable(false);labelPos9.setDisable(false);
                labelPos1.setText("");labelPos2.setText("");
                labelPos3.setText("");labelPos4.setText("");
                labelPos5.setText("");labelPos6.setText("");
                labelPos7.setText("");labelPos8.setText("");
                labelPos9.setText("");scoreLabel1.setText("WINNER:");
                getLabelForm(evaluateGame(gameBoard).get(0)+1).setTextFill(Color.BLACK);
                getLabelForm(evaluateGame(gameBoard).get(1)+1).setTextFill(Color.BLACK);
                getLabelForm(evaluateGame(gameBoard).get(2)+1).setTextFill(Color.BLACK);
                gameBoard=initGameBoard();
            }
        });
        startButton2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos11.setDisable(false);labelPos12.setDisable(false);
                labelPos13.setDisable(false);labelPos14.setDisable(false);
                labelPos15.setDisable(false);labelPos16.setDisable(false);
                labelPos17.setDisable(false);labelPos18.setDisable(false);
                labelPos19.setDisable(false);labelPos19.setDisable(false);
                labelPos11.setText("");labelPos12.setText("");
                labelPos13.setText("");labelPos14.setText("");
                labelPos15.setText("");labelPos16.setText("");
                labelPos17.setText("");labelPos18.setText("");
                labelPos19.setText("");scoreLabel2.setText("WINNER:");
                getLabelFormPlayWith(evaluateGame(gameBoard2).get(0)+1).setTextFill(Color.BLACK);
                getLabelFormPlayWith(evaluateGame(gameBoard2).get(1)+1).setTextFill(Color.BLACK);
                getLabelFormPlayWith(evaluateGame(gameBoard2).get(2)+1).setTextFill(Color.BLACK);
                gameBoard2=initGameBoard();
                currentPlayer=xplayer;
            }
        });
        startButton3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelPos21.setDisable(false);labelPos22.setDisable(false);
                labelPos23.setDisable(false);labelPos24.setDisable(false);
                labelPos25.setDisable(false);labelPos26.setDisable(false);
                labelPos27.setDisable(false);labelPos28.setDisable(false);
                labelPos29.setDisable(false);labelPos29.setDisable(false);
                labelPos21.setText("");labelPos22.setText("");
                labelPos23.setText("");labelPos24.setText("");
                labelPos25.setText("");labelPos26.setText("");
                labelPos27.setText("");labelPos28.setText("");
                labelPos29.setText("");scoreLabel3.setText("WINNER:");
                getLabelFormExpert(evaluateGame(gameBoard1).get(0)+1).setTextFill(Color.BLACK);
                getLabelFormExpert(evaluateGame(gameBoard1).get(1)+1).setTextFill(Color.BLACK);
                getLabelFormExpert(evaluateGame(gameBoard1).get(2)+1).setTextFill(Color.BLACK);
                gameBoard1=initGameBoard();
            }
        });
        labelPos1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos1.getText().equals("")) {
                    labelPos1.setText(String.valueOf(xplayer));
                    gameBoard.set(0,-1);
                    setOpenentPlayer(gameBoard,-1,1);
                    drawWinner(gameBoard);
                }
            }
        });

        labelPos2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos2.getText().equals("")) {
                    labelPos2.setText(String.valueOf(xplayer));
                    gameBoard.set(1,-1);
                    setOpenentPlayer(gameBoard,-1,2);
                    drawWinner(gameBoard);
                }
            }
        });
        labelPos3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos3.getText().equals("")){
                    labelPos3.setText(String.valueOf(xplayer));
                    gameBoard.set(2,-1);
                    setOpenentPlayer(gameBoard,-1,3);
                    drawWinner(gameBoard);
                }
            }
        });
        labelPos4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos4.getText().equals("")){
                    labelPos4.setText(String.valueOf(xplayer));
                    gameBoard.set(3,-1);
                    setOpenentPlayer(gameBoard,-1,4);
                    drawWinner(gameBoard);
                }
            }
        });
        labelPos5.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos5.getText().equals("")){
                    labelPos5.setText(String.valueOf(xplayer));
                    gameBoard.set(4,-1);
                    setOpenentPlayer(gameBoard,-1,5);
                    drawWinner(gameBoard);
                }
            }
        });
        labelPos6.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos6.getText().equals("")){
                    labelPos6.setText(String.valueOf(xplayer));
                    gameBoard.set(5,-1);
                    setOpenentPlayer(gameBoard,-1,6);
                    drawWinner(gameBoard);
                }

            }
        });
        labelPos7.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos7.getText().equals("")){
                    labelPos7.setText(String.valueOf(xplayer));
                    gameBoard.set(6,-1);
                    setOpenentPlayer(gameBoard,-1,7);
                    drawWinner(gameBoard);
                }
            }
        });
        labelPos8.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos8.getText().equals("")){
                    labelPos8.setText(String.valueOf(xplayer));
                    gameBoard.set(7,-1);
                    setOpenentPlayer(gameBoard,-1,8);
                    drawWinner(gameBoard);
                }
            }
        });
        labelPos9.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos9.getText().equals("")){
                    labelPos9.setText(String.valueOf(xplayer));
                    gameBoard.set(8,-1);
                    setOpenentPlayer(gameBoard,-1,9);
                    drawWinner(gameBoard);
                }
            }
        });
        labelPos11.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos11.getText().equals("")) {
                    if(currentPlayer==xplayer){
                        labelPos11.setText(String.valueOf(currentPlayer));
                        gameBoard2.set(0,-1);
                        currentPlayer=oplayer;
                    }else{
                        labelPos11.setText(String.valueOf(currentPlayer));
                        gameBoard2.set(0,1);
                        currentPlayer=xplayer;
                    }
                    drawWinnerPlayWith(gameBoard2);
                }
            }
        });

        labelPos12.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos12.getText().equals("")) {
                    if(currentPlayer==xplayer){
                        labelPos12.setText(String.valueOf(currentPlayer));
                        gameBoard2.set(1,-1);
                        currentPlayer=oplayer;
                    }else{
                        labelPos12.setText(String.valueOf(currentPlayer));
                        gameBoard2.set(1,1);
                        currentPlayer=xplayer;
                    }
                    drawWinnerPlayWith(gameBoard2);
                }
            }
        });
        labelPos13.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos13.getText().equals("")) {
                    if(currentPlayer==xplayer){
                        labelPos13.setText(String.valueOf(currentPlayer));
                        gameBoard2.set(2,-1);
                        currentPlayer=oplayer;
                    }else{
                        labelPos13.setText(String.valueOf(currentPlayer));
                        gameBoard2.set(2,1);
                        currentPlayer=xplayer;
                    }
                    drawWinnerPlayWith(gameBoard2);
                }
            }
        });
        labelPos14.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos14.getText().equals("")) {
                    if(currentPlayer==xplayer){
                        labelPos14.setText(String.valueOf(currentPlayer));
                        gameBoard2.set(3,-1);
                        currentPlayer=oplayer;
                    }else{
                        labelPos14.setText(String.valueOf(currentPlayer));
                        gameBoard2.set(3,1);
                        currentPlayer=xplayer;
                    }
                    drawWinnerPlayWith(gameBoard2);
                }
            }
        });
        labelPos15.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos15.getText().equals("")) {
                    if(currentPlayer==xplayer){
                        labelPos15.setText(String.valueOf(currentPlayer));
                        gameBoard2.set(4,-1);
                        currentPlayer=oplayer;
                    }else{
                        labelPos15.setText(String.valueOf(currentPlayer));
                        gameBoard2.set(4,1);
                        currentPlayer=xplayer;
                    }
                    drawWinnerPlayWith(gameBoard2);
                }
            }
        });
        labelPos16.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos16.getText().equals("")) {
                    if(currentPlayer==xplayer){
                        labelPos16.setText(String.valueOf(currentPlayer));
                        gameBoard2.set(5,-1);
                        currentPlayer=oplayer;
                    }else{
                        labelPos16.setText(String.valueOf(currentPlayer));
                        gameBoard2.set(5,1);
                        currentPlayer=xplayer;
                    }
                    drawWinnerPlayWith(gameBoard2);
                }

            }
        });
        labelPos17.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos17.getText().equals("")) {
                    if(currentPlayer==xplayer){
                        labelPos17.setText(String.valueOf(currentPlayer));
                        gameBoard2.set(6,-1);
                        currentPlayer=oplayer;
                    }else{
                        labelPos17.setText(String.valueOf(currentPlayer));
                        gameBoard2.set(6,1);
                        currentPlayer=xplayer;
                    }
                    drawWinnerPlayWith(gameBoard2);
                }
            }
        });
        labelPos18.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos18.getText().equals("")) {
                    if(currentPlayer==xplayer){
                        labelPos18.setText(String.valueOf(currentPlayer));
                        gameBoard2.set(7,-1);
                        currentPlayer=oplayer;
                    }else{
                        labelPos18.setText(String.valueOf(currentPlayer));
                        gameBoard2.set(7,1);
                        currentPlayer=xplayer;
                    }
                    drawWinnerPlayWith(gameBoard2);
                }
            }
        });
        labelPos19.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos19.getText().equals("")) {
                    if(currentPlayer==xplayer){
                        labelPos19.setText(String.valueOf(currentPlayer));
                        gameBoard2.set(8,-1);
                        currentPlayer=oplayer;
                    }else{
                        labelPos19.setText(String.valueOf(currentPlayer));
                        gameBoard2.set(8,1);
                        currentPlayer=xplayer;
                    }
                    drawWinnerPlayWith(gameBoard2);
                }
            }
        });
        labelPos21.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos21.getText().equals("")) {
                    labelPos21.setText(String.valueOf(xplayer));
                    gameBoard1.set(0,-1);
                    setOpenentPlayer(gameBoard1,1);
                    drawWinnerExpert(gameBoard1);
                }
            }
        });

        labelPos22.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos22.getText().equals("")) {
                    labelPos22.setText(String.valueOf(xplayer));
                    gameBoard1.set(1,-1);
                    setOpenentPlayer(gameBoard1,1);
                    drawWinnerExpert(gameBoard1);
                }
            }
        });
        labelPos23.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos23.getText().equals("")){
                    labelPos23.setText(String.valueOf(xplayer));
                    gameBoard1.set(2,-1);
                    setOpenentPlayer(gameBoard1,1);
                    drawWinnerExpert(gameBoard1);
                }
            }
        });
        labelPos24.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos24.getText().equals("")){
                    labelPos24.setText(String.valueOf(xplayer));
                    gameBoard1.set(3,-1);
                    setOpenentPlayer(gameBoard1,1);
                    drawWinnerExpert(gameBoard1);
                }
            }
        });
        labelPos25.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos25.getText().equals("")){
                    labelPos25.setText(String.valueOf(xplayer));
                    gameBoard1.set(4,-1);
                    setOpenentPlayer(gameBoard1,1);
                    drawWinnerExpert(gameBoard1);
                }
            }
        });
        labelPos26.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos26.getText().equals("")){
                    labelPos26.setText(String.valueOf(xplayer));
                    gameBoard1.set(5,-1);
                    setOpenentPlayer(gameBoard1,1);
                    drawWinnerExpert(gameBoard1);
                }

            }
        });
        labelPos27.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos27.getText().equals("")){
                    labelPos27.setText(String.valueOf(xplayer));
                    gameBoard1.set(6,-1);
                    setOpenentPlayer(gameBoard1,1);
                    drawWinnerExpert(gameBoard1);
                }
            }
        });
        labelPos28.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos28.getText().equals("")){
                    labelPos28.setText(String.valueOf(xplayer));
                    gameBoard1.set(7,-1);
                    setOpenentPlayer(gameBoard1,1);
                    drawWinnerExpert(gameBoard1);
                }
            }
        });
        labelPos29.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(labelPos29.getText().equals("")){
                    labelPos29.setText(String.valueOf(xplayer));
                    gameBoard1.set(8,-1);
                    setOpenentPlayer(gameBoard1,1);
                    drawWinnerExpert(gameBoard1);
                }
            }
        });


    }
    @FXML
    private void mouseExited(){
         /*method that handles exit  of the mouse from label  or responed when mouse exit.
          params:None
          return:None
         */
        labelPos1.setBackground(Background.fill(Color.WHITE));
        labelPos2.setBackground(Background.fill(Color.WHITE));
        labelPos3.setBackground(Background.fill(Color.WHITE));
        labelPos4.setBackground(Background.fill(Color.WHITE));
        labelPos5.setBackground(Background.fill(Color.WHITE));
        labelPos6.setBackground(Background.fill(Color.WHITE));
        labelPos7.setBackground(Background.fill(Color.WHITE));
        labelPos8.setBackground(Background.fill(Color.WHITE));
        labelPos9.setBackground(Background.fill(Color.WHITE));
        labelPos11.setBackground(Background.fill(Color.WHITE));
        labelPos12.setBackground(Background.fill(Color.WHITE));
        labelPos13.setBackground(Background.fill(Color.WHITE));
        labelPos14.setBackground(Background.fill(Color.WHITE));
        labelPos15.setBackground(Background.fill(Color.WHITE));
        labelPos16.setBackground(Background.fill(Color.WHITE));
        labelPos17.setBackground(Background.fill(Color.WHITE));
        labelPos18.setBackground(Background.fill(Color.WHITE));
        labelPos19.setBackground(Background.fill(Color.WHITE));
        labelPos21.setBackground(Background.fill(Color.WHITE));
        labelPos22.setBackground(Background.fill(Color.WHITE));
        labelPos23.setBackground(Background.fill(Color.WHITE));
        labelPos24.setBackground(Background.fill(Color.WHITE));
        labelPos25.setBackground(Background.fill(Color.WHITE));
        labelPos26.setBackground(Background.fill(Color.WHITE));
        labelPos27.setBackground(Background.fill(Color.WHITE));
        labelPos28.setBackground(Background.fill(Color.WHITE));
        labelPos29.setBackground(Background.fill(Color.WHITE));
    }
    private int getLocationOFPlayer(String player){
        /*method that returns the position of the player based on its location on the label;.
           params:player x or o
           return:position of the player
         */
        if(labelPos1.getText().equals(player)||labelPos21.getText().equals(player)||labelPos11.getText().equals(player)){
            return 1;
        }else if(labelPos2.getText().equals(player)||labelPos22.getText().equals(player)||labelPos12.getText().equals(player)){
            return 2;
        }else if(labelPos3.getText().equals(player)||labelPos23.getText().equals(player)||labelPos13.getText().equals(player)){
            return 3;
        }else if(labelPos4.getText().equals(player)||labelPos24.getText().equals(player)||labelPos14.getText().equals(player)){
            return 4;
        }else if(labelPos5.getText().equals(player)||labelPos25.getText().equals(player)||labelPos15.getText().equals(player)){
            return 5;
        }else if(labelPos6.getText().equals(player)||labelPos26.getText().equals(player)||labelPos16.getText().equals(player)){
            return 6;
        }else if(labelPos7.getText().equals(player)||labelPos27.getText().equals(player)||labelPos17.getText().equals(player)){
            return 7;
        }else if(labelPos8.getText().equals(player)||labelPos28.getText().equals(player)||labelPos18.getText().equals(player)){
            return 8;
        }else if(labelPos9.getText().equals(player)||labelPos29.getText().equals(player)||labelPos19.getText().equals(player)){
            return 9;
        }
        return 0;
    }
    private void setLabelPlayer(int loc,String player){
         /*method that positions the player based on its location on the label for practice case.
           params:player x or o and loc
           return:None
         */
        if(loc==1){labelPos1.setText(player);}
        if(loc==2){labelPos2.setText(player);}
        if(loc==3){labelPos3.setText(player);}
        if(loc==4){labelPos4.setText(player);}
        if(loc==5){labelPos5.setText(player);}
        if(loc==6){labelPos6.setText(player);}
        if(loc==7){labelPos7.setText(player);}
        if(loc==8){labelPos8.setText(player);}
        if(loc==9){labelPos9.setText(player);}
    }
    private void setLabelPlayerPlayWith(int loc,String player){
        /*method that positions the player based on its location on the label for playwith case.
           params:player x or o and loc
           return:None
         */
        if(loc==1){labelPos11.setText(player);}
        if(loc==2){labelPos12.setText(player);}
        if(loc==3){labelPos13.setText(player);}
        if(loc==4){labelPos14.setText(player);}
        if(loc==5){labelPos15.setText(player);}
        if(loc==6){labelPos16.setText(player);}
        if(loc==7){labelPos17.setText(player);}
        if(loc==8){labelPos18.setText(player);}
        if(loc==9){labelPos19.setText(player);}
    }
    private void setLabelPlayerExpert(int loc,String player){
        /*method that positions the player based on its location on the label for expert case.
           params:player x or o and loc
           return:None
         */
        if(loc==1){labelPos21.setText(player);}
        if(loc==2){labelPos22.setText(player);}
        if(loc==3){labelPos23.setText(player);}
        if(loc==4){labelPos24.setText(player);}
        if(loc==5){labelPos25.setText(player);}
        if(loc==6){labelPos26.setText(player);}
        if(loc==7){labelPos27.setText(player);}
        if(loc==8){labelPos28.setText(player);}
        if(loc==9){labelPos29.setText(player);}
    }
    private Label getLabelForm(int loc){
        /*method that returns the label on which the player is located for practice case.
           params:loc of the player
           return:label
         */
        if(loc==1)return labelPos1;
        if(loc==2)return labelPos2;
        if(loc==3)return labelPos3;
        if(loc==4)return labelPos4;
        if(loc==5)return labelPos5;
        if(loc==6)return labelPos6;
        if(loc==7)return labelPos7;
        if(loc==8)return labelPos8;
        if(loc==9)return labelPos9;
        return new Label();
    }
    private Label getLabelFormExpert(int loc){
         /*method that returns the label on which the player is located for Expert case.
           params:loc of the player
           return:label
         */
        if(loc==1)return labelPos21;
        if(loc==2)return labelPos22;
        if(loc==3)return labelPos23;
        if(loc==4)return labelPos24;
        if(loc==5)return labelPos25;
        if(loc==6)return labelPos26;
        if(loc==7)return labelPos27;
        if(loc==8)return labelPos28;
        if(loc==9)return labelPos29;
        return new Label();
    }
    private Label getLabelFormPlayWith(int loc){
        /*method that returns the label on which the player is located for playwith case.
           params:loc of the player
           return:label
         */
        if(loc==1)return labelPos11;
        if(loc==2)return labelPos12;
        if(loc==3)return labelPos13;
        if(loc==4)return labelPos14;
        if(loc==5)return labelPos15;
        if(loc==6)return labelPos16;
        if(loc==7)return labelPos17;
        if(loc==8)return labelPos18;
        if(loc==9)return labelPos19;
        return new Label();
    }
    @FXML
    private void quitButton(){
        /*exit out of the program*/
        System.exit(1);
    }


}