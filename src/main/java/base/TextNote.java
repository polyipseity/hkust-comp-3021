package base;

public class TextNote extends Note {
  public String content;

  public TextNote(String title) {
    this(title, "");
  }

  public TextNote(String title, String content) {
    super(title);
    this.content = content;
  }
}
