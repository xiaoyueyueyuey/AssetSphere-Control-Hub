package com.ach.domain.system.post.handler;

import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.system.post.PostModel;
import com.ach.domain.system.post.PostRepository;
import com.ach.domain.system.post.command.AddPostCommand;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class AddPostCommandHandler implements CommandHandler<AddPostCommand> {
    @Resource
    private PostRepository postRepository;

    @Override
    public Boolean handle(EventQueue eventQueue, AddPostCommand command) {
        Boolean postNameUnique = postRepository.checkPostNameUnique(command.getPostName());
        Boolean postCodeUnique = postRepository.checkPostCodeUnique(command.getPostCode());
        PostModel postModel = new PostModel();
        //赋命令上没有的值
        postModel.setPostCodeIsUnique(postCodeUnique);
        postModel.setPostNameIsUnique(postNameUnique);
        Boolean handle = postModel.handle(eventQueue, command);
        if (handle) {
            Long postId = postRepository.save(postModel);
            eventQueue.queue().forEach(event -> {
                event.setAggregateId(postId);
            });
            return postId > 0;
        }
        return false;

    }
}
