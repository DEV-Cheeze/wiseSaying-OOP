package com.test;

import com.test.Controller.WiseSayingController;
import com.test.Service.WiseSayingService;


import java.io.*;

public class App {

    public void run() throws IOException{ //실제 작업 파일
        System.out.println("== 명언 앱 ==");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        WiseSayingController wiseSayingController = new WiseSayingController();

        loop:
        while(true){
            System.out.print("명령) ");
            switch (br.readLine()) {
                case "종료":
                    break loop;
                case "등록":
                    wiseSayingController.assign();
                    break;
            }
        }

    }

}

