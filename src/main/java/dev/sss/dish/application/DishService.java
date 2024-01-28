package dev.sss.dish.application;

import dev.sss.auth.infra.security.MemberSessionHolder;
import dev.sss.dish.application.dto.CreateDishRequest;
import dev.sss.dish.application.dto.DishResponse;
import dev.sss.dish.application.dto.UpdateDishRequest;
import dev.sss.dish.domain.Dish;
import dev.sss.dish.domain.DishJPARepository;
import dev.sss.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DishService {

    private final MemberSessionHolder memberSessionHolder;
    private final DishJPARepository dishJPARepository;

    @Transactional
    public void create(CreateDishRequest request) {
        Member member = memberSessionHolder.current();

        if(dishJPARepository.existsByMemberAndDate(member, LocalDate.now())) {
            throw new RuntimeException("같은 날짜에 중복된 사진");
        }

        dishJPARepository.save(Dish.builder()
                .dairy(request.dairy())
                .url(request.url())
                .member(member)
                .build());
    }

    @Transactional
    public void update(Long id, UpdateDishRequest request) {
        Member member = memberSessionHolder.current();
        Dish dish = dishJPARepository.getById(id);

        dish.modify(member, request.dairy(), request.url());
    }

    @Transactional
    public void delete(Long id) {
        Dish dish = dishJPARepository.getById(id);
        dishJPARepository.delete(dish);
    }

    public List<DishResponse> getByDate(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, 1);

        LocalDate minDate = LocalDate.of(year, month, 1);
        LocalDate maxDate = LocalDate.of(year, month, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        Member member = memberSessionHolder.current();

        return dishJPARepository.findByMemberAndDateGreaterThanEqualAndDateLessThanEqual(member, minDate, maxDate).stream()
                .map(DishResponse::of)
                .toList();
    }

}