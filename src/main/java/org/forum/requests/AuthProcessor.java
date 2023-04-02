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
        System.out.println("Are you an existing user or do you want to create a user profile?");
        System.out.println("0 - Log In\t1 - Sign In");
        while((response = dis.readInt()) != 0 && response != 1) {dos.writeInt(INPUT_INT);}

        if (response == 0) {
            logIn();
        } else {
            signIn();
        }
    }

    private void logIn() {

    }

    private void signIn() {

    }
}
