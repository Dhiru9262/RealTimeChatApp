package com.substring.chat.chat_app_backend.controller;


import com.substring.chat.chat_app_backend.controller.payload.MessageRequest;
import com.substring.chat.chat_app_backend.entities.AppConstants;
import com.substring.chat.chat_app_backend.entities.Message.Message;
import com.substring.chat.chat_app_backend.entities.Room;
import com.substring.chat.chat_app_backend.reposetries.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@CrossOrigin(AppConstants.FRONT_END_BASE_URL)
public class ChatController
{



    private RoomRepository roomRepository;

    public ChatController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


//    for sending and reciving message
    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/room/{roomId}")
public Message sendMessage(
        @DestinationVariable String roomId,
        @RequestBody MessageRequest request
    ){



      Room room = roomRepository.findByRoomId(request.getRoomId());
      Message message = new Message();
      message.setContent(request.getContent());
      message.setSender(request.getSender());
      message.setTimeStamp(LocalDateTime.now());

      if (room != null){
          room.getMessage().add(message);
          roomRepository.save(room);
      }else{
          throw new RuntimeException("room not found");
      }
      return message;
}








}
