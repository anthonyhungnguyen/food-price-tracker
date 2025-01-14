# Food Price Tracker 🛒

[![Java](https://img.shields.io/badge/Java-21-red.svg)](https://openjdk.java.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.1-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Next.js](https://img.shields.io/badge/Next.js-14-black.svg)](https://nextjs.org/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

A web application that tracks food prices across multiple online stores, featuring real-time data crawling, advanced search capabilities, and data analytics.

## Features

- 🔍 **Smart Search**
  - Real-time price tracking
  - Auto-complete suggestions
  - Spell checking
  - Page ranking by relevance
  - Inverted index for fast results

- 📊 **Analytics**
  - Search pattern tracking
  - Data validation
  - Web crawler monitoring

## Architecture

```mermaid
graph TD
    A[Frontend - Next.js] -->|HTTP/REST| B[Backend - Spring Boot]
    B --> C[MySQL Database]
    B --> D[Web Crawlers]
    D -->|Fetch Prices| E[Online Stores]
    F[Redis Cache] --> B
```

## Tech Stack

**Backend:**
- Java 21, Spring Boot 3.3
- MySQL, Redis
- Selenium WebDriver

**Frontend:**
- Next.js 14, TypeScript
- Tailwind CSS, Ant Design

## Quick Start

### Backend
```bash
cd backend
./mvnw clean install
./mvnw spring-boot:run
```

### Frontend
```bash
cd frontend
npm install
npm run dev
```

## API Routes

- `GET /api/products` - Search products
- `GET /api/auto-complete` - Get suggestions
- `GET /api/spell-checking` - Check spelling
- `GET /api/page-ranking` - Get ranked results
- `GET /api/web-crawler` - Crawler status
- `GET /api/data-validation` - Validate data

## Contributing

1. Fork the repo
2. Create feature branch (`git checkout -b feature/new-feature`)
3. Commit changes (`git commit -am 'Add new feature'`)
4. Push branch (`git push origin feature/new-feature`)
5. Create pull request

## License

[MIT](LICENSE)