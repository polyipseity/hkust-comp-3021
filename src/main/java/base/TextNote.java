package base;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class TextNote extends Note {
  @Serial
  private static final long serialVersionUID = 843109159249680533L;

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
