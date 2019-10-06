import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Firewall {

	Set<Rules> inboundTcp;
	Set<Rules> outboundTcp;
	Set<Rules> inboundUdp;
	Set<Rules> outboundUdp;

    /**
     * intializes private variables and calls the readFile function
     * @param pathname path of the file
     * @throws IOException
     */
	public Firewall(String pathname) throws IOException {
        inboundTcp = new HashSet<>();
        outboundTcp = new HashSet<>();
        inboundUdp = new HashSet<>();
        outboundUdp = new HashSet<>();
        readFile(pathname);
    }

    /**
     * reads the data from the file and puts it in the appropriate set
     * @param pathname path of the file
     * @throws IOException
     */
    private void readFile(String pathname) throws IOException {
        File f = new File(pathname);
        FileReader freader = new FileReader(f);
        BufferedReader reader = new BufferedReader(freader);
        String rule;
        while((rule = reader.readLine()) != null){
            String [] column = rule.split(",");
            if(column[0].equals("inbound")){
                if(column[1].equals("tcp")){
                    inboundTcp.add(new Rules(column[2],column[3]));
                }
                else{
                    inboundUdp.add(new Rules(column[2],column[3]));
                }
            }
            else{
                if(column[1].equals("tcp")){
                    outboundTcp.add(new Rules(column[2],column[3]));
                }
                else{
                    outboundUdp.add(new Rules(column[2],column[3]));
                }
            }
        }
    }

    /**
     * checks if the packet should be accepted through firewall
     * @param direction the direction of the packet
     * @param protocol  the protocol of the packet
     * @param port  the port of the packet
     * @param ip_address    the ipaddress of the packet
     * @return  true if the packet should be accepted and false if the packet should be rejected
     */
    public boolean accept_packet(String direction, String protocol, int port, String ip_address) {
        Rules packet = new Rules(port+"", ip_address);
	    if(direction.equals("inbound")){
            if(protocol.equals("tcp"))
               return scanPacketWithInboundTcp(packet);
            else
               return scanPacketWithInboundUdp(packet);
        }
	    else{
            if(protocol.equals("tcp"))
               return scanPacketWithOutboundTcp(packet);
            else
               return scanPacketWithOutboundUdp(packet);
        }
    }

    /**
     * Scans the packet with the inbound tcp set
     * @param packet the packet that needs to be checked
     * @return true if the packet should be accepted else false
     */
    private boolean scanPacketWithInboundTcp(Rules packet){
        Iterator iter = inboundTcp.iterator();
        while (iter.hasNext()) {
            if (packet.equals(iter.next())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Scans the packet with the inbound udp set
     * @param packet the packet that needs to be checked
     * @return true if the packet should be accepted else false
     */
    private boolean scanPacketWithInboundUdp(Rules packet){
        Iterator iter = inboundUdp.iterator();
        while (iter.hasNext()) {
            if (packet.equals(iter.next())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Scans the packet with the outbound tcp set
     * @param packet the packet that needs to be checked
     * @return true if the packet should be accepted else false
     */
    private boolean scanPacketWithOutboundTcp(Rules packet){
        Iterator iter = outboundTcp.iterator();
        while (iter.hasNext()) {
            if (packet.equals(iter.next())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Scans the packet with the outbound udp set
     * @param packet the packet that needs to be checked
     * @return true if the packet should be accepted else false
     */
    private boolean scanPacketWithOutboundUdp(Rules packet) {
        Iterator iter = outboundUdp.iterator();
        while (iter.hasNext()) {
            if (packet.equals(iter.next())) {
                return true;
            }
        }
        return false;
    }
}
