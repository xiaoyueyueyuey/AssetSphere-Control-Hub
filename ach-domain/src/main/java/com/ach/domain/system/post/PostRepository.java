package com.ach.domain.system.post;


import com.ach.domain.common.Repository;

public interface PostRepository extends Repository<PostModel> {
    Boolean checkPostNameUnique(String postName);

    Boolean checkPostCodeUnique(String postCode);

    Boolean checkPostNameUnique(String postName, Long postId);

    Boolean checkPostCodeUnique(String postCode, Long postId);

    Integer getPostIsAssignedCount(Long postId);
}
