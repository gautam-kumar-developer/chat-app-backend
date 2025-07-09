package com.project.chat.controllers;

import com.project.chat.entities.Message;
import com.project.chat.entities.Room;
import com.project.chat.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin("https://rumora.netlify.app") // frontend url
public class RoomController {
    @Autowired
    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    //Create room if it not exists
    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody String roomId) {
        //Checking for room existence
        if(roomRepository.findByRoomId(roomId) != null) {
            //room already exists
            return new ResponseEntity<>("Room already exists!", HttpStatus.BAD_REQUEST);
        }

        //Create a new room since it doesn't exist
        Room room = new Room();
        room.setRoomId(roomId);
        Room savedRoom = roomRepository.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(room);
    }

    //get room : join
    @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable String roomId) {
        Room room = roomRepository.findByRoomId(roomId);
        if(room == null) {
            return ResponseEntity.badRequest().body("No Such Room Found!!");
        }
        return ResponseEntity.ok(room);
    }

    //get messages of room
    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessaages(
            @PathVariable String roomId,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "20", required = false) int size
    ) {
        Room room = roomRepository.findByRoomId(roomId);
        if(room == null) {
            return ResponseEntity.badRequest().build();
        }
        List<Message> messages = room.getMessages();

        //Pagination
        int start = Math.max(0, messages.size() - (page + 1) * size);
        int end = Math.min(messages.size(), start + size);
        List<Message> PaginatedMessages = messages.subList(start,end);

        return ResponseEntity.ok(messages);
    }

}
