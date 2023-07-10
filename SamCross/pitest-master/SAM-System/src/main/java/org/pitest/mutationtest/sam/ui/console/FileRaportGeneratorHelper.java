package org.pitest.mutationtest.sam.ui.console;

import org.pitest.extensions.MutationRandomizerSingleton;
import org.pitest.mutationtest.DetectionStatus;
import org.pitest.mutationtest.sam.config.FromFileMetaData;
import org.pitest.mutationtest.statistics.Score;
import org.pitest.mutationtest.statistics.StatusCount;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;

public class FileRaportGeneratorHelper {
    
    
    public static String AppendRaport(FromFileMetaData tqedtempDataLocal, FromFileMetaData data, Instant start, Instant end)
    {
        String str = "";
        try{
        System.out.println(".");
        System.out.println(".");
        System.out.println(".");
        System.out.println(".");
        System.out.println("-----------------------------------------------------------");
        System.out.println("MUTACJA TQED DLA: "+ data.Classname + " PLIK " + data.FileName);
        System.out.println("-----------------------------------------------------------");

        MutationRandomizerSingleton.ActualClass =data.Classname;

        Duration timeElapsed = Duration.between(start, end);

        java.util.HashMap<DetectionStatus,Long> mapaDanych  = new HashMap<DetectionStatus, Long>();

        mapaDanych.put(DetectionStatus.NUMBER_Of_FILES,new Long(0));
        mapaDanych.put(DetectionStatus.NUMBER_Of_LINES,new Long(0));
        mapaDanych.put(DetectionStatus.NUMBER_Of_LINES_COVERED,new Long(0));
        mapaDanych.put(DetectionStatus.NUMBER_Of_MUTATIONS,new Long(0));
        mapaDanych.put(DetectionStatus.NUMBER_Of_MUTATIONS_DETECTED,new Long(0));

        mapaDanych.put(DetectionStatus.KILLED,new Long(0));
        mapaDanych.put(DetectionStatus.SURVIVED,new Long(0));
        mapaDanych.put(DetectionStatus.NO_COVERAGE,new Long(0));
        mapaDanych.put(DetectionStatus.TIMED_OUT,new Long(0));
        mapaDanych.put(DetectionStatus.MEMORY_ERROR,new Long(0));
        mapaDanych.put(DetectionStatus.RUN_ERROR,new Long(0));
        mapaDanych.put(DetectionStatus.NON_VIABLE,new Long(0));
        mapaDanych.put(DetectionStatus.NOT_STARTED,new Long(0));
        mapaDanych.put(DetectionStatus.STARTED,new Long(0));

        String LIne="";
        if(MutationRandomizerSingleton.getInstance().MutationTotals!=null){
            mapaDanych.put(DetectionStatus.NUMBER_Of_FILES,MutationRandomizerSingleton.getInstance().MutationTotals.numberOfFiles);
            mapaDanych.put(DetectionStatus.NUMBER_Of_LINES,MutationRandomizerSingleton.getInstance().MutationTotals.numberOfLines);
            mapaDanych.put(DetectionStatus.NUMBER_Of_LINES_COVERED,MutationRandomizerSingleton.getInstance().MutationTotals.numberOfLinesCovered);
            mapaDanych.put(DetectionStatus.NUMBER_Of_MUTATIONS,MutationRandomizerSingleton.getInstance().MutationTotals.numberOfMutations);
            mapaDanych.put(DetectionStatus.NUMBER_Of_MUTATIONS_DETECTED,MutationRandomizerSingleton.getInstance().MutationTotals.numberOfMutationsDetected);
        }

        if(MutationRandomizerSingleton.GlobalStats!=null){

            for (Score sorce : MutationRandomizerSingleton.GlobalStats.getScores())
            {
                Iterable<StatusCount>  counts = sorce.GetCounts();
                long totalmut = sorce.getTotalMutations();
                // sorce.report().;

                for (StatusCount status: counts) {
                    if(status.getStatus().equals(DetectionStatus.KILLED)){ //1
                        mapaDanych.put(DetectionStatus.KILLED,mapaDanych.get(DetectionStatus.KILLED)+ status.getCount());
                    }
                    if(status.getStatus().equals(DetectionStatus.SURVIVED)){//2
                        mapaDanych.put(DetectionStatus.SURVIVED,mapaDanych.get(DetectionStatus.SURVIVED)+ status.getCount());
                    }
                    if(status.getStatus().equals(DetectionStatus.NO_COVERAGE)){ //3
                        mapaDanych.put(DetectionStatus.NO_COVERAGE,mapaDanych.get(DetectionStatus.NO_COVERAGE)+ status.getCount());
                    }
                    if(status.getStatus().equals(DetectionStatus.TIMED_OUT)){ //4
                        mapaDanych.put(DetectionStatus.TIMED_OUT,mapaDanych.get(DetectionStatus.TIMED_OUT)+ status.getCount());
                    }
                    if(status.getStatus().equals(DetectionStatus.MEMORY_ERROR)){ //5
                        mapaDanych.put(DetectionStatus.MEMORY_ERROR,mapaDanych.get(DetectionStatus.MEMORY_ERROR)+ status.getCount());
                    }
                    if(status.getStatus().equals(DetectionStatus.RUN_ERROR)){//6
                        mapaDanych.put(DetectionStatus.RUN_ERROR,mapaDanych.get(DetectionStatus.RUN_ERROR)+ MutationRandomizerSingleton.getInstance().FailedTsests.size());
                        MutationRandomizerSingleton.getInstance().FailedTsests.clear();
                    }
                    if(status.getStatus().equals(DetectionStatus.NON_VIABLE)){//7
                        mapaDanych.put(DetectionStatus.NON_VIABLE,mapaDanych.get(DetectionStatus.NON_VIABLE)+ status.getCount());
                    }
                    if(status.getStatus().equals(DetectionStatus.NOT_STARTED)){//8
                        mapaDanych.put(DetectionStatus.NOT_STARTED,mapaDanych.get(DetectionStatus.NOT_STARTED)+ status.getCount());
                    }
                    if(status.getStatus().equals(DetectionStatus.STARTED)){//9
                        mapaDanych.put(DetectionStatus.STARTED,mapaDanych.get(DetectionStatus.STARTED)+ status.getCount());
                    }
                }
            }

        }else{
            LIne ="0;0;0;0;0;0;0;0;0;0";
        }

        LIne=";"+mapaDanych.get(DetectionStatus.NUMBER_Of_FILES)+
                ";"+mapaDanych.get(DetectionStatus.NUMBER_Of_LINES)+
                ";"+mapaDanych.get(DetectionStatus.NUMBER_Of_LINES_COVERED)+
                ";"+mapaDanych.get(DetectionStatus.NUMBER_Of_MUTATIONS)+
                ";"+mapaDanych.get(DetectionStatus.NUMBER_Of_MUTATIONS_DETECTED)+ //to jest liczba wykrytych przez testy mutantów rózni się o tyle od liczby zabitych ze można wykryć mutanta ale zakończyć test timeoutem Wtedy mutant żyje.
                ";"+mapaDanych.get(DetectionStatus.KILLED)+
                ";"+mapaDanych.get(DetectionStatus.SURVIVED)+
                ";"+mapaDanych.get(DetectionStatus.NO_COVERAGE)+
                ";"+mapaDanych.get(DetectionStatus.TIMED_OUT)+
                ";"+mapaDanych.get(DetectionStatus.MEMORY_ERROR)+
                ";"+mapaDanych.get(DetectionStatus.RUN_ERROR)+
                ";"+mapaDanych.get(DetectionStatus.NON_VIABLE)+
                ";"+mapaDanych.get(DetectionStatus.NOT_STARTED)+
                ";"+mapaDanych.get(DetectionStatus.STARTED);

            String tempClassname ="NONE";
            if(!data.Classname.equals(""))
                tempClassname= data.Classname;

            str +=""+tqedtempDataLocal.FileName+";"+ tempClassname +";"+timeElapsed.toMillis()+";OK"
                +";"+MutationRandomizerSingleton.TestRan
                +";"+"cpFromLog"
                +";"+MutationRandomizerSingleton.GlobalTestsPermut.replace('.',',')
                +LIne
                +System.lineSeparator();


        }catch (Exception e){
            str +=""+ data.Classname +";-1;ClassError;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1"+System.lineSeparator();
        }
        finally {
            return str;
        }
    }
    
}
