package com.example.smart_assistant.algorithm;

import opennlp.tools.tokenize.SimpleTokenizer;

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
        SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
        String[] tokens = tokenizer.tokenize(text);

        // 불용어 제거 및 빈도 계산
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String token : tokens) {
            if (!STOP_WORDS.contains(token)) {
                wordFrequency.put(token, wordFrequency.getOrDefault(token, 0) + 1);
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
