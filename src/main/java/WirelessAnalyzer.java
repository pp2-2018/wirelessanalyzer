import pipeAndFilter.filters.StringSintaxFilter;
import pipeAndFilter.impl.QueuePipe;

public class WirelessAnalyzer {


        public static void main(String... args) {
              QueuePipe<String>  in=new QueuePipe<>();
              QueuePipe<String> out=new QueuePipe<>();
               StringSintaxFilter stringSintaxFilter = new StringSintaxFilter(in,out);
            for (String string:
                 args) {
                in.accept(string);
                stringSintaxFilter.process();

            }

            if(out.isEmpty()){
                System.out.println("Syntax Error");
            }
            else{
                while(!out.isEmpty()){
                    System.out.println("Syntax Correct: "+out.retireve());
                }

            }


        }


}
