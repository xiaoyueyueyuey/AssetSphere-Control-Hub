package com.ach.domain.system.post.command;


import com.ach.domain.Command;
import lombok.Data;

@Data
public class DeletePostCommand implements Command {
    private Long postId;
}
