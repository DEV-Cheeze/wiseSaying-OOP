package com.test.Controller;

import com.test.Service.WiseSayingService;
import com.test.domain.WiseSaying;

import java.io.*;
import java.util.*;

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
            System.out.println(wiseSaying.getId() + "번 명언이 등록되었습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    public void list() {
        List<WiseSaying> wiseSayings = wiseSayingService.findAllWiseSayings();
        Collections.reverse(wiseSayings);
        System.out.println("번호 / 작가 / 명언\n" +
                "----------------------");
        wiseSayings.stream().forEach(x -> {
            System.out.println("%d / %s / %s".formatted(x.getId(), x.getAuthor(), x.getContent()));
        });
    }

    public void delete(int id){
        try{
            int deletedId = wiseSayingService.deleteWiseSaying(id);
            System.out.println(deletedId + "번 명언이 삭제되었습니다.");
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }

    public void modify(int id) throws IOException{
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            WiseSaying ws = wiseSayingService.findWiseSaying(id);

            System.out.print("명언(기존) : " + ws.getContent() + "\n");
            System.out.print("명언 : ");
            String content = br.readLine();

            System.out.print("명언(기존) : " + ws.getAuthor()  + "\n");
            System.out.print("작가 : ");
            String author = br.readLine();
            wiseSayingService.modify(id, content, author);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void build() {
        try {
            String result = wiseSayingService.build();
            System.out.println(result);
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

}
