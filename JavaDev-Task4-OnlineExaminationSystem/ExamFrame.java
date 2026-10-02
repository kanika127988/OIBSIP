import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ExamFrame extends JFrame {
    private ArrayList<Question> questionList;
    private int currentIndex = 0;
    
    private JLabel timerLabel, questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionsGroup;
    private JButton prevButton, nextButton, submitButton;
    private Timer countdownTimer;
    private int timeLeft = 900; // 15 minutes
    private long startTime;

    public ExamFrame() {
        startTime = System.currentTimeMillis();
        initializeQuestions();

        setTitle("Online Examination System - Exam in Progress");
        setSize(850, 600);
        setMinimumSize(new Dimension(700, 500));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Window close protection
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmQuit();
            }
        });

        // Top Header Banner with Timer
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(26, 35, 126));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        
        JLabel portalTitle = new JLabel("Online Examination Portal");
        portalTitle.setForeground(Color.WHITE);
        portalTitle.setFont(new Font("Times New Roman", Font.BOLD, 17));
        headerPanel.add(portalTitle, BorderLayout.WEST);

        timerLabel = new JLabel();
        timerLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        timerLabel.setForeground(new Color(255, 235, 59));
        headerPanel.add(timerLabel, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);
        startTimer();

        // Center Wrapper Panel for Dynamic Centering on Maximize
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(new Color(245, 247, 250));

        // Question Card Panel
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(35, 45, 35, 45)
        ));

        // Question Label (Times New Roman, Font size 20)
        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        questionLabel.setForeground(new Color(33, 33, 33));
        questionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardPanel.add(questionLabel);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Radio Buttons for Options (Times New Roman)
        optionButtons = new JRadioButton[4];
        optionsGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setFont(new Font("Times New Roman", Font.PLAIN, 16));
            optionButtons[i].setBackground(Color.WHITE);
            optionButtons[i].setAlignmentX(Component.LEFT_ALIGNMENT);
            optionsGroup.add(optionButtons[i]);
            cardPanel.add(optionButtons[i]);
            cardPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        }

        centerWrapper.add(cardPanel);
        add(centerWrapper, BorderLayout.CENTER);

        // Bottom Navigation Footer with Requested Colors and Black Text
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(new Color(230, 235, 245));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JPanel navButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        navButtonsPanel.setOpaque(false);
        
        // Previous Button -> Violet with Black Text
        prevButton = new JButton("Previous");
        styleNavButton(prevButton, new Color(138, 43, 226)); 
        
        // Next Button -> Blue with Black Text
        nextButton = new JButton("Next");
        styleNavButton(nextButton, new Color(30, 144, 255)); 
        
        navButtonsPanel.add(prevButton);
        navButtonsPanel.add(nextButton);
        footerPanel.add(navButtonsPanel, BorderLayout.WEST);

        // Submit Exam Button -> Red with Black Text
        submitButton = new JButton("Submit Exam");
        styleNavButton(submitButton, new Color(211, 47, 47)); 
        
        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        submitPanel.setOpaque(false);
        submitPanel.add(submitButton);
        footerPanel.add(submitPanel, BorderLayout.EAST);

        add(footerPanel, BorderLayout.SOUTH);

        // Listeners
        prevButton.addActionListener(e -> {
            saveCurrentAnswer();
            if (currentIndex > 0) {
                currentIndex--;
                loadQuestion();
            }
        });

        nextButton.addActionListener(e -> {
            saveCurrentAnswer();
            if (currentIndex < questionList.size() - 1) {
                currentIndex++;
                loadQuestion();
            }
        });

        submitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to submit the exam?", "Submit Confirmation", 
                JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                finishExam();
            }
        });

        loadQuestion();
    }

    private void styleNavButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.BLACK); // Black text color as requested
        button.setFont(new Font("Times New Roman", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(130, 38));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void initializeQuestions() {
        questionList = new ArrayList<>();
        // Note: Removed redundant "Q1:", "Q2:" prefixes from text strings so they don't duplicate
        questionList.add(new Question("Which component is used to compile Java code?", 
            new String[]{"1. java", "2. javac", "3. jvm", "4. jdb"}, 1));
        
        questionList.add(new Question("Which of the following is not a Java feature?", 
            new String[]{"1. Object-oriented", "2. Use of pointers", "3. Portable", "4. Dynamic"}, 1));
        
        questionList.add(new Question("What is the extension of compiled Java byte code files?", 
            new String[]{"1. .txt", "2. .js", "3. .class", "4. .java"}, 2));

        questionList.add(new Question("Which package contains the Random class in Java?", 
            new String[]{"1. java.util", "2. java.io", "3. java.net", "4. java.awt"}, 0));

        questionList.add(new Question("Which data type is used to store true or false values in Java?", 
            new String[]{"1. int", "2. float", "3. boolean", "4. char"}, 2));

        questionList.add(new Question("Which method must be defined in every standalone Java application class?", 
            new String[]{"1. start()", "2. main()", "3. run()", "4. init()"}, 1));

        questionList.add(new Question("Which keyword is used to inherit a class in Java?", 
            new String[]{"1. implements", "2. extends", "3. inherits", "4. super"}, 1));

        questionList.add(new Question("What is the default value of an uninitialized int variable in a class?", 
            new String[]{"1. null", "2. 1", "3. 0", "4. -1"}, 2));

        questionList.add(new Question("Which exception is thrown when dividing by zero in integer arithmetic?", 
            new String[]{"1. NullPointerException", "2. ArithmeticException", "3. IOException", "4. ClassNotFoundException"}, 1));

        questionList.add(new Question("Which access modifier makes a member accessible only within its own class?", 
            new String[]{"1. public", "2. protected", "3. default", "4. private"}, 3));
    }

    private void loadQuestion() {
        Question q = questionList.get(currentIndex);
        questionLabel.setText((currentIndex + 1) + ". " + q.getQuestionText());
        
        optionsGroup.clearSelection();

        String[] options = q.getOptions();
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options[i]);
            optionButtons[i].setSelected(false);
        }

        int savedAnswer = q.getSelectedAnswerIndex();
        if (savedAnswer != -1) {
            optionButtons[savedAnswer].setSelected(true);
        }

        prevButton.setEnabled(currentIndex > 0);
        nextButton.setEnabled(currentIndex < questionList.size() - 1);
    }

    private void saveCurrentAnswer() {
        for (int i = 0; i < 4; i++) {
            if (optionButtons[i].isSelected()) {
                questionList.get(currentIndex).setSelectedAnswerIndex(i);
                break;
            }
        }
    }

    private void startTimer() {
        countdownTimer = new Timer(1000, e -> {
            if (timeLeft > 0) {
                int minutes = timeLeft / 60;
                int seconds = timeLeft % 60;
                timerLabel.setText(String.format("Time Left: %02d:%02d", minutes, seconds));
                timeLeft--;
            } else {
                countdownTimer.stop();
                JOptionPane.showMessageDialog(this, "Time's up! Auto-submitting your exam.");
                finishExam();
            }
        });
        countdownTimer.start();
    }

    private void confirmQuit() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to quit? Your progress will be lost.", "Quit Exam", 
            JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            countdownTimer.stop();
            dispose();
            System.exit(0);
        }
    }

    private void finishExam() {
        countdownTimer.stop();
        saveCurrentAnswer();

        int score = 0;
        StringBuilder htmlBreakdown = new StringBuilder();
        htmlBreakdown.append("<html><body style='font-family: Times New Roman; font-size: 14px; padding: 10px;'>");

        for (int i = 0; i < questionList.size(); i++) {
            Question q = questionList.get(i);
            boolean isCorrect = (q.getSelectedAnswerIndex() == q.getCorrectAnswerIndex());
            if (isCorrect) score++;

            htmlBreakdown.append("<b>").append((i + 1)).append(". ").append(q.getQuestionText()).append("</b><br>");
            htmlBreakdown.append("Your Answer: ").append(q.getSelectedAnswerText()).append(" — ");

            if (isCorrect) {
                htmlBreakdown.append("<span style='color:green; font-weight:bold;'>Correct</span><br>");
            } else {
                htmlBreakdown.append("<span style='color:red; font-weight:bold;'>Incorrect</span><br>");
                htmlBreakdown.append("<span style='color:#333333;'>Correct Answer: <b>").append(q.getCorrectAnswerText()).append("</b></span><br>");
            }
            htmlBreakdown.append("<br>");
        }

        htmlBreakdown.append("</body></html>");

        long timeTakenSeconds = (System.currentTimeMillis() - startTime) / 1000;
        String timeFormatted = String.format("%02d:%02d", timeTakenSeconds / 60, timeTakenSeconds % 60);

        dispose();
        showResultScreen(score, questionList.size(), timeFormatted, htmlBreakdown.toString());
    }

    private void showResultScreen(int score, int total, String timeTaken, String htmlBreakdownText) {
        JFrame resultFrame = new JFrame("Online Examination System - Results");
        resultFrame.setSize(750, 600);
        resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resultFrame.setLocationRelativeTo(null);
        resultFrame.setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(26, 35, 126));
        headerPanel.setPreferredSize(new Dimension(750, 55));
        JLabel titleLabel = new JLabel("You Have Successfully Completed The Exam!");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
        headerPanel.add(titleLabel);
        resultFrame.add(headerPanel, BorderLayout.NORTH);

        // Center Details & Breakdown
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 15, 25));
        centerPanel.setBackground(new Color(245, 247, 250));

        JPanel statsPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        statsPanel.setOpaque(false);
        
        JLabel scoreLabel = new JLabel("Your score: " + score + " out of " + total);
        scoreLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        statsPanel.add(scoreLabel);

        JLabel timeLabel = new JLabel("Time Taken: " + timeTaken);
        timeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        statsPanel.add(timeLabel);

        centerPanel.add(statsPanel, BorderLayout.NORTH);

        JTextPane breakdownPane = new JTextPane();
        breakdownPane.setContentType("text/html");
        breakdownPane.setText(htmlBreakdownText);
        breakdownPane.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(breakdownPane);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Detailed Answer Breakdown"));
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        resultFrame.add(centerPanel, BorderLayout.CENTER);

        // Footer Logout Button (Blue with Black text)
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(245, 247, 250));
        footerPanel.setPreferredSize(new Dimension(750, 70));
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(30, 144, 255));
        logoutButton.setForeground(Color.BLACK);
        logoutButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
        logoutButton.setFocusPainted(false);
        logoutButton.setPreferredSize(new Dimension(130, 38));
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        footerPanel.add(logoutButton);
        resultFrame.add(footerPanel, BorderLayout.SOUTH);

        logoutButton.addActionListener(e -> {
            resultFrame.dispose();
            Main.main(new String[0]);
        });

        resultFrame.setVisible(true);
    }
}