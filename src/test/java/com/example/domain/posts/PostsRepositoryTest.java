package com.example.domain.posts;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    void save_posts_and_return() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("test@gmail.com")
                .build()
        );

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    void register_base_time_entity() {
        // given
        LocalDateTime now = LocalDateTime.of(2021, 8, 4, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build()
        );

        // when
        List<Posts> postsList = postsRepository.findAll();


        // then
        Posts posts = postsList.get(0);

        System.out.println("created date= " + posts.getCreatedDate() + "\nmodified date= " + posts.getModifiedDate());

        assertAll(
                () -> assertTrue(now.isBefore(posts.getCreatedDate())),
                () -> assertTrue(now.isBefore(posts.getModifiedDate()))
        );
    }

}