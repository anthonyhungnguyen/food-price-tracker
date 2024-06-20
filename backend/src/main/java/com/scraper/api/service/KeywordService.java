package com.scraper.api.service;

import com.scraper.api.model.KeywordSearchData;

import java.util.Set;

public interface KeywordService {
    Set<KeywordSearchData> setKeywordSearched(String keyword);
    Set<KeywordSearchData> getRecentKeywordsSearched();
    Set<KeywordSearchData> getTopKeywordsSearched();
}
