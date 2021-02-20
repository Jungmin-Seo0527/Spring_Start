package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    // @Autowired // 필드 주입
    private final MemberRepository memberRepository;
    // @Autowired
    private final DiscountPolicy discountPolicy;

    @Autowired // 생성자 주입
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("1. OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // @Autowired // 수정자 주입(setter 주입)
    // public void setMemberRepository(MemberRepository memberRepository) {
    //     System.out.println("memberRepository = " + memberRepository);
    //     this.memberRepository = memberRepository;
    // }
    //
    // @Autowired
    // public void setDiscountPolicy(DiscountPolicy discountPolicy) {
    //     System.out.println("discountPolicy = " + discountPolicy);
    //     this.discountPolicy = discountPolicy;
    // }
    //
    // @Autowired // 일반 메서드 주입
    // public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    //     this.memberRepository = memberRepository;
    //     this.discountPolicy = discountPolicy;
    // }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
