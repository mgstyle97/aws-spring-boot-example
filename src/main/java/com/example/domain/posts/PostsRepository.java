package com.example.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * JpaRepository 를 상속받는 Repository 인터페이스는 @Repository 를
 * 추가할 필요가 없다.
 *
 * 여기서 주의할 점은 Entity 클래스와 기본 Entity Repository 는 함께
 * 위치해야 한다.
 * 둘은 밀접한 관계이고, Entity 클래스는 기본 Repository 없이는 제대로
 * 역할을 할 수가 없다.
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

}
