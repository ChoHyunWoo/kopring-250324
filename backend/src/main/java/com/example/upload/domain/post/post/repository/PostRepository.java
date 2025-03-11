package com.example.upload.domain.post.post.repository;

import com.example.upload.domain.member.member.entity.Member;
import com.example.upload.domain.post.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, CustomPostRepository{
    Optional<Post> findTopByOrderByIdDesc();
    Page<Post> findByListed(boolean listed, Pageable pageRequest);
    Page<Post> findByListedAndTitleLike(boolean listed, String keyword, Pageable pageRequest);
    Page<Post> findByListedAndContentLike(boolean listed, String likeKeyword, Pageable pageRequest);
    Page<Post> findByAuthorAndTitleLike(Member author, String likeKeyword, Pageable pageRequest);
    Page<Post> findByAuthorAndContentLike(Member author, String likeKeyword, Pageable pageRequest);
}
