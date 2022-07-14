package com.example.tdd_new.member;



import com.example.tdd_new.domain.Member;
import com.example.tdd_new.domain.Study;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId);

    void validate(Long memberId);

    void notify(Study newstudy);

    void notify(Member member);
}
