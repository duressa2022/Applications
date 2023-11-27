"""
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
"""
import random
import time
#The game board with 9 elements that comes with zeros at the first
Board=[0 for i in range(9)]
def drawGameState(gameBoard,comSymbol,humSymbol):
    """method to draw the state of the game/ this method draws 3 by 3 game board.
       and have the following parameters.
       gameBoard:array of numbers(9) to be drawen.
       comSymbol:computer as player has x or o symbol.
       humSymbol:human as player has x or o symbol.
       return:None
    """
    current=0
    print("______________________________")
    for i in range(3):
        for j in range(3):
            if gameBoard[current]==0:
                print("|___",current+1,end="___|")
            else:
                if gameBoard[current]==1:
                    print("|___", comSymbol, end="___|")
                else:
                    print("|___", humSymbol, end="___|")

            current+=1
        print()
    print("|____________________________|")
def chooseSymbolForPlayer():
    """method that recieve symbol from the user or other player and base on response assign.
       x or o for player.
       params:None.
       return:comSymbol or humSymbol
     """
    humSymbol=input("Choose your player: ")
    comSymbol=""
    while True==True:
        if humSymbol.upper()!="X" and humSymbol.upper()!="O":
            humSymbol=input("Please Choose from X or O: ")
        else:
            break
    if humSymbol.upper()=="X":
        comSymbol="O"
    else:comSymbol="X"
    print("______________PLAYER____________________")
    print("Your have choosen: "+humSymbol.upper())
    print("Computer's player: "+comSymbol.upper())
    print("-----------------------------------------")
    return (comSymbol,humSymbol)
def chooseWhoStarts():
    """method that recieve whot will start the game from the user and assign for
       x or o for player.
       params:None.
       return:response of player(will start or Not)
    """
    humResponse=input("Start the Game Y|N: ")
    while True==True:
        if humResponse.upper()!="Y" and humResponse.upper()!="N":
            humResponse=input("Please use only Y|N: ")
        else:
            break
    for i in "___STARTING THE GAME___":
        print(i,end="")
        time.sleep(0.2)
    print()
    return humResponse
def validateInput(gameBoard):
    """method that validate the input from player is correct or not based on the response from the player.
       params:gameBoard(current state of the game)
       return:True if player input is correct otherwise False
     """
    validInput=[str(input) for input in range(1,10)]
    humMove=input("Enter your move: ")
    if humMove not in validInput:
        return False,humMove
    humMove=int(humMove)
    if humMove<1 or humMove>=10:
        return False,humMove
    if gameBoard[humMove-1]==1 or gameBoard[humMove-1]==-1:
        return False,humMove
    return (True,humMove)
def evaluateGame(gameBoard):
    """method that evaluate the current state of the game(gameBoard),or returns who win the game.
       params:gameBoard(current state of the game)
       return:1 if computer -1 if player 0 if their is draw
     """
    if gameBoard[0]==gameBoard[1] and gameBoard[1]==gameBoard[2]:
        return gameBoard[0]
    elif gameBoard[0]==gameBoard[3] and gameBoard[3]==gameBoard[6]:
        return gameBoard[0]
    elif gameBoard[1]==gameBoard[4] and gameBoard[4]==gameBoard[7]:
        return gameBoard[1]
    elif gameBoard[2]==gameBoard[5] and gameBoard[5]==gameBoard[8]:
        return gameBoard[2]
    elif gameBoard[3]==gameBoard[4] and gameBoard[4]==gameBoard[5]:
        return gameBoard[3]
    elif gameBoard[6]==gameBoard[7] and gameBoard[7]==gameBoard[8]:
        return gameBoard[6]
    elif gameBoard[0]==gameBoard[4] and gameBoard[4]==gameBoard[8]:
        return gameBoard[0]
    elif gameBoard[2]==gameBoard[4] and gameBoard[4]==gameBoard[6]:
        return gameBoard[2]
    else:
        return 0
def depthAgentBrain(gameBoard,player):
    """method that returns the maximum score of the gameBoard for player.
       params:gameBoard(current state of the game) and player that maximize the score
       return:max score of the player
    """
    if evaluateGame(gameBoard)!=0:
        return evaluateGame(gameBoard)*player
    score=-2
    move=-1
    for i in range(len(gameBoard)):
        if gameBoard[i]==0:
            gameBoard[i]=player
            current=-depthAgentBrain(gameBoard,-player)
            if current>score:
                score=current
                move=i
            gameBoard[i]=0
    if move==-1:
        return 0
    return score

def depthAgentMove(gameBoard,player):
    """method that returns the move that produce max score for current player.
       params:gameBoard(current state of the game) and player that maximize the score
       return:move for the player
    """
    score=-2
    move=-1
    for i in range(len(gameBoard)):
        if gameBoard[i]==0:
            gameBoard[i]=player
            current=-depthAgentBrain(gameBoard,-player)
            if score<current:
                score=current
                move=i
            gameBoard[i]=0

    return move
def roughAgentBrain(gameBoard,player):
    """method that returns the random possible move for the player.
       params:gameBoard(current state of the game) and player
       return:random move for player
    """
    move=[i for i in range(9)]
    while True==True:
        choice=random.choice(move)
        if gameBoard[choice]==player or gameBoard[choice]==-player:
            continue
        else:
            return choice
def initializeGame(gameBoard):
    """This method initilizes the Game from the gameBoard.
       params:gameBoard(current state of the game).
       return:None
    """
    print("----------WELCOME--------------")
    drawGameState(Board,"","")
    for i in "---------LEVEL ONE---------":
        print(i,end="")
        time.sleep(0.2)
    print()
    drawGameState(gameBoard,"","")
def levelOneGameStarting(gameBoard,player1,player2,x,y):
    """method to start the first level of the game and have the following parameters.
       gameBoard:array of numbers(9) current state of the game.
       x:computer as player has x or o symbol.
       y:human as player has x or o symbol.
       player1:-1(player)
       player2:1(computer)
    """
    drawGameState(gameBoard, x, y)
    isvalid,input=validateInput(gameBoard)
    if isvalid==True:
        gameBoard[input- 1] =player1
    result=evaluateGame(gameBoard)
    if result==player1:
        print("You are the winner")
        return "human"
    elif result==player2:
        print("Agent is the winner")
        return "agent"
    else:
        if player1+player2 not in gameBoard:
            return "draw"
    drawGameState(gameBoard, x, y)
    input=roughAgentBrain(gameBoard, player2)
    gameBoard[input] = player2
    result = evaluateGame(gameBoard)
    if result==player1:
        print("You are the winner")
        return "human"
    elif result==player2:
        print("Agent is the winner")
        return "agent"
    else:
        if player1+player2 not in gameBoard:
            print("Draw happened!")
            return "draw"
    print("Computer has moved")
    drawGameState(gameBoard, x, y)
def levelOneGameSeconding(gameBoard,player1,player2,x,y):
    """method to start the first level but with different order of the game and have the following parameters.
       gameBoard:array of numbers(9) current state of the game.
       x:computer as player has x or o symbol.
       y:human as player has x or o symbol.
       player1:-1(player)
       player2:1(computer)
    """
    drawGameState(gameBoard, x, y)
    input=roughAgentBrain(gameBoard, player2)
    gameBoard[input]=player2
    result=evaluateGame(gameBoard)
    if result==player1:
        print("You are the winner")
        return "human"
    elif result==player2:
        print("Agent is the winner")
        return "computer"
    else:
        if player1+player2 not in gameBoard:
            print("Draw happened!")
            return "draw"
    print("Computer has moved")
    drawGameState(gameBoard, x, y)
    isvalid,input=validateInput(gameBoard)
    if isvalid==True:
        gameBoard[input- 1] =player1
    result=evaluateGame(gameBoard)
    if result==player1:
        print("You are the winner")
        return "human"
    elif result==player2:
        print("Agent is the winner")
        return "agent"
    else:
        if player1+player2 not in gameBoard:
            return "draw"
    drawGameState(gameBoard, x, y)
    
def levelTwoGameStarting(gameBoard,player1,player2,x,y):
    """method to start the second level of the game and have the following parameters.
       gameBoard:array of numbers(9) current state of the game.
       x:computer as player has x or o symbol.
       y:human as player has x or o symbol.
       player1:-1(player)
       player2:1(computer)
    """
    drawGameState(gameBoard, x, y)
    isvalid,input=validateInput(gameBoard)
    if isvalid==True:
        gameBoard[input- 1] =player1
    result=evaluateGame(gameBoard)
    if result==player1:
        print("You are the winner")
        return "human"
    elif result==player2:
        print("Agent is the winner")
        return "agent"
    else:
        if player1+player2 not in gameBoard:
            return "draw"
    drawGameState(gameBoard, x, y)
    input=depthAgentMove(gameBoard,player2)
    gameBoard[input] = player2
    result=evaluateGame(gameBoard)
    if result==player1:
        print("You are the winner")
        return "human"
    elif result==player2:
        print("Agent is the winner")
        return "computer"
    else:
        if player1+player2 not in gameBoard:
            print("Draw happened!")
            return "draw"
    print("Computer has moved")
    drawGameState(gameBoard,x,y)
def levelTwoGameSeconding(gameBoard,player1,player2,x,y):
    """method to start the second level but with different order of the game and have the following parameters.
       gameBoard:array of numbers(9) current state of the game.
       x:computer as player has x or o symbol.
       y:human as player has x or o symbol.
       player1:-1(player)
       player2:1(computer)
    """
    drawGameState(gameBoard, x, y)
    input=depthAgentMove(gameBoard, player2)
    gameBoard[input]=player2
    result = evaluateGame(gameBoard)
    if result == player1:
        print("You are the winner")
        return "human"
    elif result == player2:
        print("Agent is the winner")
        return "computer"
    else:
        if player1 + player2 not in gameBoard:
            print("Draw happened!")
            return "draw"
    print("Computer has moved")
    drawGameState(gameBoard, x, y)
    isvalid,input=validateInput(gameBoard)
    if isvalid==True:
        gameBoard[input- 1] =player1
    result=evaluateGame(gameBoard)
    if result==player1:
        print("You are the winner")
        return "human"
    elif result==player2:
        print("Agent is the winner")
        return "agent"
    else:
        if player1+player2 not in gameBoard:
            return "Draw"
    drawGameState(gameBoard, x, y)
def levelResult(gameBoard,result):
    """method that verify the progression of the game.
       params:gameBoard(current state of the game) and result(who win the game)
       return:True if it continues else False
    """
    playing=True
    if result.upper()=="HUMAN":
        for i in "-------LEVEL TWO------":
            print(i, end="")
            time.sleep(0.2)
    else:
        result=input("Play again(Y) or Quit(any key)")
        if result.upper()=="Y":
            playing=True
        else:
            playing=False
    return playing
def startingPlayer(gameBoard,x,y,player1=-1,player2=1):
    """method that starts the both level of the game in first order and have the following parameters.
       gameBoard:array of numbers(9) current state of the game.
       x:computer as player has x or o symbol.
       y:human as player has x or o symbol.
       player1:-1(player)
       player2:1(computer)
    """
    playing=True
    while True==True:
        result=levelOneGameStarting(gameBoard, player1, player2, x, y)
        if result is not None:
            playing=levelResult(gameBoard,result)
            break
    print()
    x,y=chooseSymbolForPlayer()
    respnose=chooseWhoStarts()
    gameBoard=[0] * 9
    while True==True:
        result=levelTwoGameStarting(gameBoard, player1, player2, x, y)
        if result is not None:
            playing=levelResult(gameBoard, result)
            break
    return playing
def secondingPlayer(gameBoard,x,y,player1=-1,player2=1):
    """method that starts the both level of the game in second or other order and have the following parameters.
       gameBoard:array of numbers(9) current state of the game.
       x:computer as player has x or o symbol.
       y:human as player has x or o symbol.
       player1:-1(player)
       player2:1(computer)
    """
    playing=True
    while True==True:
        result=levelTwoGameStarting(gameBoard, player1, player2, x, y)
        if result is not None:
            playing=levelResult(gameBoard,result)
            break
    print()
    x,y=chooseSymbolForPlayer()
    respnose=chooseWhoStarts()

    gameBoard=[0] * 9
    while True==True:
        result=levelTwoGameSeconding(gameBoard, player1, player2, x, y)
        if result is not None:
            playing=levelResult(gameBoard, result)
            break
    return playing
def playingGame(gameBoard,player1=-1,player2=1):
    """method that the game based on the give  order and have the following parameters.
       gameBoard:array of numbers(9) current state of the game.
       player1:-1(player)
       player2:1(computer)
    """
    initializeGame(gameBoard)
    playing=True
    while True==True:
        x,y=chooseSymbolForPlayer()
        respnose=chooseWhoStarts()
        if respnose.upper()=="Y":
            playing=startingPlayer(gameBoard,x,y,player1,player2)
            if playing==True:continue
            else:break
        else:
            playing=secondingPlayer(gameBoard,x,y,player1,player2)
            if playing==True:continue
            else:break
#=========================================================================================================
#=========================================================================================================
#=========================================================================================================
#========================================================================================================
#========================================================================================================
playingGame(Board,player1=-1,player2=-1)
#========================================================================================================
#========================================================================================================
#=========================================================================================================
#========================================================================================================
#======================================================================================================

