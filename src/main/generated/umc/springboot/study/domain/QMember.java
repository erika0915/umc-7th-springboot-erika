package umc.springboot.study.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -896257197L;

    public static final QMember member = new QMember("member1");

    public final umc.springboot.study.domain.common.QBaseEntity _super = new umc.springboot.study.domain.common.QBaseEntity(this);

    public final StringPath address = createString("address");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final EnumPath<umc.springboot.study.domain.enums.Gender> gender = createEnum("gender", umc.springboot.study.domain.enums.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<umc.springboot.study.domain.mapping.MemberFood, umc.springboot.study.domain.mapping.QMemberFood> memberFoodList = this.<umc.springboot.study.domain.mapping.MemberFood, umc.springboot.study.domain.mapping.QMemberFood>createList("memberFoodList", umc.springboot.study.domain.mapping.MemberFood.class, umc.springboot.study.domain.mapping.QMemberFood.class, PathInits.DIRECT2);

    public final ListPath<umc.springboot.study.domain.mapping.MemberMission, umc.springboot.study.domain.mapping.QMemberMission> memberMissionList = this.<umc.springboot.study.domain.mapping.MemberMission, umc.springboot.study.domain.mapping.QMemberMission>createList("memberMissionList", umc.springboot.study.domain.mapping.MemberMission.class, umc.springboot.study.domain.mapping.QMemberMission.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath phoneNum = createString("phoneNum");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final ListPath<Review, QReview> reviewList = this.<Review, QReview>createList("reviewList", Review.class, QReview.class, PathInits.DIRECT2);

    public final StringPath specAddress = createString("specAddress");

    public final EnumPath<umc.springboot.study.domain.enums.MemberStatus> status = createEnum("status", umc.springboot.study.domain.enums.MemberStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

