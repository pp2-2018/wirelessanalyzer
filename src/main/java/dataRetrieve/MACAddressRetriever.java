package dataRetrieve;

import model.Capture;
import model.Package;
import model.device.MacAddress;
import model.device.roles.Sniffer;

import java.util.ArrayList;
import java.util.List;

public class MACAddressRetriever {

    private List<Capture> captures;

    public MACAddressRetriever(List<Capture> captures) {
        this.captures = captures;
    }

    public List<Sniffer> retrieveByScannedDevice(MacAddress address){
        List<Sniffer> sniffers = new ArrayList<>();

        for(Capture capture : captures)
            for (Package pkg : capture.getPackages()) {
                //TODO aca hay que hacer algo para evitar este if
                if (address.equals(pkg.getMacAddress()))
                    sniffers.add(capture.getSniffer());
            }

        return sniffers;
    }
}
