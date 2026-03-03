package com.rev.app.revhire.rest;

import com.rev.app.revhire.entity.Message;
import com.rev.app.revhire.entity.User;
import com.rev.app.revhire.repository.UserRepository;
import com.rev.app.revhire.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageRestController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestParam Long receiverId,
                                               @RequestBody String content,
                                               @AuthenticationPrincipal UserDetails userDetails) {
        User sender = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        User receiver = userRepository.findById(receiverId).orElseThrow();
        return ResponseEntity.ok(messageService.sendMessage(sender, receiver, content));
    }

    @GetMapping("/conversation/{otherUserId}")
    public ResponseEntity<List<Message>> getConversation(@PathVariable Long otherUserId,
                                                         @AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        User otherUser = userRepository.findById(otherUserId).orElseThrow();
        return ResponseEntity.ok(messageService.getMessagesBetween(currentUser, otherUser));
    }
}
