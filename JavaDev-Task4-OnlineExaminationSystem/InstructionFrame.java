import javax.swing.*;
import java.awt.*;

public class InstructionFrame extends JFrame {
    public InstructionFrame() {
        setTitle("Online Examination System - Instructions");
        setSize(780, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header Banner
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(26, 35, 126));
        headerPanel.setPreferredSize(new Dimension(780, 55));
        JLabel titleLabel = new JLabel("Comprehensive Exam Instructions & Guidelines");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Center Panel using GridBagLayout for dynamic centering on maximize
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(new Color(245, 247, 250));

        JPanel cardPanel = new JPanel(new BorderLayout());
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(30, 35, 30, 35)
        ));
        cardPanel.setPreferredSize(new Dimension(680, 430));

        // Instructions text area with Word Wrapping enabled (No horizontal scrolling)
        JTextArea instructionsArea = new JTextArea(
            "1. Total Duration: Exactly 15 minutes (900 seconds).\n\n" +
            "2. Total Questions: 10 Multiple Choice Questions (MCQs).\n\n" +
            "3. Marking Scheme: Each correct answer carries 1 mark. There is NO negative marking.\n\n" +
            "4. Navigation: Use the 'Next' and 'Previous' buttons to move between questions freely.\n\n" +
            "5. Answer Modification: You can change your selected option at any time before final submission.\n\n" +
            "6. Auto-Submission: The examination will automatically submit when the countdown timer reaches zero.\n\n" +
            "7. Session Safety: Do not attempt to close or refresh the window during the exam session.\n\n" +
            "8. Final Submission: Click the 'Submit Exam' button once you have completed all questions."
        );
        instructionsArea.setLineWrap(true);       // Forces text to wrap inside the width
        instructionsArea.setWrapStyleWord(true);   // Prevents breaking words in the middle
        instructionsArea.setEditable(false);
        instructionsArea.setBackground(Color.WHITE);
        instructionsArea.setFont(new Font("Times New Roman", Font.BOLD, 16)); 
        instructionsArea.setForeground(new Color(30, 30, 30));
        
        JScrollPane scrollPane = new JScrollPane(instructionsArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        cardPanel.add(scrollPane, BorderLayout.CENTER);

        centerWrapper.add(cardPanel);
        add(centerWrapper, BorderLayout.CENTER);

        // Footer Button Panel
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(245, 247, 250));
        footerPanel.setPreferredSize(new Dimension(780, 65));
        
        JButton startButton = new JButton("Start Exam");
        startButton.setBackground(new Color(40, 140, 40)); // Green
        startButton.setForeground(Color.BLACK); // Black text
        startButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
        startButton.setFocusPainted(false);
        startButton.setPreferredSize(new Dimension(140, 38));
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        footerPanel.add(startButton);
        add(footerPanel, BorderLayout.SOUTH);

        startButton.addActionListener(e -> {
            dispose();
            new ExamFrame().setVisible(true);
        });
    }
}