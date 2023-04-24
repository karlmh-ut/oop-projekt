package org.forum.processors.server;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class Transaction {
    public static void runInTransaction(EntityManager entityManager, Action action) throws Exception {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            action.run();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive())
                transaction.rollback();
            throw e;
        }
    }

    public interface Action {
        void run() throws Exception;
    }
}
