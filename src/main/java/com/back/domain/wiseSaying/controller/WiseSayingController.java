package com.back.domain.wiseSaying.controller;

import com.back.Rq;
import com.back.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.*;

public class WiseSayingController {
    List<WiseSaying> wiseList = new ArrayList<>();
    private final Scanner sc;
    WiseSayingService wiseSayingService = new WiseSayingService();

    public WiseSayingController(Scanner sc) {
        this.sc = sc;
    }

    public void actionRegister() {
        System.out.print("명언 : ");
        String content = sc.nextLine();

        System.out.print("작가 : ");
        String author = sc.nextLine();

        WiseSaying item = wiseSayingService.write(wiseList, author, content);

        System.out.printf("%d번 명언이 등록되었습니다.\n", item.getId());
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



    public void actionEdit(Rq rq) {
        int id = rq.getParamAsInt("id", -1);

        WiseSaying item = wiseSayingService.findById(wiseList, id);

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

        wiseSayingService.edit(item, author, content);
    }
}
