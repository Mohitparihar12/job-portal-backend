# Job Portal Backend

Beginner-friendly Java + Spring Boot REST API for job listings and applications.

## Technologies
- Java 17
- Spring Boot 3.5.6
- Spring Web / REST
- Spring Data JPA and Hibernate
- H2 in-memory database
- Bean Validation
- Maven
- Postman for testing

## Import into Eclipse
1. Extract this ZIP.
2. Eclipse: File > Import > Maven > Existing Maven Projects.
3. Select the extracted `job-portal-backend` folder and finish.
4. Wait for Maven dependencies to download.
5. Run `JobPortalBackendApplication.java` as Java Application or Spring Boot App.
6. App URL: `http://localhost:8080`.

H2 console: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:jobportaldb`
- User: `sa`
- Password: blank

This database is in-memory, so data resets when the application restarts.

## Job endpoints
| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/jobs` | Create job |
| GET | `/api/jobs` | List all jobs |
| GET | `/api/jobs/open` | List open jobs |
| GET | `/api/jobs/1` | Get job by ID |
| PUT | `/api/jobs/1` | Update job |
| DELETE | `/api/jobs/1` | Delete job |
| GET | `/api/jobs/search?title=Java` | Search by title |
| GET | `/api/jobs/search?location=Hyderabad` | Search by location |
| GET | `/api/jobs/search?employmentType=FULL_TIME` | Search by employment type |
| GET | `/api/jobs/search?title=Java&location=Hyderabad` | Combine filters |

## Application endpoints
| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/applications?jobId=1` | Apply to job ID 1 |
| GET | `/api/applications` | List applications |
| GET | `/api/applications/1` | Get application by ID |
| GET | `/api/applications/by-applicant?email=mohit@example.com` | List by applicant email |
| GET | `/api/applications/by-job/1` | List applications for job 1 |
| PATCH | `/api/applications/1/status?status=SHORTLISTED` | Change application status |

Valid application statuses: `SUBMITTED`, `UNDER_REVIEW`, `SHORTLISTED`, `REJECTED`, `HIRED`.

## Create a job in Postman
POST `http://localhost:8080/api/jobs`
Select Body > raw > JSON:
```json
{
  "title": "Java Backend Developer",
  "companyName": "ABC Technologies",
  "location": "Hyderabad",
  "description": "Looking for a fresher with Core Java, SQL and Spring Boot basics.",
  "employmentType": "FULL_TIME",
  "salary": 350000
}
```
Expected response: `201 Created`, including the generated job ID.

## Apply for a job
Use the actual ID returned from creating the job. Example if it is 1:
POST `http://localhost:8080/api/applications?jobId=1`
```json
{
  "applicantName": "Mohit",
  "applicantEmail": "mohit@example.com",
  "resumeUrl": "https://example.com/resume.pdf"
}
```
A given email can apply only once to the same job in this starter version. `resumeUrl` is text only; no file is uploaded.

## Architecture
Controller -> Service -> Repository -> H2 database

- Entity: maps a Java class to a database table (`Job`, `JobApplication`).
- Controller: receives HTTP requests and returns JSON.
- Service: contains business rules.
- Repository: performs database operations through Spring Data JPA.
- GlobalExceptionHandler: handles common errors.

## Limitations
This is a learning project, not production-ready. It does not include login, Spring Security/JWT, verified employer accounts, resume file uploads, email notifications, pagination, or a production database. APIs are unauthenticated; do not deploy publicly or use real personal data.
