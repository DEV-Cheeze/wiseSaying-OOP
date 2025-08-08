package com.test;

import com.test.Controller.WiseSayingController;
import com.test.Service.WiseSayingService;


import java.io.*;

public class App {

    public void run() throws IOException{ //실제 작업 파일
        System.out.println("== 명언 앱 ==");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        WiseSayingController wiseSayingController = new WiseSayingController();

        while(true){
            System.out.print("명령) ");
            String cmd = br.readLine();

            if(cmd.equals("종료")) break;
            else if(cmd.equals("등록")) wiseSayingController.assign();
            else if(cmd.equals("목록")) wiseSayingController.list();
            else if(cmd.startsWith("삭제")) {
                String[] split = cmd.split("=");
                int id = Integer.parseInt(split[1]);
                wiseSayingController.delete(id);
            }else if(cmd.startsWith("수정")) {
                String[] split = cmd.split("=");
                int id = Integer.parseInt(split[1]);
                wiseSayingController.modify(id);
            }
        }

    }

}

