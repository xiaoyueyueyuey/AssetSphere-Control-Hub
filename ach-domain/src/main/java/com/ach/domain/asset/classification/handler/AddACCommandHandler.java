package com.ach.domain.asset.classification.handler;

import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.asset.classification.AssetClassificationModel;
import com.ach.domain.asset.classification.AssetClassificationRepository;
import com.ach.domain.asset.classification.command.AddACCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AddACCommandHandler implements CommandHandler<AddACCommand> {
    private final AssetClassificationRepository roomRepository;

    @Override
    public Boolean handle(EventQueue eventQueue, AddACCommand command) {
        AssetClassificationModel model = new AssetClassificationModel();//创建一个新的资产分类聚合
        Boolean acNameIsUnique = roomRepository.checkACNameIsUnique(command.getAcName());//检查名称是否唯一
        model.setNameIsUnique(acNameIsUnique);
        return model.handle(eventQueue, command);//调用教室聚合的handle方法
        //因为教室聚合被教室事件包括，所以就不需要用这里来存储聚合了，等以后功能扩展再来存储
    }
}
