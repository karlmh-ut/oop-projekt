package org.forum.requests;

import org.forum.requestprocessor.RequestProcessor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import static org.forum.Client.INPUT_INT;


public class AuthProcessor implements RequestProcessor {
    @Override
    public void processRequest(DataInputStream dis, DataOutputStream dos) throws IOException {
        dos.writeInt(INPUT_INT);
        int response;
        while((response = dis.readInt()) != 0 && response != 1) {dos.writeInt(INPUT_INT);}

        if (response == 0) {
            logIn();
        } else {
            signIn();
        }
    }

    private void logIn() {
        System.out.println("User logged in");
    }

    private void signIn() {
        System.out.println("User created");
    }
}
