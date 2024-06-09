package com.ach.domain.location.room;


public interface RoomService {
    Boolean checkLocationIdIsExist(Integer locationId);

    Boolean checkHasAsset(Integer roomId);
}
