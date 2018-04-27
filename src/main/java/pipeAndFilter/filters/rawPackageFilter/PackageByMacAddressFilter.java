package pipeAndFilter.filters.rawPackageFilter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import model.Package;
import model.TimeFrame;
import model.TimeStamp;
import model.device.MacAddress;
import packageBuilder.PackageBuilder;
import pipeAndFilter.Pipe;
import pipeAndFilter.impl.SimpleFilterImpl;

public class PackageByMacAddressFilter extends SimpleFilterImpl<Package,Package> {


    private MacAddress macAddress;


    public PackageByMacAddressFilter(Pipe<Package> inputPipe, Pipe<Package> outputPipe, MacAddress macAddress) {
        super(inputPipe, outputPipe);
        this.macAddress = macAddress;

    }



    @Override
    public void transform(Pipe<Package> input, Pipe<Package> output) {

        Package pack = input.retireve();
        if(pack!=null)
            if (pack.getMacAddress().equals(macAddress))
                output.accept(pack);


    }





}