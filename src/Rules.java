import java.util.Objects;

public class Rules {
    private int portStart;
    private long ipStart;
    private int portEnd;
    private long ipEnd;

    public Rules(String port, String ip){
        parsePort(port);
        parseIp(ip);
    }

    private void parsePort(String port){
        if(port.contains("-")){
            String [] ports = port.split("-");
            portStart = Integer.parseInt(ports[0]);
            portEnd = Integer.parseInt(ports[1]);
        }
        else{
            portStart = Integer.parseInt(port);
            portEnd = portStart;
        }
    }

    private void parseIp(String ip){
        if(ip.contains("-")){
            String [] ips = ip.split("-");
            ipStart = Long.parseLong(ips[0].replace(".",""));
            ipEnd = Long.parseLong(ips[1].replace(".",""));
        }
        else{
            ipStart = Long.parseLong(ip.replace(".",""));
            ipEnd = ipStart;
        }
    }



    @Override
    public boolean equals(Object other){
        if (other == null)
            return false;
        if (this.getClass() != other.getClass())
            return false;
        Rules o = (Rules) other;
        return (o.portStart<=portStart && o.portEnd>=portEnd && o.ipStart <= ipStart && o.ipEnd >= ipEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(portStart, portEnd, ipStart, ipEnd);
    }
}
