package com.example.smart_assistant.algorithm;

import java.util.*;
import java.util.stream.Collectors;

public class KeywordExtractor {
    // 불용어 리스트 정의
    private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList(
        "the", "is", "and", "a", "an", "of", "in", "to", "with", "that", "for", "on", "this", "by", "it", "at", "or", "as", "been", "called"
    ));

    public List<String> extractKeywords(String text, int topN) {
        // 단어 분리 및 전처리
        String[] words = text.toLowerCase().replaceAll("[^a-zA-Z ]", "").split("\\s+");

        // 불용어 제거 및 빈도 계산
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            if (!STOP_WORDS.contains(word)) {
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }

        // 빈도 기반 상위 단어 추출
        return wordFrequency.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(topN)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    }     
}
