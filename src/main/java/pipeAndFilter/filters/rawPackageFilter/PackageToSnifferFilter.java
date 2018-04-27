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
import model.device.roles.Sniffer;
import packageBuilder.PackageBuilder;
import pipeAndFilter.Pipe;
import pipeAndFilter.impl.SimpleFilterImpl;

public class PackageToSnifferFilter extends SimpleFilterImpl<Package,Sniffer> {


    public PackageToSnifferFilter(Pipe<Package> inputPipe, Pipe<Sniffer> outputPipe) {
        super(inputPipe, outputPipe);


    }


    @Override
    public void transform(Pipe<Package> input, Pipe<Sniffer> output) {

        Package aPackage = input.retireve();
        if (aPackage != null)
          output.accept(aPackage.getSniffer());

    }

}
