package pipeAndFilter.filters.rawPackageFilter;

import model.Package;
import model.device.roles.Sniffer;
import pipeAndFilter.Pipe;
import pipeAndFilter.impl.SimpleFilterImpl;

public class PackageBySnifferFilter extends SimpleFilterImpl<Package,Package> {


    private Sniffer sniffer;


    public PackageBySnifferFilter(Pipe<Package> inputPipe, Pipe<Package> outputPipe, Sniffer sniffer) {
        super(inputPipe, outputPipe);
        this.sniffer = sniffer;

    }



    @Override
    public void transform(Pipe<Package> input, Pipe<Package> output) {

        Package pack = input.retireve();
        if(pack!=null)
            if (pack.getSniffer().equals(sniffer))
                output.accept(pack);


    }





}