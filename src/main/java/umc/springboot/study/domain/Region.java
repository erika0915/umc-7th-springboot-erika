package umc.springboot.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.springboot.study.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
public class Region extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;
}
