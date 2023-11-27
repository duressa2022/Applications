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
import time


def functionReader(location):
    """This method create new array of content of the file object.try to open the
       File based on the given location ,use readlines method of file to convert it
       into python array or list by removing the new line character and spliting
       based on the special character in the file.
       params: location
       return:arrayofFile tuple with email and label
    """
    arrayFile=[]
    try:
        File=open(location)
        for file in File:
            arrayFile.append(tuple(file.strip("\n").split("#")))
    except FileNotFoundError as e:
        print("Error in loading the File")
    finally:
        File.close()
    return arrayFile
def functionTrainModel(labeledEmail):
    """This method train the model based on the given data or labeled data
       calculate the probability of each word within labaled email(spam or free)
       by using simple probality calculation p(word)=frq(word)/total word.
       params: labeled Email
       return:dict of spam and free with their respective probablity
    """
    #Declare important datastructure that used for storing possible data.
    spamWord={}
    freeWord={}
    spamEmail=[]
    freeEamil=[]
    #Append email with spam label into spamEmail list
    for email,label in labeledEmail:
        if label=="Spam":
            spamEmail.append(email)
    #Append email with free label into spamEmail list
    for email,label in labeledEmail:
        if label=="Free":
            freeEamil.append(email)
    #calculate the length of total word in the spamEmail
    spamLength=0
    for email in spamEmail:
        spamLength+=len(email.split())
    #calculate the probablity of each word in the spamEmail
    for email in spamEmail:
        for word in email.split():
            spamWord[word]=spamWord.get(word,0)+1/spamLength
    #calculate the length of total word in the freeEmail
    freeLength=0
    for email in freeEamil:
        freeLength+=len(email.split())
    #calculate the probablity of each word in the spamEmail
    for email in freeEamil:
        for word in email.split():
            freeWord[word]=freeWord.get(word,0)+1/freeLength
    return spamWord,freeWord
def functionClassifier(email,labeledEmail):
    """This method uses trained model based on the given data or labeled data.
       then try to calculate the probability of the words(spam or free) in new email.
       use the product rule from probabilty to calculate correctness of the word
       params: labeled Email,and new Email
       return:spam if email contains the spam words else free
    """
    spamWord,freeWord=functionTrainModel(labeledEmail)
    spamScore=0.5
    freeScore=1-spamScore
    for word in email.split():
        spamScore*=spamWord.get(word,10e-4)
        freeScore*=freeWord.get(word,10e-4)
    score=spamScore+freeScore
    spamScore/=score
    freeScore/=score
    return "spam" if spamScore>freeScore else "free"
def functionDraw(string):
    """Draws given string inthe format that seems loading
       params:string (as input)
       return:None
    """
    for line in string:
        print(line,end="")
        time.sleep(0.1)
def functionTestingEmailWindow():
    """Main function that draws user interface for testing and Email
       params:None
       return:None
    """
    functionDraw("------------WELCOME TO EMAIL TESTER SERVICE------------\n")
    testing=True
    while testing==True:
        functionDraw("DO YOU WANT TO TEST YOUR EMAIL(Y) OR  ANY: ")
        result=input();
        if result.upper()!="Y":functionDraw("THANKYOU FOR USING OUR SERVICE!!");return
        functionDraw("ENTER EMAIL HERE: ")
        email=input();
        functionDraw("TESTING YOUR EMAIL.....\n")
        functionDraw("LOADING FILE.....\n")
        functionDraw("LOADING CLASSIFIER ALGORITHEM....\n")
        functionDraw("FINALIZING YOUR RESULT....\n")
        labeled=functionReader("ListOfEmail")
        final=functionClassifier(email,labeled)
        functionDraw(">>>>>RESULT IS: {}\n".format(final))
        functionDraw("DO YOU WANT TO CONTINUE TO TEST YOUR EMAIL(Y) OR  ANY: ")
        result = input();
        if result.upper()!="Y":functionDraw("THANKYOUR FOR USING OUR SERVICE!");
        return
#=========================================================================================================
#=========================================================================================================
#=========================================================================================================
#========================================================================================================
#========================================================================================================
functionTestingEmailWindow()
#========================================================================================================
#========================================================================================================
#=========================================================================================================
#========================================================================================================
#======================================================================================================








