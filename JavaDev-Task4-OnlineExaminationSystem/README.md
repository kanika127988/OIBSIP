# Online Examination System (Java Swing) - OIBSIP Task 4

A fully functional, desktop-based Online Examination System developed in Java using Java Swing as part of the Oasis Infobyte Java Development Internship (Task 4). This application provides a complete testing environment complete with secure authentication, clear guidelines, a live countdown timer, interactive MCQ navigation, session management, and color-coded result analytics.

---

## 🚀 Key Features

1. **Candidate Login Portal (`Main.java`)**: 
   - Clean, centered form layout featuring blue action buttons and black text.
   - Secure credential verification (`student` / `123`) before loading instructions.
2. **Comprehensive Exam Guidelines (`InstructionFrame.java`)**: 
   - Formatted using a professional Times New Roman typography and automatic word-wrapping.
   - Details rules regarding duration, question count, marking scheme, and auto-submission.
3. **Interactive Exam Interface (`ExamFrame.java`)**: 
   - Displays 10 Multiple Choice Questions (MCQs) one at a time using a responsive card container.
   - Smooth **Next** (Blue) and **Previous** (Violet) navigation with automatic state persistence.
   - Live 15-minute countdown timer positioned at the top-right corner.
   - Session protection dialog on unexpected window closure.
4. **Detailed Performance Breakdown Screen**: 
   - Displays your score out of 10 and exact time taken.
   - Color-coded HTML breakdown rendering correct answers in **Green** and incorrect answers in **Red**, alongside correct answers for any missed questions.

---

## 🛠️ Tech Stack & Requirements
* **Language**: Java
* **UI Framework**: Java Swing (AWT, JFrame, JPanel, JRadioButton, JTextPane, Timer)
* **Design Elements**: Times New Roman styling, customized color-coded buttons (Blue, Violet, Red), and responsive centering.

---

## 📂 Project File Structure & Sample Output

```text
JavaDev-Task4-OnlineExaminationSystem/
│
├── output/                               # Application Execution Screenshots
│   ├── login-screen.png                  # [Sample Output: Candidate Login Portal]
│   ├── instruction-page.png              # [Sample Output: Exam Guidelines & Rules]
│   ├── exam-interface.png                # [Sample Output: Live MCQ & Timer Screen]
│   └── result-breakdown.png              # [Sample Output: Score & Color-Coded Review]
│
├── Main.java                             # Login entry point & authentication
├── InstructionFrame.java                 # Exam rules and guidelines screen
├── ExamFrame.java                        # Core exam logic, timer, and performance evaluation
├── Question.java                         # Data model managing questions, options, and states
└── README.md                             # Project documentation

Sample Output

1. Candidate Login Screen (Main.java)

+-------------------------------------------------+
|          Candidate Login Portal                 |
+-------------------------------------------------+
|  Username: [ student                      ]     |
|  Password: [ **********                   ]     |
|                                                 |
|                     [ Login ]                   |
+-------------------------------------------------+

2. Instruction Guidelines Screen (InstructionFrame.java)

+-------------------------------------------------+
|      Comprehensive Exam Guidelines              |
+-------------------------------------------------+
|  1. Total Duration: Exactly 15 minutes.         |
|  2. Total Questions: 10 MCQs.                   |
|  3. Marking Scheme: +1 for correct, no negative.|
|  4. Navigation: Use Next and Previous freely.   |
|  5. Auto-submit occurs when timer hits zero.    |
|                                                 |
|                   [ Start Exam ]                |
+-------------------------------------------------+

3. Exam Interface Screen (ExamFrame.java)

+-------------------------------------------------+
| Online Examination Portal        Time Left: 14:45|
+-------------------------------------------------+
|                                                 |
|   1. Which component is used to compile Java?   |
|                                                 |
|     ( ) 1. java                                 |
|     (*) 2. javac                                |
|     ( ) 3. jvm                                  |
|     ( ) 4. jdb                                  |
|                                                 |
|   [Previous]   [Next]              [Submit Exam]|
+-------------------------------------------------+

4. Result & Performance Breakdown Screen

+-------------------------------------------------+
| You Have Successfully Completed The Exam!       |
+-------------------------------------------------+
| Your score: 9 out of 10                         |
| Time Taken: 03:12                               |
|                                                 |
| ┌─────────────────────────────────────────────┐ |
| │ 1. Which component is used to compile Java? │ |
| │ Your Answer: 2. javac — Correct             │ |
| │                                             │ |
| │ 10. Which access modifier is private?       │ |
| │ Your Answer: 3. default — Incorrect         │ |
| │ Correct Answer: 4. private                  │ |
| └─────────────────────────────────────────────┘ |
|                     [Logout]                    |
+-------------------------------------------------+
