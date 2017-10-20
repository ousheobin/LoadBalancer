package org.steve.heaertbeat;

import java.io.IOException;
import java.net.ServerSocket;

public class NetworkListener implements Runnable {

	private int listenPort = 9996;
	private ClientStatus clientStatus;

	public NetworkListener(ClientStatus clientStatus) {
		super();
		this.clientStatus = clientStatus;
	}

	public NetworkListener(int listenPort, ClientStatus clientStatus) {
		super();
		this.listenPort = listenPort;
		this.clientStatus = clientStatus;
	}

	@Override
	public void run() {
		try {
			ServerSocket sc = new ServerSocket(listenPort);
			while (clientStatus.isRun()) {
				Thread pro = new Thread(new SocketProcessor(sc.accept(), clientStatus));
				pro.start();
			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
