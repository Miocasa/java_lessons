package Publisher;

public class LessonBook extends PublisherBase implements LessonBookFeatures {
    private int exercises = 0;
    private int grade = 0;

    public LessonBook(String title) {
        super(title);
    }

    @Override
    public void addExercises(int count) {
        if (count <= 0)
            throw new IllegalArgumentException("Exercises count must be positive: " + count);
        exercises += count;
    }

    @Override
    public void setGrade(int bookGrade) {
        if (bookGrade < 1 || bookGrade > 11)
            throw new IllegalArgumentException("Grade must be 1..11: " + bookGrade);
        grade = bookGrade;
    }

    @Override
    public void makeUp() {
        if (exercises <= 0)
            throw new IllegalStateException("Add exercises before layout");
        super.makeUp();
    }
}