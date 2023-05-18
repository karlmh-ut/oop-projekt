package org.forum.pipeline;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.forum.processors.server.RequestProcessor;
import org.forum.processors.server.RequestProcessorLoader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class Handler implements Runnable {
    private final DataInputStream dis;
    private final DataOutputStream dos;
    private final EntityManagerFactory entityManagerFactory;
    private final List<RequestProcessor> requestProcessors;
    private final Socket sock;



    public Handler(Socket sock, EntityManagerFactory entityManagerFactory) throws IOException {
        this.entityManagerFactory = entityManagerFactory;
        this.requestProcessors = RequestProcessorLoader.load();
        this.sock = sock;
        this.dis = new DataInputStream(this.sock.getInputStream());
        this.dos = new DataOutputStream(this.sock.getOutputStream());
    }

    @Override
    public void run() {
        try (sock; dis; dos) {
            while (!sock.isClosed()) {
                // Accept request
                System.out.println("Waiting for request...");
                int requestType = dis.readInt();
                System.out.printf("Recieved request %s, processing...%n", requestType);
                EntityManager entityManager = entityManagerFactory.createEntityManager();
                try { // Handle request
                    for (RequestProcessor requestProcessor : requestProcessors)
                        requestProcessor.process(entityManager, requestType, dis, dos, sock);
                } catch (Exception e) {
                    System.out.println(e.getMessage()); // We don't want to crash the server, so instead of throwing an error lets just log it
                } finally {
                    entityManager.close();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage()); // We don't want to crash the server, so instead of throwing an error lets just log it
        }
    }
}
