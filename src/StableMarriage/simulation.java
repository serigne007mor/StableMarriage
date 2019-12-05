package StableMarriage;

import java.util.*;
import java.util.Map.Entry;

public class simulation {
    public static void main(String[] args)
    {
        int men[][] = {
                {0,1,2},
                {0,1,2},
                {0,1,2}
        };

        int women[][] = {
                {1,0,2},
                {1,2,0},
                {0,1,2}
        };


        // Add all available men to a HashSet, so its easy to lookup remaining man
        Set<Integer> availableMen = new HashSet <Integer> ();
        for (int i=0; i<men.length; i++){
            availableMen.add(i);
        }


        // Store alliance of a women in a HashMap.
        // Null value means no alliance.
        Map<Integer, Integer> availableWomen = new HashMap <Integer, Integer> ();
        for (int i=0; i<women.length; i++){
            availableWomen.put(i, null);
        }


        // Loop till there are men available
        int size = availableMen.size();
        while (size > 0)
        {
            int currentBachelor = availableMen.iterator().next();
            // loop through preferences of this guy
            for (int w : men[currentBachelor])
            {
                Integer fiance = availableWomen.get(w);
                if (fiance == null)//meaning she free
                {
                    availableWomen.put(w, currentBachelor);
                    availableMen.remove(currentBachelor);
                    System.out.println ("Man " + currentBachelor + " proposes to woman " + w);
                    break;
                }
                else // meaning she taken
                {
                    int prefForFiance = -1;
                    int prefForCurrentBachelor = -1;
                    for (int k=0; k<women[w].length; k++)
                    {
                        if (women[w][k] == fiance) // find preference order for current fiance
                            prefForFiance = k;

                        if (women[w][k] == currentBachelor) // find preference order for current proposer
                            prefForCurrentBachelor = k;
                    }

                    if (prefForCurrentBachelor < prefForFiance) // nextBachelor has higher preference by this woman
                    {
                        availableWomen.put (w, currentBachelor); // accept current bachelor
                        availableMen.remove(currentBachelor);
                        availableMen.add(fiance); // return previous fiance to un married man list
                        System.out.println ("Man " + currentBachelor + " proposes to woman " + w);
                        System.out.println ("Man " + fiance + " is dumped by woman " + w);

                        break;
                    }
                }
            }
            size = availableMen.size();
        }


        System.out.println();
        Iterator<Entry<Integer, Integer>> itr = availableWomen.entrySet().iterator();
        while (itr.hasNext())
        {
            Entry<Integer, Integer> entry = itr.next();
            System.out.println("Man " + entry.getValue() + " married to woman " + entry.getKey());
        }
    }
}