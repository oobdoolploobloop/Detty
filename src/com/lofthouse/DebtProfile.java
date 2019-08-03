package com.lofthouse;

import java.util.*;

public class DebtProfile {
    boolean debug = true;
    private LinkedList<DebtEntry> debtEntryList;
    private Hashtable<Integer, Integer> listOfContacts; // List of <Contact ID, # of instances of debt in list>

    public DebtProfile() {
        this.debtEntryList = new LinkedList<>() {
        };
        this.listOfContacts = new Hashtable<>();
    }

    public void addDebtEntry(DebtEntry de){
        boolean knowOwe;
        boolean knowOwed;
        int owe = de.idOwe;
        int owed = de.idOwed;

        if(debug)System.out.println("Got new DebtEntry from " + owe + " to " + owed);

        // Do we already know the owed?
        knowOwed = personInListOfContacts(owed);

        // Do we already know the owee?
        knowOwe = personInListOfContacts(owe);

        // If we knew both of them, search to see if we have another debt between the two or not
        if(knowOwe && knowOwed){ // We know both of them, test if they have mutual debt
            updateDebtProfileBothKnown(de);
            if(debug)System.out.println("We know both of these users");
        }else{ // We dont know both of them, add a new node between them of debt
            if(debug)System.out.println("We don't know both of these users");
            debtEntryList.add(de);
        }

        uncycleDebtProfile();
        printDebtProfile();

    }

    // If both parties are known in our debtEntryList
    private void updateDebtProfileBothKnown(DebtEntry de){
        int owe = de.idOwe;
        int owed = de.idOwed;
        int amt = de.amt;

        boolean foundPreviousDebt = false;
        for(DebtEntry d : debtEntryList){
            if(d.idOwe == owe && d.idOwed == owed){ // A debt already exists between these two people on record
                d.amt += amt;
                foundPreviousDebt = true;

            }else if(d.idOwed == owe && d.idOwe == owed){ // Same as above, but debt in reverse direction
                if(d.amt > amt){
                    d.amt -= amt;

                }else if(d.amt < amt){ // Paying back more than they need to, reverse debt
                    int namt = amt - d.amt;
                    debtEntryList.remove(d); // Remove current entry
                    debtEntryList.add((new DebtEntry(owe, owed, namt))); // Add one of reverse order

                }else{ // Debt equal, remove the node
                    debtEntryList.remove(d);
                    // Decrement their contact count, remove if 0
                    decrementListOfContacts(owe);
                    decrementListOfContacts(owed);
                }
                foundPreviousDebt = true;
            }
        }

        if(!foundPreviousDebt){ // No debt exists between these two nodes yet, add a new one
            debtEntryList.add(de);
        }
    }

    // If the person has been seen before, update their known debt count
    // Otherwise add them to the list of known debt counts
    private boolean personInListOfContacts(int p){
        if(listOfContacts.containsKey(p)){
            listOfContacts.replace(p, listOfContacts.get(p) + 1);
            return true;
        }else{
            listOfContacts.put(p,1);
            return false;
        }
    }

    // Takes a person in the list of contacts, and either decrements their count or removes them if count=1
    private void decrementListOfContacts(int p){
        if(listOfContacts.get(p) > 1){
            listOfContacts.replace(p, listOfContacts.get(p) - 1);
        }else{
            listOfContacts.remove(p);
        }
    }

    // Process the DebtProfile in order to remove any cycles from it
    // Notify of any cycles remove
    private void uncycleDebtProfile(){

    }

    private void printDebtProfile(){
        for(DebtEntry d : debtEntryList){
            System.out.println(d.idOwe + " owes " + d.idOwed + " $" + d.amt);
        }
    }
}

