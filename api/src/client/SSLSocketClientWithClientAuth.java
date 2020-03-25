package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.logging.Logger;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/*
* This example shows how to set up a key manager to do client
* authentication if required by server.
*
* This program assumes that the client is not inside a firewall.
* The application can be modified to connect to a server outside
* the firewall by following SSLSocketClientWithTunneling.java.
*/
public class SSLSocketClientWithClientAuth {

	final Logger log = Logger.getLogger(SSLSocketClientWithClientAuth.class.getName());

	void login(String host, int port, String path) throws IOException {

		/*
		 * Set up a key manager for client authentication if asked by the server. Use the implementation's default TrustStore and secureRandom routines.
		 */
		SSLSocketFactory factory = null;

		try {

			char[] passphrase = "B9cMw7qX".toCharArray();

			SSLContext ctx = SSLContext.getInstance("TLS");
			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
			KeyStore ks = KeyStore.getInstance("JKS");

			ks.load(new FileInputStream("keystore.jks"), passphrase);

			kmf.init(ks, passphrase);

			TrustManager[] tm = { new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					log.info("getAcceptedIssuers");
					return null;
				}
				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					log.info(authType);
				}
				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					log.info(authType);
				}
			} };
			ctx.init(kmf.getKeyManagers(), tm, null);

			factory = ctx.getSocketFactory();

		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}

		SSLSocket socket = (SSLSocket) factory.createSocket(host, port);

		/*
		 * send http request
		 *
		 * See SSLSocketClient.java for more information about why there is a forced handshake here when using PrintWriters.
		 */
		socket.startHandshake();

		log.info("startHandshake");

		
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
		out.println("GET " + path + " HTTP/1.0");
		out.println();
		out.flush();
		log.info("flush");

		/*
		 * Make sure there were no surprises
		 */
		if (out.checkError())
			log.info("SSLSocketClient: java.io.PrintWriter error");


		/* read response */
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		String inputLine;

		while ((inputLine = in.readLine()) != null)
			log.info(inputLine);


		log.info("finish");
		
		in.close();
		out.close();
		socket.close();
	}

	public static void main(String[] args) throws Exception {
		try {
			new SSLSocketClientWithClientAuth().login("localhost", 8443, "/login2");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}