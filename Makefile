C=javac

all:
	javac model/Account.java
	javac model/Bank.java

	javac protocol/Tcp.java
	javac protocol/TcpServer.java
	javac protocol/TcpClient.java
	javac protocol/Udp.java
	javac protocol/UdpServer.java
	javac protocol/UdpClient.java

	javac control/ControlBank.java
	javac control/ControlThreadTcp.java
	javac control/ControlThreadServerTcp.java
	javac control/ControlThreadServerUdp.java

	javac view/Server.java
	javac view/ClientTcp.java
	javac view/ClientUdp.java

clean:
	rm -rf model/*.class
	rm -rf protocol/*.class
	rm -rf control/*.class
	rm -rf view/*.class
