package com.scraper.api.service;

import com.scraper.api.model.KeywordSearchData;
import kwsearch.SearchFrequency;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class KeywordServiceImpl implements KeywordService {

    @Override
    public Set<KeywordSearchData> setKeywordSearched(String keyword) {
        return SearchFrequency.performSearchQueries(keyword);
    }

    @Override
    public Set<KeywordSearchData> getRecentKeywordsSearched() {
        return SearchFrequency.topRecentSearchQueries();
    }

    @Override
    public Set<KeywordSearchData> getTopKeywordsSearched() {
        return SearchFrequency.topSearchQueries();
    }
}
