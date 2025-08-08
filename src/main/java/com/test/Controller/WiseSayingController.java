package com.test.Controller;

import com.test.Service.WiseSayingService;

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
        wiseSayingService.create(content, author);
    }


}
