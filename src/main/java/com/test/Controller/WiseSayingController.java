package com.test.Controller;

import com.test.Service.WiseSayingService;
import com.test.domain.WiseSaying;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WiseSayingController {

    WiseSayingService wiseSayingService = new WiseSayingService();

    public void assign() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("명언 : ");
        String content = br.readLine();

        System.out.print("작가 : ");
        String author = br.readLine();

        try {
            WiseSaying wiseSaying = wiseSayingService.create(content, author);
            System.out.println("통과");
            System.out.println(wiseSaying.getId() + "번 명언이 등록되었습니다.");
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }


}
