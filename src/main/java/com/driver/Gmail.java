package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)

    HashMap<String,String> messageSenderDb = new HashMap<>();
    HashMap<String, Date> messageDateDb = new HashMap<>();
    ArrayList<String> inboxDb = new ArrayList<>();
    ArrayList<String> trashDb = new ArrayList<>();
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.

        if(inboxDb.size()>=inboxCapacity){
            String oldestMail = inboxDb.get(0);
            trashDb.add(oldestMail);
            inboxDb.remove(0);
        }
        inboxDb.add(message);
        messageSenderDb.put(message, sender);
        messageDateDb.put(message, date);
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(int i=0;i<inboxDb.size();i++){
            if(inboxDb.get(i).equals(message)){
                trashDb.add(message);
                inboxDb.remove(i);
                messageSenderDb.remove(message);
                messageDateDb.remove(message);
                return;
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(inboxDb.size()==0){
            return null;
        }
        return inboxDb.get(inboxDb.size()-1);
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(inboxDb.size()==0){
            return null;
        }
        return inboxDb.get(0);
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count = 0;
        for(Date date : messageDateDb.values()){
            if((date.after(start) || date.equals(start)) && (date.before(end) || date.equals(end))){
                count++;
            }
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inboxDb.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trashDb.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trashDb.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
