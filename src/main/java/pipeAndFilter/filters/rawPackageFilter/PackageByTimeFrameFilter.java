package pipeAndFilter.filters.rawPackageFilter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import model.Package;
import model.TimeFrame;
import model.TimeStamp;
import packageBuilder.PackageBuilder;
import pipeAndFilter.Pipe;
import pipeAndFilter.impl.SimpleFilterImpl;
import pipeAndFilter.parameters.Parametrized;

public class PackageByTimeFrameFilter extends SimpleFilterImpl<Package,Package> {

    private TimeFrame timeFrame;

    public PackageByTimeFrameFilter(Pipe<Package> inputPipe, Pipe<Package> outputPipe, TimeFrame timeFrame) {
        super(inputPipe, outputPipe);
        this.timeFrame=timeFrame;
}



    @Override
    public void transform(Pipe<Package> input, Pipe<Package> output) {

        Package pack = input.retireve();
        if(pack!=null)

            if (timeFrame.contains(pack.getTimeStamp()))
                output.accept(pack);


    }





}
