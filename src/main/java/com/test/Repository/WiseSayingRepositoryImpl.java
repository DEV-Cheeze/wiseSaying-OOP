package com.test.Repository;

import com.test.domain.WiseSaying;

import java.io.*;
import java.util.*;
import java.util.Optional;

public class WiseSayingRepositoryImpl implements WiseSayingRepository {

    private final Map<Integer, WiseSaying> storage = new HashMap<>();
    private int sequence = 0;
    private final String DIR_PATH = "src/main/java/com/test/db";
    private final String sequencePATH = "src/main/java/com/test/db/lastnumber.txt";

    public WiseSayingRepositoryImpl(){
        try {
            memoryLoad();
            getSequence();
        } catch (IOException e) {
            throw new RuntimeException("메모리 로드 실패");
        }
    }

    public void getSequence() {
        try {
            File file = new File(sequencePATH);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null){
                sequence = Integer.parseInt(line);
            }
            br.close();
        }catch (IOException e){
            throw new RuntimeException("파일을 찾을 수 없습니다.");
        }
    }

    public String writeJson(WiseSaying wiseSaying){ //두 곳에서 쓸 수 있으니까 분리
        StringBuilder sb = new StringBuilder("");
        sb.append("{\n");
        sb.append("    \"id\": \"" + wiseSaying.getId()).append("\",\n");
        sb.append("    \"author\": \"" + wiseSaying.getAuthor()).append("\",\n");
        sb.append("    \"content\": \"" + wiseSaying.getContent()).append("\"\n");
        sb.append("}");

        return sb.toString();
    }

    @Override
    public void setSequence() { //파일 쓰고 나서 sequence 갱신
        try {
            FileWriter fw = new FileWriter(sequencePATH);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(Integer.toString(sequence));
            writer.close();
        }catch (IOException e){
            throw new RuntimeException("파일을 찾을 수 없습니다.");
        }

    }

    @Override
    public void memorySave(WiseSaying wiseSaying){
        wiseSaying.setId(++sequence);
        storage.put(wiseSaying.getId(), wiseSaying);
    }

    @Override
    public void memoryLoad() throws IOException{ //메모리에 미리 적재
        List<WiseSaying> wiseSayings = jsonFilesToObjects();
        wiseSayings.stream().
                forEach(v -> storage.put(v.getId(), v));
        //디렉토리 안에있는 json 파일들을 모두 불러오기
        //그 json 파일을 객체로 변환하여 메모리에 올리기 return WiseSaying
    }

    @Override
    public void save(WiseSaying wiseSaying) { //파일만 쓰기
        try {
            String fileName = DIR_PATH + "/" + sequence + ".json";
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(writeJson(wiseSaying));
            writer.close();
        }catch (IOException e){
            throw new RuntimeException("파일을 찾을 수 없습니다.");
        }
    }

    public void deleteFromMemory(int id){
        storage.remove(id);
    }

    @Override
    public void delete(int id) {
        String delFilePath = DIR_PATH + "/" + id + ".json";
        File file = new File(delFilePath);
        file.delete();
    }

    @Override
    public Optional<WiseSaying> findById(int id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<WiseSaying> findAll(){
        return new ArrayList<>(storage.values());
    }

    @Override
    public WiseSaying jsonFileParser(File file) throws IOException {
        StringBuilder parsingContent = new StringBuilder(" ");
        BufferedReader br = new BufferedReader(new FileReader(file)); //파일 하나 통째로 읽기
        //괄호, 중괄호 replace후 trim, 이후 쉼표로 split 후 :로 추가 split
        String line;
        while((line = br.readLine()) != null) { //입력된 값 \n 구분해서 받기
            parsingContent.append(line.trim().replaceAll("[{}\"]", "")); //공백 제거
        }

        String[] result = parsingContent.toString().split(",");

        int id = Integer.parseInt(result[0].split(":")[1].trim());
        String author = result[1].split(":")[1].trim();
        String content = result[2].split(":")[1].trim();

        br.close();
        return new WiseSaying(id, author, content);
    }

    @Override
    public List<WiseSaying> jsonFilesToObjects() throws IOException{
        List<WiseSaying> jsonStorage = new ArrayList<>();
        File folder = new File(DIR_PATH); //디렉토리 설정
        File[] jsonFiles = folder.listFiles((dir, name) -> name.endsWith(".json")); //디렉토리에 해당하는 모든 json 파일 읽기

        for(File file : jsonFiles){ //각 json 파일 할당
            //여기에 함수 두기
            WiseSaying wiseSaying = jsonFileParser(file); //책임 분리
            jsonStorage.add(wiseSaying);
        }

        return jsonStorage;
    }

}
