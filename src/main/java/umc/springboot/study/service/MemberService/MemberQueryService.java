package umc.springboot.study.service.MemberService;

import org.springframework.data.domain.Page;
import umc.springboot.study.domain.Mission;
import umc.springboot.study.domain.Review;

public interface MemberQueryService {
    Page<Review> getReviewList(Long MemberId, Integer page);
}
