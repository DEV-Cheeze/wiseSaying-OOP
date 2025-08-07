package com.test;

import java.io.*;

public class App {

    public void run() throws IOException{ //실제 작업 파일
        System.out.println("== 명언 앱 ==");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        loop:
        while(true){
            System.out.print("명령) ");
            switch (br.readLine()) {
                case "종료":
                    break loop;
            }
        }

    }

}

