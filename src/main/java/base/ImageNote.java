package base;

import java.io.File;
import java.io.Serial;

public class ImageNote extends Note {
  @Serial
  private static final long serialVersionUID = 7994861827768973392L;

  public File image;

  Icon icon;

  public ImageNote(String title) {
    super(title);
    char firstCharacter = title.charAt(0);
    if ('a' <= firstCharacter && firstCharacter <= 'z') {
      this.icon = new IconLowerCase(firstCharacter);
    } else if ('A' <= firstCharacter && firstCharacter <= 'Z') {
      this.icon = new IconUpperCase(firstCharacter);
    } else if ('0' <= firstCharacter && firstCharacter <= '9') {
      this.icon = new IconDigit(firstCharacter);
    }
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + ": " + icon + ' ' + super.toString();
  }

  public ImageNote(ImageNote note) {
    super(note);
    this.image = note.image;
    this.icon = note.icon;
  }
}
