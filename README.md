# Student Management System

A menu-driven Java console application for managing students, instructors, courses, enrolments, attendance, and academic results.

Developed as an internship project to practise object-oriented programming, Java collections, input validation, and business-rule implementation.

## Features

### Student Management

- Add, update, remove, and list students.
- Find students by ID.
- Search by first name, last name, or full name.
- Display students alphabetically by first and last name.
- Validate email addresses and Pakistani mobile numbers through the console.
- Prevent duplicate email addresses when adding students.
- Remove associated enrolments when deleting a student, following confirmation.

### Instructor Management

- Add, update, remove, and list instructors.
- Find instructors by ID or search by name.
- Display instructors alphabetically.
- Validate contact details through the console.
- Prevent duplicate email addresses when adding instructors.

### Course Management

- Create courses with a name, unique course code, credits, and capacity.
- Find courses by ID or course code.
- Update course names, assigned instructors, and credits.
- Assign instructors when creating a course or afterward.
- Display remaining seats.
- Prevent course deletion while students are enrolled.

### Enrolment Management

- Enrol students in courses and remove enrolments.
- Prevent duplicate student-course enrolments.
- Enforce course capacity during enrolment.
- Find an individual enrolment.
- Display all enrolments.
- View course rosters and a student's enrolled-course list.

### Attendance and Academic Records

- Record attendance separately for each student-course enrolment.
- Calculate attendance percentages.
- Display eligibility, probation, and ineligibility statuses.
- Add assessment results with obtained and maximum marks.
- Prevent total assessment maximum marks from exceeding 100 per enrolment.
- Calculate letter grades and credit-weighted GPA.
- Display transcripts with assessments, grades, attendance, and eligibility.

### Dashboard

Display totals for:

- Students
- Instructors
- Courses
- Active enrolments

## Technology Stack

| Technology | Purpose |
| --- | --- |
| Java 25 | Application language and configured compiler target |
| Maven | Project build |
| Java Collections | In-memory record storage |
| Java Regex | Email and phone validation |
| Java Time API | Enrolment dates |
| Scanner | Interactive console input |

The project declares no third-party dependencies.

## Project Organization

Source files are located under `src/main/java/com/centegy/sms/`.

| Component | Responsibility |
| --- | --- |
| `Main.java` | Administrator password check and main application loop |
| `MenuHandler.java` | Console menus, input handling, dashboard, and demo data |
| `model/Student.java` | Student information |
| `model/Instructor.java` | Instructor information |
| `model/Course.java` | Course details, instructor reference, and capacity |
| `model/Enrolment.java` | Student-course relationship, attendance, and assessment results |
| `model/Exam.java` | Individual assessment results |
| `model/Grade.java` | Grade values and GPA points |
| `service/StudentService.java` | Student operations |
| `service/InstructorService.java` | Instructor operations |
| `service/CourseService.java` | Course operations |
| `service/EnrolmentService.java` | Enrolment, attendance, grading, and transcript operations |
| `utils/ValidationUtils.java` | Contact-format validation |

`CourseInstructor.java` is retained in the model package but is not used by the current implementation. Courses reference instructors directly.

## Getting Started

### Prerequisites

- JDK 25
- Maven
- A terminal or Java IDE such as IntelliJ IDEA

The current `pom.xml` sets both the compiler source and target versions to `25`.

### 1. Clone the Repository

```bash
git clone https://github.com/FarazAli-1/student-management-system.git
cd student-management-system
```

### 2. Verify Your Java Installation

```bash
java -version
javac -version
mvn -version
```

Ensure Maven is using JDK 25.

### 3. Compile

Run from the repository root:

```bash
mvn clean compile
```

### 4. Start the Application

```bash
java -cp target/classes com.centegy.sms.Main
```

### Running in IntelliJ IDEA

1. Open the repository as a Maven project.
2. Select JDK 25 as the project SDK.
3. Open `src/main/java/com/centegy/sms/Main.java`.
4. Run the `main` method.

## Administrator Access

The application prompts:

```text
Enter Admin Password:
```

The current demonstration password is:

```text
admin123
```

This is a hardcoded console access check. The application does not implement separate user accounts or role-based authentication.

## Main Menu

```text
1 - Student Management Menu
2 - Course Management Menu
3 - Instructor Management Menu
4 - Enrolment Management Menu
5 - Examination & Academic Records
6 - Display System Dashboard
0 - Exit
```

Choose a number and follow the prompts. Submenus use `0` to return to the main menu.

## Included Demo Data

After successful administrator access, the application initializes:

| Record Type | Initial Count |
| --- | ---: |
| Students | 12 |
| Instructors | 6 |
| Courses | 8 |
| Enrolments | 15 |

The sample courses include Object Oriented Programming, Data Structures, Database Systems, Operating Systems, and Artificial Intelligence.

All records are stored in memory. Changes are lost when the application exits, and the demo data is recreated on the next launch.

## Example Workflow

The included data already enrols student `1` in course `1`.

To explore attendance, assessment results, and transcripts:

1. Start the application and enter the administrator password.
2. Select `5` for **Examination & Academic Records**.
3. Select `2` to mark attendance.
4. Enter student ID `1`, course ID `1`, and `1` for present.
5. Select `1` to add an assessment:
   - Name: `Midterm`
   - Obtained marks: `35`
   - Maximum marks: `40`
6. Add another assessment:
   - Name: `Final`
   - Obtained marks: `50`
   - Maximum marks: `60`
7. Select `3` to view the transcript for student `1`.

These assessments total `85/100`, corresponding to grade `A` and `4.0` grade points.

Other courses without a complete 100-mark assessment allocation are excluded from the GPA calculation.

## Attendance Rules

Attendance is tracked per enrolment.

| Recorded Attendance | Displayed Status |
| --- | --- |
| No sessions recorded | NO DATA |
| 70% or higher | ELIGIBLE |
| 60% to below 70% | PROBATION |
| Below 60% | INELIGIBLE |

The console blocks assessment entry named `Final` or `Finals` when:

- No attendance sessions have been recorded.
- Attendance is below 60%.

The current final-entry check permits students in the probation range.

## Grading and GPA

Assessment marks are added together, and the total is rounded to an integer before assigning a grade.

| Total Marks | Grade | Grade Points |
| --- | --- | ---: |
| 90–100 | A_PLUS | 4.0 |
| 85–89 | A | 4.0 |
| 80–84 | A_MINUS | 3.8 |
| 75–79 | B_PLUS | 3.4 |
| 71–74 | B | 3.0 |
| 68–70 | B_MINUS | 2.8 |
| 64–67 | C_PLUS | 2.4 |
| 61–63 | C | 2.0 |
| 57–60 | C_MINUS | 1.8 |
| 53–56 | D_PLUS | 1.4 |
| 45–52 | D | 1.0 |
| Below 45 | FAILS | 0.0 |

GPA is calculated as:

```text
Sum of (course grade points × course credits)
────────────────────────────────────────────
             Total included credits
```

Credit values such as `3+1` are interpreted as four credits.

Only enrolments with assessment maximum marks totalling 100 and a grade other than `N_A` contribute to GPA.

## Validation and Business Rules

- Contact validation is applied through the interactive student and instructor menus.
- Pakistani mobile formats include `03001234567`, `923001234567`, and `+923001234567`.
- Duplicate email checks apply when creating students and instructors.
- Course codes are checked for duplicates during creation.
- A student cannot enrol in the same course twice.
- Enrolment is refused when no seats remain.
- Courses with enrolments cannot be deleted.
- Student deletion removes the student's associated enrolments.
- The console rejects obtained marks below zero or above the assessment maximum.

## Testing

The repository currently has no automated test suite or test dependencies.

The example workflow provides a manual way to check the application. Additional manual checks include:

- Adding a duplicate email or course code.
- Attempting a duplicate enrolment.
- Filling a small-capacity course.
- Deleting a course that still has enrolled students.
- Entering final marks before attendance has been recorded.
- Adding assessments whose maximum marks would exceed 100.

## Current Limitations

- Records are stored in memory; file and database persistence are not implemented.
- Some invalid numeric inputs or missing student IDs can terminate the application.
- Email uniqueness is not rechecked during updates.
- Removing an instructor does not clear references from existing courses.
- Assessment entry appends results rather than updating an existing assessment by name.
- Transcript output can display an attendance-based failing status while GPA still uses the stored mark-based grade.
- The administrator password is hardcoded for demonstration.

## Possible Improvements

- Add file or database persistence.
- Introduce automated tests for enrolment, grading, and attendance.
- Improve input and exception handling.
- Make assessment results editable.
- Apply validation consistently across menus and services.
- Align attendance-based transcript outcomes with GPA calculation.

## Project Documentation

Additional project material is available in [SMS_Faraz_Ali.pdf](SMS_Faraz_Ali.pdf).

## Author

**Faraz Ali**

- [GitHub](https://github.com/FarazAli-1)
- [LinkedIn](https://www.linkedin.com/in/farazali-swe)
