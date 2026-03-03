package com.rev.app.revhire.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_seq")
    @SequenceGenerator(name = "message_seq", sequenceName = "MESSAGE_SEQ", allocationSize = 1)
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    @Lob
    @Column(nullable = false)
    private String content;

    private LocalDateTime sentAt = LocalDateTime.now();
    private Boolean isRead = false;

    public Message() {
    }

    public Message(Long messageId, User sender, User receiver, String content, LocalDateTime sentAt, Boolean isRead) {
        this.messageId = messageId;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.sentAt = sentAt != null ? sentAt : LocalDateTime.now();
        this.isRead = isRead != null ? isRead : false;
    }

    public static MessageBuilder builder() {
        return new MessageBuilder();
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public static class MessageBuilder {
        private Long messageId;
        private User sender;
        private User receiver;
        private String content;
        private LocalDateTime sentAt;
        private Boolean isRead;

        public MessageBuilder messageId(Long messageId) {
            this.messageId = messageId;
            return this;
        }

        public MessageBuilder sender(User sender) {
            this.sender = sender;
            return this;
        }

        public MessageBuilder receiver(User receiver) {
            this.receiver = receiver;
            return this;
        }

        public MessageBuilder content(String content) {
            this.content = content;
            return this;
        }

        public MessageBuilder sentAt(LocalDateTime sentAt) {
            this.sentAt = sentAt;
            return this;
        }

        public MessageBuilder isRead(Boolean isRead) {
            this.isRead = isRead;
            return this;
        }

        public Message build() {
            return new Message(messageId, sender, receiver, content, sentAt, isRead);
        }
    }
}
