public class Question {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex; // 0 to 3
    private int selectedAnswerIndex = -1; // -1 means unattempted

    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public int getSelectedAnswerIndex() {
        return selectedAnswerIndex;
    }

    public void setSelectedAnswerIndex(int selectedAnswerIndex) {
        this.selectedAnswerIndex = selectedAnswerIndex;
    }

    public String getCorrectAnswerText() {
        return options[correctAnswerIndex];
    }

    public String getSelectedAnswerText() {
        if (selectedAnswerIndex == -1) {
            return "Not Answered";
        }
        return options[selectedAnswerIndex];
    }
}