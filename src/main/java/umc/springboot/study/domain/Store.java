package umc.springboot.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.springboot.study.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=20)
    private String name;

    @Column(nullable=false, length=40)
    private String address;

    private Float score;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="region_id")
    private Region region;
}
