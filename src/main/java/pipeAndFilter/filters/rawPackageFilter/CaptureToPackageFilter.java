package pipeAndFilter.filters.rawPackageFilter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import model.Capture;
import model.Package;
import model.TimeFrame;
import model.TimeStamp;
import model.device.MacAddress;
import packageBuilder.PackageBuilder;
import pipeAndFilter.Pipe;
import pipeAndFilter.impl.SimpleFilterImpl;

public class CaptureToPackageFilter extends SimpleFilterImpl<Capture,Package> {





    public CaptureToPackageFilter(Pipe<Capture> inputPipe, Pipe<Package> outputPipe) {
        super(inputPipe, outputPipe);


    }


    @Override
    public void transform(Pipe<Capture> input, Pipe<Package> output) {

        Capture capture = input.retireve();
        if (capture != null)
            for (Package pack : capture.getPackages()
                    ) {
                pack.setSniffer(capture.getSniffer());
                output.accept(pack);
            }



    }

}
