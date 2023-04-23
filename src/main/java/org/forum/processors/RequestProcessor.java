package org.forum.processors;

import jakarta.persistence.EntityManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class RequestProcessor {
    public static String currentTime() {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm").format(Calendar.getInstance()
                .getTime());
    }

    public abstract void process(EntityManager entityManager, int requestType, DataInputStream dis, DataOutputStream dos) throws Exception;
}
