package view;

import java.net.Socket;

import control.ControlBank;
import control.ControlThreadServerTcp;
import control.ControlThreadServerUdp;
import control.ControlThreadTcp;
import model.Bank;
import protocol.Tcp;
import protocol.TcpServer;
import protocol.UdpServer;

/**
 * Classe principale de lancement des serveurs.
 * @author OLIVIER Thomas
 *
 */
public class Server {
	
	public static void main(String[] args) {
		ControlBank controlBank = new ControlBank(new Bank());
		TcpServer tcpServer = new TcpServer(12234);
		UdpServer udpServer = new UdpServer(12235);
		ControlThreadServerTcp serverTcp = new ControlThreadServerTcp(tcpServer, controlBank);
		ControlThreadServerUdp serverUdp = new ControlThreadServerUdp(udpServer, controlBank);
		serverTcp.start();
		serverUdp.start();
	}

}
