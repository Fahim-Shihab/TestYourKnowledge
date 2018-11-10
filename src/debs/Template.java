import java.io.IOException;

public abstract class Template {
    abstract void addQuestion() throws IOException;
    abstract void match(int pos);
    abstract void setQuestion(int pos);
    abstract void setScore(int pos);
    abstract void progressUpdate(int pos) throws IOException;

    int Judge(int pos) throws IOException {
        match(pos);
        pos++;

        progressUpdate(pos);
        setScore(pos);
        setQuestion(pos);
        return pos;
    }
}
