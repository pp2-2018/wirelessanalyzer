import pipeAndFilter.filters.StringParameterFilter;
import pipeAndFilter.filters.StringSintaxFilter;
import pipeAndFilter.impl.QueuePipe;

public class WirelessAnalyzer {


        public static void main(String... args) {
              QueuePipe<String>  in=new QueuePipe<>();
              QueuePipe<String> out=new QueuePipe<>();
              QueuePipe<String> out2=new QueuePipe<>();
              StringSintaxFilter stringSintaxFilter = new StringSintaxFilter(in,out);
              StringParameterFilter stringParameterFilter = new StringParameterFilter(out,out2);
            for (String string:
                 args) {
                in.accept(string);
                stringSintaxFilter.process();
                stringParameterFilter.process();

            }

            if(out.isEmpty()){
                System.out.println("Syntax Error");
            }
            else{
                while(!out.isEmpty()){
                    System.out.println("Syntax Correct: "+out.retireve());
                }

            }
            if(out2.isEmpty()){
                System.out.println("Not one filterter with Parameter");
            }
            else{
                while(!out2.isEmpty()){
                    System.out.println("Parameters Detected: "+out2.retireve());
                }

            }


        }


}
