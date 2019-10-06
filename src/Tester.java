import static org.junit.Assert.*;
import org.junit.Test;

import java.io.IOException;

public class Tester {
    @Test
    public void packageTester() throws IOException {
        Firewall fw = new Firewall("./rules.csv");
        assertTrue(fw.accept_packet("outbound", "udp",1000,"52.12.48.92"));
        assertTrue(fw.accept_packet("outbound", "tcp", 20000, "192.168.10.11"));
        assertTrue(fw.accept_packet("outbound", "tcp", 10000, "192.168.10.11"));
        assertTrue(fw.accept_packet("outbound", "tcp", 15000, "192.168.10.11"));
        assertFalse(fw.accept_packet("outbound", "ucp", 3000, "192.168.10.11"));
        assertTrue(fw.accept_packet("outbound", "tcp", 700, "210.56.192.101"));
        assertTrue(fw.accept_packet("outbound", "tcp", 300, "210.56.192.100"));
        assertTrue(fw.accept_packet("outbound", "tcp", 400, "210.56.192.200"));
        assertTrue(fw.accept_packet("outbound", "tcp", 335, "210.56.192.150"));
        assertFalse(fw.accept_packet("outbound", "tcp", 400, "210.56.192.99"));
        assertFalse(fw.accept_packet("outbound", "tcp", 901, "210.56.192.400"));
        assertFalse(fw.accept_packet("outbound", "tcp", 500, "210.56.182.501"));
        assertTrue(fw.accept_packet("inbound", "udp", 55, "198.59.43.26"));
        assertFalse(fw.accept_packet("inbound", "udp", 49, "198.59.43.22"));
        assertFalse(fw.accept_packet("inbound", "udp", 81, "198.59.43.22"));
        assertFalse(fw.accept_packet("inbound", "udp", 80, "198.59.43.91"));
    }
}
