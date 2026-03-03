package com.rev.app.revhire.service;

import com.rev.app.revhire.entity.Message;
import com.rev.app.revhire.entity.User;
import com.rev.app.revhire.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private com.rev.app.revhire.service.NotificationService notificationService;

    public Message sendMessage(User sender, User receiver, String content) {
        Message message = Message.builder()
                .sender(sender)
                .receiver(receiver)
                .content(content)
                .build();
        Message saved = messageRepository.save(message);

        // Notify Receiver
        notificationService.sendNotification(receiver, "New message from " + sender.getEmail());

        return saved;
    }

    public List<Message> getMessagesBetween(User user1, User user2) {
        List<Message> messages = messageRepository.findMessagesBetween(user1, user2);
        // Mark as read for user1 if they are the receiver
        messages.stream()
                .filter(m -> m.getReceiver().equals(user1) && !m.getIsRead())
                .forEach(m -> {
                    m.setIsRead(true);
                    messageRepository.save(m);
                });
        return messages;
    }
}
