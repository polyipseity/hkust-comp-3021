package base;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class TextNote extends Note implements Iconifiable {
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

  public TextNote(File f) {
    super(f.getName());
    this.content = getTextFromFile(f.getAbsolutePath());
  }

  /**
   * get the content of a file
   *
   * @param absolutePath of the file
   * @return the content of the file
   */
  private String getTextFromFile(String absolutePath) {
    try (Reader reader = new InputStreamReader(new FileInputStream(absolutePath));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Writer writer = new OutputStreamWriter(output)) {
      reader.transferTo(writer);
      return output.toString(StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * export text note to file
   *
   *
   * @param pathFolder path of the folder where to export the note
   *                   the file has to be named as the title of the note with
   *                   extension ".txt"
   *                   <p>
   *                   if the tile contains white spaces " " they have to be
   *                   replaced with underscores "_"
   *
   *
   */
  public void exportTextToFile(String pathFolder) {
    File file = new File(pathFolder + File.separator + getTitle().replace(" ", "_") + ".txt");
    try (Writer writer = new OutputStreamWriter(new FileOutputStream(file))) {
      writer.write(content);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void iconify() {
    char firstCharacter = content.charAt(0);
    if ('a' <= firstCharacter && firstCharacter <= 'z') {
      content = new IconLowerCase(firstCharacter).base + content.substring(1);
    } else if ('A' <= firstCharacter && firstCharacter <= 'Z') {
      content = new IconUpperCase(firstCharacter).base + content.substring(1);
    } else if ('0' <= firstCharacter && firstCharacter <= '9') {
      content = new IconDigit(firstCharacter).base + content.substring(1);
    }
  }

  public TextNote(TextNote note) {
    super(note);
    this.content = note.content;
  }

  public String getContent() {
    return content;
  }

  public Character unknownFunction() {
    HashMap<Character, Integer> count = new HashMap<>();
    String a = this.getTitle() + this.getContent();
    int b = 0;
    char r = ' ';
    for (int i = 0; i < a.length(); i++) {
      char c = a.charAt(i);
      if (c <= 'Z' && c >= 'A' || c <= 'z' && c >= 'a') {
        if (!count.containsKey(c)) {
          count.put(c, 1);
        } else {
          count.put(c, count.get(c) + 1);
          if (count.get(c) > b) {
            b = count.get(c);
            r = c;
          }
        }
      }
    }
    return r;
  }
}
