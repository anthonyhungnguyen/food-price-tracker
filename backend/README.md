The Scraper API provides endpoints for keyword search, product scraping, and page ranking. This document outlines the available endpoints and their usage.

## Base URL
/api

## Endpoints

### 1. Get Word Completion List
**URL:** `/api/word-complete`

**Method:** `GET`

**Query Parameter:**
- `q` (String): The text for which to find word completions.

**Response:** `Set<String>`

**Example Request:**
GET /api/word-complete?q=exam


---

### 2. Get Word Count
**URL:** `/api/word-count`

**Method:** `GET`

**Query Parameter:**
- `q` (String): The URL of the page to count words.

**Response:** `int`

**Example Request:**
GET /api/word-count?q=http://example.com

---

### 3. Get Keyword Search Frequency
**URL:** `/api/keyword-search`

**Method:** `GET`

**Query Parameter:**
- `q` (String): The search keyword.

**Response:** `Set<KeywordSearchData>`

**Example Request:**

GET /api/keyword-search?q=example


---

### 4. Get Keyword Searched List
**URL:** `/api/keyword-search-list`

**Method:** `GET`

**Query Parameter:**
- `q` (String): The type of list to return (`top` or `recent`).

**Response:** `Set<KeywordSearchData>`

**Example Request:**
GET /api/keyword-search-list?q=top
GET /api/keyword-search-list?q=recent


---

### 5. Get Products by Keyword
**URL:** `/api/products/scraping`

**Method:** `GET`

**Query Parameter:**
- `q` (String): The search keyword for scraping products.

**Response:** `Set<ProductData>`

**Example Request:**
GET /api/products/scraping?q=coffee


---

### 6. Get All Products
**URL:** `/api/products/`

**Method:** `GET`

**Response:** `Set<ProductData>`

**Example Request:**
GET /api/products/


---

### 7. Get Page Ranking
**URL:** `/api/page-ranking`

**Method:** `GET`

**Response:** `int`

**Example Request:**
GET /api/page-ranking


## Services

### KeywordService
Service for handling keyword search-related operations.

### ProductService
Service for handling product scraping-related operations.