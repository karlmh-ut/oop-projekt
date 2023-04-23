package org.forum.pipeline;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.forum.processors.RequestProcessor;
import org.forum.processors.RequestProcessorLoader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import static org.forum.pipeline.Client.INPUT_INT;


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
                dos.writeInt(INPUT_INT);
                int requestType = dis.readInt();
                EntityManager entityManager = entityManagerFactory.createEntityManager();
                try {
                    for (RequestProcessor requestProcessor : requestProcessors) {
                        requestProcessor.process(entityManager, requestType, dis, dos);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    entityManager.close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
