package org.pitest.mutationtest.sam.config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by Michał Mnich on 25.10.2016.
 */
public class FromFileMetaData implements IProjectMetaData {
    private List<String> arguments;
    private List<String> _getClaspathAsAList;
    private List<FromFileMetaData> _confList;
    public String Classname="";
    public String FileName ="";
    public FromFileMetaData() {
        this(System.getProperty("user.dir"), "metadata.ini");
    }

    public FromFileMetaData(boolean bayes) {
        this(System.getProperty("user.dir"), "metadata.ini", bayes);
    }
    public FromFileMetaData(List<String> Arguments, List<String> GetClaspathAsAList, String classname) {
        arguments =Arguments;
        _getClaspathAsAList =GetClaspathAsAList;
        Classname =classname;
    }

    public FromFileMetaData(String path) {
        this(System.getProperty("user.dir"), "metadata.ini");
    }

    public FromFileMetaData(String dir, String configFile)  {
        try {

            File f =new File(dir,configFile);
            FileName = f.getName();
            if(f.isFile()){
                BufferedReader br = new BufferedReader(new FileReader(f));

                String line = null;
                arguments = new ArrayList<String>();
                _getClaspathAsAList = new ArrayList<String>();

                int i =0;

                    while ((line = br.readLine()) != null) {
                        arguments.add(line);
                       // System.out.println(arguments.get(i).getClass());
                        if(i == 1){
                            if(!line.equals("")){

                                String[] ClassPaths =  line.split(",");
                                if(ClassPaths.length>0 && !ClassPaths[0].equals(""))_getClaspathAsAList.add(ClassPaths[0]);
                                if(ClassPaths.length>1 && !ClassPaths[1].equals("")) _getClaspathAsAList.add(ClassPaths[1]);
                            }
                        }
                        i++;
                    }

                    // lista kalsss z pliczku -------------------------------
                String classes ="";
                String classes2 ="";
                List<FromFileMetaData> confList = new ArrayList<>();
                int j=0;
                int k=0;
                for (String arg : arguments ) {
                    j++;

                    if(arg.equals("--targetClasses")){
                        classes=  arguments.get(j);
                        break;
                    }
                }
                for (String arg : arguments ) {

                    k++;

                    if(arg.equals("--reportDir")){
                        classes2=  arguments.get(k);
                        break;
                    }
                }

                for (String cls:classes.split(",")) {
                    List<String> arg= new ArrayList<String>(arguments) ;
                    List<String> clspath=new ArrayList<String>(_getClaspathAsAList);
                    arg.set(j, cls);
                    arg.set(k, arg.get(k)+"\\\\"+cls);
                    confList.add(new FromFileMetaData(arg, clspath, cls));
                }
                _confList =confList;
                    // lista kalsss z pliczku -------------------------------


            }else{
                System.out.println("ERROR: There is no ini file: "+ f.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public FromFileMetaData(String dir, String configFile, boolean bayes)  {
        try {
            File f =new File(dir,configFile);
            if(f.isFile()){
                BufferedReader br = new BufferedReader(new FileReader(f));

                String line = null;
                arguments = new ArrayList<String>();
                _getClaspathAsAList = new ArrayList<String>();

                int i =0;
                while ((line = br.readLine()) != null) {
                    arguments.add(line);
                    // System.out.println(arguments.get(i).getClass());
                    if(i == 1){
                        if(!line.equals("")){

                            _getClaspathAsAList.add(line.split(",")[0]);
                            _getClaspathAsAList.add(line.split(",")[1]);
                        }
                    }
                    i++;

                }

                if(bayes){
                    String classes ="";
                    String classes2 ="";
                    List<FromFileMetaData> confList = new ArrayList<>();
                    int j=0;
                    int k=0;
                    for (String arg : arguments ) {
                        j++;

                        if(arg.equals("--targetClasses")){
                            classes=  arguments.get(j);
                            break;
                        }
                    }
                        for (String arg : arguments ) {

                            k++;

                        if(arg.equals("--reportDir")){
                            classes2=  arguments.get(k);
                            break;
                        }
                    }

                    for (String cls:classes.split(",")) {
                         List<String> arg= new ArrayList<String>(arguments) ;
                         List<String> clspath=new ArrayList<String>(_getClaspathAsAList);
                        arg.set(j, cls);
                        arg.set(k, arg.get(k)+"\\\\"+cls);
                        confList.add(new FromFileMetaData(arg, clspath, cls));
                    }
                    _confList =confList;
                }

            }else{
                System.out.println("ERROR: There is no ini file: "+ f.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public String[] GetMetaData() {
        return  arguments.toArray(new String[arguments.size()]);
    }

    @Override
    public List<String> GetMetaDataList() {
        return arguments;
    }

    @Override
    public List<String> GetClaspathAsAList(){

        return _getClaspathAsAList;
    }

    @Override
    public List<FromFileMetaData> GetMetaDataAsAList() {
        return _confList;
    }

    public static List<FromFileMetaData> GetAllFromFileMetaDataFromDir(String MetadatasDir)
    {
        List<FromFileMetaData> ToReturn  = new ArrayList<FromFileMetaData>();
        for (String filename: listFilesUsingJavaIO(MetadatasDir).stream().sorted().collect(Collectors.toList()))
        {
            ToReturn.add(new FromFileMetaData(MetadatasDir, filename));
        }


        return ToReturn;
    }

    private static Set<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

}
