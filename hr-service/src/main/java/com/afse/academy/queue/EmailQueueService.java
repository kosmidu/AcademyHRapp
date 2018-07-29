package com.afse.academy.queue;

import com.afse.academy.Email;

public interface EmailQueueService {
    void send (Email email);
}