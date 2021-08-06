package com.example.domain.posts;

import com.example.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 어노테이션을 정렬하는 기준
 *
 * 롬복은 코드를 단순화시켜 주지만 필수 어노테이션은 아니다.
 * 주요 어노테이션은 @Entity 를 클래스에 가깝게 두고,
 * 롬복 어노테이션을 그 위로 둔다.
 *
 * Entity 클래스에는 Setter 메서드가 없다.
 * 자바빈 규약을 생각하면서 getter/setter 를 무작정 생성하는 경우가 있는데
 * 이렇게 되면 해당 클래스의 인스턴스 값들이 언제 어디서 변해야 하는지 코드상으로
 * 명확하게 구분할 수가 없어, 차후 기능 변경 시 복잡해진다.
 * 그렇기 때문에 Entity 클래스에서는 절대 Setter 메서드를 만들지 않는다.
 *
 * 대신, 해당 필드의 값 변경이 팔요하면 명확히 그 목적과 의도를 나타낼 수 있는
 * 메서드를 추가해야 한다.
 */

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    /**
     * 생성자 빌더 패턴
     * @param title
     * @param content
     * @param author
     *
     * Setter 가 없는 상황에서 생성자를 통한 초기화가 가장 바람직하다.
     * 만약, 값 변경이 필요한 경우 해당 이벤트에 맞는 public 메서드를
     * 호출하여 변경하는 것을 전제로 한다.
     *
     * 빌더 패턴은 생성시 채워야할 필드가 무엇인지 명확히 지정할 수 있다.
     * 다만, 명확히 지정하지 않으면 값은 채워지지 않는다.
     */
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
