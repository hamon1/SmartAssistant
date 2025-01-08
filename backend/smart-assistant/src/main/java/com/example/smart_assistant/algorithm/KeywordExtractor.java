package com.example.smart_assistant.algorithm;

import opennlp.tools.tokenize.SimpleTokenizer;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.en.EnglishAnalyzer;

import java.util.*;
import java.util.stream.Collectors;


public class KeywordExtractor {

    
    // 불용어 리스트 정의
    private static final CharArraySet STOP_WORDS = EnglishAnalyzer.getDefaultStopSet();

    public List<String> extractKeywords(String text, int topN) {

        // System.out.println("Default Stop Words: " + EnglishAnalyzer.getDefaultStopSet());

        // 단어 분리 및 전처리
        // String[] words = text.toLowerCase().replaceAll("[^a-zA-Z ]", "").split("\\s+");
        SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
        String[] tokens = tokenizer.tokenize(text);

        // 불용어 제거 및 빈도 계산
        // Map<String, Integer> wordFrequency = new HashMap<>();
        // for (String token : tokens) {
        //     String normalizedToken = token.toLowerCase().replaceAll("[^a-z]", "");
        //     if (!STOP_WORDS.contains(token)) {
        //         wordFrequency.put(token, wordFrequency.getOrDefault(token, 0) + 1);
        //     }
        // }
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String token : tokens) {
            // 소문자 변환 및 알파벳 이외의 문자 제거
            String normalizedToken = token.toLowerCase().replaceAll("[^a-z]", "");
            
            // 정규화된 토큰이 유효하고, 불용어가 아닐 경우만 처리
            if (!normalizedToken.isEmpty() && !STOP_WORDS.contains(normalizedToken)) {
                wordFrequency.put(normalizedToken, wordFrequency.getOrDefault(normalizedToken, 0) + 1);
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
