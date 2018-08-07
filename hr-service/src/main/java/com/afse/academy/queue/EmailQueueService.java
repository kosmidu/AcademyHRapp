package com.afse.academy.queue;

import com.afse.academy.entities.Email;

public interface EmailQueueService {
    void send (Email email);
}