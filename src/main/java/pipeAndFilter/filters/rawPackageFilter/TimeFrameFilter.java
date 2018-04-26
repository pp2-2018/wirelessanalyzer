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

public class TimeFrameFilter extends SimpleFilterImpl<Package,Package> {

    private List<Package> rawPackages;
    private TimeFrame timeFrame;


    public TimeFrameFilter(Pipe<Package> inputPipe, Pipe<Package> outputPipe, TimeFrame timeFrame) {
        super(inputPipe, outputPipe);

        this.rawPackages = new ArrayList<>();
        this.timeFrame=timeFrame;
}



    @Override
    public void transform(Pipe<Package> input, Pipe<Package> output) {

        Package pack = input.retireve();
        System.out.println(pack);
        if(pack!=null) {

            if (timeFrame.contains(pack.getTimeStamp()))
                output.accept(pack);
        }

    }





}
