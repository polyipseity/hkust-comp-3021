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

  @Override
  public String toString() {
    String ret = getClass().getSimpleName() + ':' + super.toString();
    int previewLength = content.indexOf('.');
    if (previewLength != -1) {
      previewLength = Math.min(Math.min(30, content.length()), previewLength + 1);
      ret += '\t' + content.substring(0, previewLength);
    }
    return ret;
  }
}
