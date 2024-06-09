package com.ach.domain.location.room.handler;

import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.location.room.RoomModel;
import com.ach.domain.location.room.RoomRepository;
import com.ach.domain.location.room.RoomService;
import com.ach.domain.location.room.command.AddRoomCommand;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class AddRoomCommandHandler implements CommandHandler<AddRoomCommand> {
    @Resource
    private RoomRepository roomRepository;
    @Resource
    private RoomService roomService;
    @Override
    public Boolean handle(EventQueue eventQueue, AddRoomCommand command) {
        RoomModel roomModel = new RoomModel();//创建一个新的教室聚合
        Boolean locationIdIsExist = roomService.checkLocationIdIsExist(command.getLocationId());//检查教学楼是否存在
        roomModel.setHasLocation(locationIdIsExist);
        Boolean roomNameIsUnique=roomRepository.checkRoomNameIsUnique(command.getRoomName());//检查教室名称是否唯一
        roomModel.setNameIsUnique(roomNameIsUnique);

        return roomModel.handle(eventQueue, command);//调用教室聚合的handle方法

        //因为教室聚合被教室事件包括，所以就不需要用这里来存储聚合了，等以后功能扩展再来存储
    }
}
