package org.pitest.extensions;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Michał Mnich  on 27.08.2016.
 */
public class MutationConfig {


   private LinkedHashMap<String, MutantCofig> mutatorConfig = new LinkedHashMap<String, MutantCofig>();
   private int _mutationMode=0;

    public void readConfig(File fin) throws IOException {
        // Construct BufferedReader from FileReader
        BufferedReader br = new BufferedReader(new FileReader(fin));

        String line = null;
        boolean isConfigBegined = false;
        while ((line = br.readLine()) != null) {
            //System.out.println(line);
            if(line.equals("<HeareConfigStarts>")) isConfigBegined = true;
            if( !line.equals("") && (isConfigBegined && !(line.substring(0,2).equals("//"))) ){ //substring ma złożoność  O(n)
                if(line.contains("INVERT_NEGS")) { mutatorConfig.put("INVERT_NEGS", new MutantCofig(Double.parseDouble(line.split(";")[1]), Integer.parseInt(line.split(";")[2]), Double.parseDouble(line.split(";")[3]), Double.parseDouble(line.split(";")[4])) );}
                else if(line.contains("RETURN_VALS")) { mutatorConfig.put("RETURN_VALS", new MutantCofig(Double.parseDouble(line.split(";")[1]), Integer.parseInt(line.split(";")[2]), Double.parseDouble(line.split(";")[3]), Double.parseDouble(line.split(";")[4])) );}
                else if(line.contains( "INLINE_CONSTS")) { mutatorConfig.put("INLINE_CONSTS", new MutantCofig(Double.parseDouble(line.split(";")[1]), Integer.parseInt(line.split(";")[2]), Double.parseDouble(line.split(";")[3]), Double.parseDouble(line.split(";")[4])) );}
                else if(line.contains("MATH")) { mutatorConfig.put("MATH", new MutantCofig(Double.parseDouble(line.split(";")[1]), Integer.parseInt(line.split(";")[2]), Double.parseDouble(line.split(";")[3]), Double.parseDouble(line.split(";")[4])) );}
                else if(line.contains("VOID_METHOD_CALLS")) { mutatorConfig.put("VOID_METHOD_CALLS", new MutantCofig(Double.parseDouble(line.split(";")[1]), Integer.parseInt(line.split(";")[2]), Double.parseDouble(line.split(";")[3]), Double.parseDouble(line.split(";")[4])) );}
                else if(line.contains("NEGATE_CONDITIONALS")) { mutatorConfig.put("NEGATE_CONDITIONALS", new MutantCofig(Double.parseDouble(line.split(";")[1]), Integer.parseInt(line.split(";")[2]), Double.parseDouble(line.split(";")[3]), Double.parseDouble(line.split(";")[4])) );}
                else if(line.contains("CONDITIONALS_BOUNDARY")) { mutatorConfig.put("CONDITIONALS_BOUNDARY", new MutantCofig(Double.parseDouble(line.split(";")[1]), Integer.parseInt(line.split(";")[2]), Double.parseDouble(line.split(";")[3]), Double.parseDouble(line.split(";")[4])) );}
                else if(line.contains("INCREMENTS")) { mutatorConfig.put("INCREMENTS", new MutantCofig(Double.parseDouble(line.split(";")[1]), Integer.parseInt(line.split(";")[2]),Double.parseDouble(line.split(";")[3]), Double.parseDouble(line.split(";")[4])) );}
                else if(line.contains("REMOVE_INCREMENTS")) { mutatorConfig.put("REMOVE_INCREMENTS", new MutantCofig(Double.parseDouble(line.split(";")[1]), Integer.parseInt(line.split(";")[2]), Double.parseDouble(line.split(";")[3]), Double.parseDouble(line.split(";")[4])) );}
                else if(line.contains("NON_VOID_METHOD_CALLS")) { mutatorConfig.put("NON_VOID_METHOD_CALLS", new MutantCofig(Double.parseDouble(line.split(";")[1]), Integer.parseInt(line.split(";")[2]), Double.parseDouble(line.split(";")[3]), Double.parseDouble(line.split(";")[4])) );}
                else if(line.contains("CONSTRUCTOR_CALLS")) { mutatorConfig.put("CONSTRUCTOR_CALLS", new MutantCofig(Double.parseDouble(line.split(";")[1]), Integer.parseInt(line.split(";")[2]), Double.parseDouble(line.split(";")[3]), Double.parseDouble(line.split(";")[4])) );}
                else if(line.contains("REMOVE_CONDITIONALS_EQ_IF")) { mutatorConfig.put("REMOVE_CONDITIONALS_EQ_IF", new MutantCofig(Double.parseDouble(line.split(";")[1]), Integer.parseInt(line.split(";")[2]), Double.parseDouble(line.split(";")[3]),Double.parseDouble(line.split(";")[4])) );}
                else if(line.contains("REMOVE_CONDITIONALS_EQ_ELSE")) { mutatorConfig.put("REMOVE_CONDITIONALS_EQ_ELSE", new MutantCofig(Double.parseDouble(line.split(";")[1]), Integer.parseInt(line.split(";")[2]), Double.parseDouble(line.split(";")[3]), Double.parseDouble(line.split(";")[4])) );}
                else if(line.contains("REMOVE_CONDITIONALS_ORD_IF")) { mutatorConfig.put("REMOVE_CONDITIONALS_ORD_IF", new MutantCofig(Double.parseDouble(line.split(";")[1]), Integer.parseInt(line.split(";")[2]), Double.parseDouble(line.split(";")[3]), Double.parseDouble(line.split(";")[4])) );}
                else if(line.contains("REMOVE_CONDITIONALS_ORD_ELSE")) { mutatorConfig.put("REMOVE_CONDITIONALS_ORD_ELSE", new MutantCofig(Double.parseDouble(line.split(";")[1]), Integer.parseInt(line.split(";")[2]), Double.parseDouble(line.split(";")[3]), Double.parseDouble(line.split(";")[4])) );}
                else if(line.contains("REMOVE_CONDITIONALS")) { mutatorConfig.put("REMOVE_CONDITIONALS", new MutantCofig(Double.parseDouble(line.split(";")[1]), Integer.parseInt(line.split(";")[2]), Double.parseDouble(line.split(";")[3]), Double.parseDouble(line.split(";")[4])) );}
                else if(line.contains("EXPERIMENTAL_MEMBER_VARIABLE")) { mutatorConfig.put("EXPERIMENTAL_MEMBER_VARIABLE", new MutantCofig(Double.parseDouble(line.split(";")[1]), Integer.parseInt(line.split(";")[2]), Double.parseDouble(line.split(";")[3]), Double.parseDouble(line.split(";")[4])) );}
                else if(line.contains("EXPERIMENTAL_SWITCH")) { mutatorConfig.put("EXPERIMENTAL_SWITCH", new MutantCofig(Double.parseDouble(line.split(";")[1]), Integer.parseInt(line.split(";")[2]), Double.parseDouble(line.split(";")[3]), Double.parseDouble(line.split(";")[4])) );}
                else if(line.contains("EXPERIMENTAL_ARGUMENT_PROPAGATION")) { mutatorConfig.put("EXPERIMENTAL_ARGUMENT_PROPAGATION", new MutantCofig(Double.parseDouble(line.split(";")[1]), Integer.parseInt(line.split(";")[2]), Double.parseDouble(line.split(";")[3]), Double.parseDouble(line.split(";")[4])) );}

                //INNE zmienne srodowiskowe konfigurujace oprogramowanie -------------------------------------------------
                else if(line.contains("MUTATION_MODE")){_mutationMode= Integer.parseInt(line.split(";")[1]);}
            }

        }

        br.close();
    }

    /*

    <HeareConfigStarts>

INVERT_NEGS;0.5;100
//od tego mejsce zmienne srodowiskowe dla pita
MUTATION_MODE;1
    * */
    public void UpdateConfig(){
        BufferedWriter writer = null;

        Path path = Paths.get(System.getProperty("user.dir"), "Bayes");
        File f = new File( path.toString(), "mutatorConfigBayes.ini");
        try
        {
            String s = "<HeareConfigStarts>"+System.getProperty("line.separator");;
            for (Map.Entry<String, MutantCofig> mutatorConfig :this.mutatorConfig.entrySet()) {
            s+= mutatorConfig.getKey()+";"+mutatorConfig.getValue().Probabilty+";"+mutatorConfig.getValue().Scale+ ";"+mutatorConfig.getValue().Alpha+";"+mutatorConfig.getValue().Beta      +System.getProperty("line.separator");;
            }
            s+="MUTATION_MODE;1";
            writer = new BufferedWriter( new FileWriter( f));
            writer.write( s);

        }
        catch ( IOException e)
        {
        }
        finally
        {
            try
            {
                if ( writer != null)
                    writer.close( );
            }
            catch ( IOException e)
            {
            }
        }
    }

    public double GetMutatroAlpha(String key){
        return mutatorConfig.get(key).Alpha;
    }
    public double GetMutatroBeta(String key){
        return mutatorConfig.get(key).Beta;
    }


    public int MutationMode(){
        return _mutationMode;
    }

    public int GetMutatroScale(String key){
        return mutatorConfig.get(key).Scale;
    }

    public boolean IsMutantKeyExist(String key){
        if(mutatorConfig.containsKey(key))return true;
        else return false;
    }
    ///Ta mettoda pobiera nam prawdopodobienstwo zgone z skala
    //poniewaz losowanie odbywa sie na zasadzie next int >< od prawdopodobienstwa to: wartosc prawdopodobeinstwa musi odpowiadac skali
    //to tez np 0.5 dla skali 100 to bedzei 50 dla skali 1000 to 500 czyli next int dla skali 100 bedzie musial byc wiekszy niz 50 zeby uznac ze wylosowani prawde.
    public int GetMutatorProbabilty(String key){
        return (int) (mutatorConfig.get(key).Scale - (mutatorConfig.get(key).Scale* mutatorConfig.get(key).Probabilty));
        //wartosc musi byc wieksza od  1 - prawdopodobienstwo bo liczone jest prze > next int czyli jak coś ma 0.4 szansy to loswanie musi byc wieksze niz 0.6
    }

    public double GetMutatorProbabiltyVal(String key){
        return  (mutatorConfig.get(key).Probabilty);

    }
    public void SetMutatorProbabilty(String key, double Probabilty, int Scale, double alpha, double betha){
        if(IsMutantKeyExist(key)){
            mutatorConfig.get(key).Scale  = Scale;
            mutatorConfig.get(key).Probabilty  = Probabilty;
            mutatorConfig.get(key).Alpha  = alpha;
            mutatorConfig.get(key).Beta  = betha;
        }
       else{
            mutatorConfig.put(key, new MutantCofig(Probabilty, Scale,  alpha,  betha));
        }


    }

    private class MutantCofig{

        public MutantCofig(double probabilty, int scale, double alpha, double betha){
            this.Probabilty = probabilty;
            this.Scale = scale;
            this.Alpha = alpha;
            this.Beta = betha;
        }
        public double Probabilty;
        public int Scale;
        public double Alpha;
        public double Beta;

    }

}
