package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        // discountPolicy -> 단일체계원칙을 잘 지킨 예시
        // OrderServiceImpl 입장에서는 discountPolicy 의 내용을 알지 못하고 그 결과반 반환 받는다.
        // 할인 정책이 바뀌게 되더라도 discountPolicy 의 내용만 변할뿐 OrderServiceImpl 은 변할 필요 없다.
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
