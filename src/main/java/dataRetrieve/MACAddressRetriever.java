package dataRetrieve;

import model.Captura;
import model.Package;
import model.device.MacAddress;
import model.device.roles.Sniffer;

import java.util.ArrayList;
import java.util.List;

public class MACAddressRetriever {

    private List<Captura> captures;

    public MACAddressRetriever(List<Captura> captures) {
        this.captures = captures;
    }

    public List<Sniffer> retrieveByScannedDevice(MacAddress address){
        List<Sniffer> sniffers = new ArrayList<>();

        for(Captura capture : captures)
            for (Package pkg : capture.getPaquetes()) {
                //TODO aca hay que hacer algo para evitar este if
                if (address.equals(pkg.getMacAddress()))
                    sniffers.add(capture.getSniffer());
            }

        return sniffers;
    }
}
