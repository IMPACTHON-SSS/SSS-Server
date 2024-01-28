package dev.sss.dish.ui;

import dev.sss.dish.application.DishService;
import dev.sss.dish.application.dto.CreateDishRequest;
import dev.sss.dish.application.dto.DishResponse;
import dev.sss.dish.application.dto.UpdateDishRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Dish API")
@RestController
@RequestMapping(value = "/dish")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @Operation(description = "음식일기 생성")
    @PostMapping
    public void create(@RequestBody @Validated CreateDishRequest request) {
        dishService.create(request);
    }

    @Operation(description = "음식일기 수정")
    @PatchMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody @Validated UpdateDishRequest request) {
        dishService.update(id, request);
    }

    @Operation(description = "음식일기 삭제")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        dishService.delete(id);
    }

    @Operation(description = "날짜별 (year & month) 음식일기 조회")
    @GetMapping
    public List<DishResponse> getByDate(@RequestParam("year") Integer year, @RequestParam("month") Integer month) {
        return dishService.getByDate(year, month);
    }

}