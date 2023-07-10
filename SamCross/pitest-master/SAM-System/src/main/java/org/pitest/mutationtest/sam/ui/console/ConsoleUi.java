package org.pitest.mutationtest.sam.ui.console;

import org.pitest.extensions.MutationRandomizerSingleton;
import org.pitest.mutationtest.DetectionStatus;
import org.pitest.mutationtest.sam.config.FromFileMetaData;
import org.pitest.mutationtest.sam.config.IProjectMetaData;
import org.pitest.mutationtest.sam.config.ImputMetaData;
import org.pitest.mutationtest.sam.config.SimpleMetaDataToPropagate;
import org.pitest.mutationtest.sam.ui.Iui;
import org.pitest.mutationtest.sam.web.WebSocketWorkerNode;
import org.pitest.mutationtest.statistics.Score;
import org.pitest.mutationtest.statistics.StatusCount;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gosc on 19.11.2016.
 */
public class ConsoleUi implements Iui{

    private WebSocketWorkerNode _workerSerwer;
    private  boolean  _isOn;
    private static Object _lock;
    private BufferedReader _cnsl;

    //Comands Strings-------------------------------

    String CCS_stoped = "SAM-System Console stoped";
    String CCS_start ="================================================================"+System.lineSeparator()+
                      "SAM-SYSTEM v 1.0"+System.lineSeparator()+
                      "================================================================"+System.lineSeparator()+
                      "Need help? type 'help'"
            ;
    //Comands Strings-------------------------------


    public ConsoleUi(WebSocketWorkerNode workerSerwer) {
        _workerSerwer = workerSerwer;
        _isOn = true;
        _lock = new Object();
        _cnsl = new BufferedReader(new InputStreamReader(System.in));
        Runnable consoleImput = () -> {consoleImmputTask();};
        consoleImput.run();
    }

    private void consoleImmputTask(){
        try {
            synchronized (_lock){
                System.out.println(CCS_start);
                while (_isOn){
                        String comand = _cnsl.readLine();
                    switch (comand) {
                        case "test":
                           System.out.println("Test wykonany");
                            break;
                        case "connect":
                            System.out.println("Server adress: ");
                            String adress = _cnsl.readLine();
                            System.out.println("Server Port: ");
                            int port = Integer.parseInt(_cnsl.readLine());
                            this.connectTo(adress,port);
                            break;
                        case "start":
                            System.out.println("Set server working port Port: ");
                            int port1 = Integer.parseInt(_cnsl.readLine());
                            this.startSerwer(Integer.valueOf(port1));
                            break;
                        case "run mutation -broudcast":
                            IProjectMetaData tempData =new FromFileMetaData();//i to trzeba jakos ogarnac tutaj zabawa analityczna
                            //Przed wszystkim klasy trzeba wyciac osobno i do testów ilosc klas przez ilosc nodó i wywylayac jakos
                            _workerSerwer.SendToAllConnectedNodes("PitRun", tempData);
                            break;
                        case "rm": //Odpalanie pojedynczej instacji pita projekt pobierany z konfiga
                            IProjectMetaData tempDataLocal =new FromFileMetaData();
                            MutationRandomizerSingleton.SetBayes = false;
                            _workerSerwer.RunnPitStandAlone(tempDataLocal);
                            break;

                        case "tqed": //Odpalanie pojedynczej instacji pita projekt pobierany z konfiga
                            String strtqed = "INPUT_CFG;CLASS;TIME;INFO;TEST_RAN;TEST_FOUND;TEST_RAN_PER_MUT;NUMBER_Of_FILES;NUMBER_Of_LINES;NUMBER_Of_LINES_COVERED;NUMBER_Of_MUTATIONS;NUMBER_Of_MUTATIONS_DETECTED;KILLED;SURVIVED;NO_COVERAGE;TIMED_OUT;MEMORY_ERRORRUN_ERROR;RUN_ERROR;NON_VIABLE;NOT_STARTED;STARTED"+System.lineSeparator();
                            MutationRandomizerSingleton.DetailReportLog = "FILE;G_CODE;G_TESTS;STATUS;TEST_USED;TEST_KILL;MUTANT_LINE;CLASS;DESCRIPTION"+System.lineSeparator();
                            /*
                            ******* LEGENDA DO utationRandomizerSingleton.DetailReportLog *******************
                            FILE;
                                Nazwa pliku był wsadem został zmutowany. Przykład 1.ini
                            G_CODE;
                                Identyfikador kodu, Czyli z jakiego projektu pochodzi kod.Przykład "1" znaczy że kod pochodzi od użytkownika o identyfikatorze 1
                            G_TESTS;
                                Identyfikador kodu TESTÓW, Czyli z jakiego projektu pochodzi kod.Przykład "1" znaczy że kod TESTÓW pochodzi od użytkownika o identyfikatorze 1
                            STATUS;
                                Status mutanta (NOT_STARTED(niepokryty)/KILLED(zabity)/SURVIVED(żywyw)/RUN_ERROR(Test uruchomiony z błędem [UWAGA MOZE SIE NIE POJAWIĆ])
                            TEST_USED;
                                Nazwa testu jaki testował mutanta
                            TEST_KILL;
                                Nazwa testu, który zabił mutanta w przypadku gdy mutant nie zostął wykryty wartosc będzie NONE
                            MUTANT_LINE;
                                Identyfikador mutanta numer lini
                            CLASS;
                                Identyfikador mutanta klas w jakiej sie znajduje
                            DESCRIPTION
                                Identyfikador mutanta opis mutacji na przykład "negated conditional" lub "replaced return of double value with -(x + 1) for com/uj/atm/common/Account::AccountStatus"
                            */
                            List<FromFileMetaData> tqedtempDatasLocal = FromFileMetaData.GetAllFromFileMetaDataFromDir(Paths.get(System.getProperty("user.dir"),"tqed").toString());
                            //IProjectMetaData tqedtempDataLocal =new FromFileMetaData();
                            //
                            //Petla start p wsyztkich plikach
                            for (FromFileMetaData tqedtempDataLocal: tqedtempDatasLocal)
                            {
                                for (FromFileMetaData data:tqedtempDataLocal.GetMetaDataAsAList()) {
                                    MutationRandomizerSingleton.SetBayes = false;
                                    MutationRandomizerSingleton.ActualClass =data.Classname;
                                    MutationRandomizerSingleton.FileName = tqedtempDataLocal.FileName;
                                    Instant start = Instant.now();
                                    _workerSerwer.RunnPitStandAlone(data);
                                    Instant end = Instant.now();
                                    strtqed += FileRaportGeneratorHelper.AppendRaport(tqedtempDataLocal, (FromFileMetaData) data, start, end);
                                }
                            }
                            //petla stop
                            Path path_TQED = Paths.get(System.getProperty("user.dir"), "Sonar");
                            File f_TQED = new File( path_TQED.toString(), "raport.txt");
                            BufferedWriter writer_TQED = new BufferedWriter(new FileWriter(f_TQED));
                            writer_TQED.write(strtqed);
                            writer_TQED.close();


                            File f_TQED_Detail = new File( path_TQED.toString(), "raport_detail.txt");
                            BufferedWriter writer_TQED_Detail = new BufferedWriter(new FileWriter(f_TQED_Detail));
                            writer_TQED_Detail.write(MutationRandomizerSingleton.DetailReportLog);
                            writer_TQED_Detail.close();

                            System.out.println("-----------------------------------------------------------");
                            System.out.println("      === *** --- PROCES ZAKONCZONY --- *** === ");
                            System.out.println("-----------------------------------------------------------");
                            break;


                        case "run mutation -pc": //Odpalanie pojedynczej instacji pita projekt pobierany z konfiga bez uwzglednienim bayesa

                            Calendar rightNow = Calendar.getInstance();
                            int hour = rightNow.get(Calendar.HOUR_OF_DAY);
                            int min = rightNow.get(Calendar.MINUTE);
                            int sec = rightNow.get(Calendar.SECOND);
                            Instant start = Instant.now();
                            Instant end = Instant.now();
                            String str = "CLASS;TIME;INFO;TEST_RAN;TEST_RAN_PER_MUT;KILLED;SURVIVED;NO_COVERAGE;TIMED_OUT;MEMORY_ERRORRUN_ERROR;RUN_ERROR;NON_VIABLE;NOT_STARTED;STARTED"+System.lineSeparator();


                                FromFileMetaData tempDataLocal_PC =new FromFileMetaData();
                                for (FromFileMetaData data:tempDataLocal_PC.GetMetaDataAsAList()) {
                                    try{
                                        System.out.println(".");
                                        System.out.println(".");
                                        System.out.println(".");
                                        System.out.println(".");
                                        System.out.println("-----------------------------------------------------------");
                                        System.out.println("MUTACJA Per class Dla: "+ data.Classname);
                                        System.out.println("-----------------------------------------------------------");
                                        hour = rightNow.get(Calendar.HOUR_OF_DAY);
                                        min = rightNow.get(Calendar.MINUTE);
                                        sec = rightNow.get(Calendar.SECOND);
                                       // MutationRandomizerSingleton.SetBayes = true;
                                        MutationRandomizerSingleton.ActualClass =data.Classname;
                                        start = Instant.now();
//your code

                                        _workerSerwer.RunnPitStandAlone(data);

                                        end = Instant.now();

                                        Duration timeElapsed = Duration.between(start, end);

                                        java.util.HashMap<DetectionStatus,Long> mapaDanych  = new HashMap<DetectionStatus, Long>();
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
                                                        mapaDanych.put(DetectionStatus.RUN_ERROR,mapaDanych.get(DetectionStatus.RUN_ERROR)+ status.getCount());
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
                                            LIne =";0;0;0;0;0;0;0;0;0";
                                        }

                                        LIne=";"+mapaDanych.get(DetectionStatus.KILLED)+
                                                ";"+mapaDanych.get(DetectionStatus.SURVIVED)+
                                                ";"+mapaDanych.get(DetectionStatus.NO_COVERAGE)+
                                                ";"+mapaDanych.get(DetectionStatus.MEMORY_ERROR)+
                                                ";"+mapaDanych.get(DetectionStatus.RUN_ERROR)+
                                                ";"+mapaDanych.get(DetectionStatus.NON_VIABLE)+
                                                ";"+mapaDanych.get(DetectionStatus.NOT_STARTED)+
                                                ";"+mapaDanych.get(DetectionStatus.STARTED);


                                        str +=""+ data.Classname +";"+timeElapsed.toMillis()+";OK"
                                                +";"+MutationRandomizerSingleton.TestRan
                                                +";"+MutationRandomizerSingleton.GlobalTestsPermut
                                                +LIne
                                                +System.lineSeparator();


                                    }catch (Exception e){
                                        str +=""+ data.Classname +";-1;ClassError;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1"+System.lineSeparator();
                                    }

                                }
                                //System.lineSeparator()


                            Path path = Paths.get(System.getProperty("user.dir"), "Sonar");
                            File f = new File( path.toString(), "raport.txt");
                            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
                            writer.write(str);

                            writer.close();




                            break;


                        case "run mutation -bayes -pc": //Odpalanie pojedynczej instacji pita projekt pobierany z konfiga z uwzglednienim bayesa

                           int Bayesit =0;
                           while(Bayesit<1){
                               FromFileMetaData tempDataLocal_bayes =new FromFileMetaData(true);
                               for (FromFileMetaData data:tempDataLocal_bayes.GetMetaDataAsAList()) {
                                   try{
                                       System.out.println(".");
                                       System.out.println(".");
                                       System.out.println(".");
                                       System.out.println(".");
                                       System.out.println("-----------------------------------------------------------");
                                       System.out.println("MUTACJA BAYES Dla: "+ data.Classname);
                                       System.out.println("-----------------------------------------------------------");
                                       MutationRandomizerSingleton.SetBayes = true;
                                       MutationRandomizerSingleton.ActualClass =data.Classname;
                                       _workerSerwer.RunnPitStandAlone(data);

                                   }catch (Exception e){}

                               }
                               Bayesit++;
                           }



                            break;

                        case "run mutation -bayes": //Odpalanie pojedynczej instacji pita projekt pobierany z konfiga z uwzglednienim bayesa
                            FromFileMetaData tempDataLocal_bayes2 =new FromFileMetaData(true);
                            MutationRandomizerSingleton.SetBayes = true;
                            MutationRandomizerSingleton.ActualClass ="alll project";
                            _workerSerwer.RunnPitStandAlone(tempDataLocal_bayes2);





                            break;
                        case "run mutation -i": //Metoda z wstryzkiwanym imputem hardcoded narazie
                            //ImputMetaData(String ClassesClassPatch, String TestClassPath, String DumpDir, String ClassesStringList){
                            IProjectMetaData tempDataFromIn =new ImputMetaData(
                                    "D:\\\\Doktorat\\\\PitPlayground\\\\IOgr602-master\\\\target\\\\test-classes\\\\,D:\\\\Doktorat\\\\PitPlayground\\\\IOgr602-master\\\\target\\\\classes\\\\",
                                    "D:\\\\Doktorat\\\\PitPlayground\\\\IOgr602-master\\\\",
                                    "D:\\\\trash\\\\",
                                    "matrixlibrary.IMatrixMathImpl, matrixlibrary.IMatrixImpl"
                            );
                            IProjectMetaData tempDataFromIn2 = new SimpleMetaDataToPropagate(tempDataFromIn.GetMetaDataList(), tempDataFromIn.GetClaspathAsAList());
                            _workerSerwer.SendToAllConnectedNodes("PitRun", tempDataFromIn2);
                            break;
                        case "Send":
                            System.out.println("Message: ");
                            String msg = _cnsl.readLine();
                            _workerSerwer.SendToAllConnectedNodes(msg, null);
                            break;
                        case "help":
                            System.out.println("Message: ");
                            System.out.println("ENG:");

                            System.out.println("Commands:");
                            System.out.println("1. 'test' Internal test not important.");
                            System.out.println("2. 'connect' System will ask you for ip address and port. After setting correct data system will connect to SAM Serwer. (After connection you can send star mutation request for all SAM  Servers).");
                            System.out.println("3. 'start' Run SAM System server on this machine. You will be ask for port.");
                            System.out.println("4. 'run mutation -broudcast' This command send start mutation request to all connected SAM severs.");


                            System.out.println("PL:");

                            System.out.println("Komendy:");
                            System.out.println("1. 'test' Wewnętrzny test sytemu nie istotne.");
                            System.out.println("2. 'connect' System zapyta cię o adres ip. i Port a następnie po podaniu prawidłowych danych połączy cie z nimi. (po połączeniu będziesz mógł wysłać żądanie rozpoczęcia testów mutacyjnych)");
                            System.out.println("3. 'start' Uruchamia serwer mutacyjny na. Należy podać port na jakim serwer będzie działał");
                            System.out.println("4. 'run mutation -broudcast' Ta komendy wysyła broadcastem wszystkim połączonym maszynom komunikat o prośbie rozpoczęcia mutacji.");
                            System.out.println("4. 'run mutation' Ta komendy uruchamia mutacje lokalnie tylko tu");
                            System.out.println("4. 'run mutation -i '  Ta komendy wysyła broadcastem wszystkim połączonym maszynom komunikat o prośbie rozpoczęcia mutacji. zarazem po i potrzeba podać okreslony immput przykład: ");

                            break;

                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            _isOn = false;
            System.out.println(CCS_stoped);
        }

    }


    public void killConsole(){
        synchronized (_lock) {
            _isOn = false;
        }
    }


    @Override
    public void startSerwer(int port) {
        _workerSerwer.Start(port);
    }

    @Override
    public void connectTo(String adress, int port) {
        _workerSerwer.ConnectSlaveNode(adress,port);
    }

    @Override
    public void runnPit(IProjectMetaData data) {

    }
}
