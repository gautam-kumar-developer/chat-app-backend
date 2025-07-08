package com.project.chat.repositories;

import com.project.chat.entities.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, String> {
    //get room using roomId
    Room findByRoomId(String roomId);
}
