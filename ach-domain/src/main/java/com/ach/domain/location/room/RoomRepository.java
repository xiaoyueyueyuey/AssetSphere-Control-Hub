package com.ach.domain.location.room;

import com.ach.domain.Repository;

public interface RoomRepository extends Repository<RoomModel> {
    Boolean checkRoomNameIsUniqueInOrderLocation(String roomName, Integer orderLocationId);
    Boolean checkRoomNameIsUnique(String roomName, Integer excludeId);
}
