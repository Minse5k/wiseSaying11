package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WiseSayingRepository {
    List<WiseSaying> wiseList = new ArrayList<>();
    int lastId = 0;

    public void save(WiseSaying wiseSaying) {
        if(wiseSaying.getId() != 0) return;

        wiseSaying.setId(++lastId);
        wiseList.add(wiseSaying);
    }

    public WiseSaying findById(int id) {
        return wiseList.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<WiseSaying> reverseList() {
        return wiseList.reversed();
    }

    public boolean delete(int id) {
        return wiseList.removeIf(e -> e.getId() == id);
    }
}
