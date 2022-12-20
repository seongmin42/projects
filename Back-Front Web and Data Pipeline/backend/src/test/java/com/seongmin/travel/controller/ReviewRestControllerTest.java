package com.seongmin.travel.controller;

import com.seongmin.travel.model.dto.Review;
import com.seongmin.travel.model.dto.User;
import com.seongmin.travel.model.service.ReviewService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import java.text.SimpleDateFormat;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ComponentScan(basePackages = "com.seongmin.travel.**")
@MapperScan(basePackages="com.seongmin.travel.model.dao")
@AutoConfigureMybatis
@WebMvcTest(ReviewRestController.class)    // Slice 테스트
public class ReviewRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ReviewService reviewService;

    @Test
    @DisplayName("Read one review")
    void getReviewTest() throws Exception {

        given(reviewService.getReviewByIndex(2)).willReturn(
                new Review(2, 4, "밥먹으러 계단으로 올라가고 있어요",
                        "죽을것같아요 니들은 이러지마라", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2022-11-18 10:03:50"),
                        0, 0, 0, new User("qowlsgh99", "1234", "배진호", "qowlsgh99@naver.com", "진호짱짱123")
                        )
        );

        String reviewIndex = "2";

        mockMvc.perform(
                get("/review-api/detail/" + reviewIndex)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.reviewIndex").exists())
                .andExpect(jsonPath("$.videoIndex").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.regDate").exists())
                .andExpect(jsonPath("$.viewCnt").exists())
                .andExpect(jsonPath("$.like").exists())
                .andExpect(jsonPath("$.dislike").exists())
                .andExpect(jsonPath("$.user").exists())
                .andDo(print());

        verify(reviewService, Mockito.times(2)).getReviewByIndex(2);
    }

    /*
    @Test
    @DisplayName("Create review test")
    void createReviewTest() throws Exception{
        Review review = new Review(0,1,"테스트 제목","테스트 내용", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2022-11-18 10:03:50"),
                0, 0, 0, new User("qowlsgh99", "1234", "배진호", "qowlsgh99@naver.com", "진호짱짱123"));

        //given(reviewService.registReview(review)).willReturn(review);
    }
    */
}
