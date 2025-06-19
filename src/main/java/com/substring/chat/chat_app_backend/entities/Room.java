package com.substring.chat.chat_app_backend.entities;


import com.substring.chat.chat_app_backend.entities.Message.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    private String id; // Mongodb unique identifier
    private String roomId;

    private List<Message> message = new ArrayList<>();
}
