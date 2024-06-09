package com.ach.domain.location.room;

import com.ach.domain.common.Repository;

public interface RoomRepository extends Repository<RoomModel> {
    Boolean checkRoomNameIsUnique(String roomName);
    Boolean checkRoomNameIsUnique(String roomName, Integer excludeId);
}
