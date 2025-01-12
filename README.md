# Online Product Search and Analysis System 🛍️

A powerful web application that provides advanced product search capabilities, including spell checking, auto-completion, page ranking, and real-time data crawling from multiple online stores. This project leverages a robust **Spring Boot backend** and a modern **Next.js frontend**.

## Features ✨

This application offers a comprehensive suite of features for both users searching for products and administrators managing the system:

### **For Users:**

-   **🔎 Intelligent Product Search:**
    -   Search products across multiple online stores with a single query.
    -   Benefit from real-time product data fetched via web crawling.
-   **🚀 Advanced Search Capabilities:**
    -   **Auto-complete:** Get instant search suggestions powered by an efficient Red-Black Tree implementation.
    -   **Spell Checking:** Automatically correct typos using an edit distance algorithm.
    -   **Page Ranking:** See the most relevant results first, thanks to a keyword frequency-based ranking system.
    -   **Inverted Indexing**: Optimized for fast search results
### **For Administrators:**

-   **📊 Data Analysis & Management:**
    -   **Keyword Frequency Tracking:** Monitor trending search terms.
    -   **Search Pattern Analysis:** Gain insights into user search behavior.
    -   **Automated Data Validation:** Ensure product listings and CSV datasets are accurate and up-to-date.
    -   **Web Crawler Management:** Control and monitor web crawling operations.

## Tech Stack 💻

+-----------------+       +---------------------+       +-----------------------+
|   Next.js      | ----> | Spring Boot         | ----> | Product Search        |
|   Frontend    |       | API Gateway         |       | Service               |
+-----------------+       +---------------------+       +-----------------------+
                                    ^  |                      |  ^
                                    |  |                      |  |
                                    |  v                      v  |
                          +---------------------+       +-----------------------+
                          | Auto-Complete       |       | Inverted Index        |
                          | Service             |       +-----------------------+
                          +---------------------+       +-----------------------+
                                    ^
                                    |                      +-----------------------+
                                    |                      | Spell-Checking        |
                                    v                      | Service               |
                          +---------------------+       +-----------------------+
                          | Data Analysis       | <---- | Page Ranking          |
                          | Service             |       | (Component)           |
                          +---------------------+       +-----------------------+
                                    ^
                                    |
                                    |
                                    v
+-----------------+       +---------------------+       +-----------------------+
| External        | <---- | Web Crawler         | ----> | MySQL Product         |
| Data Sources  |       | Service             |       | Database              |
+-----------------+       +---------------------+       +-----------------------+

This project utilizes a modern and efficient technology stack:

### **Backend:**

-   **Java 21:** The latest LTS version of Java for a robust and performant backend.
-   **Spring Boot 3.3.1:** Simplifies development and provides a solid foundation for enterprise features.
-   **JPA/Hibernate:** For seamless database interaction and object-relational mapping.
-   **MySQL:** A reliable and widely used relational database management system.
-   **Selenium WebDriver:** Enables automated web crawling for real-time product data.

### **Frontend:**

-   **Next.js:** A React framework for building fast, server-rendered, and SEO-friendly web applications.
-   **TypeScript:** Adds static typing to JavaScript, improving code quality and maintainability.
-   **Tailwind CSS:** A utility-first CSS framework for rapid UI development.
-   **ESLint:** Enforces code style and helps catch potential errors.

## Getting Started 🚀

Follow these steps to get the project up and running on your local machine:

### **Prerequisites:**

-   **Java 21 (JDK):** Make sure you have the Java 21 Development Kit installed.
-   **Node.js (with npm):** Required for managing frontend dependencies and running the development server.
-   **MySQL:** Have a MySQL server installed and running.
-   **Maven:** Used for building and managing the backend project.

### **Backend Setup:**

1. **Clone the repository:**
    ```bash
    git clone <repository_url>
    cd <repository_name>/backend
    ```
2. **Navigate to the backend directory:**
    ```bash
    cd backend
    ```
3. **Database Configuration:**
    -   Create a MySQL database for the project.
    -   Update the `application.properties` or `application.yml` file (located in `src/main/resources`) with your database credentials:
    ```
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
    spring.datasource.username=your_database_user
    spring.datasource.password=your_database_password
    ```
4. **Build the project:**
    ```bash
    ./mvnw clean install
    ```
5. **Run the application:**
    ```bash
    ./mvnw spring-boot:run
    ```

### **Frontend Setup:**

1. **Navigate to the frontend directory:**
    ```bash
    cd ../frontend # Assuming you are still in the backend directory
    ```
2. **Install dependencies:**
    ```bash
    npm install
    ```
3. **Configure API Base URL (if necessary):**
    -   If your backend is running on a port other than the default or a different host, update the API base URL in your frontend environment files (e.g., `.env.local`, `.env.development`). You might have a variable like `NEXT_PUBLIC_API_BASE_URL`.
4. **Start the development server:**
    ```bash
    npm run dev
    ```


## API Endpoints 🗂️

The backend exposes the following REST API endpoints:

-   `/api/products`:  Handles product search and management operations.
-   `/api/auto-complete`: Provides word completion suggestions.
-   `/api/spell-checking`: Offers spell checking services.
-   `/api/page-ranking`: Calculates page ranking scores.
-   `/api/web-crawler`: Manages web crawling operations (likely for admin use).
-   `/api/keyword-search`: Provides keyword search analytics (likely for admin use).
-   `/api/data-validation`: Offers data validation services (likely for admin use).

**Note:** You might want to add more specific details about request/response formats for each endpoint in a separate API documentation file.

## Contributing

If you'd like to contribute to this project, please feel free to submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).