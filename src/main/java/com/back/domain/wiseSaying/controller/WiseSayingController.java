package com.back.domain.wiseSaying.controller;

import com.back.Rq;
import com.back.WiseSaying;

import java.util.*;

public class WiseSayingController {
    List<WiseSaying> wiseList = new ArrayList<>();
    int lastId = 1;
    private Scanner sc;

    public WiseSayingController(Scanner sc) {
        this.sc = sc;
    }

    public void actionRegister() {
        System.out.print("명언 : ");
        String content = sc.nextLine();

        System.out.print("작가 : ");
        String author = sc.nextLine();

        WiseSaying item = write(author, content);

        System.out.printf("%d번 명언이 등록되었습니다.\n", item.getId());
    }

    private WiseSaying write(String author, String content) {
        int id = lastId++;
        WiseSaying item = new WiseSaying(id, author, content);

        wiseList.add(item);

        return item;
    }

    public void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        wiseList.stream()
                .sorted(Comparator.comparingInt(WiseSaying::getId).reversed())
                .forEach(e -> System.out.println(e.toListString()));
    }

    public void actionDelete(Rq rq) {
        int id = rq.getParamAsInt("id", -1);

        boolean isDelete = wiseList.removeIf(e -> e.getId() == id);

        System.out.println(id + "번 명언" + (isDelete ? "이 삭제되었습니다." : "은 존재하지 않습니다."));
    }

    private WiseSaying findById(int id) {
        Optional<WiseSaying> opWise = wiseList.stream()
                .filter(e -> e.getId() == id)
                .findFirst();

        return opWise.orElse(null);
    }

    public void actionEdit(Rq rq) {
        int id = rq.getParamAsInt("id", -1);

        WiseSaying item = findById(id);

        if (item == null) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }

        System.out.println("명언(기존) : " + item.getContent());
        System.out.printf("명언 : ");
        String content = sc.nextLine();

        System.out.println("작가(기존) : " + item.getAuthor());
        System.out.printf("작가 : ");
        String author = sc.nextLine();

        edit(item, author, content);
    }

    private void edit(WiseSaying item, String author, String content) {
        item.setAuthor(author);
        item.setContent(content);
    }
}
